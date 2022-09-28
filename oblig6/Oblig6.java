import java.util.ArrayList;
import java.util.Scanner;

public class Oblig6 {
    public static void main(String[] args) {
        if (args.length < 1)  {
            System.err.println("Du maa oppgi filnavn!");
            System.exit(1);
        }

        System.out.println("Slik ser labyrinten ut: ");
        Labyrint lab = new Labyrint(args[0]);
        System.out.println("Skriv inn koordinater <rad> <kolonne> ('-1' for aa avslutte)");
        Scanner input = new Scanner(System.in);
        String kommando = input.nextLine();
        
        while (!kommando.equals("-1")) {
            try {
                String[] data = kommando.split(" ");
                System.out.println("Aapninger:");
                lab.finnUtveiFra(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
                System.out.println("Her er labyrinten slik du gikk gjennom den: ");
                System.out.println(lab);

                if (lab.utveier.containsKey("(" + data[0] + ", " + data[1] + ")")) {
                    ArrayList<ArrayList<Tuppel>> utveier = lab.utveier.get("(" + data[0] + ", " + data[1] + ")");
                    ArrayList<Tuppel> kortesteUtvei = null;

                    for (ArrayList<Tuppel> utvei : utveier) {
                        if (kortesteUtvei == null) {
                            kortesteUtvei = utvei;
                        } else if (utvei.size() < kortesteUtvei.size()) {
                            kortesteUtvei = utvei;
                        }
                    }

                    System.out.println("\nAntall utveier: " + utveier.size() + "\n" + "Den korteste utveien er:");
                    System.out.println(kortesteUtvei);
                    System.out.println("\n");
                }

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.err.println("Ugyldig input!");
            }

            System.out.println("\nSkriv inn nye koordinater ('-1' for aa avslutte)");
            kommando = input.nextLine();
        }

        input.close();
    }
}
