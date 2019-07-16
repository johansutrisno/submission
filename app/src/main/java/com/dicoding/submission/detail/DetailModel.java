package com.dicoding.submission.detail;

public class DetailModel {
    private String poster;
    private String title;
    private String release;
    private String vote;
    private String overview;

    DetailModel() { }

    DetailModel(String poster, String title, String release, String vote, String overview) {
        this.poster = poster;
        this.title = title;
        this.release = release;
        this.vote = vote;
        this.overview = overview;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
