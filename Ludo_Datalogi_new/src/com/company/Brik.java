package com.company;

class Brik{
    // Opret variabler
    private final int BRIK_ID;
    private int felt = 0;
    private final String FARVE; // Tiltænkt at bruges til eventuel GUI
    public boolean iMål;

    // Brik klasse constructor
    public Brik(int bID, String farveID){
        this.BRIK_ID =bID;
        this.FARVE = farveID;
    }

    // Getter metode brikID
    public int getBrikID() {return BRIK_ID;}

    // Getter metode Lokation
    public int getLokation() {return felt;}

    // Setter metode til at sætte felt
    public void setFelt(int feltPlus) {
        // Return hvis brik er i mål
        if (iMål) return;

        // Tjekker om feltet er tilsvarende målfeltet eller højere og sætter til 76 (målværdi)
        if (this.felt + feltPlus >= 76){
            this.felt = 76;
            iMål = true;
        } else {
            this.felt = this.felt + feltPlus;
        }
    }

    // Setter metode til at definere specielle felter
    public void setFeltSituation(int feltPlus) {
        if (iMål) return;
        this.felt = feltPlus;
    }

    // Metode til at tjekke om en brik er i mål
    public boolean brikIMål(){
        return iMål;
    }
}
