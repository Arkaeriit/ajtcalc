
all : ASCcalc.jar 

ASCcalc.jar : Calculette.class Decodeur.class Expression.class Interface.class ListExp.class
	jar vcf0e ASCcalc.jar Interface Calculette.class Decodeur.class Expression.class Interface.class ListExp.class

Calculette.class : Calculette.java
	javac Calculette.java 

Decodeur.class : Decodeur.java
	javac Decodeur.java 

Expression.class : Expression.java 
	javac Expression.java 

Interface.class : Interface.java
	javac Interface.java 

ListExp.class : ListExp.java
	javac ListExp.java

clean : 
	rm -f *.class
	rm -f *.jar
	rm -f ASCcalc

install : ASCcalc
	mkdir -p /usr/local/share/ASCcalc
	cp -f ASCcalc /usr/local/bin
	cp -f ASCcalc.jar /usr/local/share/ASCcalc
	rm -f ASCcalc
	
uninstall : 
	rm -Rf /usr/local/share/ASCcalc
	rm -f /usr/local/bin/ASCcalc
ASCcalc :
	echo '#!/bin/sh' > ASCcalc
	echo '#This little stript is ment to lauch the calculator.' > ASCcalc
	echo 'java -jar /usr/local/share/ASCcalc/ASCcalc.jar $$@' >> ASCcalc 
	chmod 755 ASCcalc
