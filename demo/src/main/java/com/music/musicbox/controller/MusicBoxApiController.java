package com.music.musicbox.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.musicbox.entity.Name;
import com.music.musicbox.service.MusicBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/findBy")
public class MusicBoxApiController {

    @Autowired
    private final MusicBoxService musicBoxService;

    public MusicBoxApiController(MusicBoxService musicBoxService) {
        this.musicBoxService = musicBoxService;
    }

    @GetMapping("/name")
    public Mono<String> searchArtist(@RequestBody String name) {
        System.out.println(name);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Name usuario = objectMapper.readValue(name, Name.class);
            System.out.println(usuario.getName());
            return musicBoxService.searchArtistByName(usuario.getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/musicname")
    public Mono<String> searchMusic(@RequestBody String name) {
        System.out.println(name);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Name usuario = objectMapper.readValue(name, Name.class);
            System.out.println(usuario.getName());

            return musicBoxService.searchMusicByName(usuario.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
