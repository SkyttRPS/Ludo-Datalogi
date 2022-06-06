package com.company;
import java.util.Scanner; // Import Java's Scanner class for user input

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
        // Fortæller hvilken brik og hvilket felt der rykkes fra
        System.out.println("Rykker brik " + spillere.list.get(spillerNR-1).brikker.get(brikNR-1).getBrikID() + " fra felt " + spillere.list.get(spillerNR-1).brikker.get(brikNR-1).getLokation());
        // Tjekker om der er slået en globus, finder og rykker til næste globus
        if (terning.globustjek()){
            spillere.list.get(spillerNR-1).brikker.get(brikNR-1).setFeltSituation(board.list.get(spillerNR).findNaesteGlobus(spillere.list.get(spillerNR-1).brikker.get(brikNR-1).getLokation()));
        // Tjekker om der er slået en stjerne, finder og rykker til næste stjerne
        } else if (terning.stjernetjek()){
            //
            if (board.list.get(spillerNR).findNaesteStjerne(spillere.list.get(spillerNR-1).brikker.get(brikNR-1).getLokation()) == 0) return;
            spillere.list.get(spillerNR-1).brikker.get(brikNR-1).setFeltSituation(board.list.get(spillerNR).findNaesteStjerne(spillere.list.get(spillerNR-1).brikker.get(brikNR-1).getLokation()));
        // Hvis det ikke er tilfældet rykkes med terningens antal øjne
        } else {
            spillere.list.get(spillerNR-1).brikker.get(brikNR-1).setFelt(terning.addRul());
        }
        // Fortæller til hvilket felt der rykkes
        System.out.println(" til felt " + spillere.list.get(spillerNR-1).brikker.get(brikNR-1).getLokation());
    }

    // Metode til at hoppe videre hvis der landes på en stjerne
    void rykBrikStjerneSituation(int spillerNR, int brikNR){
        if (board.list.get(spillerNR).findNaesteStjerne(spillere.list.get(spillerNR-1).brikker.get(brikNR-1).getLokation()) == 0) return;
        spillere.list.get(spillerNR-1).brikker.get(brikNR-1).setFeltSituation(board.list.get(spillerNR).findNaesteStjerne(spillere.list.get(spillerNR-1).brikker.get(brikNR-1).getLokation()));
    }

    // Opdaterer alle situationer på et felt
    void opdaterFelt(int feltNR, int spillerNR,int brikID){
        // Tjekker om der er en brik på feltet og feltets type
        if (board.list.get(feltNR-1).tjekBrik()){
            // Tjekker om feltet er en globus
            if (board.list.get(feltNR-1).erFeltSikkert(feltNR)){
                System.out.println("Du ramte en globus hvor der allerede staer en brik bliver slaet hjem til foerste felt!");
                // Slår spilleren som lander på feltet hjem
                spillere.slaaHjem(board.list.get(feltNR-1).getSpillerPaaFelt(),spillere.list.get(board.list.get(feltNR-1).getSpillerPaaFelt()).brikker.get(brikID-1).getBrikID());
            } else {
                // Slår spilleren der er på feltet hjem
                spillere.slaaHjem(spillerNR,spillere.list.get(spillerNR-1).brikker.get(brikID-1).getBrikID());
                System.out.println("Du har slået brikID hjem til foerste felt -Wuuhuu- ");
            }
        // Sætter brik på felt
        } else {
            board.setFeltOptaget(spillere.list.get(spillerNR-1).brikker.get(brikID-1).getLokation(),spillerNR);
        }
    }

    // Fjerner brik fra felt
    void forladFelt(int feltNR){
        board.forladFelt(feltNR);
    }

    // Metode til at tjekke om en spiller har vundet
    public boolean harVundet(int spillerNR){
        //  Tjekker om all en spillers fire brikker i mål
        return spillere.list.get(spillerNR - 1).brikker.get(0).brikIMael()
                && spillere.list.get(spillerNR - 1).brikker.get(1).brikIMael()
                && spillere.list.get(spillerNR - 1).brikker.get(2).brikIMael()
                && spillere.list.get(spillerNR - 1).brikker.get(3).brikIMael();
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
        spil.hvisTur(turTaeller);
        String tekst = scan.nextLine();

        // While loop der kører til spillet er "slukket"
        while (!tekst.trim().equalsIgnoreCase("sluk")){
            if (turTaeller==1){
                if (spil.harVundet(turTaeller)){
                    System.out.println(spil.spillere.spillerensFarve(turTaeller) + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                } else {
                    turTaeller = getTurTaeller(spil ,turTaeller,tekst);
                }
            } else if (turTaeller == 2){
                if (spil.harVundet(turTaeller)){
                    System.out.println(spil.spillere.spillerensFarve(turTaeller) + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                } else {
                    turTaeller = getTurTaeller(spil ,turTaeller,tekst);
                }
            } else if (turTaeller == 3){
                if (spil.harVundet(turTaeller)){
                    System.out.println(spil.spillere.spillerensFarve(turTaeller) + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                } else {
                    turTaeller = getTurTaeller(spil ,turTaeller,tekst);
                }
            } else if (turTaeller ==4){
                if (spil.harVundet(turTaeller)){
                    System.out.println(spil.spillere.spillerensFarve(turTaeller) + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                } else {
                    turTaeller = getTurTaeller(spil ,turTaeller,tekst);
                }
            } else {
                System.out.println("Der er sket en fejl");
            }
            spil.hvisTur(turTaeller);
            tekst = scan.nextLine();
        }
    }

    // Opdaterer turen
    private static int getTurTaeller(SpilController spil , int turTaeller, String tekst){
        int brik = 0;

        // Stiller krav til at et input skal være specifikt før der kan slås med terningen
        if (tekst.trim().equalsIgnoreCase("r")){
            // Printer alle brikkers placering
            for (int i=0;i<4;i++){
                System.out.println(spil.spillere.list.get(i).getSpillerfarve() + " Brikker er på følgene felter:");
                spil.spillere.list.get(i).hvorErBrikker();
                System.out.println(" ");
            }
            System.out.println("\n");
            System.out.println("Vælg brik med et tal mellem 1-4");

            // Prøver at køre vælgbrik metoden
            try{
                brik = spil.vaelgBrik();
            } catch (Exception ex) {
                // Fanger hvis inputtet ikke er et tal
                System.out.println("Du skrev ikke et tal");
            }
            // Fjerner en valgt brik fra et felt
            if (spil.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation() != 0) {
                spil.forladFelt(spil.spillere.list.get(turTaeller - 1).brikker.get(brik - 1).getLokation());
            }
            // Rykker brikken
            spil.rykBrik(turTaeller,brik);

            // Opdaterer feltet
            if (spil.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation() != 0) spil.opdaterFelt(spil.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation(),turTaeller,brik);

            // Tjekker om slaget ikke er en stjerne
            if (!spil.terning.stjernetjek()){
                // Tjekker om feltet der landes på er en stjerne
                if (spil.board.list.get(spil.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation()).erStjerne(spil.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation())){
                    // Forlader feltet
                    if (spil.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation() != 0) spil.forladFelt(spil.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation());
                    // Rykker
                    spil.rykBrikStjerneSituation(turTaeller,brik);
                    // Opdaterer nyt felt
                    if (spil.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation() != 0) spil.opdaterFelt(spil.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation(),turTaeller,brik);
                }
            }
            // Tjekker om slaget er en globus
            if (spil.terning.globustjek()){
                // Fortæller at der skal vælges en brik
                System.out.println("Du har slaet en globus og ruller terningen igen, skriv et tal mellem 1-4");
                getTurTaeller(spil ,turTaeller,tekst);
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