/*-----------------------------------------------------------------\
|Cette classe permet de lire un ficher dans lequel on a indiqué des|
|expressions à résoudre et de les interprétés, les réponses sont   |
|mise sur la stack et lisible quand on fait appel à la methode     |
|toString                                                          |
\-----------------------------------------------------------------*/

import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import java.util.ArrayList;

class Interpreteur {

    private ArrayList<String> listFichier;
    private ArrayList<String> listReponses;

    public Interpreteur(String filename) throws FileNotFoundException{ //On lance l'exception ici car de toute façon un interprééteur est lié au fichier et n'a pas de sens sans
        Stack.enableStack();
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        listFichier = new ArrayList<String>();
        
        String tmp = "";
        while(sc.hasNextLine()){
            tmp = tmp+sc.nextLine();
            if(tmp.length() > 2){ //On cherche à enlever les #! si 
                if(tmp.substring(0,2).equals("#!"))
                    tmp = "";
            }
            if(tmp.length() > 0){
                if(tmp.charAt(tmp.length() - 1) == '\\'){ //On a une expression sur plusieur lignes
                    tmp = tmp.substring(0,tmp.length() - 1); //On enlève le \
                }else{ //Sinon on ajoute l'exprssion sur la ligne
                    appNempty(listFichier,tmp);
                    tmp = "";
                }
            }else{ //Sinon on ajoute l'exprssion sur la ligne
                appNempty(listFichier,tmp);
                tmp = "";
            }
        }
        listReponses = new ArrayList<String>(listFichier.size()); //On parre du principe que l'on aura une réponse pour chaque expression
        interprete();
    }   

    private static void appNempty(ArrayList<String> list,String str){ //Permet de trier les chaines vides
        if(!str.equals(""))
            list.add(str);
    }

    private Boolean interprete(){ //On calcule toute les valeuurs de listFichier et on met le résultat dans listReponses
        for(int i=0;i<listFichier.size();i++){
            try{
                String str = listFichier.get(i).toString(); //On explicite le type de l'élem de la liste
                Nombre valeurIn = new Nombre(str);
                Stack.addElem(valeurIn.getValeur());
                if(Math.round(valeurIn.getValeur()) == valeurIn.getValeur())
                    listReponses.add(Math.round(valeurIn.getValeur())+"\n");
                else
                    listReponses.add(valeurIn.getValeur()+"\n");
            }catch(UnsolvableException e){
                Stack.addElem(Double.NaN);
                listReponses.add(e.raison());
            }catch(NoSolveJustPrintException e){
                if(e.getSpecialMessage().equals("exit")){
                    return false;
                }else if(e.getSpecialMessage().equals("noStack")){
                    listReponses.add(e.getMessage());
                }else if(e.getSpecialMessage().equals("disp")){
                    System.out.print(this.toString());
                    listReponses.clear();
                }else{ //on print
                    listReponses.add(e.getMessage());
                    Stack.addElem(Double.NaN);
                }
            }
        }
        return true;
    }

    public String toString(){
        String ret = "";
        for(int i=0;i<listReponses.size();i++)
            ret = ret + listReponses.get(i);
        return ret;
    }

    public static void main(String[] args){
        try{
            if(args.length >= 1){ //On a une expression en plus du fichier
                String argExp = "";
                for(int i=1; i<args.length;i++)
                    argExp = argExp + args[i];
                Stack.enableStack();
                try{
                    Nombre valeurIn = new Nombre(argExp);
                    Stack.addElem(valeurIn.getValeur());
                }catch(Exception e){
                    Stack.addElem(Double.NaN);
                }
            }
            Interpreteur inte = new Interpreteur(args[0]);
            System.out.print(inte);
        }catch(FileNotFoundException e){
            System.err.println("No such file as "+args[0]);
            System.exit(3);
        }
    }

}

