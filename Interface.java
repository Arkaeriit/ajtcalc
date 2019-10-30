/*-----------------------------------------------------------------\
|Cette classe sert à décoder les arguments et à lancer les calculs.|
\-----------------------------------------------------------------*/

import java.io.FileNotFoundException;

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
        if(argv[0].equals("shell") && argv.length == 1){
            try{
                Interpreteur inte = new Interpreteur("/usr/local/share/ajtcalc/shell.ajt");
                System.out.print(inte);
                System.exit(0);
            }catch(FileNotFoundException e){
                System.err.println("shell.ajt not found, the instalation must be made correctely.");
                System.exit(3);
            }
        }
        if(argv[0].equals("file") && argv.length >= 2){
            try{
                if(argv.length > 2){ //Une expression en argument
                    String argExp = "";
                    for(int i=2;i<argv.length;i++)
                        argExp = argExp+argv[i];
                    Stack.enableStack();
                    try{
                        Nombre valeurTop = new Nombre(argExp);
                        Stack.addElem(valeurTop.getValeur());
                    }catch(Exception e){ //Si il y a l'ombre d'um problème on met NaN dans la stack
                        Stack.addElem(Double.NaN);
                    }
                }
                Interpreteur inte = new Interpreteur(argv[1]);
                System.out.print(inte);
                System.exit(0);
            }catch(FileNotFoundException e){
                System.err.println("No such file as "+argv[1]);
                System.exit(3);
            }
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
            System.exit(0);
        }catch(UnsolvableException e){
            System.err.println(e.raison());
            quickHelp();
            System.exit(1);
        }catch(NoSolveJustPrintException e){
            System.out.print(e.getMessage());
            System.exit(0);
        }        
    }

    private static void quickHelp(){
        System.err.println("Usage : ajtcalc [expression to solve]   solve the expression");
        System.err.println("        ajtcalc help                    print an help message");
        System.err.println("        ajtcalc --help                  print an help message");
        System.err.println("        ajtcalc shell                   start a shell");
        System.err.println("        ajtcalc file [filename] <Exp>   interprete a file where there is a list of expressions, the result of Exp will be put on top of the stack");
    }

    private static void manuel(){
        System.out.println("Usage: ");
        System.out.println("    ajtcalc [expression to solve]              to solve the expression");
        System.out.println("    ajtcalc shell                              to start a shell where you will be able to enter expressions");
        System.out.println("    ajtcalc file [filename] <expressionw>      to interprete a file where there is list of expressions");
        System.out.println("    ajtcalc help                               to prit an user manual");
        System.out.println("");
        System.out.println("");
        System.out.println("Expressions");
        System.out.println("");
        System.out.println("    The expression to solve is a sequence of number or sub-expression put between parentheses and operations such as: 3 x ( 4 + 1 )");
        System.out.println("    Spaces are ignored, so you can use them to make your expression easier to read while you write them.");
        System.out.println("");
        System.out.println("    Operations:");
        System.out.println("        List of operations:");
        System.out.println("        * addition, symbolised by +");
        System.out.println("        * substation, symbolised by -");
        System.out.println("        * multiplication, symbolised by * or x");
        System.out.println("        * division, symbolised by /");
        System.out.println("        * exponentiation, symbolised by ^ or **");
        System.out.println("");
        System.out.println("        For operation with more than one symbol such as multiplication you can use each one in the same expression.");
        System.out.println("");
        System.out.println("    Numbers:");
        System.out.println("        Number are typed in the decimal system. You can use decimal numbers.");
        System.out.println("");
        System.out.println("    Parenthesis:");
        System.out.println("        To compute a sub-expression first you can put it between parentheses. You can use (, { or [ to open parenthesis and ), } or ] to close them.");
        System.out.println("");
        System.out.println("    Basic Functions:");
        System.out.println("        You can apply function on numbers or expressions.");
        System.out.println("        To do that you have to write the name of the function then it's arguments between parentheses, bracket or curly brackets.");
        System.out.println("        List of basic functions:");
        System.out.println("        * abs(x) : return the absolute value of x");
        System.out.println("        * floor(x) : return x rounded down");
        System.out.println("        * ceil(x) : return x rounded up");
        System.out.println("        * max(x,y) : return the biggest number between x and y");
        System.out.println("        * min(x,y) : return the smallest number between x and y");
        System.out.println("        * solve(var,Exp,min,max) : solve Exp(var) = 0 with var being between min and max");
        System.out.println("        * e() : return the constant e, Euler's number");
        System.out.println("        * pi() : return the number pi);
        System.out.println("        * exit() : exit a shell or stop an interpreter");
        System.out.println("");
        System.out.println("    Comments:");
        System.out.println("        If you put a semi-colon after an expression to ignore everything comming after the semi-colon. You can use this to put comment on your operations.");
        System.out.println("");
        System.out.println("    Examples:");
        System.out.println("        Here is a list of some valid expressions:");
        System.out.println("        ( 3 * (4.0 + 0.01)) ^3");
        System.out.println("        { (3+2) * 4**2 x 6 - 5 ]^0.3");
        System.out.println("        3 ** (4 - 5)");
        System.out.println("        4*10**1+2x10^0");
        System.out.println("        2**max(4,abs(-76*2))");
        System.out.println("        abs(floor(-3.5)*3)");
        System.out.println("");
        System.out.println("The stack");
        System.out.println("");
        System.out.println("    When using a shell or running a file thought an interpreter the previous answers are stored in a stack. You can access this with the function ans. ans() or ans(0) will give you the last calculated answer and ans(n) will give you the nth previous answer.");
        System.out.println("    If the previous expression had a mistake in it or just printed something with echo() NaN will be added on the stack.");
        System.out.println("    If you start an Interpreter by giving an expression after the filename in augment the result of the expression will be on top of the stack.");
        System.out.println("");
        System.out.println("    Preserving the stack:");
        System.out.println("    If you want to preserve the state of the stack, you can use the function stackSave(). To revert the stack to the previously saved state you can use stackBack(). Theses two functions don't change the stack in any other way. When you save the state of the stack, you can still access the values that where there before you saved. When you revert to the previous saved the changes on the stack made between the save and the revert are lost.");
        System.out.println("");
        System.out.println("    Example");
        System.out.println("        Here is an example of the use of the shell where the stack is used:");
        System.out.println("        > 1+2");
        System.out.println("        3");
        System.out.println("        > 4*4");
        System.out.println("        16");
        System.out.println("        > ans(1)");
        System.out.println("        3");
        System.out.println("        > 7");
        System.out.println("        7");
        System.out.println("        > stackSave()");
        System.out.println("        > 8*8");
        System.out.println("        64");
        System.out.println("        > 9*9");
        System.out.println("        81");
        System.out.println("        > stackBack()");
        System.out.println("        > ans()");
        System.out.println("        7");
        System.out.println("        > ans(2)");
        System.out.println("        3");
        System.out.println("");
        System.out.println("Programming with ajtcalc");
        System.out.println("");
        System.out.println("    Even if I don't advice it becaus it is quite tidious you can use ajtcalc as an interpreter for a turing compatible language. To access a value you have to call it's position on the stack. You can call a function you wrote with the run function. You can do conditional branching with the functions ifBE and ifGT. You should hide the calculations that aren't interesting with the functions q ou quiet.");
        System.out.println("");
        System.out.println("    Creating a function:");
        System.out.println("        To create a function, you must write it in a new file. You should start it with stackSave() to ensure that the code executed inside the function doesn't affect the rest of the program. The file must end with stackBack(Exp1,...,ExpN) where the content of the stackBack is what your function return. To call your function from a file you mist put it's argument on top of the stack and then call it with run(nameOfTheFileOfYourFunction).");
        System.out.println("");
        System.out.println("List of all functions");
        System.out.println("");
        System.out.println("* abs(x) : return the absolute value of x");
        System.out.println("* floor(x) : return x rounded down");
        System.out.println("* ceil(x) : return x rounded up");
        System.out.println("* max(x,y) : return the biggest number between x and y");
        System.out.println("* min(x,y) : return the smallest number between x and y");
        System.out.println("* echo(message) : print message, ignore everything else");
        System.out.println("* exit() : exit a shell or stop an interpreter");
        System.out.println("* ans(n) : in a shell or in an interpreter, return the nth previous answer (starting at 0), if no argument is given the previous answer will be chosen");
        System.out.println("* define(var,Exp1,Exp2) : replace every instance of var in Exp2 by Exp1");
        System.out.println("* solve(var,Exp,min,max) : solve Exp(var) = 0 with var being between min and max");
        System.out.println("* e() : return the constant e, Euler's number");
        System.out.println("* pi() : return the number pi);
        System.out.println("* stackSave() : save the state of the stack");
        System.out.println("* stackBack() : revert to the previously saved state of the stack");
        System.out.println("* stackBack(Exp1,...,ExpN) : revert to the previously saved state of the stack and put the evaluation of each arguments on to of the stack, at the end the last argument will be on top of the stack");
        System.out.println("* quiet(Exp) : solve Exp and put the resut on the stack but don't print anything");
        System.out.println("* q(Exp) : like quiet");
        System.out.println("* run(filename) : interpret the file named filename, the results of each expression from the file are put on the stack ans the results are printed");
        System.out.println("* ifBE(Exp1,Exp2,Exp3) : evaluate Exp1, if Exp1 is above 0 the function evaluate Exp3, if Exp1 is equal or below 0 it evaluate Exp2");
        System.out.println("* ifGT(Exp1,Exp2,Exp3) : evaluate Exp1, if Exp1 is below or equal to 0 the function evaluate Exp3, if Exp1 is above it evaluate Exp2");
        System.out.println("* input(prompt) : ask the user for input, asking with the desired prompt or no prompt if none is given");
        System.out.println("* disp() : display all the answer of the interpreter so far");
        System.out.println("* showStack() : show all the content of the stack, useful for debugging, the stack is not changed");
    }

}

