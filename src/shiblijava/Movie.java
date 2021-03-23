/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiblijava;

/**
 *
 * @author WarSpite
 */
public class Movie {
    private String title;
    private String description;
    private String releaseDate;
    private String producer;

    public Movie() {
        
    }

    
    public Movie(String title, String description, String releaseDate, String producer) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.producer = producer;
    }

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public String toString() {
        return "Movie{" + "title=" + title + ", description=" + description + ", releaseDate=" + releaseDate + ", producer=" + producer + '}';
    }
    
    
    
}
