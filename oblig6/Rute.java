import java.util.ArrayList;

public abstract class Rute {
    protected int radNr, kolonneNr, nr;
    protected Rute nord, syd, vest, oest;
    protected Labyrint labyrint;
    protected static int teller;
    protected boolean besoekt = false;

    public Rute(int radNr, int kolonneNr, Labyrint labyrint) {
        this.radNr = radNr;
        this.kolonneNr = kolonneNr;
        this.labyrint = labyrint;
    }

    public void settNord(Rute rute) {nord = rute;}

    public void settSyd(Rute rute) {syd = rute;}

    public void settVest(Rute rute) {vest = rute;}

    public void settOest(Rute rute) {oest = rute;}

    public void finn(Rute fra, ArrayList<Tuppel> vei) {
        besoekt = true;
        ArrayList<Tuppel> nyVei = new ArrayList<>();
        nyVei.addAll(vei);
        nyVei.add(new Tuppel(radNr, kolonneNr));
        teller++;
        nr = teller;
        if (nord != fra && !nord.besoekt) nord.finn(this, nyVei);
        if (syd != fra && !syd.besoekt) syd.finn(this, nyVei);
        if (vest != fra && !vest.besoekt) vest.finn(this, nyVei);
        if (oest != fra && !oest.besoekt) oest.finn(this, nyVei);
    }

    @Override
    public String toString() {
        if (nr > 9) {
            return " " + nr;
        } else if (nr > 0) {
            return "  " + nr;
        } else {
            return null;
        }
    }
}