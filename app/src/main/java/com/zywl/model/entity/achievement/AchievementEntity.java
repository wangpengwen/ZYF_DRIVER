package com.zywl.model.entity.achievement;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by TCJK on 2018/7/16.
 */

public class AchievementEntity implements Parcelable {

    List<AchievementDataEntity> list;

    AchievementDayEntity dayDto;

    public List<AchievementDataEntity> getList() {
        return list;
    }

    public void setList(List<AchievementDataEntity> list) {
        this.list = list;
    }

    public AchievementDayEntity getDayDto() {
        return dayDto;
    }

    public void setDayDto(AchievementDayEntity dayDto) {
        this.dayDto = dayDto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.list);
        dest.writeParcelable(this.dayDto, flags);
    }

    public AchievementEntity() {
    }

    protected AchievementEntity(Parcel in) {
        this.list = in.createTypedArrayList(AchievementDataEntity.CREATOR);
        this.dayDto = in.readParcelable(AchievementDayEntity.class.getClassLoader());
    }

    public static final Parcelable.Creator<AchievementEntity> CREATOR = new Parcelable.Creator<AchievementEntity>() {
        @Override
        public AchievementEntity createFromParcel(Parcel source) {
            return new AchievementEntity(source);
        }

        @Override
        public AchievementEntity[] newArray(int size) {
            return new AchievementEntity[size];
        }
    };
}
