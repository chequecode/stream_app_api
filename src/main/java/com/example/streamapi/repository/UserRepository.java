package com.example.streamapi.repository;

import com.example.streamapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
