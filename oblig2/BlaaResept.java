import java.lang.Math;

public class BlaaResept extends Resept {
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    @Override
    public String farge() {
        return "blaa";
    }

    @Override
    public int prisAaBetale() {
        return (int) Math.round(legemiddel.hentPris() * 0.25);
    }

    @Override
    public String toString() {
        return "Blaa resept\n" + super.toString(); 
    }
}
