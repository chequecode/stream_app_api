package com.example.streamapi.controller;

import com.example.streamapi.DTO.ArtistDTO;
import com.example.streamapi.entities.Album;
import com.example.streamapi.entities.Artist;
import com.example.streamapi.entities.Track;
import com.example.streamapi.entities.User;
import com.example.streamapi.errors.ErrorMessage;
import com.example.streamapi.service.AlbumService;
import com.example.streamapi.service.ArtistService;
import com.example.streamapi.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    
    @Autowired
    private ArtistService artistService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private TrackService trackService;

    @PostMapping
    public ResponseEntity<Object> createAlbum(@RequestBody ArtistDTO artistDTO) {
        try {
            Artist createdArtist = artistService.createArtist(convertToEntity(artistDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(createdArtist);
        } catch (DataIntegrityViolationException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage); //400
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage); //500
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getTrackById(@PathVariable Long id) {
        Optional<Artist> artist = artistService.getArtistById(id);
        return artist.map(this::convertToDTO).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ArtistDTO> getAllArtists() {
        return artistService.getAllArtists().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTrack(@PathVariable Long id, @RequestBody ArtistDTO artistDTO) {
        try {
            Artist updatedArtist = artistService.artistUpdate(id, convertToEntity(artistDTO));
            return ResponseEntity.ok(updatedArtist);
        } catch (DataIntegrityViolationException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        } catch (RuntimeException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }

    private Artist convertToEntity(ArtistDTO artistDTO) {
        Artist artist = new Artist();
        artist.setListeningSessions(artistDTO.getListeningSessions());
        artist.setName(artistDTO.getName());
        artist.setId(artistDTO.getId());
        artist.setDescription(artistDTO.getDescription());
        artist.setPhoto(artistDTO.getPhoto());

        if (artistDTO.getAlbumsId() != null) {
            List<Album> albums = artistDTO.getAlbumsId().stream().map(albumId -> albumService.getAlbumById(albumId).orElseGet(null)).collect(Collectors.toList());
            artist.setAlbums(albums);
        }
        if (artistDTO.getTracksId() != null) {
            List<Track> tracks = artistDTO.getTracksId().stream().map(trackId -> trackService.getTrackById(trackId).orElseGet(null)).collect(Collectors.toList());
            artist.setTracks(tracks);
        }
        return artist;
    }

    private ArtistDTO convertToDTO(Artist artist) {
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setId(artist.getId());
        artistDTO.setDescription(artist.getDescription());
        artistDTO.setName(artist.getName());
        artistDTO.setListeningSessions(artist.getListeningSessions());
        artistDTO.setPhoto(artist.getPhoto());
        artistDTO.setAlbumsId(artist.getAlbums().stream().map(Album::getId).collect(Collectors.toList()));
        artistDTO.setTracksId(artist.getTracks().stream().map(Track::getId).collect(Collectors.toList()));
        return artistDTO;
    }
}
