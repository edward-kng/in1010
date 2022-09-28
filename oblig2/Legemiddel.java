public abstract class Legemiddel {
    protected String navn;
    protected int pris, id;
    protected double virkestoff;
    protected static int teller = 1;

    public Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        this.id = teller;
        teller++;
    }

    public int hentId() {
        return id;
    }

    public String hentNavn() {
        return navn;
    }

    public int hentPris() {
        return pris;
    }

    public double hentVirkestoff() {
        return virkestoff;
    }

    public void settNyPris(int nyPris) {
        pris = nyPris;
    }

    @Override
    public String toString() {
        return "Navn: " + navn +
        "\nPris: " + pris +
        "\nVirkestoff: " + virkestoff +
        "\nID: " + id;
    }
}