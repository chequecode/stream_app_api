package com.example.streamapi.controller;

import com.example.streamapi.DTO.AlbumDTO;
import com.example.streamapi.DTO.ArtistDTO;
import com.example.streamapi.DTO.TrackDTO;
import com.example.streamapi.entities.Album;
import com.example.streamapi.entities.Artist;
import com.example.streamapi.entities.Track;
import com.example.streamapi.errors.ErrorMessage;
import com.example.streamapi.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @PostMapping
    public ResponseEntity<Object> createTrack(@RequestBody TrackDTO trackDTO) {
        try {
            Track createdTrack = trackService.createTrack(convertToEntity(trackDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTrack);
        } catch (DataIntegrityViolationException e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage); //400
        } catch (Exception e) {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage); //500
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackDTO> getTrackById(@PathVariable Long id) {
        Optional<Track> track = trackService.getTrackById(id);
        return track.map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<TrackDTO> getAllTracks() {
        return trackService.getAllTracks().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTrack(@PathVariable Long id, @RequestBody TrackDTO trackDTO) {
        try {
            Track updatedTrack = trackService.trackUpdate(id, convertToEntity(trackDTO));
            return ResponseEntity.ok(updatedTrack);
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
    public ResponseEntity<Void> deleteTrack(@PathVariable Long id) {
        trackService.deleteTrack(id);
        return ResponseEntity.noContent().build();
    }

    private Track convertToEntity(TrackDTO trackDTO) {
        Track track = new Track();
        track.setId(trackDTO.getId());
        track.setName(trackDTO.getName());
        track.setMp3(trackDTO.getMp3());
        track.setListeningSessions(trackDTO.getListeningSessions());
        track.setDuration(trackDTO.getDuration());
        track.setText(trackDTO.getText());

        if (trackDTO.getArtist() != null) {
            Artist artist = new Artist();
            artist.setId(trackDTO.getArtist().getId());
            artist.setName(trackDTO.getArtist().getName());
            artist.setDescription(trackDTO.getArtist().getDescription());
            artist.setPhoto(trackDTO.getArtist().getPhoto());
            artist.setListeningSessions(trackDTO.getArtist().getListeningSessions());
            track.setArtist(artist);
        }
        if (trackDTO.getAlbum() != null) {
            Album album = new Album();
            album.setId(trackDTO.getAlbum().getId());
            album.setName(trackDTO.getAlbum().getName());
            album.setImage(trackDTO.getAlbum().getImage());
            track.setAlbum(album);
        } else track.setAlbum(null);
        return track;
    }

    private TrackDTO convertToDTO(Track track) {
        TrackDTO trackDTO = new TrackDTO();
        AlbumDTO albumDTO = new AlbumDTO();
        ArtistDTO artistDTO = new ArtistDTO();

        albumDTO.setId(track.getAlbum().getId());
        artistDTO.setId(track.getArtist().getId());

        trackDTO.setAlbum(albumDTO);
        trackDTO.setArtist(artistDTO);
        trackDTO.setId(track.getId());
        trackDTO.setName(track.getName());
        trackDTO.setMp3(track.getMp3());
        trackDTO.setListeningSessions(track.getListeningSessions());
        trackDTO.setDuration(track.getDuration());
        trackDTO.setText(track.getText());
        return trackDTO;
    }
}
