package culturemedia.service.impl;

import java.util.List;

import org.mockito.internal.matchers.Null;

import culturemedia.service.CultureMediaService;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import culturemedia.model.Video;
import culturemedia.model.View;

import culturemedia.exception.VideoNotFoundExeption;

public class CultureMediaServiceImpl implements CultureMediaService{
    private VideoRepository videoRepository;
    private ViewsRepository viewsRepository;

    public CultureMediaServiceImpl(VideoRepository videoRepository, ViewsRepository viewsRepository) 
    {
        this.videoRepository = videoRepository;
        this.viewsRepository = viewsRepository;
    }

    @Override
    public Video add(Video video)
    {
        Video videoAdd = videoRepository.save(video);
        return videoAdd;
    }

    @Override
    public View add(View view)
    {
        View viewAdd = viewsRepository.save(view);
        return viewAdd;
    }

    @Override
    public List<Video> ListAllVideos() throws VideoNotFoundExeption
     {
    
        List<Video> videos = videoRepository.findAll();
        if(videos.isEmpty())

        {
            throw new VideoNotFoundExeption();
        }

        else

        {
            return videos;
        }
    }
}