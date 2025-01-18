package com.example.streamapi.repository;

import com.example.streamapi.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

}
