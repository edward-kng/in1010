public class Subsekvens {
    public final String SUBSEKVENS;
    private int antall;

    public Subsekvens(String subsekvens, int antall) {
        SUBSEKVENS = subsekvens;
        this.antall = antall;
    }

    public int hentAntall() {
        return antall;
    }

    public void endreAntall(int antall) {
        this.antall = antall;
    }

    @Override
    public String toString() {
        return "(" + SUBSEKVENS + "," + antall + ")";
    }
}