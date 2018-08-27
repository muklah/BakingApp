package com.example.user_pc.bakingapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {

    private Integer id;
    private String name;
    private List<Ingredient> ingredients = null;
    private ArrayList<Step> steps = null;
    private Integer servings;
    private String image;

    @Override
    public void writeToParcel(Parcel out, int flags) {
        if (id == null) {
            out.writeByte((byte) (0x00));
        } else {
            out.writeByte((byte) (0x01));
            out.writeInt(id);
        }
        out.writeString(name);
        if (ingredients == null) {
            out.writeByte((byte) (0x00));
        } else {
            out.writeByte((byte) (0x01));
            out.writeList(ingredients);
        }
        if (steps == null) {
            out.writeByte((byte) (0x00));
        } else {
            out.writeByte((byte) (0x01));
            out.writeList(steps);
        }
        if (servings == null) {
            out.writeByte((byte) (0x00));
        } else {
            out.writeByte((byte) (0x01));
            out.writeInt(servings);
        }
        out.writeString(image);
    }

    protected Recipe(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        name = in.readString();
        if (in.readByte() == 0x01) {
            ingredients = new ArrayList<>();
            in.readList(ingredients, Ingredient.class.getClassLoader());
        } else {
            ingredients = null;
        }
        if (in.readByte() == 0x01) {
            steps = new ArrayList<>();
            in.readList(steps, Step.class.getClassLoader());
        } else {
            steps = null;
        }
        servings = in.readByte() == 0x00 ? null : in.readInt();
        image = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int i) {
            return new Recipe[i];
        }
    };

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public Integer getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

}