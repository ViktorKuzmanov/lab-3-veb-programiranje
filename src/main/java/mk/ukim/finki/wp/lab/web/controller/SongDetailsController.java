package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.WebContext;

import java.util.List;

@Controller
public class SongDetailsController {

    private final SongService songService;
    private final ArtistService artistService;

    public SongDetailsController(SongService songService, ArtistService artistService) {
        this.songService = songService;
        this.artistService = artistService;
    }

    @PostMapping("/songDetails")
    public String getSongDetailsPage(@RequestParam String trackId,
                                     @RequestParam Long artistId,
                                     Model model) throws Exception {
        Artist artist = artistService.findById(artistId);
        Song song = songService.findByTrackId(trackId);
        songService.addArtistToSong(artist,song);
        System.out.println("v2");
        model.addAttribute("song", song);
        return "songDetails";
    }
}
