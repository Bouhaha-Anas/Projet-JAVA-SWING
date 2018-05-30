package vue;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuPrincipal extends JFrame
{
	private JPanel contenuPanel;
	private JMenuBar MenuRubriques;
	private JMenu menugGestionEtudiants = new JMenu("Gestion �tudiants"), menugGestionClasses = new JMenu("Gestion classes"), menugGestionNotes = new JMenu("Gestion notes"),
				  menugGestionFilieres = new JMenu("Gestion fili�res"), menugGestionMatieres = new JMenu("Gestion mati�res"), menugGestionProfesseurs = new JMenu("Gestion professeurs"),
				  menugGestionMoyennes = new JMenu("Gestion moyennes"), menugGestionReclamations = new JMenu("Gestion r�clamations"), menugGestionDemandes = new JMenu("Gestion demandes");
	
	private JMenu menuOperations = new JMenu("Op�rations");
	
	private JMenuItem menuItemAccueil = new JMenuItem("Accueil");
	private JMenuItem menuItemQuitter = new JMenuItem("Quitter");
				  
	private JMenuItem menuItemAjoutEtudiant = new JMenuItem("Ajouter un(e) �tudiant(e)");
	private JMenuItem menuItemAfficherEtudiant = new JMenuItem("Table des �tudiants");
	private JMenuItem menuItemModifierEtudiant = new JMenuItem("Modifier un(e) �tudiant(e)");
	
	private JMenuItem menuItemAjoutClasse = new JMenuItem("Ajouter une classe");
	private JMenuItem menuItemAfficherClasse = new JMenuItem("Table de classes");
	private JMenuItem menuItemModifierClasse = new JMenuItem("Modifier une classe");
	
	private JMenuItem menuItemAjoutFiliere = new JMenuItem("Ajouter une fili�re");
	private JMenuItem menuItemAfficherFiliere = new JMenuItem("Table de fili�res");
	private JMenuItem menuItemModifierFiliere = new JMenuItem("Modifier une fili�re");
	
	private JMenuItem menuItemAjoutMatiere = new JMenuItem("Ajouter une mati�re");
	private JMenuItem menuItemAfficherMatiere = new JMenuItem("Table de mati�res");
	private JMenuItem menuItemModifierMatiere = new JMenuItem("Modifier une mati�re");
	
	private JMenuItem menuItemAjoutProfesseur = new JMenuItem("Ajouter un(e) professeur(e)");
	private JMenuItem menuItemAfficherProfesseur = new JMenuItem("Table de professeurs");
	private JMenuItem menuItemModifierProfesseur = new JMenuItem("Modifier un(e) professeur(e)");
	
	private JMenuItem menuItemAjoutReclamation = new JMenuItem("Ajouter une r�clamation");
	private JMenuItem menuItemAfficherReclamation = new JMenuItem("Table de r�clamations");
	private JMenuItem menuItemModifierReclamation = new JMenuItem("Modifier une r�clamation");
	
	private JMenuItem menuItemAjoutDemande = new JMenuItem("Ajouter une demande");
	private JMenuItem menuItemAfficherDemande = new JMenuItem("Table de demandes");
	private JMenuItem menuItemModifierDemande = new JMenuItem("Modifier une demande");
	
	
	
	private JLabel imageAccueil = new JLabel(new ImageIcon("./assets/img/EpiSousse.jpg"));
	
	public MenuPrincipal()
	{
		this.setTitle("Accueil");
		this.setSize(1035,680);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		contenuPanel = new JPanel();
		contenuPanel.add(imageAccueil);
		
		menugGestionEtudiants.add(menuItemAjoutEtudiant);
		menugGestionEtudiants.add(menuItemAfficherEtudiant);
		menugGestionEtudiants.add(menuItemModifierEtudiant);
		
		menugGestionClasses.add(menuItemAjoutClasse);
		menugGestionClasses.add(menuItemAfficherClasse);
		menugGestionClasses.add(menuItemModifierClasse);
		
		menugGestionFilieres.add(menuItemAjoutFiliere);
		menugGestionFilieres.add(menuItemAfficherFiliere);
		menugGestionFilieres.add(menuItemModifierFiliere);
		
		menugGestionMatieres.add(menuItemAjoutMatiere);
		menugGestionMatieres.add(menuItemAfficherMatiere);
		menugGestionMatieres.add(menuItemModifierMatiere);
		
		menugGestionProfesseurs.add(menuItemAjoutProfesseur);
		menugGestionProfesseurs.add(menuItemAfficherProfesseur);
		menugGestionProfesseurs.add(menuItemModifierProfesseur);
		
		menugGestionReclamations.add(menuItemAjoutReclamation);
		menugGestionReclamations.add(menuItemAfficherReclamation);
		menugGestionReclamations.add(menuItemModifierReclamation);
		
		menugGestionDemandes.add(menuItemAjoutDemande);
		menugGestionDemandes.add(menuItemAfficherDemande);
		menugGestionDemandes.add(menuItemModifierDemande);
		
		menuOperations.add(menuItemAccueil);
		menuOperations.add(menuItemQuitter);
		
		// les jMenuItem de rubrique Gestion �tudiants
		menuItemAjoutEtudiant.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MenuPrincipal.this.remove(contenuPanel);	
						}
						
						MenuPrincipal.this.setTitle("Ajouter un nouveau �tudiant");
						contenuPanel = new VueAjoutEtudiant();
						MenuPrincipal.this.add(contenuPanel);
						MenuPrincipal.this.validate();
						MenuPrincipal.this.repaint();
					}
				}
		);
		
		menuItemAfficherEtudiant.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MenuPrincipal.this.remove(contenuPanel);	
						}
						
						MenuPrincipal.this.setTitle("Listes des �tudiants");
						contenuPanel = new VueTableEtudiant();
						MenuPrincipal.this.add(contenuPanel);
						MenuPrincipal.this.validate();
						MenuPrincipal.this.repaint();
					}
				}
		);
		
		menuItemModifierEtudiant.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MenuPrincipal.this.remove(contenuPanel);	
						}
						
						MenuPrincipal.this.setTitle("Modifier un �tudiant");
						
						MenuPrincipal.this.validate();
						MenuPrincipal.this.repaint();
					}
				}
		);
		
		// les jMenuItem de rubrique Gestion classes
		menuItemAjoutClasse.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(contenuPanel != null)
					{
						MenuPrincipal.this.remove(contenuPanel);	
					}
					
					MenuPrincipal.this.setTitle("Ajouter une nouvelle classe");
					contenuPanel = new VueAjoutClasse();
					MenuPrincipal.this.add(contenuPanel);
					MenuPrincipal.this.validate();
					MenuPrincipal.this.repaint();
				}
			}
		);
		
		menuItemAfficherClasse.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MenuPrincipal.this.remove(contenuPanel);	
						}
						
						MenuPrincipal.this.setTitle("Listes des classes");
						contenuPanel = new VueTableClasse();
						MenuPrincipal.this.add(contenuPanel);
						MenuPrincipal.this.validate();
						MenuPrincipal.this.repaint();
					}
				}
		);
		
		menuItemModifierClasse.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MenuPrincipal.this.remove(contenuPanel);	
						}
						
						MenuPrincipal.this.setTitle("Modifier une classe");
						
						MenuPrincipal.this.validate();
						MenuPrincipal.this.repaint();
					}
				}
		);
		
		// les jMenuItem de rubrique Gestion fili�res
		menuItemAjoutFiliere.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(contenuPanel != null)
					{
						MenuPrincipal.this.remove(contenuPanel);	
					}
					
					MenuPrincipal.this.setTitle("Ajouter une nouvelle fili�re");
					contenuPanel = new VueAjoutFiliere();
					MenuPrincipal.this.add(contenuPanel);
					MenuPrincipal.this.validate();
					MenuPrincipal.this.repaint();
				}
			}
		);
		
		menuItemAfficherFiliere.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MenuPrincipal.this.remove(contenuPanel);	
						}
						
						MenuPrincipal.this.setTitle("Listes des fili�res");
						contenuPanel = new VueTableFiliere();
						MenuPrincipal.this.add(contenuPanel);
						MenuPrincipal.this.validate();
						MenuPrincipal.this.repaint();
					}
				}
		);
		
		menuItemModifierFiliere.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MenuPrincipal.this.remove(contenuPanel);	
						}
						
						MenuPrincipal.this.setTitle("Modifier une fili�re");
						
						MenuPrincipal.this.validate();
						MenuPrincipal.this.repaint();
					}
				}
		);
		
		// les jMenuItem de rubrique Gestion mati�res
		menuItemAjoutMatiere.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(contenuPanel != null)
					{
						MenuPrincipal.this.remove(contenuPanel);	
					}
					
					MenuPrincipal.this.setTitle("Ajouter une nouvelle mati�re");
					contenuPanel = new VueAjoutMatiere();
					MenuPrincipal.this.add(contenuPanel);
					MenuPrincipal.this.validate();
					MenuPrincipal.this.repaint();
				}
			}
		);
		
		menuItemAfficherMatiere.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MenuPrincipal.this.remove(contenuPanel);	
						}
						
						MenuPrincipal.this.setTitle("Listes des mati�res");
						contenuPanel = new VueTableMatiere();
						MenuPrincipal.this.add(contenuPanel);
						MenuPrincipal.this.validate();
						MenuPrincipal.this.repaint();
					}
				}
		);
		
		menuItemModifierMatiere.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MenuPrincipal.this.remove(contenuPanel);	
						}
						
						MenuPrincipal.this.setTitle("Modifier une mati�re");
						
						MenuPrincipal.this.validate();
						MenuPrincipal.this.repaint();
					}
				}
		);
		
		// les jMenuItem de rubrique Gestion professeurs
		menuItemAjoutProfesseur.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(contenuPanel != null)
					{
						MenuPrincipal.this.remove(contenuPanel);	
					}
					
					MenuPrincipal.this.setTitle("Ajouter un nouveau professeur");
					contenuPanel = new VueAjoutProfesseur();
					MenuPrincipal.this.add(contenuPanel);
					MenuPrincipal.this.validate();
					MenuPrincipal.this.repaint();
				}
			}
		);
		
		menuItemAfficherProfesseur.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MenuPrincipal.this.remove(contenuPanel);	
						}
						
						MenuPrincipal.this.setTitle("Listes des professeurs");
						contenuPanel = new VueTableProfesseur();
						MenuPrincipal.this.add(contenuPanel);
						MenuPrincipal.this.validate();
						MenuPrincipal.this.repaint();
					}
				}
		);
		
		menuItemModifierProfesseur.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MenuPrincipal.this.remove(contenuPanel);	
						}
						
						MenuPrincipal.this.setTitle("Modifier un(e) professeur(e)");
						
						MenuPrincipal.this.validate();
						MenuPrincipal.this.repaint();
					}
				}
		);
		
		// les jMenuItem de rubrique Gestion moyennes
	/*	itemMoyennes.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(contenuPanel != null)
					{
						MenuPrincipal.this.remove(contenuPanel);	
					}
					
					contenuPanel = new JPanel();
					GestionMoyenne gestionMoyenne = new GestionMoyenne();
					contenuPanel.add(gestionMoyenne);
					MenuPrincipal.this.add(contenuPanel,BorderLayout.CENTER);
					MenuPrincipal.this.validate();
					MenuPrincipal.this.repaint();
				}
			}
		);
	*/	
		
		// les jMenuItem de rubrique Gestion r�clamations
		menuItemAjoutReclamation.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(contenuPanel != null)
					{
						MenuPrincipal.this.remove(contenuPanel);	
					}
					
					MenuPrincipal.this.setTitle("Ajouter une nouvelle r�clamation");
					contenuPanel = new VueAjoutReclamation();
					MenuPrincipal.this.add(contenuPanel);
					MenuPrincipal.this.validate();
					MenuPrincipal.this.repaint();
				}
			}
		);
		
		menuItemAfficherReclamation.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MenuPrincipal.this.remove(contenuPanel);	
						}
						
						MenuPrincipal.this.setTitle("Listes des r�clamations");
						contenuPanel = new VueTableReclamation();
						MenuPrincipal.this.add(contenuPanel);
						MenuPrincipal.this.validate();
						MenuPrincipal.this.repaint();
					}
				}
		);
		
		menuItemModifierReclamation.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MenuPrincipal.this.remove(contenuPanel);	
						}
						
						MenuPrincipal.this.setTitle("Modifier une r�clamation");
						
						MenuPrincipal.this.validate();
						MenuPrincipal.this.repaint();
					}
				}
		);
		
		// les jMenuItem de rubrique Gestion demandes
		menuItemAjoutDemande.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(contenuPanel != null)
					{
						MenuPrincipal.this.remove(contenuPanel);	
					}
					
					MenuPrincipal.this.setTitle("Ajouter une nouvelle demande");
					contenuPanel = new VueAjoutDemande();
					MenuPrincipal.this.add(contenuPanel);
					MenuPrincipal.this.validate();
					MenuPrincipal.this.repaint();
				}
			}
		);
		
		menuItemAfficherDemande.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MenuPrincipal.this.remove(contenuPanel);	
						}
						
						MenuPrincipal.this.setTitle("Listes des demandes");
						contenuPanel = new VueTableDemande();
						MenuPrincipal.this.add(contenuPanel);
						MenuPrincipal.this.validate();
						MenuPrincipal.this.repaint();
					}
				}
		);
		
		menuItemModifierDemande.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MenuPrincipal.this.remove(contenuPanel);	
						}
						
						MenuPrincipal.this.setTitle("Modifier une demande");
						
						MenuPrincipal.this.validate();
						MenuPrincipal.this.repaint();
					}
				}
		);
		
		// les jMenuItem de rubrique Gestion notes
	/*	itemNotes.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(contenuPanel != null)
					{
						MenuPrincipal.this.remove(contenuPanel);	
					}
					
					contenuPanel = new GestionNotes();
					MenuPrincipal.this.add(contenuPanel);
					MenuPrincipal.this.validate();
					MenuPrincipal.this.repaint();
				}
			}
		);
	*/
		
		menuItemAccueil.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(contenuPanel != null)
					{
						MenuPrincipal.this.remove(contenuPanel);	
					}
					
					MenuPrincipal.this.setTitle("Accueil");
					contenuPanel = new JPanel();
					contenuPanel.add(imageAccueil);
					MenuPrincipal.this.add(contenuPanel,BorderLayout.CENTER);
					MenuPrincipal.this.validate();
					MenuPrincipal.this.repaint();
				}
			}
		);
		
		menuItemQuitter.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					MenuPrincipal.this.dispose();
				}
			}
		);
		
		MenuRubriques = new JMenuBar();
		
		MenuRubriques.add(menuOperations);
		MenuRubriques.add(menugGestionEtudiants);
		MenuRubriques.add(menugGestionClasses);
		MenuRubriques.add(menugGestionFilieres);
		MenuRubriques.add(menugGestionMatieres);
		MenuRubriques.add(menugGestionProfesseurs);
		MenuRubriques.add(menugGestionReclamations);
		MenuRubriques.add(menugGestionDemandes);
		MenuRubriques.add(menugGestionMoyennes);
		MenuRubriques.add(menugGestionNotes);
		
		this.add(MenuRubriques,BorderLayout.NORTH);
		this.add(contenuPanel,BorderLayout.CENTER);
	}

}

