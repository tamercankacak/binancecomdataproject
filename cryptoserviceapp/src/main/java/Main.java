import java.util.Timer;

public class Main {
    public static void main(String[] args) {
        try {
            Timer timer = new Timer();
            SchedulerLayer _schlayer = new SchedulerLayer();
            timer.scheduleAtFixedRate(_schlayer, 0, 5 * 1 * 1000);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }
}
