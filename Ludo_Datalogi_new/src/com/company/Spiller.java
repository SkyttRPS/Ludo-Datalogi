package com.company;
import java.util.ArrayList;
import java.util.List;

class Brik{
    private int brikID;
    private int felt = 0;
    private String farve;
    public boolean iMael;

    public Brik(int bID, String farveID){
        this.brikID =bID;
        this.farve = farveID;
    }

    public int getBrikID() {return brikID;}

    public int getLokation() {return felt;}

    public void setFelt(int feltPlus) {
        if (iMael) return;
        if (this.felt + feltPlus >= 74){
            this.felt = 74;
            iMael = true;
        } else {
            this.felt = this.felt + feltPlus;
        }
    }

    public void setFeltSituation(int feltPlus) {
        if (iMael) return;
        this.felt = feltPlus;
    }

    public boolean brikIMael(){
        if (iMael) return true;
        return false;
    }
}

public class Spiller {
    int spillernummer;
    String spillerfarve;
    //int spillerPoint;
    List<Brik> brikker = new ArrayList<>();

    public Spiller(int spillerNR){
        this.spillernummer = spillerNR;
        if (spillernummer==1) spillerfarve = "roed";
        if (spillernummer==2) spillerfarve = "blae";
        if (spillernummer==3) spillerfarve = "groen";
        if (spillernummer==4) spillerfarve = "gul";
        for (int i =1;i<5;i++){
            brikker.add(new Brik(i,spillerfarve));
        }
    }

    public String getSpillerfarve() {return spillerfarve;}

    public void hvorErBrikker(){
        for (int i = 0; i<4;i++){
            System.out.println("Brik " + brikker.get(i).getBrikID() + " er pÃ¥ felt "+ brikker.get(i).getLokation());
        }
    }
}

class LavSpillere{
    List<Spiller> list = new ArrayList<>();
    public LavSpillere(){
        for (int i =1;i<5;i++){
            list.add(new Spiller(i));
        }
    }
    public String spillerensfarve(int spillerNR){
        String spillerensfarve = list.get(spillerNR-1).getSpillerfarve();
        return spillerensfarve;
    }

    public void slaeHjem(int spillerNR, int brikID){
        list.get(spillerNR-1).brikker.get(brikID-1).setFeltSituation(0);
    }
}