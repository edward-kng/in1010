public class TestLegemiddel {
    public static void main(String[] args) {
        Vanlig nesespray = new Vanlig("nesespray", 50, 20);
        System.out.println(nesespray);
        System.out.println(TestLegemiddelId(nesespray, 1));
        System.out.println(TestLegemiddelNavn(nesespray, "nesespray"));
        System.out.println(TestLegemiddelVirkestoff(nesespray, 20));
        System.out.println(TestLegemiddelPris(nesespray, 50));
        nesespray.settNyPris(65);
        System.out.println(TestLegemiddelPris(nesespray, 65));

        Narkotisk morfin = new Narkotisk("morfin", 110, 30, 75);
        System.out.println(morfin);
        System.out.println(TestLegemiddelId(morfin, 2));
        System.out.println(TestLegemiddelNavn(morfin, "morfin"));
        System.out.println(TestLegemiddelPris(morfin, 110));
        System.out.println(TestLegemiddelVirkestoff(morfin, 30));
        System.out.println(TestLegemiddelStyrke(morfin, 75));
        morfin.settNyPris(125);
        System.out.println(TestLegemiddelPris(morfin, 125));

        Vanedannende sovepiller = new Vanedannende("sovepiller", 80, 40, 60);
        System.out.println(sovepiller);
        System.out.println(TestLegemiddelId(sovepiller, 3));
        System.out.println(TestLegemiddelNavn(sovepiller, "sovepiller"));
        System.out.println(TestLegemiddelPris(sovepiller, 80));
        System.out.println(TestLegemiddelVirkestoff(sovepiller, 40));
        System.out.println(TestLegemiddelStyrke(sovepiller, 60));
        sovepiller.settNyPris(95);
        System.out.println(TestLegemiddelPris(sovepiller, 95));
    }

    public static boolean TestLegemiddelId(Legemiddel legemiddel, int forventetLegemiddelId) {
        return legemiddel.hentId() == forventetLegemiddelId;
    }

    public static boolean TestLegemiddelNavn(Legemiddel legemiddel, String forventetLegemiddelNavn) {
        return legemiddel.hentNavn() == forventetLegemiddelNavn;
    }

    public static boolean TestLegemiddelVirkestoff(Legemiddel legemiddel, double forventetLegemiddelVirkestoff) {
        return legemiddel.hentVirkestoff() == forventetLegemiddelVirkestoff;
    }

    public static boolean TestLegemiddelPris(Legemiddel legemiddel, int forventetLegemiddelPris) {
        return legemiddel.hentPris() == forventetLegemiddelPris;
    }

    public static boolean TestLegemiddelStyrke(Legemiddel legemiddel, int forventetLegemiddelStyrke) {
        if (legemiddel instanceof Narkotisk) {
            return ((Narkotisk) legemiddel).hentNarkotiskStyrke() == forventetLegemiddelStyrke;
        } else if (legemiddel instanceof Vanedannende) {
            return ((Vanedannende) legemiddel).hentVanedannendeStyrke() == forventetLegemiddelStyrke;
        }
        
        return false;
    }
}
