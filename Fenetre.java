import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Fenetre extends JFrame implements ActionListener{
    
    //Éléments de l'éditeur de texte
    private JPanel codeEditor;
    private JTextArea code;
    private JScrollPane scrollCode;

    public Fenetre(String nom){
        super(nom);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Arrète le programme quand on ferme la fenètre
        Container contentPane = getContentPane() ; //Permet de disposer des éléments

        //Menu déroulants
        JMenuBar menuBar = menu();
        setJMenuBar(menuBar);

        //Contenu
        JPanel organisator = new JPanel();
        organisator.setLayout(new GridLayout(2,1));
        codeEditor = new JPanel();
        code = new JTextArea();
        scrollCode = new JScrollPane(code);
        code.setMargin( new Insets(5,5,5,5) );
        codeEditor.add(scrollCode);
        codeEditor.setSize(1000,1000);
        organisator.add(codeEditor);

        contentPane.add(organisator,"Center");
        setSize(800,500); //taille
        setLocation(30,30); //position 
        setVisible(true); //activation
    }

    static public void main(String[] argv){
        Fenetre win = new Fenetre("ajtcalc");
    }
    
    private JMenuBar menu() { //Créé la menubar
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFichier = new JMenu("File"); //Premier menu
        JMenuItem ouvrir = new JMenuItem("Open");
        ouvrir.setAccelerator(KeyStroke.getKeyStroke('O',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(),false)); //Racourcis
        menuFichier.add(ouvrir);
        JMenuItem nouveau = new JMenuItem("New");
        nouveau.setAccelerator(KeyStroke.getKeyStroke('N',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(),false)); 
        menuFichier.add(nouveau);
        JMenuItem enregistrer = new JMenuItem("Save");
        enregistrer.setAccelerator(KeyStroke.getKeyStroke('S',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(),false)); 
        menuFichier.add(enregistrer);
        menuFichier.addSeparator();
        JMenuItem quitter = new JMenuItem("Quit");
        quitter.setAccelerator(KeyStroke.getKeyStroke('Q',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(),false)); //Racourcis
        quitter.addActionListener(this);
        menuFichier.add(quitter);
        menuBar.add(menuFichier);
        JMenu menuApropos = new JMenu("About"); //Second menu
        JMenuItem auteurs = new JMenuItem("Author");
        menuApropos.add(auteurs);
        menuBar.add(menuApropos);
        return menuBar;
     } 
     
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(cmd.equals("Quit")) {
            System.exit(0);
        }else {
            System.err.println(cmd);
        }
    }

}
