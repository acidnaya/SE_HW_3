package restraunt.agents;

import lombok.Getter;
import restraunt.Config;
import restraunt.Main;
import restraunt.agents.Agent;
import restraunt.messages.Message;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time extends Agent {
    public static String now = "";
    private DateFormat formatter;
    @Getter
    private volatile long currentTime;
    private long startTime;
    private long endTime;
    private long realTime;
    private int speed;

    private Thread workingThread;

    public Time() {
        formatter = new SimpleDateFormat(Config.format);
        try {
            currentTime = formatter.parse(Config.startTime).getTime();
            startTime = formatter.parse(Config.startTime).getTime();
            endTime = formatter.parse(Config.endTime).getTime();
        } catch (ParseException e) {
            currentTime = 0;
            startTime = 0;
            endTime = 0;
        }
        realTime = new Date().getTime();
        speed = Config.speed;
    }

    public boolean isTimeToAct(long agentTime) {
        return currentTime > agentTime;
    }

    public Date getCurrentDate() throws ParseException {
        return formatter.parse(timeToString(currentTime));
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
            now = timeToString(currentTime);
        }
        AgentRepository.remove(this);
        AgentRepository.stopAll();
        Main.restaurant.writeLog();
        stop(this);
    }
}
