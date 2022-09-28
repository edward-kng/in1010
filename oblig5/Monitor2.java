import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.*;
import java.util.HashMap;
import java.util.ArrayList;

public class Monitor2 extends Monitor1 {
    private Condition harToEllerFerdig = laas.newCondition();
    private boolean lestFerdig = false;

    public Monitor2(SubsekvensRegister subsekvensRegister) {
        super(subsekvensRegister);
    }

    @Override
    public void settInn(HashMap<String, Subsekvens> hashMap) {
        laas.lock();

        try {
            subsekvensRegister.settInn(hashMap);
        } finally {
            if (subsekvensRegister.stoerrelse() >= 2) harToEllerFerdig.signalAll();
            laas.unlock();
        }
    }

    @Override
    public HashMap<String, Subsekvens> hent() {
        laas.lock();
        
        try {
            return super.hent();
        } finally {
            laas.unlock();
        }
    }

    public ArrayList<HashMap<String, Subsekvens>> hentUtTo() {
        laas.lock();

        try {
            while (stoerrelse() < 2 && !lestFerdig) {
                harToEllerFerdig.await();
            }

            if (lestFerdig && stoerrelse() < 2) return null;

            ArrayList<HashMap<String, Subsekvens>> maps = new ArrayList<>();
            maps.add(subsekvensRegister.hent());
            maps.add(subsekvensRegister.hent());
            return maps;
        } catch (InterruptedException e) {
            System.err.println("Traad forstyrret!");
            System.exit(5);
            return null;
        } finally {
            laas.unlock();
        }
    }

    public void oppdaterLeseStatus(CountDownLatch barriere) {
        laas.lock();
        
        try {
            if (barriere.getCount() <= 8 && !lestFerdig) {
                lestFerdig = true;
            }
        } finally {
            if (lestFerdig && stoerrelse() < 2) harToEllerFerdig.signalAll();
            laas.unlock();
        }
    }
}