package com.example.user_pc.bakingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user_pc.bakingapp.pojo.Recipe;
import com.example.user_pc.bakingapp.retrofit.Api;
import com.example.user_pc.bakingapp.retrofit.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeFragment extends Fragment {

    public RecipeFragment() {
        // Required empty public constructor
    }

    public static RecipeFragment newInstance() {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recipe_fragment, container, false);

        RecyclerView recipesList;
        final RecipeAdapter recipeAdapter;

        recipesList = (RecyclerView)  view.findViewById(R.id.recipe_recycler);

        recipeAdapter = new RecipeAdapter((MainActivity)getActivity());
        recipesList.setAdapter(recipeAdapter);

        if (view.getTag() != null && view.getTag().equals("phone-land")){
            GridLayoutManager LayoutManager = new GridLayoutManager(getContext(),3);
            recipesList.setLayoutManager(LayoutManager);
        }
        else {
            LinearLayoutManager LayoutManager = new LinearLayoutManager(getContext());
            recipesList.setLayoutManager(LayoutManager);
        }

        final SimpleIdlingResource idlingResource = (SimpleIdlingResource)((MainActivity)getActivity()).getIdlingResource();

        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }

        ApiInterface apiService = Api.getClient().create(ApiInterface.class);

        Call<ArrayList<Recipe>> call = apiService.getRecipe();
        call.enqueue(new Callback<ArrayList<Recipe>>() {
            @Override
            public void onResponse(Call<ArrayList<Recipe>> call, Response<ArrayList<Recipe>> response) {
                Integer statusCode = response.code();
                Log.v("status code: ", statusCode.toString());

                ArrayList<Recipe> recipes = response.body();

                Bundle recipesBundle = new Bundle();
                recipesBundle.putParcelableArrayList("Recipes", recipes);

                recipeAdapter.setRecipeData(recipes,getContext());
                if (idlingResource != null) {
                    idlingResource.setIdleState(true);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Recipe>> call, Throwable t) {
                Log.v("http fail: ", t.getMessage());
            }
        });

        return view;
    }


}
