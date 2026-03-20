package com.cg.MovieTrackAPI.web;

import com.cg.MovieTrackAPI.entity.Track;
import com.cg.MovieTrackAPI.repo.TrackRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tracks")
public class TrackController {

    @Autowired
    private TrackRepository trackRepository;

    @PostMapping
    public ResponseEntity<String> addTrack(@RequestBody Track track) {
        trackRepository.save(track);
        return new ResponseEntity<>("Track added successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Track>> getTracks() {
        List<Track> tracks = trackRepository.findAll();
        return new ResponseEntity<>(tracks, HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Track>> getTracksByTitle(@PathVariable String title) {
        List<Track> tracks = trackRepository.findByTitle(title);
        return new ResponseEntity<>(tracks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTrack(@PathVariable Integer id) {
        Optional<Track> track = trackRepository.findById(Long.valueOf(id));
        if (track.isPresent()) {
            return new ResponseEntity<>(track.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Track not found", HttpStatus.NOT_FOUND);
    }
}