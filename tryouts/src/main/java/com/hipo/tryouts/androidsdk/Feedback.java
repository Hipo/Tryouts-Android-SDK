package com.hipo.tryouts.androidsdk;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Feedback implements Parcelable {

    public static final Parcelable.Creator<Feedback> CREATOR = new Parcelable.Creator<Feedback>() {
        @Override
        public Feedback createFromParcel(Parcel source) {
            return new Feedback(source);
        }

        @Override
        public Feedback[] newArray(int size) {
            return new Feedback[size];
        }
    };
    @SerializedName("user_name")
    private String userName;
    @SerializedName("release_version")
    private String releaseVersion;
    private String message;
    private String screenshot;

    public Feedback(String userName, String releaseVersion, String message) {
        setUserName(userName);
        setReleaseVersion(releaseVersion);
        setMessage(message);
    }

    public Feedback(String userName, String releaseVersion, String message, String screenshot) {
        setUserName(userName);
        setReleaseVersion(releaseVersion);
        setMessage(message);
        setScreenshot(screenshot);
    }

    protected Feedback(Parcel in) {
        this.userName = in.readString();
        this.releaseVersion = in.readString();
        this.message = in.readString();
        this.screenshot = in.readString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(String releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.releaseVersion);
        dest.writeString(this.message);
        dest.writeString(this.screenshot);
    }
}
