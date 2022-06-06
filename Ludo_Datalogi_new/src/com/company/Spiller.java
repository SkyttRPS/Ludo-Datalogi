package com.company;
import java.util.ArrayList; // Importer Java's ArrayList klasse
import java.util.List; // Importer Java's List interface

class Brik{
    // Opret variabler
    private final int brikID;
    private int felt = 0;
    private final String farve;
    public boolean iMael;

    // Brik klasse contructor
    public Brik(int bID, String farveID){
        this.brikID =bID;
        this.farve = farveID;
    }

    // Getter metode brikID
    public int getBrikID() {return brikID;}

    // Getter metode Lokation
    public int getLokation() {return felt;}

    // Setter metode til at sætte felt
    public void setFelt(int feltPlus) {
        // Return hvis brik er i mål
        if (iMael) return;

        // Tjekker om feltet er tilsvarende målfeltet eller højere og sætter til 76 (målværdi)
        if (this.felt + feltPlus >= 76){
            this.felt = 76;
            iMael = true;
        } else {
            this.felt = this.felt + feltPlus;
        }
    }

    // Setter metode til at definere specielle felter
    public void setFeltSituation(int feltPlus) {
        if (iMael) return;
        this.felt = feltPlus;
    }

    // Metode til at tjekke om en brik er i mål
    public boolean brikIMael(){
        return iMael;
    }
}

public class Spiller {
    // Opret variabler
    int spillernummer;
    String spillerfarve;
    //int spillerPoint;
    List<Brik> brikker = new ArrayList<>(); // Opret en ArrayList med brikker

    // Spiller constructor
    public Spiller(int spillerNR){
        this.spillernummer = spillerNR;
        // Tildel farve til hvert spillernummer
        switch(spillernummer) {
            case 1:
                spillerfarve = "roed";
                break;
            case 2:
                spillerfarve = "blaa";
                break;
            case 3:
                spillerfarve = "groen";
                break;
            case 4:
                spillerfarve = "gul";
                break;
        }

        /* if (spillernummer==1) spillerfarve = "roed";
        if (spillernummer==2) spillerfarve = "blae";
        if (spillernummer==3) spillerfarve = "groen";
        if (spillernummer==4) spillerfarve = "gul"; */
        for (int i =1;i<5;i++){
            brikker.add(new Brik(i,spillerfarve));
        }
    }

    // Getter metode til at returnere spillerfarve
    public String getSpillerfarve() {return spillerfarve;}

    // Tjekker hvor alle en spillers fire brikker er placeret
    public void hvorErBrikker(){
        for (int i = 0; i<4;i++){
            System.out.println("Brik " + brikker.get(i).getBrikID() + " er paa felt "+ brikker.get(i).getLokation());
        }
    }
}

// Klasse til at lave spillerne
class LavSpillere{
    List<Spiller> list = new ArrayList<>(); // ArrayList til at holde spiller objekter

    // LavSpillere constructor
    public LavSpillere(){
        for (int i =1;i<5;i++){
            list.add(new Spiller(i));
        }
    }

    // Getter for spillerens farve
    public String spillerensFarve(int spillerNR){
        String spillerensFarve = list.get(spillerNR-1).getSpillerfarve();
        return spillerensFarve;
    }

    // Metode til at slå en brik hjem
    public void slaaHjem(int spillerNR, int brikID){
        list.get(spillerNR-1).brikker.get(brikID-1).setFeltSituation(0);
    }
}