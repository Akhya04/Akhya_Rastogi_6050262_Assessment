package com.cg.MovieTrackAPI.repo;

import com.cg.MovieTrackAPI.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
// Added <Track, Long> here
public interface TrackRepository extends JpaRepository<Track, Long> {
    List<Track> findByTitle(String title);
}