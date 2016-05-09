package com.hipo.tryouts.androidsdk;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

class AppRelease implements Parcelable {

    private long id;
    private String name;

    @SerializedName("release_number")
    private String releaseNumber;

    @SerializedName("release_notes")
    private String releaseNotes;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("minimum_os_version")
    private String minOsVersion;

    private String size;

    @SerializedName("short-hash")
    private String shortHash;

    @SerializedName("is_private")
    private boolean isPrivate;

    @SerializedName("download_link")
    private String downloadLink;

    @SerializedName("public_install_link")
    private String publicInstallLink;

    protected AppRelease(Parcel in) {
        id = in.readLong();
        name = in.readString();
        releaseNumber = in.readString();
        releaseNotes = in.readString();
        releaseDate = in.readString();
        minOsVersion = in.readString();
        size = in.readString();
        shortHash = in.readString();
        isPrivate = in.readByte() != 0;
        downloadLink = in.readString();
        publicInstallLink = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(releaseNumber);
        dest.writeString(releaseNotes);
        dest.writeString(releaseDate);
        dest.writeString(minOsVersion);
        dest.writeString(size);
        dest.writeString(shortHash);
        dest.writeByte((byte) (isPrivate ? 1 : 0));
        dest.writeString(downloadLink);
        dest.writeString(publicInstallLink);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AppRelease> CREATOR = new Creator<AppRelease>() {
        @Override
        public AppRelease createFromParcel(Parcel in) {
            return new AppRelease(in);
        }

        @Override
        public AppRelease[] newArray(int size) {
            return new AppRelease[size];
        }
    };

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReleaseNumber() {
        return releaseNumber;
    }

    public String getReleaseNotes() {
        return releaseNotes;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getMinOsVersion() {
        return minOsVersion;
    }

    public String getSize() {
        return size;
    }

    public String getShortHash() {
        return shortHash;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public String getPublicInstallLink() {
        return publicInstallLink;
    }


}
