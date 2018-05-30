package vue;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import dao.DemandeDAO;

public class MainFrame extends JFrame
{
	private JPanel contenuPanel;
	private JMenuBar MenuRubriques;
	private JMenu menugGestionEtudiants = new JMenu("Gestion étudiants"), menugGestionClasses = new JMenu("Gestion classes"), menugGestionNotes = new JMenu("Gestion notes"),
				  menugGestionFilieres = new JMenu("Gestion filières"), menugGestionMatieres = new JMenu("Gestion matières"), menugGestionProfesseurs = new JMenu("Gestion professeurs"),
				  menugGestionReclamations = new JMenu("Gestion réclamations"), menugGestionDemandes = new JMenu("Gestion demandes");
	
	private JMenu menuOperations = new JMenu("Opérations");
	
	private JMenuItem menuItemAccueil = new JMenuItem("Accueil");
	private JMenuItem menuItemQuitter = new JMenuItem("Quitter");
				  
	private JMenuItem menuItemAjoutEtudiant = new JMenuItem("Ajouter un(e) étudiant(e)");
	private JMenuItem menuItemAfficherEtudiant = new JMenuItem("Table des étudiants");
	private JMenuItem menuItemModifierSupprimerEtudiant = new JMenuItem("Modifier/Supprimer un(e) étudiant(e)");
	
	private JMenuItem menuItemAjoutClasse = new JMenuItem("Ajouter une classe");
	private JMenuItem menuItemAfficherClasse = new JMenuItem("Table de classes");
	private JMenuItem menuItemModifierSupprimerClasse = new JMenuItem("Modifier/Supprimer une classe");
	
	private JMenuItem menuItemAjoutFiliere = new JMenuItem("Ajouter une filière");
	private JMenuItem menuItemAfficherFiliere = new JMenuItem("Table de filières");
	private JMenuItem menuItemModifierSupprimerFiliere = new JMenuItem("Modifier/Supprimer une filière");
	private JMenuItem menuItemAssocierFiliere_Matiere = new JMenuItem("Associer une filière à une matière");
	private JMenuItem menuItemTableFiliere_Matiere = new JMenuItem("Table de filères-matières");
	
	private JMenuItem menuItemAjoutMatiere = new JMenuItem("Ajouter une matière");
	private JMenuItem menuItemAfficherMatiere = new JMenuItem("Table de matières");
	private JMenuItem menuItemModifierSupprimerMatiere = new JMenuItem("Modifier/Supprimer une matière");
	private JMenuItem menuItemAssocierMatiere_Filiere = new JMenuItem("Associer une matière à une filière");
	private JMenuItem menuItemTableMatiere_Filiere = new JMenuItem("Table de matières-filères");
	private JMenuItem menuItemAssocierMatiere_Professeur = new JMenuItem("Associer une matière à un professeur");
	private JMenuItem menuItemTableMatiere_Professeur = new JMenuItem("Table de matières-professeurs");
	
	private JMenuItem menuItemAjoutProfesseur = new JMenuItem("Ajouter un(e) professeur(e)");
	private JMenuItem menuItemAfficherProfesseur = new JMenuItem("Table de professeurs");
	private JMenuItem menuItemModifierSupprimerProfesseur = new JMenuItem("Modifier/Supprimer un(e) professeur(e)");
	private JMenuItem menuItemAssocierProfesseur_Matiere = new JMenuItem("Associer un professeur à une matière");
	private JMenuItem menuItemTableProfesseur_Matiere = new JMenuItem("Table de professeurs-matières");
	
	private JMenuItem menuItemAjoutReclamation = new JMenuItem("Ajouter une réclamation");
	private JMenuItem menuItemAfficherReclamation = new JMenuItem("Table de réclamations");
	
	private JMenuItem menuItemAjoutDemande = new JMenuItem("Ajouter une demande");
	private JMenuItem menuItemAfficherDemande = new JMenuItem("Table de demandes");
	
	private JMenuItem menuItemSaisieNote = new JMenuItem("Saisir des notes");
	private JMenuItem menuItemAfficherNote = new JMenuItem("Table de notes");
	
	
	private JLabel imageAccueil = new JLabel(new ImageIcon("./assets/img/EpiSousse.jpg"));
	
	public MainFrame()
	{
		this.setTitle("Accueil");
		this.setSize(1035,680);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contenuPanel = new JPanel();
		contenuPanel.add(imageAccueil);
		
		// MENU GESTION ETUDIANTS //
		menugGestionEtudiants.add(menuItemAjoutEtudiant);
		menugGestionEtudiants.add(menuItemAfficherEtudiant);
		menugGestionEtudiants.add(menuItemModifierSupprimerEtudiant);
		
		// MENU GESTION CLASSES //
		menugGestionClasses.add(menuItemAjoutClasse);
		menugGestionClasses.add(menuItemAfficherClasse);
		menugGestionClasses.add(menuItemModifierSupprimerClasse);
		
		// MENU GESTION FILIERES //
		menugGestionFilieres.add(menuItemAjoutFiliere);
		menugGestionFilieres.add(menuItemAfficherFiliere);
		menugGestionFilieres.add(menuItemModifierSupprimerFiliere);
		menugGestionFilieres.add(menuItemAssocierFiliere_Matiere);
		menugGestionFilieres.add(menuItemTableFiliere_Matiere);
		
		// MENU GESTION MATIERES //
		menugGestionMatieres.add(menuItemAjoutMatiere);
		menugGestionMatieres.add(menuItemAfficherMatiere);
		menugGestionMatieres.add(menuItemModifierSupprimerMatiere);
		menugGestionMatieres.add(menuItemAssocierMatiere_Filiere);
		menugGestionMatieres.add(menuItemTableMatiere_Filiere);
		menugGestionMatieres.add(menuItemAssocierMatiere_Professeur);
		menugGestionMatieres.add(menuItemTableMatiere_Professeur);
		
		// MENU GESTION PROFESSEUR //
		menugGestionProfesseurs.add(menuItemAjoutProfesseur);
		menugGestionProfesseurs.add(menuItemAfficherProfesseur);
		menugGestionProfesseurs.add(menuItemModifierSupprimerProfesseur);
		menugGestionProfesseurs.add(menuItemAssocierProfesseur_Matiere);
		menugGestionProfesseurs.add(menuItemTableProfesseur_Matiere);
		
		// MENU GESTION RECLAMATIONS //
		menugGestionReclamations.add(menuItemAjoutReclamation);
		menugGestionReclamations.add(menuItemAfficherReclamation);
		
		// MENU GESTION DEMANDES //
		menugGestionDemandes.add(menuItemAjoutDemande);
		menugGestionDemandes.add(menuItemAfficherDemande);
		
		// MENU OPERATIONS //
		menuOperations.add(menuItemAccueil);
		menuOperations.add(menuItemQuitter);
		
		// MENU GESTION NOTES //
		menugGestionNotes.add(menuItemSaisieNote);
		menugGestionNotes.add(menuItemAfficherNote);
		
		menuItemQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,KeyEvent.ALT_MASK));
		menuItemAccueil.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,KeyEvent.CTRL_MASK));
		
		
		// les jMenuItem de rubrique Gestion étudiants
		menuItemAjoutEtudiant.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Ajouter un nouveau étudiant");
						contenuPanel = new VueAjoutEtudiant();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
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
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Listes des étudiants");
						contenuPanel = new VueTableEtudiant();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
					}
				}
		);
		
		menuItemModifierSupprimerEtudiant.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Modifier/Supprimer un étudiant");
						contenuPanel = new VueModifierEtudiant();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
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
						MainFrame.this.remove(contenuPanel);	
					}
					
					MainFrame.this.setSize(1035,680);
					MainFrame.this.setResizable(false);
					MainFrame.this.setTitle("Ajouter une nouvelle classe");
					contenuPanel = new VueAjoutClasse();
					MainFrame.this.add(contenuPanel);
					MainFrame.this.validate();
					MainFrame.this.repaint();
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
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Listes des classes");
						contenuPanel = new VueTableClasse();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
					}
				}
		);
		
		menuItemModifierSupprimerClasse.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Modifier/Supprimer une classe");
						contenuPanel = new VueModifierClasse();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
					}
				}
		);
		
		// les jMenuItem de rubrique Gestion filières
		menuItemAjoutFiliere.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(contenuPanel != null)
					{
						MainFrame.this.remove(contenuPanel);	
					}
					
					MainFrame.this.setSize(1035,680);
					MainFrame.this.setResizable(false);
					MainFrame.this.setTitle("Ajouter une nouvelle filière");
					contenuPanel = new VueAjoutFiliere();
					MainFrame.this.add(contenuPanel);
					MainFrame.this.validate();
					MainFrame.this.repaint();
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
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Listes des filières");
						contenuPanel = new VueTableFiliere();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
					}
				}
		);
		
		menuItemModifierSupprimerFiliere.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Modifier/Supprimer une filière");
						contenuPanel = new VueModifierFiliere();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
					}
				}
		);
		
		menuItemAssocierFiliere_Matiere.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Associer une filière à une matière");
						contenuPanel = new VueAssocierFiliereMatiere();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
					}
				}
		);
		
		menuItemTableFiliere_Matiere.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Tables filière-matière");
						contenuPanel = new VueTableFiliereMatiere();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
					}
				}
		);
		
		// les jMenuItem de rubrique Gestion matières
		menuItemAjoutMatiere.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(contenuPanel != null)
					{
						MainFrame.this.remove(contenuPanel);	
					}
					
					MainFrame.this.setSize(1035,680);
					MainFrame.this.setResizable(false);
					MainFrame.this.setTitle("Ajouter une nouvelle matière");
					contenuPanel = new VueAjoutMatiere();
					MainFrame.this.add(contenuPanel);
					MainFrame.this.validate();
					MainFrame.this.repaint();
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
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Listes des matières");
						contenuPanel = new VueTableMatiere();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
					}
				}
		);
		
		menuItemModifierSupprimerMatiere.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Modifier/Supprimer une matière");
						contenuPanel = new VueModifierMatiere();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
					}
				}
		);
		
		menuItemAssocierMatiere_Filiere.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Associer une matière à une filière");
						contenuPanel = new VueAssocierFiliereMatiere();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
					}
				}
		);
		
		menuItemTableMatiere_Filiere.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Tables matière-filière");
						contenuPanel = new VueTableFiliereMatiere();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
					}
				}
		);
		
		menuItemAssocierMatiere_Professeur.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Associer une matière à un professeur");
						contenuPanel = new VueAssocierProfesseurMatiere();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
					}
				}
		);
		
		menuItemTableMatiere_Professeur.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Tables matière-professeur");
						contenuPanel = new VueTableProfesseurMatiere();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
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
						MainFrame.this.remove(contenuPanel);	
					}
					
					MainFrame.this.setSize(1035,680);
					MainFrame.this.setResizable(false);
					MainFrame.this.setTitle("Ajouter un nouveau professeur");
					contenuPanel = new VueAjoutProfesseur();
					MainFrame.this.add(contenuPanel);
					MainFrame.this.validate();
					MainFrame.this.repaint();
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
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Listes des professeurs");
						contenuPanel = new VueTableProfesseur();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
					}
				}
		);
		
		menuItemModifierSupprimerProfesseur.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Modifier/Supprimer un(e) professeur(e)");
						contenuPanel = new VueModifierProfesseur();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
					}
				}
		);
		
		menuItemAssocierProfesseur_Matiere.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Associer un professeur à une matière");
						contenuPanel = new VueAssocierProfesseurMatiere();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
					}
				}
		);
		
		menuItemTableProfesseur_Matiere.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Tables professeur-matière");
						contenuPanel = new VueTableProfesseurMatiere();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
					}
				}
		);	
		
		// les jMenuItem de rubrique Gestion réclamations
		menuItemAjoutReclamation.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(contenuPanel != null)
					{
						MainFrame.this.remove(contenuPanel);	
					}
					
					MainFrame.this.setSize(1035,680);
					MainFrame.this.setResizable(false);
					MainFrame.this.setTitle("Ajouter une nouvelle réclamation");
					contenuPanel = new VueAjoutReclamation();
					MainFrame.this.add(contenuPanel);
					MainFrame.this.validate();
					MainFrame.this.repaint();
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
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						MainFrame.this.setTitle("Listes des réclamations");
						contenuPanel = new VueTableReclamation();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
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
						MainFrame.this.remove(contenuPanel);	
					}
					
					MainFrame.this.setTitle("Ajouter une nouvelle demande");
					contenuPanel = new VueAjoutDemande();
					MainFrame.this.add(contenuPanel);
					MainFrame.this.validate();
					MainFrame.this.repaint();
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
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setResizable(true);
						MainFrame.this.setTitle("Listes des demandes");
						contenuPanel = new VueTableDemande();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.validate();
						MainFrame.this.repaint();
					}
				}
		);
		
		// les jMenuItem de rubrique Gestion notes
		menuItemSaisieNote.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(contenuPanel != null)
					{
						MainFrame.this.remove(contenuPanel);	
					}
					
					MainFrame.this.setSize(1035,680);
					MainFrame.this.setResizable(false);
					contenuPanel = new VueSaisieNoteMatiere();
					MainFrame.this.add(contenuPanel);
					MainFrame.this.setTitle("Saisie des notes");
					MainFrame.this.validate();
					MainFrame.this.repaint();
				}
			}
		);
		
		menuItemAfficherNote.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(contenuPanel != null)
						{
							MainFrame.this.remove(contenuPanel);	
						}
						
						MainFrame.this.setSize(1035,680);
						MainFrame.this.setResizable(false);
						contenuPanel = new VueTableNote();
						MainFrame.this.add(contenuPanel);
						MainFrame.this.setTitle("Liste des notes");
						MainFrame.this.validate();
						MainFrame.this.repaint();
					}
				}
			);
	
		
		menuItemAccueil.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(contenuPanel != null)
					{
						MainFrame.this.remove(contenuPanel);	
					}
					
					MainFrame.this.setSize(1035,680);
					MainFrame.this.setResizable(false);
					MainFrame.this.setTitle("Accueil");
					contenuPanel = new JPanel();
					contenuPanel.add(imageAccueil);
					MainFrame.this.add(contenuPanel,BorderLayout.CENTER);
					MainFrame.this.validate();
					MainFrame.this.repaint();
				}
			}
		);
		
		menuItemQuitter.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					MainFrame.this.dispose();
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
		MenuRubriques.add(menugGestionNotes);
		
		this.add(MenuRubriques,BorderLayout.NORTH);
		this.add(contenuPanel,BorderLayout.CENTER);
	}
	
	public void displayTray() throws AWTException, java.net.MalformedURLException 
	{
        SystemTray tray = SystemTray.getSystemTray();

        Image image = Toolkit.getDefaultToolkit().createImage("./assets/img/epis.png");
        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Système de Gestion-Scolarité");
        tray.add(trayIcon);

        trayIcon.displayMessage("Bonjour Mr/Mme l'agent", "Vous etes dans le système de Gestion-Scolarité EPI-Sousse", MessageType.INFO);    
    }

}
