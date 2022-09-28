import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Oblig5Del1 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Du maa oppgi filnavn!");
            System.exit(1);
        }

        ArrayList<String> filer = new ArrayList<>();
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
        SubsekvensRegister subsekvensRegister = new SubsekvensRegister();

        for (String fil : filer) {
            subsekvensRegister.settInn(SubsekvensRegister.lesFil(fil));
        }

        HashMap<String, Subsekvens> fullHashMap = new HashMap<>();
        int antHashMaps = subsekvensRegister.stoerrelse();

        for (int i = 0; i < antHashMaps; i++) {
            fullHashMap = SubsekvensRegister.slaaSammen(fullHashMap, subsekvensRegister.hent());
        }

        String storst = null;

        for (String k : fullHashMap.keySet()) {
            if (storst == null) storst = k;
            else if (fullHashMap.get(k).hentAntall() > fullHashMap.get(storst).hentAntall()) storst = k;
        }

        System.out.println(fullHashMap.get(storst));
    }
}