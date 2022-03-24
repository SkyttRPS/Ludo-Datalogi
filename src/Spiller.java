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

    // Metode til at printe en spillers information
    public void printSpillerInfo(){
        System.out.println("Farve: " + getFarve());
        System.out.println("Start position: " + getStartPosition());
        System.out.println("Ny position: " + getNyPosition());
    }

    // Getters og setters
    public String getFarve() {
        return farve;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public int getNyPosition() {
        return nyPosition;
    }

    public int setStartPosition(int startPos) {
        startPosition = startPos;
        return startPosition;
    }

    public int setNyPosition(int nyPos) {
        nyPosition = nyPos;
        return nyPosition;
    }
}
