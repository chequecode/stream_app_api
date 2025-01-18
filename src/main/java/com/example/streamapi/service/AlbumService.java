package com.example.streamapi.service;

import com.example.streamapi.entities.Album;
import com.example.streamapi.entities.User;
import com.example.streamapi.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public Album updateAlbum(Long id, Album albumDetails) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("album not found"));
        album.setArtist(albumDetails.getArtist());
        album.setImage(albumDetails.getImage());
        album.setTracks(albumDetails.getTracks());
        album.setId(albumDetails.getId());
        album.setName(album.getName());
        return albumRepository.save(album);
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }
    public Optional<Album> getAlbumById(long id) {
        return albumRepository.findById(id);
    }
    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }
    public void deleteAlbum(Long id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        albumRepository.delete(album);
    }
}
