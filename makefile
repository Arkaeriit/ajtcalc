
all : ajtcalc.jar 

ajtcalc.jar : Classes
	jar cf0e ajtcalc.jar Interface *.class

Classes : Calculette.java Decodeur.java Expression.java Interface.java ListExp.java Nombre.java Operation.java Formule.java Fonction.java Solveur.java Stack.java Interpreteur.java DecodageException.java NoSolveJustPrintException.java UnsolvableException.java
	javac *.java

clean : 
	rm -f *.class
	rm -f *.jar
	rm -f ajtcalc

install : ajtcalc shell.ajt
	mkdir -p /usr/local/share/ajtcalc
	cp -f ajtcalc /usr/local/bin
	cp -f ajtcalc.jar /usr/local/share/ajtcalc
	cp -f shell.ajt /usr/local/share/ajtcalc
	rm -f ajtcalc
	
uninstall : 
	rm -Rf /usr/local/share/ASCcalc
	rm -f /usr/local/bin/ASCcalc

ajtcalc :
	echo '#!/bin/sh' > ajtcalc
	echo '#This little stript is ment to lauch the calculator.' >> ajtcalc
	echo 'java -jar /usr/local/share/ajtcalc/ajtcalc.jar "$$@"' >> ajtcalc 
	chmod 755 ajtcalc

