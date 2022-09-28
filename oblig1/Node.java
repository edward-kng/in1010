public class Node {
    private int antPros, minne;

    public Node(int antPros, int minne) {
        this.antPros = antPros;
        this.minne = minne;
    }

    public int antProsessorer() {
        return antPros;
    }

    public int hentMinne() {
        return minne;
    }
}