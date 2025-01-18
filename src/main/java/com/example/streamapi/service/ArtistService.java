package com.example.streamapi.service;

import com.example.streamapi.entities.Artist;
import com.example.streamapi.entities.Track;
import com.example.streamapi.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public Artist artistUpdate(long id, Artist artistDetails) {
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new RuntimeException("artist not found"));
        artist.setAlbums(artistDetails.getAlbums());
        artist.setDescription(artistDetails.getDescription());
        artist.setTracks(artistDetails.getTracks());
        artist.setPhoto(artistDetails.getPhoto());
        artist.setName(artistDetails.getName());
        artist.setListeningSessions(artistDetails.getListeningSessions());
        return artistRepository.save(artist);
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }
    public Optional<Artist> getArtistById(long id) {
        return artistRepository.findById(id);
    }
    public void deleteArtist (long id) {
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new RuntimeException("artist not found"));
        artistRepository.delete(artist);
    }
    public Artist createArtist (Artist artist) {
        return artistRepository.save(artist);
    }
}



