package mk.ukim.finki.wp.lab.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.model.exceptions.IdNotFoundException;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Transactional
    @Override
    public void addArtistToSong(Artist artist, Song song) {
        song.getPerformers().add(artist);
        songRepository.save(song);
    }

    @Override
    public Song update(Long songId, String title, String trackId, String genre, Integer releaseYear, Long albumId) {

        Song song = findById(songId).get();
        song.setTitle(title);
        song.setTrackId(trackId);
        song.setGenre(genre);
        song.setReleaseYear(releaseYear);

        Album album = this.albumRepository.findAlbumById(albumId);
        song.setAlbum(album);
        return songRepository.save(song);
    }

    @Override
    public List<Song> findSongsByAlbumId(Long albumId) {
        return this.songRepository.findAllByAlbumId(albumId);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public Song save(String trackId, String title, String genre, Integer releaseYear, Album album) {
        Song song = new Song(trackId, title, genre, releaseYear, album);
        return songRepository.save(song);
    }
    @Override
    public Optional<Song> findById(Long Id) {
        Optional<Song> song = songRepository.findById(Id);
        if(song.isEmpty()) {
            throw new IdNotFoundException();
        } else {
            return song;
        }
    }

    @Override
    public void deleteById(Long id) {
        this.songRepository.deleteById(id);
    }
}
