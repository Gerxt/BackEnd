package culturemedia.service;
import culturemedia.exception.VideoNotFoundExeption;
import culturemedia.model.*;
import java.util.List;

public interface CultureMediaService 
{

    Video add(Video video);
    View add(View view);

    List<Video> ListAllVideos() throws VideoNotFoundExeption;
    List<Video> find(String title) throws VideoNotFoundExeption;
    List<Video> find(double fromDuration, double toDuration) throws VideoNotFoundExeption;
    
}