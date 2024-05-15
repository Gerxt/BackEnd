package culturemedia.exception;
import java.text.MessageFormat;

public class DurationNotValidExeption extends CultureMediaException{
    public DurationNotValidExeption(String title, Double duration) {
        super(MessageFormat.format("The duration is not valid for video: {0} with duration: {1} ", title, duration));
    }
}