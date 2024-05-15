package culturemedia.service;
import culturemedia.model.*;
import java.util.List;

public interface CultureMediaService 
{

    
    Video add(Video video);
    View add(View view);
    List<Video> ListAllVideos();

}