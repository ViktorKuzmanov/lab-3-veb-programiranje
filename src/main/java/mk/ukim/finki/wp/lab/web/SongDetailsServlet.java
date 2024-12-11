package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "SongDetailsServlet", urlPatterns = "/songDetails1")
@AllArgsConstructor
public class SongDetailsServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final SongService songService;
    private final ArtistService artistService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String trackId = req.getParameter("trackId");
        Long artistId = Long.parseLong(req.getParameter("artistId"));

        Artist artist = null;
        try {
            artist = artistService.findById(artistId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Song song = songService.findByTrackId(trackId);

        songService.addArtistToSong(artist,song);

        context.setVariable("song", song);

        springTemplateEngine.process("songDetails.html", context, resp.getWriter());
    }
}
