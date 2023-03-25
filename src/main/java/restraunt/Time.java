package restraunt;

import lombok.Getter;
import restraunt.agent.Agent;
import restraunt.agent.Message;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Time extends Agent {
    public DateFormat formatter;
    @Getter
    private volatile long currentTime;
    private long startTime;
    private long endTime;
    private long realTime;
    private int speed;

    private Thread workingThread;

    public Time() {
//        formatter = new SimpleDateFormat("HH:mm:ss.SSS");
//        formatter.setTimeZone(TimeZone.getTimeZone(Configurations.timezone));
//        String DEFAULT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
        formatter = new SimpleDateFormat(Configurations.format);
        try {
            currentTime = formatter.parse(Configurations.startTime).getTime();
            startTime = formatter.parse(Configurations.startTime).getTime();
            endTime = formatter.parse(Configurations.endTime).getTime();
        } catch (ParseException e) {
            currentTime = 0;
            startTime = 0;
            endTime = 0;
        }
        realTime = new Date().getTime();
        speed = Configurations.speed;
    }

    public boolean isTimeToAct(long agentTime) { // мб кэширует дебил???
        // System.out.println("<><><>: " + Main.restaurant.time.timeToString(currentTime));
        return currentTime > agentTime;
    }

    public String timeToString(long t) {
        return formatter.format(t);
    }

    @Override
    protected void proceed(Message message) throws Exception {

    }

    @Override
    public void run() {
        while (currentTime < endTime) {
            var time = new Date().getTime();
            currentTime = startTime + (time - realTime) * speed;
        }
    }
}
