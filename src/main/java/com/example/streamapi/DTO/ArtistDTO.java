package com.example.streamapi.DTO;

import java.util.List;

public class ArtistDTO {
    private Long id;
    private String name;
    private String description;
    private String photo;
    private int listeningSessions;
    private List<Long> albumsId;
    private List<Long> tracksId;

    public ArtistDTO() {
    }

    public ArtistDTO(Long id, String name, String description, String photo, int listeningSessions, List<Long> albumsId, List<Long> tracksId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.listeningSessions = listeningSessions;
        this.albumsId = albumsId;
        this.tracksId = tracksId;
    }

    public List<Long> getAlbumsId() {
        return albumsId;
    }

    public void setAlbumsId(List<Long> albumsId) {
        this.albumsId = albumsId;
    }

    public List<Long> getTracksId() {
        return tracksId;
    }

    public void setTracksId(List<Long> tracksId) {
        this.tracksId = tracksId;
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
}
