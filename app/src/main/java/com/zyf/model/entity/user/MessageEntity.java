package com.zyf.model.entity.user;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Title: MessageEntity
 * Description:
 * Copyright:Copyright(c)2016
 * Company: 博智维讯信息技术有限公司
 * CreateTime:2017/12/21  17:41
 *
 * @author wangwei
 * @version 1.0
 */
public class MessageEntity implements Parcelable {

    public String _messageId;
    public String content;

    public Long createTimestamp;

    public long id;

    public String mobile;

    public String noticeType;

    public boolean read;

    public String title;

    public Long updateTimestamp;

    public String url;

    public long userId;

    public MessageEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._messageId);
        dest.writeString(this.content);
        dest.writeValue(this.createTimestamp);
        dest.writeLong(this.id);
        dest.writeString(this.mobile);
        dest.writeString(this.noticeType);
        dest.writeByte(this.read ? (byte) 1 : (byte) 0);
        dest.writeString(this.title);
        dest.writeValue(this.updateTimestamp);
        dest.writeString(this.url);
        dest.writeLong(this.userId);
    }

    protected MessageEntity(Parcel in) {
        this._messageId = in.readString();
        this.content = in.readString();
        this.createTimestamp = (Long) in.readValue(Long.class.getClassLoader());
        this.id = in.readLong();
        this.mobile = in.readString();
        this.noticeType = in.readString();
        this.read = in.readByte() != 0;
        this.title = in.readString();
        this.updateTimestamp = (Long) in.readValue(Long.class.getClassLoader());
        this.url = in.readString();
        this.userId = in.readLong();
    }

    public static final Creator<MessageEntity> CREATOR = new Creator<MessageEntity>() {
        @Override
        public MessageEntity createFromParcel(Parcel source) {
            return new MessageEntity(source);
        }

        @Override
        public MessageEntity[] newArray(int size) {
            return new MessageEntity[size];
        }
    };
}
