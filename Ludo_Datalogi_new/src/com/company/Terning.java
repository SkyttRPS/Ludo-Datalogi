package com.company;
import java.util.Random;

public class Terning {
    int ojne;
    boolean gyldigtrul;
    int rulvardi;

    public Terning(int aOjne) { this.ojne = aOjne; }

    public void rul(){
        Random random = new Random();
        rulvardi = random.nextInt(ojne+1);
        if (rulvardi == 0){
            gyldigtrul = false;
        } else {
            gyldigtrul = true;
        }
        if (gyldigtrul){
            if (rulvardi != 5 && rulvardi != 3){
                System.out.println("Du har slaet en " + rulvardi);
            } else if (rulvardi == 5){
                System.out.println("Du har slaet en globus!");
            }
            else {
                System.out.println("Du har slaet en stjerne!");
            }
        } else {
            rul();
        }
    }

    public int addRul(){
        int merRul = 0;
        if (gyldigtrul){
            merRul = rulvardi;
            return merRul;
        } else {
            merRul = 0;
            return merRul;
        }
    }

    public boolean globustjek(){
        if (rulvardi == 5){
            return true;
        } else {
            return false;
        }
    }

    public boolean stjernetjek(){
        if (rulvardi == 3){
            return true;
        } else {
            return false;
        }
    }
}