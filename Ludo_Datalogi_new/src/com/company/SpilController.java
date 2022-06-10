package com.company;

import java.util.Scanner;

class SpilController {
    // Opret variabler
    Terning terning = new Terning(6);
    SpillerModul spillere = new SpillerModul();
    FeltModul board = new FeltModul();
    Scanner scan = new Scanner(System.in);

    // Tur constructor
    public SpilController(){
        System.out.println("Spillet er startet!!!");
        System.out.println("Rød starter spillet, skriv r for at slå med terningen");
        System.out.println("Når en spiller har skrevet r vil du skulle vælge en af dine med et tal i mellem 1-4");
    }

    // Metode til at printe hvis tur det er
    void hvisTur(int i){
        System.out.println("Det er " + spillere.spillerensFarve(i) + " tur");
    }

    // Metode til at rykke brik
    void rykBrik(int spillerNR, int brikNR){
        Brik brikRyk = spillere.sp.get(spillerNR-1).brikker.get(brikNR-1);

        // Fortæller hvilken brik og hvilket felt der rykkes fra
        System.out.println("Rykker brik " + brikRyk.getBrikID() + " fra felt " + brikRyk.getLokation());
        // Tjekker om der er slået en globus, finder og rykker til næste globus
        if (terning.globusTjek()){
            brikRyk.setFeltSituation(board.felter.get(spillerNR).findNæsteGlobus(brikRyk.getLokation()));
            // Tjekker om der er slået en stjerne, finder og rykker til næste stjerne
        } else if (terning.stjerneTjek()){
            //
            if (board.felter.get(spillerNR).findNæsteStjerne(brikRyk.getLokation()) == 0) return;
            brikRyk.setFeltSituation(board.felter.get(spillerNR).findNæsteStjerne(brikRyk.getLokation()));
            // Hvis det ikke er tilfældet rykkes med terningens antal øjne
        } else {
            brikRyk.setFelt(terning.addRul());
        }
        // Fortæller til hvilket felt der rykkes
        System.out.println(" til felt " + brikRyk.getLokation());
    }

    // Metode til at hoppe videre hvis der landes på en stjerne
    void rykBrikStjerneSituation(int spillerNR, int brikNR){
        Brik brikRyk = spillere.sp.get(spillerNR-1).brikker.get(brikNR-1);

        if (board.felter.get(spillerNR).findNæsteStjerne(brikRyk.getLokation()) == 0) return;
        brikRyk.setFeltSituation(board.felter.get(spillerNR).findNæsteStjerne(brikRyk.getLokation()));
    }

    // Opdaterer alle situationer på et felt
    void opdaterFelt(int feltNR, int spillerNR,int brikID){
        Felt plads = board.felter.get(feltNR-1);
        int usikkerBrik = spillere.sp.get(plads.getSpillerPåFelt()).brikker.get(brikID-1).getBrikID();
        int sikkerBrik = spillere.sp.get(spillerNR-1).brikker.get(brikID-1).getBrikID();

        // Tjekker om der er en brik på feltet og feltets type
        if (plads.tjekBrik()){
            // Tjekker om feltet er en globus
            if (plads.erFeltSikkert(feltNR)){
                System.out.println("Du ramte en globus hvor " + plads.getSpillerPåFelt() + " står, din brik bliver slåt hjem til første felt!");
                // Slår spilleren som lander på feltet hjem
                spillere.slåHjem(plads.getSpillerPåFelt(), usikkerBrik);
            } else {
                // Slår spilleren der er på feltet hjem
                spillere.slåHjem(spillerNR, sikkerBrik);
                System.out.println("Du har slået " + spillere.spillerensFarve(spillerNR) + " hjem til første felt -Wuuhuu- ");
            }
            // Sætter brik på felt
        } else {
            board.setFeltOptaget(spillere.sp.get(spillerNR-1).brikker.get(brikID-1).getLokation(),spillerNR);
        }
    }

    // Fjerner brik fra felt
    void forladFelt(int feltNR){
        board.forladFelt(feltNR);
    }

    // Metode til at tjekke om en spiller har vundet
    public boolean harVundet(int spillerNR){
        //  Tjekker om all en spillers fire brikker i mål
        return spillere.sp.get(spillerNR - 1).brikker.get(0).brikIMål()
                && spillere.sp.get(spillerNR - 1).brikker.get(1).brikIMål()
                && spillere.sp.get(spillerNR - 1).brikker.get(2).brikIMål()
                && spillere.sp.get(spillerNR - 1).brikker.get(3).brikIMål();
    }

    // Metode til at vælge en brik
    public int vælgBrik() throws Exception{
        terning.rul();
        while (scan.hasNext()){
            if (scan.hasNextInt()){
                int brik = scan.nextInt();
                if (0<brik && brik < 5){
                    return brik;
                }
            } else {
                scan.next();
            }
            System.out.println("Du skal skrive et tal imellem 1-4!!!!");
        }
        throw new Exception("SKRIV NOGET MELLEM 1-4!!!");
    }

}