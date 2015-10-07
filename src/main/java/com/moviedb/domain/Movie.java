package com.moviedb.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Movie.
 */
@Entity
@Table(name = "MOVIE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "title")
    private String title;
    
    @Column(name = "year")
    private String year;
    
    @Column(name = "rated")
    private String rated;
    
    @Column(name = "released")
    private String released;
    
    @Column(name = "runtime")
    private String runtime;
    
    @Column(name = "genre")
    private String genre;
    
    @Column(name = "director")
    private String director;
    
    @Column(name = "actors")
    private String actors;
    
    @Column(name = "plot")
    private String plot;
    
    @Column(name = "language")
    private String language;
    
    @Column(name = "country")
    private String country;
    
    @Column(name = "awards")
    private String awards;
    
    @Column(name = "poster")
    private String poster;
    
    @Column(name = "metascore")
    private String metascore;
    
    @Column(name = "imdb_rating")
    private String imdbRating;
    
    @Column(name = "imdb_votes")
    private String imdbVotes;
    
    @Column(name = "imdb_id")
    private String imdbID;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "response")
    private String response;
    
    @Column(name = "comment")
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Movie movie = (Movie) o;

        if ( ! Objects.equals(id, movie.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + "'" +
                ", year='" + year + "'" +
                ", rated='" + rated + "'" +
                ", released='" + released + "'" +
                ", runtime='" + runtime + "'" +
                ", genre='" + genre + "'" +
                ", director='" + director + "'" +
                ", actors='" + actors + "'" +
                ", plot='" + plot + "'" +
                ", language='" + language + "'" +
                ", country='" + country + "'" +
                ", awards='" + awards + "'" +
                ", poster='" + poster + "'" +
                ", metascore='" + metascore + "'" +
                ", imdbRating='" + imdbRating + "'" +
                ", imdbVotes='" + imdbVotes + "'" +
                ", imdbID='" + imdbID + "'" +
                ", type='" + type + "'" +
                ", response='" + response + "'" +
                ", comment='" + comment + "'" +
                '}';
    }
}
