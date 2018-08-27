package com.example.user_pc.bakingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user_pc.bakingapp.pojo.Ingredient;
import com.example.user_pc.bakingapp.pojo.Recipe;
import com.example.user_pc.bakingapp.widget.UpdateService;

import java.util.ArrayList;
import java.util.List;


public class RecipeDetailFragment extends Fragment {

    ArrayList<Recipe> recipe;
    String recipeName;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    public static RecipeDetailFragment newInstance() {
        RecipeDetailFragment fragment = new RecipeDetailFragment();
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
        View view = inflater.inflate(R.layout.recipe_detail_fragment, container, false);

        RecyclerView recipesDetailsList;
        TextView ingredients;
        TextView name;

        recipe = new ArrayList<>();

        name = (TextView)view.findViewById(R.id.recipe_name);
        ingredients = (TextView)view.findViewById(R.id.recipe_detail_text);

        if(savedInstanceState != null) {
            recipe = savedInstanceState.getParcelableArrayList("Recipe");

        }
        else {
            recipe = getArguments().getParcelableArrayList("Recipe");
        }

        recipeName = recipe.get(0).getName();
        List<Ingredient> ingredientList = recipe.get(0).getIngredients();

        name.setText(recipeName);

        ArrayList<String> ingredientListWidgets = new ArrayList<>();

        StringBuilder ingredientsBuilder = new StringBuilder();
        for (Ingredient s : ingredientList) {
            ingredientsBuilder.append(s.getIngredient() + "\n");
            ingredientsBuilder.append("Quantity: " + s.getQuantity().toString() + "\n");
            ingredientsBuilder.append("Measure: " + s.getMeasure() + "\n\n");

            ingredientListWidgets.add(s.getIngredient() + "\n" +
            "Quantity: " + s.getQuantity().toString() + "\n" +
            "Measure: " + s.getMeasure() + "\n");
        }
        ingredients.setText(ingredientsBuilder.toString());

        recipesDetailsList = (RecyclerView)view.findViewById(R.id.recipe_detail_recycler);
        LinearLayoutManager LayoutManager=new LinearLayoutManager(getContext());
        recipesDetailsList.setLayoutManager(LayoutManager);

        RecipeDetailAdapter mRecipeDetailAdapter =new RecipeDetailAdapter((RecipeDetailActivity)getActivity());
        recipesDetailsList.setAdapter(mRecipeDetailAdapter);
        mRecipeDetailAdapter.setRecipeDetailData(recipe,getContext());

        UpdateService.startService(getContext(), ingredientListWidgets);

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        super.onSaveInstanceState(currentState);
        currentState.putParcelableArrayList("Recipe", recipe);
    }

}


