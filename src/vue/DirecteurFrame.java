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

import javax.swing.*;

import dao.DemandeDAO;

public class DirecteurFrame extends JFrame
{
	private JPanel contenuPanel;
	private JMenuBar MenuRubriques;
	private JMenu menugGestionDemandes = new JMenu("Gestion demandes");
	
	private JMenu menuOperations = new JMenu("Opérations");
	
	private JMenuItem menuItemAccueil = new JMenuItem("Accueil");
	private JMenuItem menuItemQuitter = new JMenuItem("Quitter");
	
	private JMenuItem menuItemApprouverDemande = new JMenuItem("Consulter & approuver");
	
	private JLabel imageAccueil = new JLabel(new ImageIcon("./assets/img/EpiSousse.jpg"));
	
	public DirecteurFrame()
	{
		this.setTitle("Accueil");
		this.setSize(1035,680);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		contenuPanel = new JPanel();
		contenuPanel.add(imageAccueil);
		
		// MENU GESTION DEMANDES //
		menugGestionDemandes.add(menuItemApprouverDemande);
		
		// MENU OPERATIONS //
		menuOperations.add(menuItemAccueil);
		menuOperations.add(menuItemQuitter);
	
		
		// les jMenuItem de rubrique Gestion demandes
		menuItemApprouverDemande.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(contenuPanel != null)
					{
						DirecteurFrame.this.remove(contenuPanel);	
					}
					
					DirecteurFrame.this.setTitle("Consulter & approuver les demandes");
					contenuPanel = new VueTableDemandeDirecteur();
					DirecteurFrame.this.add(contenuPanel);
					DirecteurFrame.this.validate();
					DirecteurFrame.this.repaint();
				}
			}
		);
		
		// les jMenuItem de rubrique Opérations	
		menuItemAccueil.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(contenuPanel != null)
					{
						DirecteurFrame.this.remove(contenuPanel);	
					}
					
					DirecteurFrame.this.setTitle("Accueil");
					contenuPanel = new JPanel();
					contenuPanel.add(imageAccueil);
					DirecteurFrame.this.add(contenuPanel,BorderLayout.CENTER);
					DirecteurFrame.this.validate();
					DirecteurFrame.this.repaint();
				}
			}
		);
		
		menuItemQuitter.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					DirecteurFrame.this.dispose();
				}
			}
		);
		
		MenuRubriques = new JMenuBar();
		
		MenuRubriques.add(menuOperations);
		MenuRubriques.add(menugGestionDemandes);
	
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
        
        int demande = DemandeDAO.nombreDemande();
        
        if(demande == 0)
        {
        	trayIcon.displayMessage("Bonjour Mr le directeur", "Vous n'avez pas aucune demande à approuver", MessageType.INFO);
        }
        else
        {
        	trayIcon.displayMessage("Bonjour Mr le directeur", "Vous avez "+ demande +" demande à approuver", MessageType.INFO);
        }
     
    }

}
