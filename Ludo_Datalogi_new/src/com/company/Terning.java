package com.company;
import java.util.Random; // Importerer Java's random klasse
public class Terning {
    // Opret variabler
    int øjne;
    boolean gyldigtRul;
    int rulVærdi;
    // Klasse constructor
    public Terning(int antalØjne) { this.øjne = antalØjne; }
    // Metode til at rulle med terningen
    public void rul(){
        Random random = new Random(); // lav random objekt
        rulVærdi = random.nextInt(øjne +1); // +1 for at undgå at rulle 0
        // Dobbelt sikring at terningen ikke ruller 0, hvis det er tilfældet sættes gyldigt rul til false
        if (rulVærdi == 0){
            gyldigtRul = false;
        } else {
            gyldigtRul = true;
        }
        // Tjek om rullet er en normal værdi, globus eller stjerne og prompter hvad man har slået
        if (gyldigtRul){
            if (rulVærdi != 5 && rulVærdi != 3){
                System.out.println("Du har slået en " + rulVærdi);
            } else if (rulVærdi == 5){
                System.out.println("Du har slået en globus!");
            }
            else {
                System.out.println("Du har slået en stjerne!");
            }
        } else {
            rul(); // Slå igen hvis slaget ikke er gyldigt
        }
    }
    // Get rul værdi
    public int addRul(){
        int tilføjRul = 0;
        if (gyldigtRul){
            tilføjRul = rulVærdi;
            return tilføjRul;
        } else {
            tilføjRul = 0;
            return tilføjRul;
        }
    }
    // Tjekker ja eller nej om rul er en globus
    public boolean globusTjek(){
        if (rulVærdi == 5){
            return true;
        } else {
            return false;
        }
    }
    // Tjekker ja eller nej om rul er en stjerne
    public boolean stjerneTjek(){
        if (rulVærdi == 3){
            return true;
        } else {
            return false;
        }
    }
}