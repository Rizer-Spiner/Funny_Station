package com.example.session7.persistence.remote;

import com.example.session7.model.Joke;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface JokeApi {

    @Headers({
            "Accept: application/json",
            "User-Agent: AND_Exam(280055@via.dk)",
    })
    @GET("/")
    Call<Joke> getRandomJoke();

    @Headers({
            "Accept: application/json",
            "User-Agent: AND_Exam(280055@via.dk)",
    })
    @GET("/j/{joke_id}")
    Call<Joke> getJokeById(@Path("joke_id") String id);


}
