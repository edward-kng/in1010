public abstract class Resept {
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected int pasientId, reit, id;
    protected static int teller;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasientId = pasientId;
        this.reit = reit;
        teller++;
        id = teller;
    }

    public int hentId() {
        return id;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return utskrivendeLege;
    }

    public int hentPasientId() {
        return pasientId;
    }

    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if (reit > 0) {
            reit--;
            return true;
        }
        return false;
    }

    abstract public String farge();

    abstract public int prisAaBetale();

    @Override
    public String toString() {
        return "Legemiddel: [" + legemiddel + "]" +
        "\nUtskrivende lege: [" + utskrivendeLege + "]" +
        "\nPasient-ID: " + pasientId +
        "\nReit: " + reit +
        "\nID: " + id;
    }
}
