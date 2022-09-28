import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.Scanner;
import java.util.Set;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Oblig5Hele {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Du maa oppgi filnavn!");
            System.exit(1);
        }

        ArrayList<String> filerTrue = new ArrayList<>();
        ArrayList<String> filerFalse = new ArrayList<>();
        Scanner scFil;

        try {
            scFil = new Scanner(new File(args[0] + "/metadata.csv"));
        } catch (FileNotFoundException e) {
            System.err.println("Kunne ikke aapne mappen " + args[0] + "!");
            System.exit(2);
            return;
        }

        while (scFil.hasNextLine()) {
            String[] data = scFil.nextLine().split(",");
            String filnavn = args[0] + "/" + data[0];

            if (filnavn.contains(".csv")) {
                Boolean harHattSykdommen = Boolean.parseBoolean(data[1]);
                if (harHattSykdommen) filerTrue.add(filnavn);
                else filerFalse.add(filnavn);
            }
        }

        scFil.close();
        Monitor2 monitorTrue = new Monitor2(new SubsekvensRegister());
        Monitor2 monitorFalse = new Monitor2(new SubsekvensRegister());
        CountDownLatch barriereTrue = new CountDownLatch(filerTrue.size() + 8);
        CountDownLatch barriereFalse = new CountDownLatch(filerFalse.size() + 8);

        for (String fil : filerTrue) {
            new Thread(new LeseTrad(monitorTrue, fil, barriereTrue)).start();;
        }

        for (String fil : filerFalse) {
            new Thread(new LeseTrad(monitorFalse, fil, barriereFalse)).start();;
        }

        for (int i = 0; i < 8; i++) {
            new Thread(new FletteTrad(monitorTrue, barriereTrue)).start();
            new Thread(new FletteTrad(monitorFalse, barriereFalse)).start();
        }

        try {
            barriereFalse.await();
            barriereTrue.await();
        } catch (InterruptedException e) {
            System.err.println("Traad forstyrret!");
            System.exit(5);
        }

        String forekommerOftest = null;
        int storsteForskjell = 0;
        HashMap<String, Subsekvens> fullHmTrue = monitorTrue.hent();
        HashMap<String, Subsekvens> fullHmFalse = monitorFalse.hent();
        HashMap<Subsekvens, Integer> forekommerOfte = new HashMap<>();

        for (String k : fullHmTrue.keySet()) {
            if (!fullHmFalse.containsKey(k)) {
                if (fullHmTrue.get(k).hentAntall() >= storsteForskjell) {
                    forekommerOftest = k;
                    storsteForskjell = fullHmTrue.get(k).hentAntall();
                }

                if (fullHmTrue.get(k).hentAntall() >= 7) forekommerOfte.put(fullHmTrue.get(k), fullHmTrue.get(k).hentAntall());
            } else if (fullHmTrue.get(k).hentAntall() - fullHmFalse.get(k).hentAntall() > storsteForskjell) {
                forekommerOftest = k;
                storsteForskjell = fullHmTrue.get(k).hentAntall() - fullHmFalse.get(k).hentAntall();
                
                if (fullHmTrue.get(k).hentAntall() - fullHmFalse.get(k).hentAntall() >= 7) {
                    forekommerOfte.put(fullHmTrue.get(k), fullHmTrue.get(k).hentAntall() - fullHmFalse.get(k).hentAntall());
                }
            } else if (fullHmTrue.get(k).hentAntall() - fullHmFalse.get(k).hentAntall() >= 7) {
                forekommerOfte.put(fullHmTrue.get(k), fullHmTrue.get(k).hentAntall() - fullHmFalse.get(k).hentAntall());
            }
        }

        System.out.println(fullHmTrue.get(forekommerOftest).toString() + " forekommer oftest med " + storsteForskjell + " flere forekomster");

        if (forekommerOfte.size() > 0) {
            System.out.println("\nDisse forekommer 7 eller flere ganger oftere: ");

            PriorityQueue<Integer> forekomster = new PriorityQueue<>();
            forekomster.addAll(forekommerOfte.values());
            int antDominanteSubsekvenser = forekomster.size();

            for (int i = 0; i < antDominanteSubsekvenser; i++) {
                int antForekomster = forekomster.remove();
                Set<Subsekvens> dominanteSubsekvenser = forekommerOfte.keySet();
                
                for (Subsekvens s : dominanteSubsekvenser) {
                    if (forekommerOfte.get(s) == antForekomster) {
                        System.out.println(s + " med " + forekommerOfte.remove(s) + " flere forekomster");
                        break;
                    }
                }
            }
        }
    }
}