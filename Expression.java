class Expression {
    private String formule; //La formule à calculer.
    public Boolean ValeurSym; //Indique si on est une valleur ou une syblole de calcul
    public Boolean solved; //Indique si on connait la valeur de l'exression

    private int valeur;
    private Character Symbole;
    private Expression[] decoupage; //Les expressions vont être découpées en sous-éléments calculables

    public Expression(String formule){ //On créé un élément avec une formule à calculer
        this.formule = formule;
        this.ValeurSym = true; //On récupèrera une valeur calculable
        this.solved = false; //Mais on ne la connait pas encore.
        this.valeur = 0;
    }

    public Boolean resolution(){ //Fait les calculs de manière récursive. Si il y a une erreur de syntaxe on renvoie false, si tout va bien on renvoie true
        if(this.decode()){
            return true;
        }else{
            System.err.println("On ne sais pas encore résoudre l'expression suivante: ");
            System.err.println(this.formule);
            return false;
        }
    }

    public Boolean decode(){ //On lit la formule et on remplie avec découpage
        int pointeurFormule = 0;
        Boolean nombre = true;//On s'attend à avoir une suite de nombres et d'oppérations.
        while(pointeurFormule < this.formule.length()){
            if(nombre){
                int startNombre = pointeurFormule; //début du nombre
                int xpoint = 0; //Compte le nombre de . et de ,
                while(Character.isDigit(this.formule.charAt(pointeurFormule)) || this.formule.charAt(pointeurFormule) == '.'){
                    pointeurFormule++;
                    if(pointeurFormule == this.formule.length()){ //On a dépasé la taille max
                        break;
                    }
                    if(this.formule.charAt(pointeurFormule) == '.'){
                        xpoint++;
                    }
                }
               String nmb = this.formule.substring(startNombre,pointeurFormule); //On récupère le nombre
                if(xpoint < 2 && nmb.length() > 0){ //On vérifie que l'on peut le convertir
                    double val = Double.parseDouble(nmb);
                    nombre = false;
                }else{
                    return false;
                }
            }else{ //On a une oppération
                switch(this.formule.charAt(pointeurFormule)){
                    case '+':
                        pointeurFormule++;
                        break;
                    case '-':
                        pointeurFormule++;
                        break;
                    case '/':
                        pointeurFormule++;
                        break;
                    case ':':
                        pointeurFormule++;
                        break;
                    case 'x':
                        pointeurFormule++;
                        break;
                    case '*':
                        if(this.formule.charAt(pointeurFormule + 1) == '*'){
                            pointeurFormule += 2;
                        }else{
                            pointeurFormule++;
                        }
                        break;
                    default :
                        return false;
                }
                nombre = true;
            } //EndIf        
        }//End while
        return !nombre; //Si on termine pas par un nombre c'est qu'il y a une erreur
    }
}
