package com.example.streamapi.entities;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "Artists")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Artist extends BaseEntity{
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String photo;
    @Column(nullable = false)
    private int listeningSessions;

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER)
    private List<Album> albums;

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER)
    private List<Track> tracks;

    public Artist() {
    }

    public Artist(long id, String name, String description, String photo, int listeningSessions, List<Album> albums, List<Track> tracks) {
        super(id, name);
        this.description = description;
        this.photo = photo;
        this.listeningSessions = listeningSessions;
        this.albums = albums;
        this.tracks = tracks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getListeningSessions() {
        return listeningSessions;
    }

    public void setListeningSessions(int listeningSessions) {
        this.listeningSessions = listeningSessions;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
