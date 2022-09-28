import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class SubsekvensRegister {
    private ArrayList<HashMap<String, Subsekvens>> hashBeholder = new ArrayList<>();

    public void settInn(HashMap<String, Subsekvens> hashMap) {
        hashBeholder.add(hashMap);
    }

    public HashMap<String, Subsekvens> hent() {
        return hashBeholder.remove(0);
    }

    public int stoerrelse() {
        return hashBeholder.size();
    }

    public static HashMap<String, Subsekvens> lesFil(String filnavn) {
        try {
            Scanner scFil = new Scanner(new File(filnavn));
            HashMap<String, Subsekvens> hashMap = new HashMap<>();

            while (scFil.hasNextLine()) {
                String linje = scFil.nextLine().trim();
                if (linje.length() < 3) throw new Exception();

                for (int i = 0; i < linje.length() - 2; i++) {
                    String subsekvens = "" + linje.charAt(i) + linje.charAt(i+1) + linje.charAt(i+2);
                    if (!hashMap.containsKey(subsekvens)) hashMap.put(subsekvens, new Subsekvens(subsekvens, 1));
                }
            }

            scFil.close();
            return hashMap;
        } catch (FileNotFoundException e) {
            System.err.println("Kunne ikke aapne fil " + filnavn + "!");
            System.exit(3);
            return null;
        } catch (Exception e) {
            System.err.println("Feil format i fil!");
            System.exit(4);
            return null;
        }
    }

    public static HashMap<String, Subsekvens> slaaSammen(HashMap<String, Subsekvens> hashMap1, HashMap<String, Subsekvens> hashMap2) {
        HashMap<String, Subsekvens> nyHashMap = new HashMap<>();
        nyHashMap.putAll(hashMap1);

        for (String k : hashMap2.keySet()) {
            if (nyHashMap.containsKey(k)) nyHashMap.get(k).endreAntall(nyHashMap.get(k).hentAntall() + hashMap2.get(k).hentAntall());
            else nyHashMap.put(k, hashMap2.get(k));
        }

        return nyHashMap;
    }
}
