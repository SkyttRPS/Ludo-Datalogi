package com.company;
import java.util.Random; // Importerer Java's random klasse

public class Terning {
    // Opret variabler
    int ojne;
    boolean gyldigtrul;
    int rulvardi;

    // Klasse constructor
    public Terning(int aOjne) { this.ojne = aOjne; }

    // Metode til at rulle med terningen
    public void rul(){
        Random random = new Random(); // lav random objekt
        rulvardi = random.nextInt(ojne+1); // +1 for at undgå at rulle 0

        // Dobbel sikring at terningen ikke ruller 0, hvis det er tilfældet sættes gyldigt rul til false
        if (rulvardi == 0){
            gyldigtrul = false;
        } else {
            gyldigtrul = true;
        }

        // Tjek om rullet er en normal værdi, globus eller stjerne og prompter hvad man har slået
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
            rul(); // Slå igen hvis slaget ikke er gyldigt
        }
    }

    // Get rul værdi
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

    // Tjekker ja eller nej om rul er en globus
    public boolean globustjek(){
        if (rulvardi == 5){
            return true;
        } else {
            return false;
        }
    }

    // Tjekker ja eller nej om rul er en stjerne
    public boolean stjernetjek(){
        if (rulvardi == 3){
            return true;
        } else {
            return false;
        }
    }
}