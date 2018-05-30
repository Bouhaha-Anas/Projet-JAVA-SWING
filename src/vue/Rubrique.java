package vue;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Rubrique extends JPanel
{
	private JButton etudiants, classes, filieres, matieres, professeurs, moyennes, reclamations, demandes, notes;
	private JPanel panel;
	public Rubrique()
	{
		panel = new JPanel();
		panel.setLayout(new GridLayout(3,3));
		panel.setBorder(BorderFactory.createTitledBorder("Rubriques"));
		
		//Gestion des étudiants
		Icon icon1 = new ImageIcon("./assets/img/etudiants.png");
		etudiants = new JButton(icon1);
		etudiants.setSize(100,100);
		etudiants.setToolTipText("Gestion des etudiants");
		etudiants.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						VueAjoutEtudiant gestionEtudiant = new VueAjoutEtudiant();
						gestionEtudiant.setVisible(true);
					}
				}
		);
		
	    //Gestion des classes
	  	Icon icon2 = new ImageIcon("./assets/img/classes.png");
	  	classes = new JButton(icon2);
	  	classes.setSize(100,100);
	  	classes.setToolTipText("Gestion des classes");
	  	classes.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						VueAjoutClasse gestionClasse = new VueAjoutClasse();
						gestionClasse.setVisible(true);
					}
				}
		);
	  	
	  	//Gestion des filères
	  	Icon icon3 = new ImageIcon("./assets/img/filieres.png");
	  	filieres = new JButton(icon3);
	  	filieres.setSize(100,100);
	  	filieres.setToolTipText("Gestion des filieres");
	  	filieres.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						VueAjoutFiliere gestionFiliere = new VueAjoutFiliere();
						gestionFiliere.setVisible(true);
					}
				}
		);
	  	
	  	//Gestion des matieres
	  	Icon icon4 = new ImageIcon("./assets/img/matieres.png");
	  	matieres = new JButton(icon4);
	  	matieres.setSize(100,100);
	  	matieres.setToolTipText("Gestion des matieres");
	  	matieres.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						VueAjoutMatiere gestionMatiere = new VueAjoutMatiere();
						gestionMatiere.setVisible(true);
					}
				}
		);
	  	
	  	//Gestion des professeurs
	  	Icon icon5 = new ImageIcon("./assets/img/professeurs.png");
	  	professeurs = new JButton(icon5);
	  	professeurs.setSize(100,100);
	  	professeurs.setToolTipText("Gestion des professeurs");
	  	professeurs.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						VueAjoutProfesseur gestionProfesseur = new VueAjoutProfesseur();
						gestionProfesseur.setVisible(true);
					}
				}
		);
	  	
	  	//Gestion des moyennes
	  	Icon icon6 = new ImageIcon("./assets/img/moyennes.png");
	  	moyennes = new JButton(icon6);
	  	moyennes.setSize(100,100);
	  	moyennes.setToolTipText("Gestion des moyennes");
	  	moyennes.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						
					}
				}
		);
	  	
	  	//Gestion des reclamations
	  	Icon icon7 = new ImageIcon("./assets/img/reclamations.png");
	  	reclamations = new JButton(icon7);
	  	reclamations.setSize(100,100);
	  	reclamations.setToolTipText("Gestion des reclamations");
	  	reclamations.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						VueAjoutEtudiant gestionEtudiant = new VueAjoutEtudiant();
						gestionEtudiant.setVisible(true);
					}
				}
		);
	  	
	  	//Gestion des demandes
	  	Icon icon8 = new ImageIcon("./assets/img/demandes.png");
	  	demandes = new JButton(icon8);
	  	demandes.setSize(100,100);
	  	demandes.setToolTipText("Gestion des demandes");
	  	demandes.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						VueAjoutDemande gestionDemande = new VueAjoutDemande();
						gestionDemande.setVisible(true);
					}
				}
		);
	  	
	  	//Gestion des notes
	  	Icon icon9 = new ImageIcon("./assets/img/notes.png");
	  	notes = new JButton(icon9);
	  	notes.setSize(100,100);
	  	notes.setToolTipText("Gestion des notes");
	  	notes.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						
					}
				}
		);
	  	
	  	panel.add(etudiants);
	  	panel.add(classes);
	  	panel.add(filieres);
	  	panel.add(matieres);
	  	panel.add(professeurs);
	  	panel.add(moyennes);
	  	panel.add(reclamations);
	  	panel.add(demandes);
	  	panel.add(notes);
	  	this.add(panel);
	}
}
