package com.hipo.tryouts.androidsdk;

import com.google.gson.annotations.SerializedName;

class Application {

    private long id;
    private String name;

    @SerializedName("os_name")
    private String osName;

    @SerializedName("device_type")
    private String deviceType;

    @SerializedName("short_hash")
    private String shortHash;

    @SerializedName("latest_release")
    private AppRelease latestRelease;

    @SerializedName("icon_original")
    private String iconOriginal;

    @SerializedName("icon_thumbnail")
    private String iconThumbnail;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOsName() {
        return osName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getShortHash() {
        return shortHash;
    }

    public AppRelease getLatestRelease() {
        return latestRelease;
    }

    public String getIconOriginal() {
        return iconOriginal;
    }

    public String getIconThumbnail() {
        return iconThumbnail;
    }
}
