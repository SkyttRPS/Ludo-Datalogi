package com.company;
import java.util.ArrayList; // Importer Java's ArrayList klasse
import java.util.List; // Importer Java's List interface

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
        switch (spillernummer) {
            case 1 -> spillerfarve = "rød";
            case 2 -> spillerfarve = "blå";
            case 3 -> spillerfarve = "grøn";
            case 4 -> spillerfarve = "gul";
        }
        
        for (int i =1;i<5;i++){
            brikker.add(new Brik(i,spillerfarve));
        }
    }

    // Getter metode til at returnere spillerfarve
    public String getSpillerfarve() {return spillerfarve;}

    // Tjekker hvor alle en spillers fire brikker er placeret
    public void hvorErBrikker(){
        for (int i = 0; i<4;i++){
            System.out.println("Brik " + brikker.get(i).getBrikID() + " er på felt "+ brikker.get(i).getLokation());
        }
    }
}
