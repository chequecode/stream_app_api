package com.example.streamapi.DTO;

public class TrackDTO {

    private Long id;
    private String name;
    private String mp3;
    private int duration;
    private String text;
    private int listeningSessions;
    private AlbumDTO album;
    private ArtistDTO artist;

    public TrackDTO() {
    }

    public TrackDTO(Long id, String name, String mp3, int duration, String text, int listeningSessions, AlbumDTO album, ArtistDTO artist) {
        this.id = id;
        this.name = name;
        this.mp3 = mp3;
        this.duration = duration;
        this.text = text;
        this.listeningSessions = listeningSessions;
        this.album = album;
        this.artist = artist;
    }

    public AlbumDTO getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDTO album) {
        this.album = album;
    }

    public ArtistDTO getArtist() {
        return artist;
    }

    public void setArtist(ArtistDTO artist) {
        this.artist = artist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
