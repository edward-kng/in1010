public class TestResept {
    public static void main(String[] args) {
        Lege lege1 = new Lege("Dr1");
        Lege lege2 = new Lege("Dr2");
        Lege lege3 = new Lege("Dr3");
        Lege lege4 = new Lege("Dr4");
        Legemiddel med1 = new Vanlig("med1", 100, 10);
        Legemiddel med2 = new Vanlig("med2", 200, 20);
        Legemiddel med3 = new Vanlig("med3", 300, 30);
        Legemiddel med4 = new Vanlig("med4", 400, 40);

        BlaaResept blaa = new BlaaResept(med1, lege1, 1, 3);
        HvitResept hvit = new HvitResept(med2, lege2, 2, 0);
        MilResept mil = new MilResept(med3, lege3, 3);
        PResept p = new PResept(med4, lege4, 4, 1);

        System.out.println(blaa);
        System.out.println(testReseptId(blaa, 1));
        System.out.println(testReseptLegemiddel(blaa, med1));
        System.out.println(testReseptLege(blaa, lege1));
        System.out.println(testReseptPasientId(blaa, 1));
        System.out.println(testReseptReit(blaa, 3));
        System.out.println(testReseptPris(blaa, 25));
        blaa.bruk();
        System.out.println(testReseptReit(blaa, 2));
        System.out.println(testReseptFarge(blaa, "blaa"));
        
        System.out.println("\n" + hvit);
        System.out.println(testReseptPris(hvit, 200));
        System.out.println(testReseptFarge(hvit, "hvit"));

        System.out.println("\n" + mil);
        System.out.println(testReseptPris(mil, 0));

        System.out.println("\n" + p);
        System.out.println(testReseptPris(p, 292));
    }

    public static boolean testReseptId(Resept resept, int forventetId) {
        return resept.hentId() == forventetId;
    }

    public static boolean testReseptLegemiddel(Resept resept, Legemiddel forventetlegemiddel) {
        return resept.hentLegemiddel() == forventetlegemiddel;
    }

    public static boolean testReseptLege(Resept resept, Lege forventetlege) {
        return resept.hentLege() == forventetlege;
    }

    public static boolean testReseptPasientId(Resept resept, int forventetId) {
        return resept.hentPasientId() == forventetId;
    }

    public static boolean testReseptReit(Resept resept, int forventetReit) {
        return resept.hentReit() == forventetReit;
    }

    public static boolean testReseptPris(Resept resept, int forventetPris) {
        return resept.prisAaBetale() == forventetPris;
    }

    public static boolean testReseptFarge(Resept resept, String farge) {
        return resept.farge() == farge;
    }
}
