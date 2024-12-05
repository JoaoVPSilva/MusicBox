package com.music.musicbox.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class MusicBoxService {

    private static final Logger log = LoggerFactory.getLogger(MusicBoxService.class);

    @Autowired
    private final WebClient.Builder webClientBuilder;

    private final String musicBrainzApiUrl;

    public MusicBoxService(WebClient.Builder webClientBuilder, @Value("${musicbrainz.api.url}") String musicBrainzApiUrl) {
        this.webClientBuilder = webClientBuilder;
        this.musicBrainzApiUrl = musicBrainzApiUrl;
    }

    public Mono<String> searchArtistByName(String artistName) {

        return webClientBuilder.baseUrl(musicBrainzApiUrl)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("artist")
                        .queryParam("query", "artist:" + artistName)
                        .queryParam("fmt", "json")
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> searchMusicByName(String musicName) {

        return webClientBuilder.baseUrl(musicBrainzApiUrl)

                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("recording")
                        .queryParam("query", "recording:" + musicName)
                        .queryParam("fmt", "json")
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }



}
