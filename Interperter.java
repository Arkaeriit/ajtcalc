
import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import java.util.ArrayList;

class Interpreteur {

    ArrayList listFichier;

    public Interpreteur(String filename) throws FileNotFoundException{ //On lance l'exception ici car de toute façon un interprééteur est lié au fichier et n'a pas de sens sans
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        listFichier = new ArrayList();
        System.out.println("OK1");
        
        String tmp = "";
        while(sc.hasNextLine()){
            tmp = tmp+sc.nextLine();
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
    }   

    private static void appNempty(ArrayList list,String str){ //Permet de trier les chaines vides
        if(!str.equals(""))
            list.add(str);
    }

    public static void main(String argv[]){
        try{
        Interpreteur inte = new Interpreteur("test");
        System.out.println(inte.listFichier);
        }catch(FileNotFoundException e){}
    }    

}


    

