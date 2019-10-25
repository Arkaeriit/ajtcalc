/*-------------------------------------------------------------------\
|Cette classe permet d'avoir une shell pour interpréter dynamiquement|
|des expressions.                                                    |
\-------------------------------------------------------------------*/

import java.util.Scanner;

class Shell {
    
    Scanner scanner;

    public Shell(){
        scanner = new Scanner(System.in);
        Boolean boolKeep = true;
        while(boolKeep)
            boolKeep = question();
    }

    public Boolean question() { //Oppération élémentaire
        System.out.print(">");
        String input = "";//Deffinition ici pour éviter les problèmes de def liés au try/catch
        try{
            input = scanner.nextLine() ;//getUserInput
        }catch(java.util.NoSuchElementException e){
            return false;
        }
        if(input.substring(0,5).equals("exit")) //fausse fonction pour sortir du shell
            return false;
        try{
            Nombre valeurIn = new Nombre(input);
            if(Math.round(valeurIn.getValeur()) == valeurIn.getValeur())
                System.out.println((long) valeurIn.getValeur());
            else
                System.out.println(valeurIn.getValeur());
        }catch(UnsolvableException e){
            System.out.print(e.raison());
        }catch(NoSolveJustPrintException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

}

