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
        String tekst = scan.nextLine();

        // While loop der kører til spillet er "slukket"
        while (!tekst.trim().equalsIgnoreCase("sluk")){
            String farve = spil.spillere.spillerensFarve(turTæller);

            if (turTæller==1){
                if (spil.harVundet(turTæller)){
                    System.out.println(farve + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                } else {
                    turTæller = getTurTæller(spil ,turTæller,tekst, mode);
                }
            } else if (turTæller == 2){
                if (spil.harVundet(turTæller)){
                    System.out.println(farve + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                } else {
                    turTæller = getTurTæller(spil ,turTæller,tekst, mode);
                }
            } else if (turTæller == 3){
                if (spil.harVundet(turTæller)){
                    System.out.println(farve + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                } else {
                    turTæller = getTurTæller(spil ,turTæller,tekst, mode);
                }
            } else if (turTæller ==4){
                if (spil.harVundet(turTæller)){
                    System.out.println(farve + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                } else {
                    turTæller = getTurTæller(spil ,turTæller,tekst, mode);
                }
            } else {
                System.out.println("Der er sket en fejl");
            }
            spil.hvisTur(turTæller);
            tekst = scan.nextLine();
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
        } else {
            return "multi";
        }
    }

    // Opdaterer turen
    private static int getTurTæller(SpilController spil , int turTæller, String tekst, String mode){
        int brik = 0;
        Random r = new Random();
        System.out.println("Spiltilstand: " + mode);

        // Stiller krav til at et input skal være specifikt før der kan slås med terningen
        if (tekst.trim().equalsIgnoreCase("r")){
            // Printer alle brikkers placering
            for (int i=0;i<4;i++){
                System.out.println(spil.spillere.sp.get(i).getSpillerFarve() + " Brikker er på følgende felter:");
                spil.spillere.sp.get(i).hvorErBrikker();
                System.out.println(" ");
            }
            System.out.println("\n");
            System.out.println("Vælg brik med et tal mellem 1-4");

            // Switch case til at vælge om man vil automatisere spiller 2-4's valg af brikker
            switch (mode) {
                case "single":
                    if (turTæller == 1) {
                        try {
                            brik = spil.vælgBrik();
                        } catch (Exception ex) {
                            // Fanger hvis inputtet ikke er et tal
                            System.out.println("Du skrev ikke et tal");
                        }
                    } else {
                        brik = r.nextInt(4+1);
                    }
                    break;
                case "multi":
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

            // Fjerner en valgt brik fra et felt
            if (lokation != 0) {
                spil.forladFelt(lokation);
            }
            // Rykker brikken
            spil.rykBrik(turTæller,brik);

            // Opdaterer feltet
            if (lokation != 0) spil.opdaterFelt(lokation,turTæller,brik);

            // Tjekker om slaget ikke er en stjerne
            if (!spil.terning.stjerneTjek()){
                // Tjekker om feltet der landes på er en stjerne
                if (spil.board.felter.get(lokation).erStjerne(lokation)){
                    // Forlader feltet
                    if (lokation != 0) spil.forladFelt(lokation);
                    // Rykker
                    spil.rykBrikStjerneSituation(turTæller,brik);
                    // Opdaterer nyt felt
                    if (lokation != 0) spil.opdaterFelt(lokation,turTæller,brik);
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