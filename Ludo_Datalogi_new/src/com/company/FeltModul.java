package com.company;
import java.util.ArrayList;
import java.util.List;
class FeltModul {
    // Lav en ArrayList af felt objekter
    List<Felt> felter = new ArrayList<>();
    // LavFelter constructor
    public FeltModul(){
        for (int i =1; i<= 76; i++){
            felter.add(new Felt(i));
        }
    }
    // Setter metode for at sætte et felt til at være optaget
    public void setFeltOptaget(int feltNR, int spillerNR, int brikID){
        felter.get(feltNR-1).setOptaget(true,spillerNR, brikID);
    }
    // Setter metode til at forlade og frigøre et felt
    public void forladFelt(int feltNR){
        felter.get(feltNR-1).setOptaget(false,0, 0);
    }
}