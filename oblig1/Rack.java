public class Rack {
    private Node[] noder = new Node[12];
    private int antNoder = 0;

    public int hentAntNoder() {
        return antNoder;
    }

    public void settInnNode(Node node) {
        if (antNoder < 12) {
            noder[antNoder] = node;
            antNoder++;
        }
    }

    public int noderMedNokMinne(int paakrevdMinne) {
        int noderMedNokMinne = 0;

        for (int i = 0; i < antNoder; i++) {
            if (noder[i].hentMinne() >= paakrevdMinne) {
                noderMedNokMinne++;
            }
        }
        
        return noderMedNokMinne;
    }

    public int hentAntPros() {
        int antPros = 0;

        for (int i = 0; i < antNoder; i++) {
            antPros += noder[i].antProsessorer();
        }

        return antPros;
    }
}
