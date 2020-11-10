package com.example.session7;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.session7.firebase.FireBase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class CreateAccountActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText username;
    EditText email;
    EditText password;
    EditText reEnterPassword;
    ProgressBar progressBar2;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if (mAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity3.class));
            finish();
        }

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        reEnterPassword = findViewById(R.id.reEnterPassword);
        progressBar2 = findViewById(R.id.progressBar2);


    }

    public void createAccount(View view) {

        String username = this.username.getText().toString();
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();
        String reEnterPassword = this.reEnterPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            this.username.setError("Username is required");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            this.email.setError("Email is required");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            this.password.setError("Password is required");
            return;
        }

        if (TextUtils.isEmpty(reEnterPassword)) {
            this.reEnterPassword.setError("Password Verification is required ");
            return;
        }

        if (password.length() < 6 || reEnterPassword.length() < 6) {
            this.password.setError("Password is required to have at least 6 characters");
            this.reEnterPassword.setError("Password is required to have at least 6 characters");
            return;
        }

        if (!(password.equals(reEnterPassword))) {
            this.password.setError("Passwords must match");
            this.reEnterPassword.setError("Passwords must match");
            return;
        }

        progressBar2.setVisibility(View.VISIBLE);



        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    String userId = mAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = fStore.collection("user").document(userId);

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

                    Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                    startActivity(intent);
                }
                else {
                    progressBar2.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Error occurred! "+ task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}