import java.util.concurrent.locks.*;
import java.util.HashMap;

public class Monitor1 {
    protected SubsekvensRegister subsekvensRegister;
    protected Lock laas = new ReentrantLock();

    public Monitor1(SubsekvensRegister subsekvensRegister) {
        this.subsekvensRegister = subsekvensRegister;
    }

    public HashMap<String, Subsekvens> hent() {
        return subsekvensRegister.hent();
    }

    public int stoerrelse() {
        return subsekvensRegister.stoerrelse();
    }

    public void settInn(HashMap<String, Subsekvens> hashMap) {
        laas.lock();

        try {
            subsekvensRegister.settInn(hashMap);
        } finally {
            laas.unlock();
        }
    }

    public static HashMap<String, Subsekvens> lesFil(String filnavn) {
        return SubsekvensRegister.lesFil(filnavn);
    }

    public static HashMap<String, Subsekvens> slaaSammen(HashMap<String, Subsekvens> hashMap1, HashMap<String, Subsekvens> hashMap2) {
        return SubsekvensRegister.slaaSammen(hashMap1, hashMap2);
    }


}