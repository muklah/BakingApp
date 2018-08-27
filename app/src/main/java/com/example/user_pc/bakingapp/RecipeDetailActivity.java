package com.example.user_pc.bakingapp;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.user_pc.bakingapp.pojo.Recipe;
import com.example.user_pc.bakingapp.pojo.Step;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailActivity extends AppCompatActivity implements RecipeDetailAdapter.RecipeDetailAdapterOnClickHandler,RecipeStepDetailFragment.ListItemClickListener{

    private ArrayList<Recipe> recipe;
    String recipeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        if (savedInstanceState == null) {

            Bundle recipeBundle = getIntent().getExtras();

            recipe = new ArrayList<>();
            recipe = recipeBundle.getParcelableArrayList("Recipe");
            recipeName = recipe.get(0).getName();

            final RecipeDetailFragment fragment = new RecipeDetailFragment();
            fragment.setArguments(recipeBundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment).addToBackStack("RECIPES_DETAILS")
                    .commit();

            if (findViewById(R.id.recipe_detail).getTag()!=null && findViewById(R.id.recipe_detail).getTag().equals("tablet-land")) {

                final RecipeStepDetailFragment fragment2 = new RecipeStepDetailFragment();
                fragment2.setArguments(recipeBundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.fragment_container2, fragment2).addToBackStack("STEPS_DETAILS")
                        .commit();

            }

        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                if (findViewById(R.id.fragment_container2)==null) {
                    if (fm.getBackStackEntryCount() > 1) {
                        //go back to "Recipe Detail" screen
                        fm.popBackStack("RECIPES_DETAILS", 0);
                    } else if (fm.getBackStackEntryCount() > 0) {
                        //go back to "Recipe" screen
                        finish();

                    }


                }
                else {

                    finish();

                }

            }
        });
    }

    @Override
    public void onClick(List<Step> stepsOut, int selectedItemIndex, String recipeName) {


        final RecipeStepDetailFragment fragment = new RecipeStepDetailFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        Bundle stepDetailsBundle = new Bundle();
        stepDetailsBundle.putParcelableArrayList("Steps",(ArrayList<Step>) stepsOut);
        stepDetailsBundle.putInt("Step",selectedItemIndex);
        stepDetailsBundle.putString("Title",recipeName);
        fragment.setArguments(stepDetailsBundle);

        if (findViewById(R.id.recipe_detail).getTag()!=null && findViewById(R.id.recipe_detail).getTag().equals("tablet-land")) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container2, fragment).addToBackStack("STEPS_DETAILS")
                    .commit();

        }
        else {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment).addToBackStack("STEPS_DETAILS")
                    .commit();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Title",recipeName);
    }

}
