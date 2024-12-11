package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    List<Song> listSongs();
    void addArtistToSong(Artist artist, Song song);
    Song findByTrackId(String trackId);
    Optional<Song> findById(Long Id);
    void deleteById(Long id);
    Song save(String trackId, String title, String genre, Integer releaseYear, Album album);
    Song update(Long songId, String title, String trackId, String genre, Integer releaseYear, Long albumId);
}
