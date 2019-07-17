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

    public String getTitle() {
        return title;
    }

    public String getRelease() {
        return release;
    }

    public String getVote() {
        return vote;
    }

    public String getOverview() {
        return overview;
    }

}
