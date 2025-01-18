package com.example.streamapi.service;

import com.example.streamapi.entities.Track;
import com.example.streamapi.entities.User;
import com.example.streamapi.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import java.util.List;
import java.util.Optional;

@Service
public class TrackService {

    @Autowired
    private TrackRepository trackRepository;

    public Track trackUpdate(long id, Track trackDetails) {
        Track track = trackRepository.findById(id).orElseThrow(() -> new RuntimeException("track not found"));
        track.setAlbum(trackDetails.getAlbum());
        track.setArtist(trackDetails.getArtist());
        track.setDuration(trackDetails.getDuration());
        track.setText(trackDetails.getText());
        track.setListeningSessions(trackDetails.getListeningSessions());
        track.setMp3(trackDetails.getMp3());
        track.setId(trackDetails.getId());
        track.setName(trackDetails.getName());
        return trackRepository.save(track);
    }

    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }
    public Optional<Track> getTrackById(long id) {
        return trackRepository.findById(id);
    }
    public void deleteTrack(Long id) {
        Track track = trackRepository.findById(id).orElseThrow(() -> new RuntimeException("track not found"));
        trackRepository.delete(track);
    }
    public Track createTrack(Track track) {
        return trackRepository.save(track);
    }
}
