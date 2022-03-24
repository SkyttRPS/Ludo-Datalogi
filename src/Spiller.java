public class Spiller {

    // Klasseattributter
    public String farve; // Spillerens farve, kan ændres til "navn" til brug i andre spil
    public int startPosition; // Sidst kendte position, bruges i starten af turen
    public int nyPosition; // Den opdaterede position efter endt tur

    // Konstruktør
    public Spiller(){
        farve = "";
        startPosition = 0;
        nyPosition = 0;
    }

    public Spiller(String farve, int startPosition, int nyPosition){
        this.farve = farve;
        this.startPosition = startPosition;
        this.nyPosition = nyPosition;
    }

    public void printDetaljer(){
        System.out.println("Farve: " + fåFarve());
        System.out.println("Start position: " + fåStartPosition());
        System.out.println("Ny position: " + fåNyPosition());
    }

    public String fåFarve() {
        return farve;
    }

    public int fåStartPosition() {
        return startPosition;
    }

    public int fåNyPosition() {
        return nyPosition;
    }

    public int sætStartPosition() {
        return startPosition;
    }

    public int sætNyPosition() {
        return nyPosition;
    }
}
