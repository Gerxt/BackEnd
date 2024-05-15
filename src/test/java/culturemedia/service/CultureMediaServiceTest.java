package culturemedia.service;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import culturemedia.model.Video;

import static org.junit.jupiter.api.Assertions.assertEquals;
import culturemedia.repository.impl.*;

import culturemedia.exception.VideoNotFoundExeption;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import culturemedia.service.CultureMediaService;
import culturemedia.service.impl.CultureMediaServiceImpl;


import culturemedia.repository.*;




class CultureMediaServiceTest 
{
    private VideoRepository videoRepository = Mockito.mock();
    private CultureMediaService cultureMediaService;

    Video VideoA = new Video("100", "The Lion King", "_ _ _ _ _", 2.2);
    Video VideoB = new Video("200", "Finding Nemo", "_ _ _ _ _", 3.3);
    Video VideoC = new Video("300", "How to Train Your Dragon", "_ _ _ _ _", 4.4);
    Video VideoD = new Video("400", "The Wind Rises", "_ _ _ _ _", 5.5);
    Video VideoE = new Video("500", "The LEGO Movie", "_ _ _ _ _", 6.6);
    Video VideoF = new Video("500", "Song of the Sea", "_ _ _ _ _", 7.7);

   
    @BeforeEach
    void init() {
        
        ViewsRepository viewsRepository = new ViewsRepositoryImpl();
        cultureMediaService = new CultureMediaServiceImpl(videoRepository, viewsRepository);


    }

    private void mockFindAll(List <Video> videos){
        doReturn(videos)
        .when(videoRepository)
        .findAll();
    }

    private void mockFind(String title, List<Video> videos){
        doReturn(videos)
        .when(videoRepository)
        .find(eq(title));
    }

    private void mockFindTime(Double fromDuration, Double toDuration, List<Video> videos){
        doReturn(videos)
        .when(videoRepository)
        .find(eq(fromDuration), eq(toDuration));
    }

    

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        mockFindAll(Collections.emptyList());
        assertThrows(VideoNotFoundExeption.class, () -> 
        {
        cultureMediaService.ListAllVideos();
        });
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundExeption{
        
       
            mockFindAll(List.of( VideoA, VideoB, VideoC, VideoD, VideoE, VideoF));
            
            List<Video> videos = cultureMediaService.ListAllVideos();
            assertEquals(6, videos.size());
    }

    @Test
    void when_you_search_by_title_and_it_is_not_found_exception_VideoNotFoundExeption() {
        mockFind(null, Collections.emptyList());
        assertThrows(VideoNotFoundExeption.class, () -> {
            cultureMediaService.find("better luck for the next");
        });
    }

    @Test
    void when_you_search_by_forDuration_and_it_is_not_found_exception_VideoNotFoundExeption() {
        mockFindTime(null, null, Collections.emptyList());
        assertThrows(VideoNotFoundExeption.class, () -> {
            cultureMediaService.find(0.1, 0.3);
        });
    }


    @Test
    void when_find_forDuration_should_be_returned_succesfully()  throws VideoNotFoundExeption{
        mockFindTime(0.0, 7.7, List.of( VideoA, VideoB, VideoC, VideoD, VideoE, VideoF));
        List<Video> videos = cultureMediaService.find(0.0, 7.7);
        assertEquals(6, videos.size());
    }

    @Test
    void when_find_forTitle_should_be_returned_succesfully() throws VideoNotFoundExeption{

        mockFind("Sea", List.of(VideoF));

        List<Video> videos = cultureMediaService.find("Sea");
        assertEquals(1, videos.size());
        assertEquals(VideoF, videos.get(0));
    }

   

}