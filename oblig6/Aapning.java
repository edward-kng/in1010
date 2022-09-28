import java.util.ArrayList;

public class Aapning extends HvitRute {
    public Aapning(int radNr, int kolonneNr, Labyrint labyrint) {
        super(radNr, kolonneNr, labyrint);
    }

    @Override
    public void finn(Rute fra, ArrayList<Tuppel> vei) {
        teller++;
        nr = teller;
        ArrayList<Tuppel> nyVei = new ArrayList<>();
        nyVei.addAll(vei);
        nyVei.add(new Tuppel(radNr, kolonneNr));
        System.out.println("(" + radNr + ", " + kolonneNr + ")\n");
        System.out.println("Slik ser utveien ut:\n" + nyVei + "\n");
        
        if (!labyrint.utveier.containsKey(nyVei.get(0).toString())) {
            labyrint.utveier.put(nyVei.get(0).toString(), new ArrayList<>());
        }

        labyrint.utveier.get(nyVei.get(0).toString()).add(nyVei);
    }
}
