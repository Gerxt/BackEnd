package culturemedia.service;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import culturemedia.model.Video;

import static org.junit.jupiter.api.Assertions.assertEquals;
import culturemedia.repository.impl.*;

import culturemedia.exception.VideoNotFoundExeption;
import static org.junit.jupiter.api.Assertions.assertThrows;

import culturemedia.service.impl.CultureMediaServiceImpl;

class CultureMediaServiceTest 
{

    private CultureMediaService cultureMediaService;

    Video VideoA = new Video("100", "The Lion King", "_ _ _ _ _", 2.2);
    Video VideoB = new Video("200", "Finding Nemo", "_ _ _ _ _", 3.3);
    Video VideoC = new Video("300", "How to Train Your Dragon", "_ _ _ _ _", 4.4);
    Video VideoD = new Video("400", "The Wind Rises", "_ _ _ _ _", 5.5);
    Video VideoE = new Video("500", "The LEGO Movie", "_ _ _ _ _", 6.6);
    Video VideoF = new Video("500", "Song of the Sea", "_ _ _ _ _", 7.7);

   
    @BeforeEach
    void init() {
        VideoRepository videoRepository = new VideoRepositoryImpl();
        ViewsRepository viewsRepository = new ViewsRepositoryImpl();
        cultureMediaService = new CultureMediaServiceImpl(videoRepository, viewsRepository);

       

    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        assertThrows(VideoNotFoundExeption.class, () -> {
            cultureMediaService.ListAllVideos();
        });
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() {
        List<Video> videos = List.of( VideoA, VideoB, VideoC, VideoD, VideoE, VideoF);
        
        for ( Video video : videos ) {
            cultureMediaService.add( video );
        }

        try 
        {
            List<Video> Videos = cultureMediaService.ListAllVideos();
            assertEquals(6, Videos.size());
        } catch (VideoNotFoundExeption e) {
            assert(false);
        }
    }

    @Test
    void when_you_search_by_title_and_it_is_not_found_exception_VideoNotFoundExeption() {
        assertThrows(VideoNotFoundExeption.class, () -> {
            cultureMediaService.find("better luck for the next");
        });
    }

    @Test
    void when_you_search_by_title_and_you_are_successful() {
        List<Video> videos = List.of(VideoA, VideoB, VideoC, VideoD, VideoE, VideoF);
        
        for ( Video video : videos ) {
            cultureMediaService.add( video );
        }

        try {
            List<Video> Videos = cultureMediaService.find("The");
            assertEquals(3, Videos.size());
            
        } catch (VideoNotFoundExeption e) {
            assert(false);
        }    
    }



    @Test 
    void when_you_search_by_forDuration_and_it_is_not_found_exception_VideoNotFoundExeption() {
        assertThrows(VideoNotFoundExeption.class, () -> {
            cultureMediaService.find(0.002, 0.3);
        });
    }

    @Test
    void when_find_forDuration_should_be_returned_succesfully() {
        List<Video> videos = List.of(VideoA, VideoB, VideoC, VideoD, VideoE, VideoF);
        
        for ( Video video : videos ) {
            cultureMediaService.add( video );
        }

        try {
            List<Video> Videos = cultureMediaService.find(3.3, 6.6);
            assertEquals(4, Videos.size());
        } catch (VideoNotFoundExeption e) {
            assert(false);
        }
    }

}