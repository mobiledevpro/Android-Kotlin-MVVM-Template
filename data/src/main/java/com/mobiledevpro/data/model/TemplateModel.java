package com.mobiledevpro.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class uses only as example
 * <p>
 * TODO: don't forget to remove it in your project
 * <p>
 * <p>
 * Created by Dmitriy V. Chernysh on 21.10.17.
 * dmitriy.chernysh@gmail.com
 * <p>
 * https://fb.me/mobiledevpro/
 * <p>
 * #MobileDevPro
 */

public class TemplateModel implements Parcelable {

    public static final Parcelable.Creator<TemplateModel> CREATOR = new Parcelable.Creator<TemplateModel>() {

        public TemplateModel createFromParcel(Parcel in) {
            return new TemplateModel(in);
        }

        @Override
        public TemplateModel[] newArray(int size) {
            return new TemplateModel[size];
        }
    };

    private int id;
    private String value;

    public TemplateModel(int id, String value) {
        this.id = id;
        this.value = value;
    }

    private TemplateModel(Parcel in) {
        id = in.readInt();
        value = in.readString();
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
