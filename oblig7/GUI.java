import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.*;

public class GUI {
    private Kontroll kontroll;
    private JLabel[][] ruter;
    private JLabel lengdeLabel;

    class SettRetning implements ActionListener {
        String retning;

        SettRetning(String retning) {
            this.retning = retning;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            kontroll.settRetning(retning);
        }
    }

    class SettRutenett implements ActionListener {
        Input inpRader;
        Input inpKolonner;

        public SettRutenett(Input inpRader, Input inpKolonner) {
            this.inpRader = inpRader;
            this.inpKolonner = inpKolonner;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            lagHovedVindu(inpRader.verdi, inpKolonner.verdi);
            kontroll.lagRutenett(inpRader.verdi, inpKolonner.verdi);
        }
    }

    class Avslutt implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    class SpillVindu extends JFrame implements KeyListener {
        public SpillVindu(String tittel) {
            super(tittel);
            addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                kontroll.settRetning("opp");
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                kontroll.settRetning("ned");
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                kontroll.settRetning("venstre");
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                kontroll.settRetning("hoyre");
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }

    class Input extends JFormattedTextField implements PropertyChangeListener {
        int verdi;
        
        public Input() {
            super();
            setColumns(3);
            setValue(12);
            addPropertyChangeListener(this);
        }

        @Override
        public void propertyChange(PropertyChangeEvent e) {
            verdi = ((Number)getValue()).intValue();
        }
    }

    public GUI(Kontroll kontroll) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Kunne ikke sette standardutseende!");
            System.exit(1);
        }

        this.kontroll = kontroll;
        JFrame vindu = new JFrame("Instillinger");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("Rader: "));
        panel.add(new JLabel("Kolonner: "));
        Input inpRader = new Input();
        Input inpKolonner = new Input();
        panel.add(inpRader);
        panel.add(inpKolonner);
        JButton okKnapp = new JButton("OK");
        okKnapp.addActionListener(new SettRutenett(inpRader, inpKolonner));
        panel.add(okKnapp);
        JButton avsluttKnapp = new JButton("Avslutt");
        avsluttKnapp.addActionListener(new Avslutt());
        panel.add(avsluttKnapp);
        vindu.add(panel);
        vindu.setPreferredSize(new Dimension(500, 500));
        vindu.pack();
        vindu.setVisible(true);
    }

    private void lagHovedVindu(int antRader, int antKolonner) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Kunne ikke sette standardutseende!");
            System.exit(1);
        }

        ruter = new JLabel[antRader][antKolonner];
        SpillVindu vindu = new SpillVindu("Slangespillet");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vindu.setLayout(new BorderLayout());
        JPanel styring = new JPanel();
        styring.setLayout(new GridLayout(4, 3));
        JButton oppKnapp = new JButton("↑");
        oppKnapp.addActionListener(new SettRetning("opp"));
        styring.add(new JLabel(""));
        styring.add(oppKnapp);
        styring.add(new JLabel(""));
        JButton venstreKnapp = new JButton("←");
        venstreKnapp.addActionListener(new SettRetning("venstre"));
        styring.add(venstreKnapp);
        styring.add(new JLabel(""));
        JButton hoyreKnapp = new JButton("→");
        hoyreKnapp.addActionListener(new SettRetning("hoyre"));
        styring.add(hoyreKnapp);
        lengdeLabel = new JLabel("Lengde: 1");
        JButton nedKnapp = new JButton("↓");
        nedKnapp.addActionListener(new SettRetning("ned"));
        styring.add(new JLabel(""));
        styring.add(nedKnapp);
        styring.add(new JLabel(""));
        styring.add(lengdeLabel);
        JButton avsluttKnapp = new JButton("Avslutt");
        avsluttKnapp.addActionListener(new Avslutt());
        styring.add(avsluttKnapp);
        vindu.add(styring, BorderLayout.NORTH);
        JPanel rutenett = new JPanel();
        rutenett.setLayout(new GridLayout(antRader, antKolonner));
        vindu.add(rutenett, BorderLayout.SOUTH);

        for (int i = 0; i < antRader; i++) {
            for (int j = 0; j < antKolonner; j++) {
                JLabel rute = new JLabel(" ");
                rute.setOpaque(true);
                rute.setBackground(Color.WHITE);
                rute.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                ruter[i][j] = rute;
                rutenett.add(rute);
            }
        }

        rutenett.setPreferredSize(new Dimension(500, 500));
        vindu.pack();
        vindu.setVisible(true);
    }

    public void settRute(String symbol, int rad, int kolonne) {
        ruter[rad][kolonne].setText(symbol);
        ruter[rad][kolonne].setOpaque(true);
        ruter[rad][kolonne].setHorizontalAlignment(SwingConstants.CENTER);
        ruter[rad][kolonne].setVerticalAlignment(SwingConstants.CENTER);
        
        if (symbol.equals("S")) {
            ruter[rad][kolonne].setBackground(Color.GREEN);
        } else if (symbol.equals("1") || symbol.equals("2") || symbol.equals("3")) {
            ruter[rad][kolonne].setBackground(Color.YELLOW);
        } else {
            ruter[rad][kolonne].setBackground(Color.WHITE);
        }
    }

    public void settLengde(int lengde) {
        lengdeLabel.setText("Lengde: " + Integer.toString(lengde));
    }
}