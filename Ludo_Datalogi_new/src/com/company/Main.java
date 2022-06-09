package com.company;
import java.util.Scanner; // Importer Java's Scanner klasse
import java.util.Random; // Importer Java's Random klasse

class SpilController {
    // Opret variabler
    Terning terning = new Terning(6);
    LavSpillere spillere = new LavSpillere();
    LavFelter board = new LavFelter();
    Scanner scan = new Scanner(System.in);

    // Tur constructor
    public SpilController(){
        System.out.println("Spillet er startet!!!");
        System.out.println("Roed starter spillet, skriv r for at slaa med terningen");
        System.out.println("Naar en spiller har skrevet r vil du skulle vaelge en af dine med et tal i mellem 1-4");
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
        if (terning.globustjek()){
            brikRyk.setFeltSituation(board.list.get(spillerNR).findNaesteGlobus(brikRyk.getLokation()));
        // Tjekker om der er slået en stjerne, finder og rykker til næste stjerne
        } else if (terning.stjernetjek()){
            //
            if (board.list.get(spillerNR).findNaesteStjerne(brikRyk.getLokation()) == 0) return;
            brikRyk.setFeltSituation(board.list.get(spillerNR).findNaesteStjerne(brikRyk.getLokation()));
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

        if (board.list.get(spillerNR).findNaesteStjerne(brikRyk.getLokation()) == 0) return;
        brikRyk.setFeltSituation(board.list.get(spillerNR).findNaesteStjerne(brikRyk.getLokation()));
    }

    // Opdaterer alle situationer på et felt
    void opdaterFelt(int feltNR, int spillerNR,int brikID){
        Felt plads = board.list.get(feltNR-1);
        int usikkerBrik = spillere.sp.get(plads.getSpillerPaaFelt()).brikker.get(brikID-1).getBrikID();
        int sikkerBrik = spillere.sp.get(spillerNR-1).brikker.get(brikID-1).getBrikID();

        // Tjekker om der er en brik på feltet og feltets type
        if (plads.tjekBrik()){
            // Tjekker om feltet er en globus
            if (plads.erFeltSikkert(feltNR)){
                System.out.println("Du ramte en globus hvor " + plads.getSpillerPaaFelt() + " staar, din brik bliver slaet hjem til foerste felt!");
                // Slår spilleren som lander på feltet hjem
                spillere.slaaHjem(plads.getSpillerPaaFelt(), usikkerBrik);
            } else {
                // Slår spilleren der er på feltet hjem
                spillere.slaaHjem(spillerNR, sikkerBrik);
                System.out.println("Du har slået " + spillere.spillerensFarve(spillerNR) + " hjem til foerste felt -Wuuhuu- ");
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
        return spillere.sp.get(spillerNR - 1).brikker.get(0).brikIMael()
                && spillere.sp.get(spillerNR - 1).brikker.get(1).brikIMael()
                && spillere.sp.get(spillerNR - 1).brikker.get(2).brikIMael()
                && spillere.sp.get(spillerNR - 1).brikker.get(3).brikIMael();
    }

    // Metode til at vælge en brik
    int vaelgBrik() throws Exception{
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

// Main klasse
public class Main {

    public static void main(String[] args) {
        // Opret variabler og objekter
        SpilController spil = new SpilController();
        Scanner scan = new Scanner(System.in);
        int turTaeller = 1;
        String mode = setup();
        spil.hvisTur(turTaeller);
        String tekst = scan.nextLine();

        // While loop der kører til spillet er "slukket"
        while (!tekst.trim().equalsIgnoreCase("sluk")){
            String farve = spil.spillere.spillerensFarve(turTaeller);

            if (turTaeller==1){
                if (spil.harVundet(turTaeller)){
                    System.out.println(farve + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                } else {
                    turTaeller = getTurTaeller(spil ,turTaeller,tekst, mode);
                }
            } else if (turTaeller == 2){
                if (spil.harVundet(turTaeller)){
                    System.out.println(farve + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                } else {
                    turTaeller = getTurTaeller(spil ,turTaeller,tekst, mode);
                }
            } else if (turTaeller == 3){
                if (spil.harVundet(turTaeller)){
                    System.out.println(farve + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                } else {
                    turTaeller = getTurTaeller(spil ,turTaeller,tekst, mode);
                }
            } else if (turTaeller ==4){
                if (spil.harVundet(turTaeller)){
                    System.out.println(farve + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                } else {
                    turTaeller = getTurTaeller(spil ,turTaeller,tekst, mode);
                }
            } else {
                System.out.println("Der er sket en fejl");
            }
            spil.hvisTur(turTaeller);
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
    private static int getTurTaeller(SpilController spil , int turTaeller, String tekst, String mode){
        int brik = 0;
        Random r = new Random();
        System.out.println("Spiltilstand: " + mode);

        // Stiller krav til at et input skal være specifikt før der kan slås med terningen
        if (tekst.trim().equalsIgnoreCase("r")){
            // Printer alle brikkers placering
            for (int i=0;i<4;i++){
                System.out.println(spil.spillere.sp.get(i).getSpillerfarve() + " Brikker er på følgene felter:");
                spil.spillere.sp.get(i).hvorErBrikker();
                System.out.println(" ");
            }
            System.out.println("\n");
            System.out.println("Vælg brik med et tal mellem 1-4");

            // Switch case til at vælge om man vil automatisere spiller 2-4's valg af brikker
            switch (mode) {
                case "single":
                    if (turTaeller == 1) {
                        try {
                            brik = spil.vaelgBrik();
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
                        brik = spil.vaelgBrik();
                    } catch (Exception ex) {
                        // Fanger hvis inputtet ikke er et tal
                        System.out.println("Du skrev ikke et tal");
                    }
                    break;
            }

            // Varibel til at få en brik's lokation
            int lokation = spil.spillere.sp.get(turTaeller-1).brikker.get(brik-1).getLokation();

            // Fjerner en valgt brik fra et felt
            if (lokation != 0) {
                spil.forladFelt(lokation);
            }
            // Rykker brikken
            spil.rykBrik(turTaeller,brik);

            // Opdaterer feltet
            if (lokation != 0) spil.opdaterFelt(lokation,turTaeller,brik);

            // Tjekker om slaget ikke er en stjerne
            if (!spil.terning.stjernetjek()){
                // Tjekker om feltet der landes på er en stjerne
                if (spil.board.list.get(lokation).erStjerne(lokation)){
                    // Forlader feltet
                    if (lokation != 0) spil.forladFelt(lokation);
                    // Rykker
                    spil.rykBrikStjerneSituation(turTaeller,brik);
                    // Opdaterer nyt felt
                    if (lokation != 0) spil.opdaterFelt(lokation,turTaeller,brik);
                }
            }
            // Tjekker om slaget er en globus
            if (spil.terning.globustjek()){
                // Fortæller at der skal vælges en brik
                System.out.println("Du har slaet en globus og ruller terningen igen, skriv et tal mellem 1-4");
                getTurTaeller(spil ,turTaeller,tekst, mode);
            }

            // Fortæller at turen går ver til næste spiller
            System.out.println("Turen Skifter \n");
            if (turTaeller == 4){
                turTaeller = 1;
                return turTaeller;
            }
            turTaeller++;
        } else {
            System.out.println("Du skal skrive rul (r)!");
        }
        return turTaeller;
    }
}