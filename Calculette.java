class Calculette{
    public static void main(String argv[]){
        if(argv.length < 1){
            manuel();
        }else{
            System.err.println("Okidokie");
            String formule = ""; //Formule indique le contenu total des arguments que l'on donne
            for(int i=0;i < argv.length;i++){
                formule = formule.concat(argv[i]);
            }
            Expression valeurIn = new Expression(formule);
            if(valeurIn.resolution()){
                System.out.println("lokie");
           }else{
               manuel();
           }
        }
    }
    public static void manuel(){
        System.err.println("Ça ne marche pas comme ça.");
    }
} 
