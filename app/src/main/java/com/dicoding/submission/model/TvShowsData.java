package com.dicoding.submission.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "tvshow")
public class TvShowsData implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int mId;

    @ColumnInfo(name = "id")
    @SerializedName("id")
    private Integer id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String title;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String poster_path;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String overview;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private String voteAverage;

    @ColumnInfo(name = "isFavorite")
    private boolean isFavorite;

    public TvShowsData(int mId, Integer id, String title, String poster_path, String overview, String voteAverage, boolean isFavorite) {
        this.mId = mId;
        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.voteAverage = voteAverage;
        this.isFavorite = isFavorite;
    }

    public TvShowsData() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeValue(this.id);
        dest.writeString(this.title);
        dest.writeString(this.poster_path);
        dest.writeString(this.overview);
        dest.writeString(this.voteAverage);
        dest.writeByte(this.isFavorite ? (byte) 1 : (byte) 0);
    }

    protected TvShowsData(Parcel in) {
        this.mId = in.readInt();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.poster_path = in.readString();
        this.overview = in.readString();
        this.voteAverage = in.readString();
        this.isFavorite = in.readByte() != 0;
    }

    public static final Creator<TvShowsData> CREATOR = new Creator<TvShowsData>() {
        @Override
        public TvShowsData createFromParcel(Parcel source) {
            return new TvShowsData(source);
        }

        @Override
        public TvShowsData[] newArray(int size) {
            return new TvShowsData[size];
        }
    };
}
