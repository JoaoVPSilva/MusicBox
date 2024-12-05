package com.music.demo;

import com.music.musicbox.MusicBoxApplication;
import com.music.musicbox.service.MusicBoxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testng.Assert;
import reactor.core.publisher.Mono;

@SpringBootTest(classes = MusicBoxApplication.class)
@ActiveProfiles("test")
public class MusicBoxServiceTest {

    @Autowired
        private MusicBoxService musicBoxService;

        @BeforeEach
        public void setUp(){

        }

        @Test
        public void testSearchArtistApi(){

            String artistName = "The Beatles";

            String resultFromService =  musicBoxService.searchArtistByName(artistName).block();

            Assert.assertNotNull(resultFromService);

    }

}
