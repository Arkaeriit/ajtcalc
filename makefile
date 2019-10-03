
all : jtcalc.jar 

jtcalc.jar : Classes
	jar cf0e jtcalc.jar Interface *.class

Classes : Calculette.java Decodeur.java Expression.java Interface.java ListExp.java
	javac *.java

clean : 
	rm -f *.class
	rm -f *.jar
	rm -f ASCcalc

install : jtcalc
	mkdir -p /usr/local/share/jtcalc
	cp -f jtcalc /usr/local/bin
	cp -f jtcalc.jar /usr/local/share/jtcalc
	rm -f jtcalc
	
uninstall : 
	rm -Rf /usr/local/share/ASCcalc
	rm -f /usr/local/bin/ASCcalc
jtcalc :
	echo '#!/bin/sh' > jtcalc
	echo '#This little stript is ment to lauch the calculator.' >> jtcalc
	echo 'java -jar /usr/local/share/jtcalc/jtcalc.jar $$@' >> jtcalc 
	chmod 755 jtcalc
