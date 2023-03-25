package restraunt;

import lombok.Getter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time extends Thread {
    @Getter
    private long currentTime;
    private long startTime;
    private long endTime;
    private long realTime;
    private int speed;

    private Thread workingThread;

    public Time() throws ParseException {
        String DEFAULT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
        DateFormat formatter = new SimpleDateFormat(DEFAULT_PATTERN);
        currentTime = formatter.parse(Configurations.startTime).getTime();
        startTime = formatter.parse(Configurations.startTime).getTime();
        endTime = formatter.parse(Configurations.endTime).getTime();
        realTime = new Date().getTime();
        speed = Configurations.speed;
    }

    @Override
    public void run() {
        while (currentTime < endTime) {
            var time = new Date().getTime();
            currentTime = startTime + (time - realTime) * speed;
        }
    }
}
