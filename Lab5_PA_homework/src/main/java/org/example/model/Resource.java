package org.example.model;

public class Resource {
    private String id;
    private String title;
    private String location;
    private int year;
    private String author;

    public Resource() {}

    public Resource(String id, String title, String location, int year, String author) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.year = year;
        this.author = author;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    @Override
    public String toString() {
        return "Resource{id='" + id + "', title='" + title + "'}";
    }
}