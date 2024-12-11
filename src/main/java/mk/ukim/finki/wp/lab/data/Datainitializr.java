package mk.ukim.finki.wp.lab.data;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import java.util.ArrayList;
import java.util.List;

@Component
public class Datainitializr {

    public static List<Artist> artists = null;
    public static List<Song> songs = null;

    public static List<Album> albums = null;

    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    public Datainitializr(ArtistRepository artistRepository, SongRepository songRepository, AlbumRepository albumRepository) {
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    @PostConstruct
    public void init(){
        artists = new ArrayList<>();
        if(this.artistRepository.count()==0){
            for (int i = 0; i < 5; i++) {
                Artist artist = new Artist("ImeA" + i, "PrezimeA" + i, "BioA" + i);
                artists.add(artist);
            }
            this.artistRepository.saveAll(artists);
        }

        albums = new ArrayList<>();
        if(this.albumRepository.count()==0){
            for (int i = 0; i < 5; i++) {
                Album album = new Album("AlbumIme" + (i + 1), "AlbumGenre" + + (i + 1), "" + (i + 1));
                albums.add(album);
            }
            this.albumRepository.saveAll(albums);
        }

        songs = new ArrayList<>();
        if(this.songRepository.count()==0){
            for (int i = 0; i < 5; i++) {
                Song song = new Song(String.valueOf(i + 1), "ImeS" + i, "ZanrS" + i, i, albumRepository.findAll().get(i));
                songs.add(song);
            }
            this.songRepository.saveAll(songs);
        }


    }
}
