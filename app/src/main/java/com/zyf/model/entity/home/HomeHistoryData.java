package com.zyf.model.entity.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.zyf.model.entity.info.InfoEntity;

import java.util.List;

/**
 * Created by TCJK on 2018/4/12.
 */

public class HomeHistoryData implements Parcelable {

    @SerializedName("in")
    List<InfoEntity> inHistory;

    @SerializedName("out")
    List<InfoEntity> outHistory;

    public List<InfoEntity> getInHistory() {
        return inHistory;
    }

    public void setInHistory(List<InfoEntity> inHistory) {
        this.inHistory = inHistory;
    }

    public List<InfoEntity> getOutHistory() {
        return outHistory;
    }

    public void setOutHistory(List<InfoEntity> outHistory) {
        this.outHistory = outHistory;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.inHistory);
        dest.writeTypedList(this.outHistory);
    }

    public HomeHistoryData() {
    }

    protected HomeHistoryData(Parcel in) {
        this.inHistory = in.createTypedArrayList(InfoEntity.CREATOR);
        this.outHistory = in.createTypedArrayList(InfoEntity.CREATOR);
    }

    public static final Parcelable.Creator<HomeHistoryData> CREATOR = new Parcelable.Creator<HomeHistoryData>() {
        @Override
        public HomeHistoryData createFromParcel(Parcel source) {
            return new HomeHistoryData(source);
        }

        @Override
        public HomeHistoryData[] newArray(int size) {
            return new HomeHistoryData[size];
        }
    };
}
