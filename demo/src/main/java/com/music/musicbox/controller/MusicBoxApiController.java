package com.music.musicbox.controller;

import com.music.musicbox.service.MusicBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/findBy")
public class MusicBoxApiController {

    @Autowired
    private final MusicBoxService musicBoxService;

    @Autowired
    public MusicBoxApiController(MusicBoxService musicBoxService) {
        this.musicBoxService = musicBoxService;
    }

    @GetMapping("/{name}")
    public Mono<String> searchArtist(@RequestParam String name){
        return musicBoxService.searchArtistByName(name);
    }
}
