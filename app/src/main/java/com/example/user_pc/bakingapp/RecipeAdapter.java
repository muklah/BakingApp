package com.example.user_pc.bakingapp;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user_pc.bakingapp.pojo.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecyclerViewHolder> {

    ArrayList<Recipe> lRecipes;
    private Context mContext;
    private final RecipeAdapterOnClickHandler mClickHandler;

    public interface RecipeAdapterOnClickHandler {
        void onClick(Recipe recipe);
    }

    public RecipeAdapter(RecipeAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView recipeTitle;
        public final TextView recipeServings;
        public final ImageView recipeImage;


        public RecyclerViewHolder(View view) {
            super(view);
            recipeTitle = (TextView) view.findViewById(R.id.recipe_title);
            recipeServings = (TextView) view.findViewById(R.id.recipe_servings);
            recipeImage = (ImageView) view.findViewById(R.id.recipe_image);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Recipe recipe = lRecipes.get(adapterPosition);
            mClickHandler.onClick(recipe);
        }

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        mContext = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recipe_list_item;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup,  shouldAttachToParentImmediately);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.recipeTitle.setText(String.valueOf(lRecipes.get(position).getName()));
        holder.recipeServings.setText(String.valueOf(lRecipes.get(position).getServings()));
        String imageUrl = lRecipes.get(position).getImage();

        if (imageUrl != "") {
            Uri builtUri = Uri.parse(imageUrl).buildUpon().build();
            Picasso.with(mContext).load(builtUri).into(holder.recipeImage);
        }

    }

    @Override
    public int getItemCount() {
        if (null == lRecipes)
            return 0;
        else {
            return lRecipes.size();
        }

    }

    public void setRecipeData(ArrayList<Recipe> recipesIn, Context context) {
        lRecipes = recipesIn;
        mContext = context;
        notifyDataSetChanged();
    }
}
