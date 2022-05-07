package com.company;
import java.util.ArrayList;
import java.util.List;

public class Felt {
    int feltNR;
    int[] stjerner = {5,11,18,24,31,37,44,50,57,63,70};
    int[] globus = {8,13,21,26,34,39,47,52,60,65,73};
    int spillerPaaFelt;
    boolean optaget = false;

    public Felt(int feltX){
        this.feltNR = feltX;
    }
    //public int getFeltNR() {return feltNR;}

    public int getSpillerPaaFelt() {return spillerPaaFelt;}

    public void setOptaget(boolean optaget, int spillerNR){
        this.optaget = optaget;
        this.spillerPaaFelt = spillerNR;
    }

    public boolean tjekBrik(){
        if (optaget){
            System.out.println("Der er allerede en brik pÃ¥ dette felt ");
            return true;
        }
        else {
            System.out.println("Feltet er ledigt");
            return false;
        }
    }

    public int findNaesteGlobus(int felt){
        if (felt < 70){
            for (int j = 0; j<globus.length; j++){
                if (felt==globus[j]){
                    System.out.println("Du har slaet en globus og rykker til " + globus[j+1]);
                    return globus[j+1];
                } else if (felt < globus[j]){
                    System.out.println("Du har slaet en globus og rykker til " + globus[j]);
                    return globus[j];
                } else {
                    System.out.print(" ");
                }
            }
        }
        return 0;
    }

    public int findNaesteStjerne(int felt){
        if (felt < 75){
            for (int j =0; j<stjerner.length; j++){
                if (felt==stjerner[j]){
                    System.out.println("Du har slaet en stjerne og rykker til " + stjerner[j+1]);
                    return stjerner[j+1];
                } else if (felt<stjerner[j]){
                    System.out.println("Du har slaet en stjerne og rykker til " + stjerner[j]);
                    return stjerner[j];
                } else {
                    System.out.print(" ");
                }
            }
        }
        return 0;
    }

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

    public boolean erFeltSikkert(int feltNR){
        if (erGlobus(feltNR)){
            return true;
        }
        return false;
    }
}

class LavFelter{
    List<Felt> list = new ArrayList<>();

    public LavFelter(){
        for (int i =1; i< 76; i++){
            list.add(new Felt(i));
        }
    }

    public void setFeltOptaget(int feltNR, int spillerNR){ list.get(feltNR-1).setOptaget(true,spillerNR);}

    public void forladFelt(int feltNR){ list.get(feltNR-1).setOptaget(false,0);}
}