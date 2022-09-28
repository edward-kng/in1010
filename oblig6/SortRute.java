import java.util.ArrayList;

public class SortRute extends Rute {
    public SortRute(int radNr, int kolonneNr, Labyrint labyrint) {
        super(radNr, kolonneNr, labyrint);
    }

    @Override
    public void finn(Rute fra, ArrayList<Tuppel> vei) {
        if (fra == null) {
            System.out.println("Kan ikke starte i sort rute");
        }
    }

    @Override
    public String toString() {
        return "  #";
    }
}