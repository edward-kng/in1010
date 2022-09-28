public class Integrasjonstest {
    public static void main(String[] args) {
        Vanlig nesespray = new Vanlig("nesespray", 50, 20);
        Narkotisk morfin = new Narkotisk("morfin", 110, 30, 75);
        Vanedannende sovepiller = new Vanedannende("sovepiller", 80, 40, 60);

        Lege DrLee = new Lege("Lee");
        Spesialist DrKang = new Spesialist("Kang", "K52");

        BlaaResept blaa = new BlaaResept(nesespray, DrLee, 1, 3);
        HvitResept hvit = new HvitResept(sovepiller, DrLee, 2, 0);
        MilResept mil = new MilResept(sovepiller, DrKang, 3);
        PResept p = new PResept(morfin, DrKang, 4, 1);

        System.out.println(nesespray
        + "\n\n" + morfin + "\n\n" + sovepiller
        + "\n\n" + DrLee + "\n\n" + DrKang
        + "\n\n" + blaa + "\n\n" + hvit + "\n\n" + mil + "\n\n" + p
        );
    }
}