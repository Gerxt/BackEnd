package culturemedia.service;
import culturemedia.exception.VideoNotFoundExeption;
import culturemedia.model.*;
import java.util.List;

public interface CultureMediaService 
{
    List<Video> ListAllVideos() throws VideoNotFoundExeption;

    Video add(Video video);
    View add(View view);
    

}