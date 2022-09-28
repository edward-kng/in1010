import java.util.concurrent.CountDownLatch;

public class LeseTrad implements Runnable {
    private Monitor1 monitor;
    private String filnavn;
    private CountDownLatch barriere;

    public LeseTrad(Monitor1 monitor, String filnavn, CountDownLatch barriere) {
        this.monitor = monitor;
        this.filnavn = filnavn;
        this.barriere = barriere;
    }

    @Override
    public void run() {
        monitor.settInn(Monitor1.lesFil(filnavn));
        barriere.countDown();
        
        if (monitor instanceof Monitor2) {
            ((Monitor2) monitor).oppdaterLeseStatus(barriere);
        }
    }
}