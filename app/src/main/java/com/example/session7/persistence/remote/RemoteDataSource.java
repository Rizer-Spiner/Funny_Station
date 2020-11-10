package com.example.session7.persistence.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.session7.model.Joke;
import com.example.session7.model.JokeWithoutStatus2;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource {

    private JokeApi jokesApi;
    private Joke fetchedJoke;


    public RemoteDataSource() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://icanhazdadjoke.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        jokesApi = retrofit.create(JokeApi.class);

        fetchedJoke = new Joke();

    }

    public LiveData<JokeWithoutStatus2> getRandomJoke() {
        Call<Joke> call = jokesApi.getRandomJoke();

        call.enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {

                Joke randomJoke = response.body();

                fetchedJoke = randomJoke;
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {
                t.printStackTrace();
                Joke falseJoke = new Joke("888", "An error appeared! Check your internet connection!", 1);
                fetchedJoke = falseJoke;
            }
        });
        JokeWithoutStatus2 jokeWithoutStatus = new JokeWithoutStatus2(fetchedJoke.getId(), fetchedJoke.getJoke());
        MutableLiveData<JokeWithoutStatus2> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(jokeWithoutStatus);
        return mutableLiveData;
    }
}
