import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.*;
import java.util.concurrent.locks.ReentrantLock;

public class Modell {
    private GUI gui;
    private Rute[][] ruter;
    private List<Rute> slange = new ArrayList<>();
    private String retning = "opp";
    private int slangeLengde = 1;
    private Lock laas = new ReentrantLock();
    private int antRader, antKolonner;
    private Boolean kollisjon = false;

    class Rute {
        int skatt, rad, kolonne;
    
        Rute(int skatt, int rad, int kolonne) {
            this.skatt = skatt;
            this.rad = rad;
            this.kolonne = kolonne;
        }
    }

    Modell(GUI gui) {
        this.gui = gui;
    }

    public void lagRutenett(int antRader, int antKolonner) {
        this.antRader = antRader;
        this.antKolonner = antKolonner;

        ruter = new Rute[antRader][antKolonner];

        for (int rad = 0; rad < antRader; rad++) {
            for (int kolonne = 0; kolonne < antKolonner; kolonne++) {
                ruter[rad][kolonne] = new Rute(0, rad, kolonne);
            }
        }

        slange.add(ruter[antRader - 1][antKolonner - 1]);

        for (int i = 0; i < 10; i++) {
            leggSkatt();
        }
    }

    private static int trekk(int a, int b) {
        return (int)(Math.random()*(b-a+1))+a;
    }

    private void leggSkatt() {
        int rad = trekk(0, antRader - 1);
        int kolonne = trekk(0, antKolonner - 1);
        
        if (ruter[rad][kolonne].skatt > 0 || slange.contains(ruter[rad][kolonne])) {
            leggSkatt();
        } else {
            ruter[rad][kolonne].skatt = trekk(1, 3);
            gui.settRute(Integer.toString(ruter[rad][kolonne].skatt), rad, kolonne);
        }
    }

    public void settRetning(String retning) {
        laas.lock();

        try {
            this.retning = retning;
        } finally {
            laas.unlock();
        }
    }

    public void oppdater() {
        laas.lock();

        try {
            int radOkning;
            int kolonneOkning;

            if (retning.equals("opp")) {
                radOkning = -1;
                kolonneOkning = 0;
            } else if (retning.equals("ned")) {
                radOkning = 1;
                kolonneOkning = 0;
            } else if (retning.equals("venstre")) {
                radOkning = 0;
                kolonneOkning = -1;
            } else {
                radOkning = 0;
                kolonneOkning = 1;
            }

            Rute hode = slange.get(slange.size() - 1);
            Rute hale = slange.get(0);
            kollisjon = hode.rad + radOkning == -1 || hode.rad + radOkning == antRader || hode.kolonne + kolonneOkning == -1 || hode.kolonne + kolonneOkning == antKolonner;

            for (Rute rute : slange) {
                if (rute != hode && (hode.rad + radOkning == rute.rad && hode.kolonne + kolonneOkning == rute.kolonne)) {
                    kollisjon = true;
                }
            }

            if (!kollisjon) {
                Rute ruteForan = ruter[hode.rad + radOkning][hode.kolonne + kolonneOkning];

                slange.add(ruteForan);
                gui.settRute("S", hode.rad + radOkning, hode.kolonne + kolonneOkning);

                if (ruteForan.skatt > 0) {
                    slangeLengde += ruteForan.skatt;
                    gui.settLengde(slange.size());
                    ruteForan.skatt = 0;
                    leggSkatt();
                } else if (slangeLengde + 1 > slange.size()) {
                    gui.settLengde(slange.size());
                } else {
                    slange.remove(hale);
                    gui.settRute(" ", hale.rad, hale.kolonne);
                }
            }
        } finally {
            laas.unlock();
        }
    }

    public int hentLengde() {
        return slangeLengde;
    }

    public boolean kollisjon() {
        return kollisjon;
    }
}