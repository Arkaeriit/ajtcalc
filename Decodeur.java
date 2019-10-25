/*-----------------------------------------------------------------\
|Cette classe permet de créer une liste d'expression à partir d'une|
|chaine de charactères.                                            |
\-----------------------------------------------------------------*/

class Decodeur extends ListExp {

    public Decodeur(String formule) throws DecodageException,UnsolvableException,NoSolveJustPrintException { //On lit la formule et on remplie avec découpage
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
                        throw new DecodageException("Parenthesis mismatch");
                    }else{
                        addExpression(formule.substring(debut + 1,pointeurFormule - 1));
                        nombre = false;
                    }
                }else if(Character.isDigit(formule.charAt(pointeurFormule)) || formule.charAt(pointeurFormule) == '-'){ //Nombre mais pas parenthèse
                    int startNombre = pointeurFormule; //début du nombre
                    int xpoint = 0; //Compte le nombre de . et de ,
                    Boolean moinsValide = true; //Sert à vérifier que les - ne sont qu'en première position
                    while(Character.isDigit(formule.charAt(pointeurFormule))  || (formule.charAt(pointeurFormule) == '-' && moinsValide) || formule.charAt(pointeurFormule) == '.' || formule.charAt(pointeurFormule) == 'E'){
                        moinsValide = false;
                        if(formule.charAt(pointeurFormule) == 'E')
                            moinsValide = true; //Si on a un exposant on peut en faire un négatif
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
                            throw new DecodageException("Invalid number ("+nmb+")");
                        }
                        addExpression(val);
                        nombre = false;
                    }else{
                       throw new DecodageException("Nothing to solve");
                    }
                }else{ //On a une fonction
                    int debutNomFonction = pointeurFormule;
                    String[] argv = new String[4]; //Nombre maximum d'arguments
                    int argc = 0;
                    while( formule.charAt(pointeurFormule) != '(' && formule.charAt(pointeurFormule) != '{' && formule.charAt(pointeurFormule) != '[' ){
                        pointeurFormule++;
                        if(pointeurFormule == formule.length()){ //On a dépasé la taille max
                            throw new DecodageException("Empty function");
                        }
                    }
                    int finNomFonction = pointeurFormule - 1;
                    int debutNomArg = pointeurFormule + 1;
                    int PilePar = 1; //On cherche la fin de la parenth_se
                    while(PilePar > 0){
                        pointeurFormule++;
                        if(pointeurFormule >= formule.length()) //Limite dépacée
                            throw new DecodageException("Never ending function");
                        char act = formule.charAt(pointeurFormule);
                        if(act == '(' || act == '[' || act == '{'){
                            PilePar++;
                        }else if(act == ')' || act == ']' || act == '}'){
                            PilePar--;
                        }else if(act == ',' && PilePar == 1){ //fin argument
                            argv[argc] = formule.substring(debutNomArg,pointeurFormule);
                            argc++;
                            debutNomArg = pointeurFormule + 1;
                        }
                    }
                    argv[argc] = formule.substring(debutNomArg,pointeurFormule);
                    argc++;
                    addExpression(formule.substring(debutNomFonction,finNomFonction+1),argc,argv);
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
                    case ';': //On met un commentaire derrière, on arrète de lire l'expression
                        return;
                    default :
                        throw new DecodageException("Unknow symbol");
                }
                nombre = true;
            } //EndIf        
        }//End while
        if(nombre){
            throw new DecodageException("The expression end with an operation symbol"); //Si on termine pas par un nombre c'est qu'il y a une erreur   
        }
    }

}

class DecodageException extends Exception { //Permet de voir si on a une erreur de lecture
    public String problème;

    public DecodageException() {}
    public DecodageException(String s){
        super(s);
        problème = s;
    }

    public String raison(){
        return "Reason : "+problème;
    }
}

