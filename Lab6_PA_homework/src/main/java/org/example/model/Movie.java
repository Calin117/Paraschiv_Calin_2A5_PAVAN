package org.example.model;

import java.sql.Date;

public class Movie {
    private String title;
    private Date releaseDate;
    private double score;
    private String genreName;

    public Movie(String title, Date releaseDate, double score, String genreName) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.score = score;
        this.genreName = genreName;
    }

    public String getTitle() { return title; }
    public Date getReleaseDate() { return releaseDate; }
    public double getScore() { return score; }
    public String getGenreName() { return genreName; }
}