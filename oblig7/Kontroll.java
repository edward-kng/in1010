public class Kontroll {
    private GUI gui;
    private Modell modell;

    Kontroll() {
        gui = new GUI(this);
        modell = new Modell(gui);
    }

    public void settRetning(String retning) {
        modell.settRetning(retning);
    }

    public void oppdater() {
        modell.oppdater();
    }

    public int hentLengde() {
        return modell.hentLengde();
    }

    public void lagRutenett(int antRader, int antKolonner) {
        modell.lagRutenett(antRader, antKolonner);
        Thread klokketraad = new Thread(new Klokketraad(this));
        klokketraad.start();
    }

    public boolean kollisjon() {
        return modell.kollisjon();
    }
}
