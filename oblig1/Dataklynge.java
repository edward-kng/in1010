import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dataklynge {
    private ArrayList<Rack> racks = new ArrayList<Rack>();
    private int antRacks = 0;

    public void settInnNode(Node node) {
        if (antRacks == 0 || racks.get(antRacks - 1).hentAntNoder() >= 12) {
            racks.add(new Rack());
            antRacks++;
            racks.get(antRacks - 1).settInnNode(node);
        } else {
            racks.get(antRacks - 1).settInnNode(node);
        }
    }

    public int noderMedNokMinne(int paakrevdMinne) {
        int noderMedNokMinne = 0;

        for (int i = 0; i < antRacks; i++) {
            noderMedNokMinne += racks.get(i).noderMedNokMinne(paakrevdMinne);
        }

        return noderMedNokMinne;
    }

    public int antRacks() {
        return antRacks;
    }

    public int antProsessorer() {
        int antPros = 0;

        for (int i = 0; i < antRacks; i++) {
            antPros += racks.get(i).hentAntPros();
        }

        return antPros;
    }

    public void lesFraFil(String filnavn) {
        try {
            Scanner fil = new Scanner(new File(filnavn));
            int antNoder;
            int antPros;
            int minne;

            while (fil.hasNextLine()) {
                String[] data = fil.nextLine().split(" ");
                antNoder = Integer.parseInt(data[0]);
                antPros = Integer.parseInt(data[1]);
                minne = Integer.parseInt(data[2]);

                if (minne > 4096 || antPros > 16 || data.length != 3) {
                    throw new Exception();
                }

                for (int i = 0; i < antNoder; i++) {
                    this.settInnNode(new Node(antPros, minne));
                } 
            }
            
            fil.close();
        } catch (FileNotFoundException e) {
            System.err.println("Kan ikke lese " + filnavn + "!");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Feil format paa datafil!");
            System.exit(1);
        }
    }
}
