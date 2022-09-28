import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class FletteTrad implements Runnable {
    private Monitor2 monitor;
    private CountDownLatch barriere;

    public FletteTrad(Monitor2 monitor, CountDownLatch barriere) {
        this.monitor = monitor;
        this.barriere = barriere;
    }

    @Override
    public void run() {
        ArrayList<HashMap<String, Subsekvens>> maps = monitor.hentUtTo();

        while (maps != null) {
            HashMap<String, Subsekvens> nyHashMap = Monitor2.slaaSammen(maps.get(0), maps.get(1));
            monitor.settInn(nyHashMap);
            maps = monitor.hentUtTo();
        }

        barriere.countDown();
    }
} 