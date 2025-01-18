package com.example.streamapi.controller;

import com.example.streamapi.DTO.AlbumDTO;
import com.example.streamapi.DTO.ArtistDTO;
import com.example.streamapi.entities.Album;
import com.example.streamapi.entities.Artist;
import com.example.streamapi.entities.Track;
import com.example.streamapi.errors.ErrorMessage;
import com.example.streamapi.service.AlbumService;
import com.example.streamapi.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private TrackService trackService;

    @PostMapping
    public ResponseEntity<Object> createAlbum(@RequestBody AlbumDTO albumDTO) {
        try {
            Album createdAlbum = albumService.createAlbum(convertToEntity(albumDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAlbum);
        } catch (DataIntegrityViolationException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage); //400
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage); //500
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable Long id) {
        Optional<Album> album = albumService.getAlbumById(id);
        return album.map(this::convertToDTO).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<AlbumDTO> getAllAlbums() {
        return albumService.getAllAlbums().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAlbum(@PathVariable Long id, @RequestBody AlbumDTO albumDTO) {
        try {
            Album updatedAlbum = albumService.updateAlbum(id, convertToEntity(albumDTO));
            return ResponseEntity.ok(updatedAlbum);
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
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }

    private Album convertToEntity(AlbumDTO albumDTO) {
        Album album = new Album();
        album.setName(albumDTO.getName());
        album.setId(albumDTO.getId());
        album.setImage(albumDTO.getImage());

        if (albumDTO.getArtist() != null) {
            Artist artist = new Artist();
            artist.setId(albumDTO.getArtist().getId());
            artist.setName(albumDTO.getArtist().getName());
            artist.setDescription(albumDTO.getArtist().getDescription());
            artist.setPhoto(albumDTO.getArtist().getPhoto());
            artist.setListeningSessions(albumDTO.getArtist().getListeningSessions());
            album.setArtist(artist);
        }
        if (albumDTO.getTracksId() != null) {
            List<Track> tracks = albumDTO.getTracksId().stream().map(trackId -> trackService.getTrackById(trackId).orElseGet(null)).collect(Collectors.toList());
            album.setTracks(tracks);
        }

        return album;
    }

    private AlbumDTO convertToDTO(Album album) {
        AlbumDTO albumDTO = new AlbumDTO();
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setName(album.getArtist().getName());
        List<Long> tracks = album.getTracks().stream().map(Track::getId).collect(Collectors.toList());

        albumDTO.setName(album.getName());
        albumDTO.setId(album.getId());
        albumDTO.setArtist(artistDTO);
        albumDTO.setImage(album.getImage());
        albumDTO.setTracksId(tracks);
        return albumDTO;
    }
}
