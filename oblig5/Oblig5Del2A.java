import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Oblig5Del2A {
    public static void main(String[] args) {
        ArrayList<String> filer = new ArrayList<>();

        if (args.length < 1) {
            System.err.println("Du maa oppgi filnavn!");
            System.exit(1);
        }

        Scanner scFil;

        try {
            scFil = new Scanner(new File(args[0] + "/metadata.csv"));
        } catch (FileNotFoundException e) {
            System.err.println("Kunne ikke aapne mappen " + args[0] + "!");
            System.exit(2);
            return;
        }

        while (scFil.hasNextLine()) {
            String filnavn = args[0] + "/" + scFil.nextLine().split(",")[0];
            if (filnavn.contains(".csv")) filer.add(filnavn);
        }

        scFil.close();
        Monitor1 monitor1 = new Monitor1(new SubsekvensRegister());
        CountDownLatch barriere = new CountDownLatch(filer.size());

        for (String fil : filer) {
            new Thread(new LeseTrad(monitor1, fil, barriere)).start();
        }

        try {
            barriere.await();
        } catch (InterruptedException e) {
            System.err.println("Traad forstyrret!");
            System.exit(5);
        }

        HashMap<String, Subsekvens> fullHashMap = new HashMap<>();
        int antHashMaps = monitor1.stoerrelse();

        for (int i = 0; i < antHashMaps; i++) {
            fullHashMap = SubsekvensRegister.slaaSammen(fullHashMap, monitor1.hent());
        }

        String storst = null;

        for (String k : fullHashMap.keySet()) {
            if (storst == null) storst = k;
            else if (fullHashMap.get(k).hentAntall() > fullHashMap.get(storst).hentAntall()) storst = k;
        }

        System.out.println(fullHashMap.get(storst));
    }
}