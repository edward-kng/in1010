import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class Labyrint {
    private int antRader, antKolonner;
    private Rute[][] ruter;
    HashMap<String, ArrayList<ArrayList<Tuppel>>> utveier = new HashMap<>();

    public Labyrint(String filnavn) {
        Scanner sc;

        try {
            sc = new Scanner(new File(filnavn));
        } catch (FileNotFoundException e) {
            System.err.println("Kan ikke finne filen " + filnavn);
            System.exit(2);
            return;
        }

        antRader = sc.nextInt();
        antKolonner = sc.nextInt();
        ruter = new Rute[antRader][antKolonner];
        sc.nextLine();
        int radNr = 0;

        try {
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().trim().split("");
                    if (data.length == 0) {
                        break;
                    } else if (data.length != antKolonner) {
                        throw new Exception();
                    }

                    for (int kolonneNr = 0; kolonneNr < antKolonner; kolonneNr++) {
                        String symbol = data[kolonneNr];
                        Rute rute;

                        if (symbol.equals(".") && (kolonneNr == 0 || kolonneNr == antKolonner - 1 || radNr == 0 || radNr == antRader - 1)) {
                            rute = new Aapning(radNr, kolonneNr, this);
                            ruter[radNr][kolonneNr] = rute;
                        } else if (symbol.equals(".")) {
                            rute = new HvitRute(radNr, kolonneNr, this);
                            ruter[radNr][kolonneNr] = rute;
                        } else if (symbol.equals("#")) {
                            rute = new SortRute(radNr, kolonneNr, this);
                            ruter[radNr][kolonneNr] = rute;
                        } else {
                            throw new Exception();
                        }

                        if (radNr > 0) {
                            rute.settNord(ruter[radNr - 1][kolonneNr]);
                            ruter[radNr - 1][kolonneNr].settSyd(rute);
                        }
                        
                        if (kolonneNr > 0) {
                            rute.settVest(ruter[radNr][kolonneNr - 1]);
                            ruter[radNr][kolonneNr - 1].settOest(rute);
                        }
                    }

                    radNr++;
            }

            if (radNr != antRader) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.err.println("Ugyldig filformat!");
            System.exit(3);
        }

        sc.close();
        System.out.println(this);
    }

    public void finnUtveiFra(int rad, int kol) {
        if (rad > antRader - 1 || kol > antKolonner - 1) {
            System.out.println("Ugyldige koordinater!");
        } else {
            ruter[rad][kol].finn(null, new ArrayList<Tuppel>());
        }
    }

    @Override
    public String toString() {
        String str = "";

        for (int radNr = 0; radNr < antRader; radNr++) {
            for (int kolonneNr = 0; kolonneNr < antKolonner; kolonneNr++) {
                str += ruter[radNr][kolonneNr];
            }

            str += "\n";
        }

        return str;
    }
}