package co.hiddentrack.partners_demo.model;

import java.util.Random;
import java.util.Date;


public class Article {
    private int id;
    private String title;
    private String contents;
    private String location;
    private Date createdAt;
    private Date updatedAt;

    public Article(String title, String contents, String location) {
        this.id = new Random().nextInt();
        this.title = title;
        this.contents = contents;
        this.location = location;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.updatedAt = new Date();
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
        this.updatedAt = new Date();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
