package com.company;
import java.util.ArrayList; // Importer Java's ArrayList klasse
import java.util.List; // Importer Java's List interface

public class Spiller {
    // Opret variabler
    int spillerNummer;
    String spillerFarve;
    //int spillerPoint;
    List<Brik> brikker = new ArrayList<>(); // Opret en ArrayList med brikker

    // Spiller constructor
    public Spiller(int spillerNR){
        this.spillerNummer = spillerNR;
        // Tildel farve til hvert spillernummer
        switch (spillerNummer) {
            case 1 -> spillerFarve = "rød";
            case 2 -> spillerFarve = "blå";
            case 3 -> spillerFarve = "grøn";
            case 4 -> spillerFarve = "gul";
        }
        
        for (int i =1;i<5;i++){
            brikker.add(new Brik(i, spillerFarve));
        }
    }

    // Getter metode til at returnere spillerfarve
    public String getSpillerFarve() {return spillerFarve;}

    // Tjekker hvor alle en spillers fire brikker er placeret
    public void hvorErBrikker(){
        for (int i = 0; i<4;i++){
            System.out.println("Brik " + brikker.get(i).getBrikID() + " er på felt "+ brikker.get(i).getLokation());
        }
    }
}
