public class Hovedprogram {
    public static void main(String[] args) {
        Dataklynge saga = new Dataklynge();

        for (int i = 0; i < 450; i++) {
            saga.settInnNode(new Node(4, 512));
        }
        
        for (int i = 0; i < 16; i++) {
            saga.settInnNode(new Node(8, 1024));
        }

        System.out.println("Info om dataklynge saga:");
        System.out.println("Noder med minst 128 GB: " + saga.noderMedNokMinne(128));
        System.out.println("Noder med minst 512 GB: " + saga.noderMedNokMinne(512));
        System.out.println("Noder med minst 1024 GB: " + saga.noderMedNokMinne(1024));
        System.out.println("\nAntall prosessorer: " + saga.antProsessorer());
        System.out.println("Antall rack:" + saga.antRacks());

        Dataklynge abel = new Dataklynge();
        abel.lesFraFil("dataklynge2.txt");
        System.out.println("\nInfo om dataklynge abel (fra datafil2.txt):");
        System.out.println("Noder med minst 128 GB: " + abel.noderMedNokMinne(128));
        System.out.println("Noder med minst 512 GB: " + abel.noderMedNokMinne(512));
        System.out.println("Noder med minst 1024 GB: " + abel.noderMedNokMinne(1024));
        System.out.println("\nAntall prosessorer: " + abel.antProsessorer());
        System.out.println("Antall rack:" + abel.antRacks());
    }
}
