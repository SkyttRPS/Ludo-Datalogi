public class Spiller {

    // Klasseattributter
    public String farve; // Spillerens farve, kan ændres til "navn" til brug i andre spil
    public int startPosition; // Sidst kendte position, bruges i starten af turen
    public int nuværendePosition; // Den opdaterede position efter endt tur

    // Konstruktør
    public Spiller(){
        farve = "";
        startPosition = 0;
        nuværendePosition = 0;
    }

    public Spiller(String farve, int startPosition, int nuværendePosition){
        this.farve = farve;
        this.startPosition = startPosition;
        this.nuværendePosition = nuværendePosition;
    }

    public void printDetaljer(){
        System.out.println("Farve: " + fåFarve());
        System.out.println("Start position: " + fåStartPosition());
        System.out.println("Ny position: " + fåNuværendePosition());
    }

    public String fåFarve() {
        return farve;
    }

    public int fåStartPosition() {
        return startPosition;
    }

    public int fåNuværendePosition() {
        return nuværendePosition;
    }

    public int sætStartPosition() {
        return startPosition;
    }

    public int sætNuværendePosition() {
        return nuværendePosition;
    }
}
