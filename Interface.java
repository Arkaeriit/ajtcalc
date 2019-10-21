/*----------------------------------------------------------------\
|Cette classe sert à décoder les arguments et à lacer les calculs.|
\----------------------------------------------------------------*/

class Interface{
    public static void main(String argv[]){
        if(argv.length < 1){
            quickHelp();
            System.exit(2);
        }
        if(argv[0].equals("help") || argv[0].equals("--help")){
            manuel();
            System.exit(0);
        }
        String formule = ""; //Formule indique le contenu total des arguments que l'on donne
        for(int i=0;i < argv.length;i++){
            formule = formule.concat(argv[i]);
        }
        try{
            Nombre valeurIn = new Nombre(formule);
            if(Math.round(valeurIn.getValeur()) == valeurIn.getValeur()) //Le résultat est entier
                System.out.println((long) valeurIn.getValeur());
            else
                System.out.println(valeurIn.getValeur());     
        }catch(UnsolvableException e){
            quickHelp();
            System.exit(1);
        }
        
    }

    private static void quickHelp(){
        System.err.println("Usage : ajtcalc [expression to solve]   solve the expression");
        System.err.println("        ajtcalc help                    print an help message");
        System.err.println("        ajtcalc --help                  print an help message");
    }

    private static void manuel(){
        System.err.println("Usage: ajtcalc [expression to solve]\n");
        System.err.println("The expression to solve is a sequence of number or sub-expression put between parenthesis and operations such as: 3 x ( 4 + 1 )\n");
        System.err.println("You can use {,[ or ( to open a parenthesis and },] or ) to close one.\nNumber are in the decimal system and can be decimal.");
        System.err.println("List of operations:\n* addition, symbolised by +\n* substation, symbolised by -\n* multiplication, symbolised by * or x\n* division, symbolised by /\n* exponentiation, symbolised by ^ or **\n");
        System.err.println("You can apply function on numbers or expressions.\nTo do thant you have to write the name of the function then it's attributes between parenthesis, bracket or curly brackets.\nList of functions:\n* abs(x) : return the absolute value of x\n* floor(x) : return x rouned down\n* ceil(x) : return x rouned up\n* max(x,y) : return the bigest number between x and y\n* min(x,y) : return the smallest number between x and y");
    }
    
} 

