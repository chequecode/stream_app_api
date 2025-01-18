package com.example.streamapi.entities;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "Albums")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Album extends BaseEntity{

    @Column(nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Track> tracks; // в списке с треками(альбоме) много треков

    public Album() {
    }

    public Album(Artist artist, long id, String name) {
        super(id, name);
        this.artist = artist;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
