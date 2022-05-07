package com.company;
import java.util.Scanner;

class Tur{
    Terning terning = new Terning(6);
    LavSpillere spillere = new LavSpillere();
    LavFelter board = new LavFelter();
    Scanner scan = new Scanner(System.in);

    public Tur(){
        System.out.println("Spillet er startet!!!");
        System.out.println("Roed starter spillet, skriv r for at slae med terningen");
        System.out.println("Naer en spiller har skrevet r vil du skulle vaelge en af dine med et tal i mellem 1-4");
    }

    void hvisTur(int i){
        System.out.println("Det er " + spillere.spillerensfarve(i) + " tur");
    }

    void rykBrik2(int spillerNR,int brikNR){
        System.out.println("Rykker brik " + spillere.list.get(spillerNR-1).brikker.get(brikNR-1).getBrikID() + " fra felt " + spillere.list.get(spillerNR-1).brikker.get(brikNR-1).getLokation());
        if (terning.globustjek()){
            spillere.list.get(spillerNR-1).brikker.get(brikNR-1).setFeltSituation(board.list.get(spillerNR).findNaesteGlobus(spillere.list.get(spillerNR-1).brikker.get(brikNR-1).getLokation()));
        } else if (terning.stjernetjek()){
            if (board.list.get(spillerNR).findNaesteStjerne(spillere.list.get(spillerNR-1).brikker.get(brikNR-1).getLokation()) == 0) return;
            spillere.list.get(spillerNR-1).brikker.get(brikNR-1).setFeltSituation(board.list.get(spillerNR).findNaesteStjerne(spillere.list.get(spillerNR-1).brikker.get(brikNR-1).getLokation()));
        } else {
            spillere.list.get(spillerNR-1).brikker.get(brikNR-1).setFelt(terning.addRul());
        }
        System.out.println(" til felt " + spillere.list.get(spillerNR-1).brikker.get(brikNR-1).getLokation());
    }

    void rykBrikStjerneSituation(int spillerNR, int brikNR){
        if (board.list.get(spillerNR).findNaesteStjerne(spillere.list.get(spillerNR-1).brikker.get(brikNR-1).getLokation()) == 0) return;
        spillere.list.get(spillerNR-1).brikker.get(brikNR-1).setFeltSituation(board.list.get(spillerNR).findNaesteStjerne(spillere.list.get(spillerNR-1).brikker.get(brikNR-1).getLokation()));
    }

    void opdaterFelt(int feltNR, int spillerNR,int brikID){
        if (board.list.get(feltNR-1).tjekBrik()){
            if (board.list.get(feltNR-1).erFeltSikkert(feltNR)){
                System.out.println("Du ramte en globus hvor der allerede staer en brik bliver slaet hjem til foerste felt!");
                spillere.slaeHjem(board.list.get(feltNR-1).getSpillerPaaFelt(),spillere.list.get(board.list.get(feltNR-1).getSpillerPaaFelt()).brikker.get(brikID-1).getBrikID());
            } else {
                spillere.slaeHjem(spillerNR,spillere.list.get(spillerNR-1).brikker.get(brikID-1).getBrikID());
                System.out.println("Du er blevet slaer X hjem til foerste felt -Wuuhuu- ");
            }
        } else {
            board.setFeltOptaget(spillere.list.get(spillerNR-1).brikker.get(brikID-1).getLokation(),spillerNR);
        }
    }

    void forladFelt(int feltNR){ board.forladFelt(feltNR);}

    public boolean harVundet(int spillerNR){
        if (spillere.list.get(spillerNR-1).brikker.get(0).brikIMael()
                && spillere.list.get(spillerNR-1).brikker.get(1).brikIMael()
                && spillere.list.get(spillerNR-1).brikker.get(2).brikIMael()
                && spillere.list.get(spillerNR-1).brikker.get(3).brikIMael()) return true;
        return false;
    }

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

public class Main {

    public static void main(String[] args) {
        Tur tur = new Tur();
        Scanner scan = new Scanner(System.in);
        int turTaeller = 1;
        tur.hvisTur(turTaeller);
        String tekst = scan.nextLine();

        while (!tekst.trim().equalsIgnoreCase("sluk")){
            if (turTaeller==1){
                if (tur.harVundet(turTaeller)){
                    System.out.println(tur.spillere.spillerensfarve(turTaeller) + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                } else {
                    turTaeller = getTurTaeller(tur,turTaeller,tekst);
                }
            } else if (turTaeller == 2){
                if (tur.harVundet(turTaeller)){
                    System.out.println(tur.spillere.spillerensfarve(turTaeller) + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                } else {
                    turTaeller = getTurTaeller(tur,turTaeller,tekst);
                }
            } else if (turTaeller == 3){
                if (tur.harVundet(turTaeller)){
                    System.out.println(tur.spillere.spillerensfarve(turTaeller) + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                } else {
                    turTaeller = getTurTaeller(tur,turTaeller,tekst);
                }
            } else if (turTaeller ==4){
                if (tur.harVundet(turTaeller)){
                    System.out.println(tur.spillere.spillerensfarve(turTaeller) + " har vundet, skriv venligst 'sluk' for at stoppe spillet");
                } else {
                    turTaeller = getTurTaeller(tur,turTaeller,tekst);
                }
            } else {
                System.out.println("Der er sket en fejl");
            }
            tur.hvisTur(turTaeller);
            tekst = scan.nextLine();
        }

    }

    private static int getTurTaeller(Tur tur, int turTaeller, String tekst){
        int brik = 0;

        if (tekst.trim().equalsIgnoreCase("r")){
            for (int i=0;i<4;i++){
                System.out.println(tur.spillere.list.get(i).getSpillerfarve() + " Brikker er på følgene felter:");
                tur.spillere.list.get(i).hvorErBrikker();
                System.out.println(" ");
            }
            System.out.println("\n");

            try{
                brik = tur.vaelgBrik();
            } catch (Exception ex) {
                System.out.println("Du skrev ikke et tal");
            }
            if (tur.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation() != 0) tur.forladFelt(tur.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation());
            tur.rykBrik2(turTaeller,brik);
            if (tur.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation() != 0) tur.opdaterFelt(tur.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation(),turTaeller,brik);
            if (!tur.terning.stjernetjek()){
                if (tur.board.list.get(tur.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation()).erStjerne(tur.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation())){
                    if (tur.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation() != 0) tur.forladFelt(tur.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation());
                    tur.rykBrikStjerneSituation(turTaeller,brik);
                    if (tur.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation() != 0) tur.opdaterFelt(tur.spillere.list.get(turTaeller-1).brikker.get(brik-1).getLokation(),turTaeller,brik);
                }
            }
            if (tur.terning.globustjek()){
                System.out.println("Du har slaet en globus og ruller terningen igen, skriv et tal mellem 1-4");
                getTurTaeller(tur,turTaeller,tekst);
            }
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