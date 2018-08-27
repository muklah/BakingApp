package com.example.user_pc.bakingapp.retrofit;

import com.example.user_pc.bakingapp.pojo.Recipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("baking.json")
    Call<ArrayList<Recipe>> getRecipe();
}
