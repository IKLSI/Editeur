import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;

public class Editeur extends JFrame implements ActionListener 
{
	JTextArea txt = new JTextArea();
	JFrame frame = new JFrame("Éditeur de texte");
	JMenuBar menuBar = new JMenuBar();

	JMenu menuFichier = new JMenu("Fichier");

	JMenuItem menuiNouveau = new JMenuItem("Nouveau");
	JMenuItem menuiOuvrir = new JMenuItem("Ouvrir");
	JMenuItem menuiSauvegarder = new JMenuItem("Enregistrer Sous");
	JMenuItem menuiImprimer = new JMenuItem("Imprimer");

	JMenu menuEdition = new JMenu("Edition");

	JMenuItem menuiCouper = new JMenuItem("Couper");
	JMenuItem menuiCopier = new JMenuItem("Copier");
	JMenuItem menuiColler = new JMenuItem("Coller");

	JMenu menuRecherche = new JMenu("Recherche");

	JMenuItem menuiRechercher = new JMenuItem("Chercher");
	JMenuItem menuiRemplacer = new JMenuItem("Remplacer");

	JMenu menuStyle = new JMenu("Style");

	JMenuItem menuiPolice = new JMenuItem("Police");
	JMenuItem menuiCouleur = new JMenuItem("Couleur");
	JMenuItem menuiTaille = new JMenuItem("Taille");
	JMenuItem menuiGras = new JMenuItem("Gras");
	JMenuItem menuiItalique = new JMenuItem("Italique");

	JScrollPane scrollPane = new JScrollPane(txt);

	public Editeur()
	{
		menuiNouveau.addActionListener(this);
		menuiOuvrir.addActionListener(this);
		menuiSauvegarder.addActionListener(this);
		menuiImprimer.addActionListener(this);
 
		menuFichier.add(menuiNouveau);
		menuFichier.add(menuiOuvrir);
		menuFichier.add(menuiSauvegarder);
		menuFichier.add(menuiImprimer);

		menuiCouper.addActionListener(this);
		menuiCopier.addActionListener(this);
		menuiColler.addActionListener(this);
 
		menuEdition.add(menuiCouper);
		menuEdition.add(menuiCopier);
		menuEdition.add(menuiColler);

		menuiRechercher.addActionListener(this);
		menuiRemplacer.addActionListener(this);

		menuRecherche.add(menuiRechercher);
		menuRecherche.add(menuiRemplacer);

		menuiPolice.addActionListener(this);
		menuiCouleur.addActionListener(this);
		menuiTaille.addActionListener(this);
		menuiGras.addActionListener(this);
		menuiItalique.addActionListener(this);

		menuStyle.add(menuiPolice);
		menuStyle.add(menuiCouleur);
		menuStyle.add(menuiTaille);
		menuStyle.add(menuiGras);
		menuStyle.add(menuiItalique);
 
		menuBar.add(menuFichier);
		menuBar.add(menuEdition);
		menuBar.add(menuRecherche);
		menuBar.add(menuStyle);

		menuFichier.setMnemonic('F');
		menuEdition.setMnemonic('E');
		menuRecherche.setMnemonic('R');
		menuStyle.setMnemonic('S');

		menuiNouveau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuiOuvrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menuiSauvegarder.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuiImprimer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

		menuiCouper.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		menuiCopier.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		menuiColler.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

		menuiRechercher.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		menuiRemplacer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));

		menuiPolice.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		menuiCouleur.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		menuiTaille.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		menuiGras.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
		menuiItalique.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));

		// Font nouvellePolice = new Font("Verdana", Font.BOLD + Font.ITALIC, 18);
		Font police = new Font("Arial", Font.PLAIN, 14);
		txt.setFont(police);
 
		frame.setJMenuBar(menuBar);
		frame.add(scrollPane);
		frame.setSize(500, 500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e)
	{
		String chaine = e.getActionCommand();
 
		if (chaine.equals("Couper"))
			txt.cut();
		else if (chaine.equals("Copier"))
			txt.copy();
		else if (chaine.equals("Coller"))
			txt.paste();
		else if (chaine.equals("Enregistrer Sous"))
		{
			JFileChooser selecteurFichier = new JFileChooser("f:");
			int indice = selecteurFichier.showSaveDialog(null);
 
			if (indice == JFileChooser.APPROVE_OPTION)
			{
				File fichier = new File(selecteurFichier.getSelectedFile().getAbsolutePath());
 
				try
				{
					FileWriter fw = new FileWriter(fichier, false);
 
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(txt.getText());
 
					bw.flush();
					bw.close();
				}
				catch (Exception evt) { JOptionPane.showMessageDialog(frame, evt.getMessage()); }
			}
		}
		else if (chaine.equals("Imprimer"))
		{
			try { txt.print(); }
			catch (Exception evt) { JOptionPane.showMessageDialog(frame, evt.getMessage()); }
		}
		else if (chaine.equals("Ouvrir"))
		{
			JFileChooser ouvertureFichier = new JFileChooser("f:");
			int indice2 = ouvertureFichier.showOpenDialog(null);
 
			if (indice2 == JFileChooser.APPROVE_OPTION)
			{
				File file = new File(ouvertureFichier.getSelectedFile().getAbsolutePath());
 
				try
				{
					String str = "", ligne = "";
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
 
					ligne = br.readLine();
 
					while ((str = br.readLine()) != null)
						ligne = ligne + "\n" + str;
 
					txt.setText(ligne);
				}
				catch (Exception evt) { JOptionPane.showMessageDialog(frame, evt.getMessage()); }
			}
		}
		else if (chaine.equals("Nouveau"))
			txt.setText("");
		else if (chaine.equals("Chercher"))
		{
			String str = JOptionPane.showInputDialog(frame, "Entrez le texte à chercher");
			String text = txt.getText();
			int index = text.indexOf(str);
				
			if (index >= 0)
			{
				txt.setSelectionStart(index);
				txt.setSelectionEnd(index + str.length());
			} else
				JOptionPane.showMessageDialog(frame, "Texte non trouvé");
		}
		else if (chaine.equals("Remplacer"))
		{
			String str = JOptionPane.showInputDialog(frame, "Entrez le texte à remplacer");
			String str2 = JOptionPane.showInputDialog(frame, "Entrez le nouveau texte");
			txt.setText(txt.getText().replaceAll(str, str2));
		}
		else if (chaine.equals("Police"))
		{
			String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
			String str = (String) JOptionPane.showInputDialog(frame, "Choisissez la police", "Police", JOptionPane.PLAIN_MESSAGE, null, fonts, fonts[0]);
			txt.setFont(new Font(str, Font.PLAIN, 14));
		}
		else if (chaine.equals("Couleur"))
		{
			String[] couleurs = {"Noir", "Bleu", "Gris", "Vert", "Rouge", "Blanc", "Jaune"};
			String str = (String) JOptionPane.showInputDialog(frame, "Choisissez la couleur", "Couleur", JOptionPane.PLAIN_MESSAGE, null, couleurs, couleurs[0]);
 
			if (str.equals("Noir"))
				txt.setForeground(java.awt.Color.BLACK);
			else if (str.equals("Bleu"))
				txt.setForeground(java.awt.Color.BLUE);
			else if (str.equals("Gris"))
				txt.setForeground(java.awt.Color.GRAY);
			else if (str.equals("Vert"))
				txt.setForeground(java.awt.Color.GREEN);
			else if (str.equals("Rouge"))
				txt.setForeground(java.awt.Color.RED);
			else if (str.equals("Blanc"))
				txt.setForeground(java.awt.Color.WHITE);
			else if (str.equals("Jaune"))
				txt.setForeground(java.awt.Color.YELLOW);
		}
		else if (chaine.equals("Taille"))
		{
			String[] tailles = {"10", "12", "14", "16", "18", "20", "22", "24", "26", "28"};
			String str = (String) JOptionPane.showInputDialog(frame, "Choisissez la taille", "Taille", JOptionPane.PLAIN_MESSAGE, null, tailles, tailles[0]);
			txt.setFont(new Font("Arial", Font.PLAIN, Integer.parseInt(str)));
		}
		else if (chaine.equals("Gras"))
		{
			if (menuiGras.getText().equals("Gras"))
				txt.setFont(txt.getFont().deriveFont(Font.BOLD));
		}
		else if (chaine.equals("Italique"))
		{
			if (menuiItalique.getText().equals("Italique"))
				txt.setFont(txt.getFont().deriveFont(Font.ITALIC));
		}
	}

	public static void main(String args[]) { new Editeur(); }
}