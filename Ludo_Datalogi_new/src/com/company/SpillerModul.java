package com.company;

import java.util.ArrayList;
import java.util.List;

// Klasse til at lave spillerne
class SpillerModul {
    List<Spiller> sp = new ArrayList<>(); // ArrayList til at holde spiller objekter

    // LavSpillere constructor
    public SpillerModul(){
        for (int i =1;i<5;i++){
            sp.add(new Spiller(i));
        }
    }

    // Getter for spillerens farve
    public String spillerensFarve(int spillerNR){
        String spillerensFarve = sp.get(spillerNR-1).getSpillerFarve();
        return spillerensFarve;
    }

    // Metode til at slå en brik hjem
    public void slåHjem(int spillerNR, int brikID){
        Spiller s = sp.get(spillerNR-1);
        Brik b = s.brikker.get(brikID-1); // (brikID-1)
        b.setFeltSituation(0);
        // list.get(spillerNR-1).brikker.get(brikID-1).setFeltSituation(0);
    }
}
