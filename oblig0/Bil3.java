public class Bil3 {
    private String bilnr;

    public Bil3(String bilnr) {
        this.bilnr = bilnr;
    }

    public void skriv() {
        System.out.println(this.bilnr);
    }
    
    public String hentNummer() {
        return this.bilnr;
    }
}
