package restraunt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

// PUSH TO ACIDNAYA
public class Main {
    public static Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        RestaurantSimulation rs = new RestaurantSimulation();
        rs.getResources();
        // Тут смотрела как треды работают, сделала эээ класс времени, чтобы он с ускорением считал.
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone(Configurations.timezone));
         try {
             var t = new Time();
             t.start();
             for (int i = 0; i < 10; ++i) {
                 String dateFormatted = formatter.format(t.getCurrentTime());
                 System.out.println("time: " + dateFormatted);
                 Thread.sleep(500);
             }
         } catch (ParseException e) {
             throw new RuntimeException(e);
         } catch (InterruptedException e) {
             throw new RuntimeException(e);
         }
    }
}