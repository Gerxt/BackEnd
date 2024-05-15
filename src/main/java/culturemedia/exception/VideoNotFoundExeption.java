package culturemedia.exception;

import java.text.MessageFormat;


public class VideoNotFoundExeption extends CultureMediaException{
    public VideoNotFoundExeption() {
        super("Video not found");
    }
    public VideoNotFoundExeption(String title) {
        super(MessageFormat.format("The video {0} was not found", title));
    }
}