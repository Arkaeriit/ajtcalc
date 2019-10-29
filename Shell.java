/*-------------------------------------------------------------------\
|Cette classe permet d'avoir une shell pour interpréter dynamiquement|
|des expressions.                                                    |
\-------------------------------------------------------------------*/

import java.util.Scanner;

class Shell {
    
    Scanner scanner;

    public Shell(){
        Stack.enableStack();
        scanner = new Scanner(System.in);
        Boolean boolKeep = true;
        while(boolKeep)
            boolKeep = question();
    }

    public Boolean question() { //Opération élémentaire
        System.out.print(">");
        String input = "";//Definition ici pour éviter les problèmes de def liées au try/catch
        try{
            input = scanner.nextLine() ;//getUserInput
        }catch(java.util.NoSuchElementException e){
            return false;
        }
        try{
            Nombre valeurIn = new Nombre(input);
            if(Math.round(valeurIn.getValeur()) == valeurIn.getValeur())
                System.out.println((long) valeurIn.getValeur());
            else
                System.out.println(valeurIn.getValeur());
            Stack.addElem(valeurIn.getValeur());
        }catch(UnsolvableException e){
            System.out.print(e.raison());
            Stack.addElem(Double.NaN);
        }catch(NoSolveJustPrintException e){
            if(e.getSpecialMessage().equals("exit")){
                return false;
            }else if(e.getSpecialMessage().equals("noStack")){
                //Rien à faire
            }else{ //tout va bien, on print
                System.out.print(e.getMessage());
                Stack.addElem(Double.NaN);
            }
        }
        return true;
    }

}

