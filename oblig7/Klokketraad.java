public class Klokketraad implements Runnable {
    private Kontroll kontroll;

    public Klokketraad(Kontroll kontroll) {
        this.kontroll = kontroll;
    }

    @Override
    public void run() {
        while (!kontroll.kollisjon()) {
            kontroll.oppdater();

            try {
                int s = 2000 - (100 + kontroll.hentLengde() * 100);
                
                if (s < 100) {
                    s = 100;
                }

                Thread.sleep(s);
            } catch (InterruptedException e) {
                System.err.println("Traad forstyrret!");
                System.exit(2);
            }
        }
    }
}
