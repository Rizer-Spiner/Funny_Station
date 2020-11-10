package com.example.session7.firebase;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.session7.MainActivity3;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;

public class FireBase {

    private FirebaseAuth auth;
    private FirebaseFirestore firestore;
    private static FireBase instance;

    private static final boolean RESULT_NEGATIVE = false;
    private static final boolean RESULT_AFFIRMATIVE = true;

    private String name;
    private String eMail;

    private FireBase()
    {
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    public synchronized static FireBase getInstance()
    {
        if (instance == null)
        {
            instance = new FireBase();
        }
        return instance;
    }


    public boolean checkIfSignedIn() {

        FirebaseUser user = auth.getCurrentUser();

        if (user != null)
        {
           return RESULT_AFFIRMATIVE;
        }

        return RESULT_NEGATIVE;
    }

    public void getUserEmail(Activity activityCompat)
    {
        FirebaseUser user = auth.getCurrentUser();
        String userId = user.getUid();
        DocumentReference documentReference = firestore.collection("user").document(userId);
        documentReference.addSnapshotListener(activityCompat, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot != null)
                {
                   String name = documentSnapshot.get("username").toString();
                   String eMail = documentSnapshot.get("email").toString();

                   setNameAndEmail(name, eMail);

                }

            }
        });

    }
    private void setNameAndEmail(String name, String eMail) {
        this.name = name;
        this.eMail = eMail;
    }

    public String[] retrieveEmailAndName()
    {
        return new String[]{name, eMail};
    }

    public boolean createAccount(String username, String email, String password)
    {
        final boolean[] result = {false};
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    String userId = auth.getCurrentUser().getUid();
                    DocumentReference documentReference = firestore.collection("user").document(userId);

                    HashMap<String, Object> userInfo = new HashMap<>();
                    userInfo.put("username", username);
                    userInfo.put("email", email);

                    documentReference.set(userInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("FIRESTORE", "onSuccess: usr Profile is created for " + userId);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("FIRESTORE", "onFailure: " + e.getMessage());
                        }
                    });

                    result[0] = true;
                }
            }
        });

        return result[0];
    }



    public void sigOut()
    {
        auth.signOut();
    }


    public boolean signIn(String email, String password)
    {
        final boolean[] result = {false};
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    result[0] = true;
                }
                else
                {
                    Log.d("FIREBASE", "Error occurred! " + task.getException().getMessage());

                }
            }
        });

        return result[0];

    }


}
