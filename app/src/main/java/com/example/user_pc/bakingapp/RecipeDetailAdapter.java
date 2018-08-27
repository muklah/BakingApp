package com.example.user_pc.bakingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user_pc.bakingapp.pojo.Recipe;
import com.example.user_pc.bakingapp.pojo.Step;

import java.util.ArrayList;
import java.util.List;

public class RecipeDetailAdapter extends RecyclerView.Adapter<RecipeDetailAdapter.RecyclerViewHolder> {

    ArrayList<Step> lSteps;
    private Context mContext;
    private String recipeName;

    final private RecipeDetailAdapterOnClickHandler mClickHandler;

    public interface RecipeDetailAdapterOnClickHandler {
        void onClick(List<Step> stepsOut, int clickedItemIndex, String recipeName);
    }

    public RecipeDetailAdapter(RecipeDetailAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView steps;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            steps = (TextView) itemView.findViewById(R.id.steps);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.onClick(lSteps,adapterPosition,recipeName);
        }

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        mContext = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recipe_detail_item;

        LayoutInflater inflater = LayoutInflater.from(mContext);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.steps.setText(lSteps.get(position).getId()+". "+ lSteps.get(position).getShortDescription());

    }

    @Override
    public int getItemCount() {
        if (null == lSteps)
            return 0;
        else {
            return lSteps.size();
        }

    }

    public void setRecipeDetailData(ArrayList<Recipe> recipesIn, Context context) {
        lSteps= recipesIn.get(0).getSteps();
        recipeName=recipesIn.get(0).getName();
        notifyDataSetChanged();
    }

}
