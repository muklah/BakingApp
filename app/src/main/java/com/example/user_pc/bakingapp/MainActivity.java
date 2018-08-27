package com.example.user_pc.bakingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user_pc.bakingapp.pojo.Recipe;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeAdapterOnClickHandler{

    @Nullable
    private SimpleIdlingResource mIdlingResource;

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getIdlingResource();
    }

    @Override
    public void onClick(Recipe recipe) {

        Bundle recipeDetailsBundle = new Bundle();
        ArrayList<Recipe> recipeDetails = new ArrayList<>();
        recipeDetails.add(recipe);
        recipeDetailsBundle.putParcelableArrayList("Recipe", recipeDetails);

        final Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtras(recipeDetailsBundle);
        startActivity(intent);

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


}
