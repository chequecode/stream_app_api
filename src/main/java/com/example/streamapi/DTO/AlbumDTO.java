package com.example.streamapi.DTO;

import java.util.List;

public class AlbumDTO {
    private String name;
    private Long id;
    private String image;
    private ArtistDTO artistdto;
    private List<Long> tracksId;

    public AlbumDTO() {
    }

    public AlbumDTO(String image, ArtistDTO artistdto, List<Long> tracksId, String name, Long id) {
        this.image = image;
        this.id = id;
        this.name = name;
        this.artistdto = artistdto;
        this.tracksId = tracksId;
    }

    public String getName() {
        return name;
    }

    public ArtistDTO getArtist() {
        return artistdto;
    }

    public void setArtist(ArtistDTO artistdto) {
        this.artistdto = artistdto;
    }

    public List<Long> getTracksId() {
        return tracksId;
    }

    public void setTracksId(List<Long> tracksId) {
        this.tracksId = tracksId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
