package culturemedia.service.impl;

import java.util.List;
import culturemedia.service.CultureMediaService;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import culturemedia.model.Video;
import culturemedia.model.View;


public class CultureMediaImpl implements CultureMediaService
{
    private VideoRepository videoRepository;
    private ViewsRepository viewRepository;

    public CultureMediaImpl(VideoRepository videoRepository, ViewsRepository viewRepository)
    {
        this.videoRepository = videoRepository;
        this.viewRepository = viewRepository;
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
        View viewAdd = viewRepository.save(view);
        return viewAdd;
    }

    @Override
    public List<Video> ListAllVideos() 
    {
        List<Video> videos = videoRepository.findAll();
        return videos;
    }
    
}