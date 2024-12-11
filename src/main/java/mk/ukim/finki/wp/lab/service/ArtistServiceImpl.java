package mk.ukim.finki.wp.lab.service;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArtistServiceImpl implements ArtistService{

    private final ArtistRepository artistRepository;

    @Override
    public List<Artist> listArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist findById(Long id) throws Exception {
        return artistRepository.findById(id).orElseThrow(Exception::new);
    }
}
