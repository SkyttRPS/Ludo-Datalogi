package com.company;
public class Felt {
    // Opret variabler
    int feltNR;
    int[] stjerner = {5,11,18,24,31,37,44,50,57,63,70};
    int[] globus = {8,13,21,26,34,39,47,52,60,65,73};
    int spillerPåFelt;
    int brikPåFelt;
    boolean optaget = false;
    // Felt klasse constructor
    public Felt(int feltX){
        this.feltNR = feltX;
    }
    //public int getFeltNR() {return feltNR;}
    // Getter metode til at tjekke om der er en brik på feltet
    public int getSpillerPåFelt() {
        return spillerPåFelt;
    }
    // Setter metode til at placere brik på et felt
    public void setOptaget(boolean optaget, int spillerNR, int brikID){
        this.optaget = optaget;
        this.spillerPåFelt = spillerNR;
        this.brikPåFelt = brikID;
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
    public int findNæsteGlobus(int felt){
        if (felt < 70){
            for (int j = 0; j<globus.length; j++){
                // Tjek først om felt allerede er en globus
                if (felt==globus[j]){
                    System.out.println("Du har slået en globus og rykker til " + globus[j+1]);
                    return globus[j+1];
                } else if (felt < globus[j]){ // Tjek om felt ikke er en globus
                    System.out.println("Du har slået en globus og rykker til " + globus[j]);
                    return globus[j];
                }
            }
        }
        return felt;
    }
    // Søg efter næste stjerne fra et givent felt
    public int findNæsteStjerne(int felt){
        if (felt <= 69){
            for (int j =0; j<stjerner.length; j++){
                // Tjek først om felt allerede er en stjerne
                if (felt==stjerner[j]){
                    System.out.println("Du har slået en stjerne og rykker til " + stjerner[j+1]);
                    return stjerner[j+1];
                } else if (felt<stjerner[j]){ // Tjek om felt ikke er en stjerne
                    System.out.println("Du har slået en stjerne og rykker til " + stjerner[j]);
                    return stjerner[j];
                }
            }
        }
        return felt;
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
                    System.out.println("Du har ramt en stjerne og rykker til næste stjerne");
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
    public void yell(){
        System.out.println("Den brik som er på feltet tilhører: " + spillerPåFelt);
    }
}