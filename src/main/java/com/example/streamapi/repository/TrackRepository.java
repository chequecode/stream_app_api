package com.example.streamapi.repository;

import com.example.streamapi.entities.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

}


