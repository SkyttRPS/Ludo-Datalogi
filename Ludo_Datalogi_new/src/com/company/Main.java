package com.company;
import java.util.Scanner; // Importer Java's Scanner klasse
import java.util.Random; // Importer Java's Random klasse
// Main klasse
public class Main {
    public static void main(String[] args) {
        // Opret variabler og objekter
        SpilController spil = new SpilController();
        Scanner scan = new Scanner(System.in);
        int turTæller = 1;
        String mode = setup();
        spil.hvisTur(turTæller);
        String tekst = "r"; //scan.nextLine()"";
        // While loop der kører til spillet er "slukket"
        while (!tekst.trim().equalsIgnoreCase("sluk")){
            String farve = spil.spillere.spillerensFarve(turTæller);
            if (turTæller==1){
                if (spil.harVundet(turTæller)){
                    System.out.println(farve + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                    tekst = scan.nextLine();
                } else {
                    turTæller = getTurTæller(spil ,turTæller,tekst, mode);
                }
            } else if (turTæller == 2){
                if (spil.harVundet(turTæller)){
                    System.out.println(farve + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                    tekst = scan.nextLine();
                } else {
                    turTæller = getTurTæller(spil ,turTæller,tekst, mode);
                }
            } else if (turTæller == 3){
                if (spil.harVundet(turTæller)){
                    System.out.println(farve + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                    tekst = scan.nextLine();
                } else {
                    turTæller = getTurTæller(spil ,turTæller,tekst, mode);
                }
            } else if (turTæller ==4){
                if (spil.harVundet(turTæller)){
                    System.out.println(farve + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                    tekst = scan.nextLine();
                } else {
                    turTæller = getTurTæller(spil ,turTæller,tekst, mode);
                }
            } else {
                System.out.println("Der er sket en fejl");
            }
            spil.hvisTur(turTæller);
        }
    }
    // Funktion til at definere spiltilstand, for at spille mod computeren eller med andre
    static String setup() {
        System.out.println("Skriv single for at spille mod computeren, alt andet for at spille med venner");
        // Input og tekst variabel
        Scanner s = new Scanner(System.in);
        String tekst = s.nextLine();
        // Tjek om input er single eller alt andet for multi
        if(tekst.trim().equalsIgnoreCase("single")) {
            return "single";
        } else if (tekst.trim().equalsIgnoreCase("test")){
            return "test";
        } else {
            return "multi";
        }
    }
    private static int getTurTæller(SpilController spil , int turTæller, String tekst, String mode){
        int brik = 0;
        Random r = new Random();
        // Stiller krav til at et input skal være specifikt før der kan slås med terningen
        if (tekst.trim().equalsIgnoreCase("r")){
            System.out.println("\n");
            // Switch case til at vælge om man vil automatisere spiller 2-4's valg af brikker + fuld testmode hvor spillet simuleres fra start til slut1
            switch (mode) {
                case "single":
                    if (turTæller == 1) {
                        for (int i=0;i<4;i++){
                            System.out.println(spil.spillere.sp.get(i).getSpillerFarve() + " Brikker er på følgende felter:");
                            spil.spillere.sp.get(i).hvorErBrikker();
                            System.out.println(" ");
                        }
                        System.out.println("\n");
                        try {
                            brik = spil.vælgBrik();
                        } catch (Exception ex) {
                            // Fanger hvis inputtet ikke er et tal
                            System.out.println("Du skrev ikke et tal");
                        }
                    } else {
                        brik = spil.AIrul();
                    }
                    break;
                case "test":
                    brik = spil.AIrul();
                    break;
                case "multi":
                    for (int i=0;i<4;i++){
                        System.out.println(spil.spillere.sp.get(i).getSpillerFarve() + " Brikker er på følgende felter:");
                        spil.spillere.sp.get(i).hvorErBrikker();
                        System.out.println(" ");
                    }
                    System.out.println("\n");
                    try {
                        brik = spil.vælgBrik();
                    } catch (Exception ex) {
                        // Fanger hvis inputtet ikke er et tal
                        System.out.println("Du skrev ikke et tal");
                    }
                    break;
            }
            // Variabel til at få en brik's lokation
            int lokation = spil.spillere.sp.get(turTæller-1).brikker.get(brik-1).getLokation();
            if (lokation == 76){
                if (turTæller == 4){
                    turTæller = 1;
                    return turTæller;
                }
                turTæller++;
                return turTæller;
            }
            System.out.println("lokation er: "+ lokation);
            // Fjerner en valgt brik fra et felt
            if (lokation != 0) {
                spil.forladFelt(lokation);
            }
            // Rykker brikken
            spil.rykBrik(turTæller,brik);
            lokation = spil.spillere.sp.get(turTæller-1).brikker.get(brik-1).getLokation();
            // Opdaterer feltet
            if (lokation != 0) spil.opdaterFelt(lokation,turTæller,brik);
            // Tjekker om slaget ikke er en stjerne
            if (!spil.terning.stjerneTjek()){
                // Tjekker om feltet der landes på er en stjerne
                if (lokation==76){
                    System.out.println("Do nothing");
                } else {
                    if (spil.board.felter.get(lokation).erStjerne(lokation)){
                        // Forlader feltet
                        if (lokation != 0) spil.forladFelt(lokation);
                        // Rykker
                        spil.rykBrikStjerneSituation(turTæller,brik);
                        // Opdaterer nyt felt
                        if (lokation != 0) spil.opdaterFelt(lokation,turTæller,brik);
                    }
                }

            }
            // Tjekker om slaget er en globus
            if (spil.terning.globusTjek()){
                // Fortæller at der skal vælges en brik
                System.out.println("Du har slået en globus og ruller terningen igen, skriv et tal mellem 1-4");
                getTurTæller(spil ,turTæller,tekst, mode);
            }
            // Fortæller at turen går ver til næste spiller
            System.out.println("Turen Skifter \n");
            if (turTæller == 4){
                turTæller = 1;
                return turTæller;
            }
            turTæller++;
        } else {
            System.out.println("Du skal skrive rul (r)!");
        }
        return turTæller;
    }
}