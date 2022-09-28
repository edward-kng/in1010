public class HvitRute extends Rute {
    public HvitRute(int radNr, int kolonneNr, Labyrint labyrint) {
        super(radNr, kolonneNr, labyrint);
    }

    @Override
    public String toString() {
        if (super.toString() == null) {
            return "  .";
        } else {
            return super.toString();
        }
    }
}
