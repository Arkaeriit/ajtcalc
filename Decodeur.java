/*-----------------------------------------------------------------\
|Cette classe permet de créer une liste d'expression à partir d'une|
|chaine de charactères.                                            |
\-----------------------------------------------------------------*/

class Decodeur extends ListExp {

    public Decodeur(String formule) throws DecodageExeption { //On lit la formule et on remplie avec découpage
        init(); 


        int pointeurFormule = 0;
        Boolean nombre = true;//On s'attend à avoir une suite de nombres et d'oppérations.
        while(pointeurFormule < formule.length()){
            while(pointeurFormule < formule.length() && formule.charAt(pointeurFormule) == ' ') //Enlève les espaces en trop
                pointeurFormule++;
            if(pointeurFormule >= formule.length()) //Gère le cas où le dernier char est un espace
                break;

            if(nombre){
                if(formule.charAt(pointeurFormule) == '(' || formule.charAt(pointeurFormule) == '[' || formule.charAt(pointeurFormule) == '{'){ //Une parenthèse
                    int PilePar = 0;
                    int debut = pointeurFormule;
                    Boolean out = true; //Indique si on a trouvé la parenthèse fermante
                    pointeurFormule++;
                    while(pointeurFormule < formule.length() && out){
                        char act = formule.charAt(pointeurFormule);
                        if(act == '(' || act == '[' || act == '{'){
                            PilePar++;
                        }else if(act == ')' || act == ']' || act == '}'){
                            if(PilePar > 0){
                                PilePar--;
                            }else{
                                out = false;
                            }
                        }
                        pointeurFormule++;
                    }
                    if(out || pointeurFormule - debut < 3){ //On régarde si laes parenthèses se complètent ou si il n'y a rien dedant
                        System.err.println("Parenthesis mismatch");
                        throw new DecodageExeption("Parenthèse");
                    }else{
                        addExpression(formule.substring(debut + 1,pointeurFormule - 1));
                        nombre = false;
                    }
                }else if(Character.isDigit(formule.charAt(pointeurFormule)) || formule.charAt(pointeurFormule) == '-'){ //Nombre mais pas parenthèse
                    int startNombre = pointeurFormule; //début du nombre
                    int xpoint = 0; //Compte le nombre de . et de ,
                    while(Character.isDigit(formule.charAt(pointeurFormule))  || formule.charAt(pointeurFormule) == '-'|| formule.charAt(pointeurFormule) == '.'){
                        pointeurFormule++;
                        if(pointeurFormule == formule.length()){ //On a dépasé la taille max
                            break;
                        }
                        if(formule.charAt(pointeurFormule) == '.'){
                            xpoint++;
                        }
                    }
                   String nmb = formule.substring(startNombre,pointeurFormule); //On récupère le nombre
                    if(xpoint < 2 && nmb.length() > 0){ //On vérifie que l'on peut le convertir
                        double val = 0;
                        try{
                            val = Double.parseDouble(nmb);
                        }catch(NumberFormatException e){
                            throw new DecodageExeption("Erreur nombre");
                        }
                        addExpression(val);
                        nombre = false;
                    }else{
                       throw new DecodageExeption("Pas de contenu");
                    }
                }else{ //On a une fonction
                    int debutNomFonction = pointeurFormule;
                    while( formule.charAt(pointeurFormule) != '(' && formule.charAt(pointeurFormule) != '{' && formule.charAt(pointeurFormule) != '[' ){
                        pointeurFormule++;
                        if(pointeurFormule == formule.length()){ //On a dépasé la taille max
                            throw new DecodageExeption("Fonction sans contenu;");
                        }
                    }
                    int finNomFonction = pointeurFormule - 1;
                    int PilePar = 1; //On cherche la fin de la parenth_se
                    while(PilePar > 0){
                        pointeurFormule++;
                        if(pointeurFormule >= formule.length()) //Limite dépacée
                            throw new DecodageExeption("Fonction sans fin");
                        char act = formule.charAt(pointeurFormule);
                        if(act == '(' || act == '[' || act == '{'){
                            PilePar++;
                        }else if(act == ')' || act == ']' || act == '}'){
                            PilePar--;
                        }
                    }
                    addExpression(formule.substring(debutNomFonction,finNomFonction+1),formule.substring(finNomFonction+2,pointeurFormule));
                    pointeurFormule ++;
                    nombre = false;
                }
            }else{ //On a une oppération
                switch(formule.charAt(pointeurFormule)){
                    case '+':
                        pointeurFormule++;
                        addExpression('+');
                        break;
                    case '-':
                        pointeurFormule++;
                        addExpression('-');
                        break;
                    case '/':
                        pointeurFormule++;
                        addExpression('/');
                        break;
                    case ':':
                        pointeurFormule++;
                        addExpression('/');
                        break;
                    case 'x':
                        pointeurFormule++;
                        addExpression('x');
                        break;
                    case '^':
                        pointeurFormule++;
                        addExpression('^');
                        break;
                    case '*':
                        if(formule.charAt(pointeurFormule + 1) == '*'){
                            addExpression('^');
                            pointeurFormule += 2;
                        }else{
                            pointeurFormule++;
                            addExpression('x');
                        }
                        break;
                    default :
                        throw new DecodageExeption("Symbole inconu");
                }
                nombre = true;
            } //EndIf        
        }//End while
        if(nombre){
            throw new DecodageExeption("Fini par un symbole"); //Si on termine pas par un nombre c'est qu'il y a une erreur   
        }
    }

}

class DecodageExeption extends Exception { //Permet de voir si on a une erreur de lecture
    public String problème;

    public DecodageExeption() {}
    public DecodageExeption(String s){
        super(s);
        problème = s;
    }

    public void raison(){
        System.err.println(problème);
    }
}

