package com.ced.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ced.app.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    
}
