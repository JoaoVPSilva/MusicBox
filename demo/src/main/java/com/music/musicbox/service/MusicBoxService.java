package com.music.musicbox.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final WebClient.Builder webClientBuilder;

    private final String musicBrainzApiUrl;

    public MusicBoxService(WebClient.Builder webClientBuilder, @Value("${musicbrainz.api.url}") String musicBrainzApiUrl) {
        this.webClientBuilder = webClientBuilder;
        this.musicBrainzApiUrl = musicBrainzApiUrl;
    }

    public List<String> searchArtistByName(String artistName) {

        Mono<String> apiSearch = webClientBuilder.baseUrl(musicBrainzApiUrl)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("artist")
                        .queryParam("query", "artist:" + artistName)
                        .queryParam("fmt", "json")
                        .build())
                .retrieve()
                .bodyToMono(String.class);

        Mono<List<String>> apiData = apiSearch.flatMap(jsonString -> {
            ObjectMapper objectMapper = new ObjectMapper();
            try{
                List<String> artistList = objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructCollectionType(List.class, String.class));
                return  Mono.just(artistList);
            }catch (Exception ex){
                return Mono.error(ex);
            }
        });
        processList(apiData);
    }

    public void processList (Mono<List<String>> apiData){

        apiData.subscribe(
                artistList -> {
                    System.out.println("Lista Recebida: " + artistList);
                    clearList(artistList);
                },
                error -> System.out.println("error" + error)
        );
    }

    public List<String> clearList(List<String> list){

        System.out.println("");

        return;
    }


}
