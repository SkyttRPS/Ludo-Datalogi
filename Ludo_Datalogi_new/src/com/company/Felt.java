package com.company;
import java.util.ArrayList; // Importer Java's ArrayList klasse
import java.util.List; // Importer Java's List interface

public class Felt {
    // Opret variabler
    int feltNR;
    int[] stjerner = {5,11,18,24,31,37,44,50,57,63,70};
    int[] globus = {8,13,21,26,34,39,47,52,60,65,73};
    int spillerPaaFelt;
    boolean optaget = false;

    // Felt klasse constructor
    public Felt(int feltX){
        this.feltNR = feltX;
    }
    //public int getFeltNR() {return feltNR;}

    // Getter metode til at tjekke om der er en brik på feltet
    public int getSpillerPaaFelt() {
        return spillerPaaFelt;
    }

    // Setter metode til at placere brik på et felt
    public void setOptaget(boolean optaget, int spillerNR){
        this.optaget = optaget;
        this.spillerPaaFelt = spillerNR;
    }

    // Tjek om der en brik eller om feltet er ledigt
    public boolean tjekBrik(){
        if (optaget){
            System.out.println("Der er allerede en brik paa dette felt ");
            return true;
        }
        else {
            System.out.println("Feltet er ledigt");
            return false;
        }
    }

    // Søg efter næste globus fra et givent felt
    public int findNaesteGlobus(int felt){
        if (felt < 70){
            for (int j = 0; j<globus.length; j++){
                // Tjek først om felt allerede er en globus
                if (felt==globus[j]){
                    System.out.println("Du har slaet en globus og rykker til " + globus[j+1]);
                    return globus[j+1];
                } else if (felt < globus[j]){ // Tjek om felt ikke er en globus
                    System.out.println("Du har slaet en globus og rykker til " + globus[j]);
                    return globus[j];
                } else {
                    System.out.print(" ");
                }
            }
        }
        return 0;
    }

    // Søg efter næste stjerne fra et givent felt
    public int findNaesteStjerne(int felt){
        if (felt < 75){
            for (int j =0; j<stjerner.length; j++){
                // Tjek først om felt allerede er en stjerne
                if (felt==stjerner[j]){
                    System.out.println("Du har slaaet en stjerne og rykker til " + stjerner[j+1]);
                    return stjerner[j+1];
                } else if (felt<stjerner[j]){ // Tjek om felt ikke er en stjerne
                    System.out.println("Du har slaaet en stjerne og rykker til " + stjerner[j]);
                    return stjerner[j];
                } else {
                    System.out.print(" ");
                }
            }
        }
        return 0;
    }

    // Tjek om et felt der er landet på er en globus
    public boolean erGlobus(int feltNR){
        if (feltNR < 74){
            for (int i = 0; i < globus.length;i++){
                if (feltNR == globus[i]){
                    System.out.println("Du har ramt en globus wuuhuu du er i sikkerhed");
                    return true;
                }
            }
        }
        return false;
    }

    // Tjek om et felt der er landet på er en stjerne
    public boolean erStjerne(int feltNR){
        if (feltNR < 74){
            for (int i = 0; i < stjerner.length; i++){

                if (feltNR == stjerner[i]){
                    System.out.println("Du har ramt en stjerne og rykker til naeste stjerne");
                    return true;
                }
            }
        }
        return false;
    }

    // Tjek om et felt er sikkert ved at tjekke om det er en globus
    public boolean erFeltSikkert(int feltNR){
        if (erGlobus(feltNR)){
            return true;
        }
        return false;
    }
}

class LavFelter{
    // Lav en ArrayList af felt objekter
    List<Felt> list = new ArrayList<>();

    // LavFelter constructor
    public LavFelter(){
        for (int i =1; i<= 76; i++){
            list.add(new Felt(i));
        }
    }

    // Setter metode for at sætte et felt til at være optaget
    public void setFeltOptaget(int feltNR, int spillerNR){
        list.get(feltNR-1).setOptaget(true,spillerNR);
    }

    // Setter metode til at forlade og frigøre et felt
    public void forladFelt(int feltNR){
        list.get(feltNR-1).setOptaget(false,0);
    }
}