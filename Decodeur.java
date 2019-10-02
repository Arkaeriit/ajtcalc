class Decodeur extends ListExp {
    public Boolean succes;

    public Decodeur(String formule){ //On lit la formule et on remplie avec découpage
        this.succes = false; //Initialisation
        this.init(); 


        int pointeurFormule = 0;
        Boolean nombre = true;//On s'attend à avoir une suite de nombres et d'oppérations.
        while(pointeurFormule < formule.length()){
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
                    if(out){
                        System.err.println("Parenthesis mismatch");
                        succes = false;
                        return;
                    }else{
                        this.addExpression(formule.substring(debut,pointeurFormule));
                        nombre = false;
                    }
                }else{ //Nombre mais pas parenthèse
                    int startNombre = pointeurFormule; //début du nombre
                    int xpoint = 0; //Compte le nombre de . et de ,
                    while(Character.isDigit(formule.charAt(pointeurFormule)) || formule.charAt(pointeurFormule) == '.'){
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
                        double val = Double.parseDouble(nmb);
                        this.addExpression(val);
                        nombre = false;
                    }else{
                       succes = false;
                       return;
                    }
                }
            }else{ //On a une oppération
                switch(formule.charAt(pointeurFormule)){
                    case '+':
                        pointeurFormule++;
                        this.addExpression('+');
                        break;
                    case '-':
                        pointeurFormule++;
                        this.addExpression('-');
                        break;
                    case '/':
                        pointeurFormule++;
                        this.addExpression('/');
                        break;
                    case ':':
                        pointeurFormule++;
                        this.addExpression('/');
                        break;
                    case 'x':
                        pointeurFormule++;
                        this.addExpression('x');
                        break;
                    case '^':
                        pointeurFormule++;
                        this.addExpression('^');
                        break;
                    case '*':
                        if(formule.charAt(pointeurFormule + 1) == '*'){
                            this.addExpression('^');
                            pointeurFormule += 2;
                        }else{
                            pointeurFormule++;
                            this.addExpression('x');
                        }
                        break;
                    default :
                        succes = false;
                        return;
                }
                nombre = true;
            } //EndIf        
        }//End while
        succes = !nombre; //Si on termine pas par un nombre c'est qu'il y a une erreur   
    }

}

