package com.example.streamapi.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "Tracks")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Track extends BaseEntity{
    @Column(nullable = false)
    private String mp3;
    @Column(nullable = false)
    private int duration;
    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private int listeningSessions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    public Track() {
    }

    public Track(long id, String name, String mp3, int duration, String text, int listeningSessions, Album album, Artist artist) {
        super(id, name);
        this.mp3 = mp3;
        this.duration = duration;
        this.text = text;
        this.listeningSessions = listeningSessions;
        this.album = album;
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getMp3() {
        return mp3;
    }

    public void setMp3(String mp3) {
        this.mp3 = mp3;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getListeningSessions() {
        return listeningSessions;
    }

    public void setListeningSessions(int listeningSessions) {
        this.listeningSessions = listeningSessions;
    }
}
