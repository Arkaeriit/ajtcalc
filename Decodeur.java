class Decodeur {
    public Expression[] tabExp;
    public Boolean succes;
    private Boolean initTabl; //Indique si tabExp est vide ou pas

    public Decodeur(String formule){ //On lit la formule et on remplie avec découpage
        succes = false; //Initiaalisation
        initTabl = false; 


        int pointeurFormule = 0;
        Boolean nombre = true;//On s'attend à avoir une suite de nombres et d'oppérations.
        while(pointeurFormule < formule.length()){
            if(nombre){
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

    private void addExpression(char symbole){
        if(initTabl){
            Expression[] tmp = new Expression[ this.tabExp.length + 1 ];
            for(int i=0;i < this.tabExp.length;i++){
                tmp[i] = this.tabExp[i];
            }
            tmp[this.tabExp.length] = new Expression(symbole);
            this.tabExp = tmp;
        }else{
            System.err.println("Erreur impossible");
        }
    }

    private void addExpression(double nombre){
        if(initTabl){
            Expression[] tmp = new Expression[ this.tabExp.length + 1 ];
            for(int i=0;i < this.tabExp.length;i++){
                tmp[i] = this.tabExp[i];
            }
            tmp[this.tabExp.length] = new Expression(nombre);
            this.tabExp = tmp;
        }else{
            this.tabExp = new Expression[1];
            tabExp[0] = new Expression(nombre);
            this.initTabl = true;
        }
    }
}
