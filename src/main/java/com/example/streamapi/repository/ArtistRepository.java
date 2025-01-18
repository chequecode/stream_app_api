package com.example.streamapi.repository;

import com.example.streamapi.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

}
