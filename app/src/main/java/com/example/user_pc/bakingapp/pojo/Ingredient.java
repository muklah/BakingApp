package com.example.user_pc.bakingapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements Parcelable {

    private Double quantity;
    private String measure;
    private String ingredient;

    @Override
    public void writeToParcel(Parcel out, int flags) {
        if (quantity == null) {
            out.writeByte((byte) (0x00));
        } else {
            out.writeByte((byte) (0x01));
            out.writeDouble(quantity);
        }
        out.writeString(measure);
        out.writeString(ingredient);
    }

    protected Ingredient(Parcel in) {
        quantity = in.readByte() == 0x00 ? null : in.readDouble();
        measure = in.readString();
        ingredient = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int i) {
            return new Ingredient[i];
        }
    };

    public Double getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

}