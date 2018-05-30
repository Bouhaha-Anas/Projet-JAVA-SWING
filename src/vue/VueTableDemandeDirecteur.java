package vue;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

import dao.DemandeDAO;
import dao.EtudiantDAO;
import dao.ProfesseurMatiereDAO;
import tableModel.TableModel;
import webFilesCreation.HTMLGeneration;

import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import net.miginfocom.swing.MigLayout;


public class VueTableDemandeDirecteur extends JPanel
{
	JTable tableDemandeNonapprouves = new JTable();
	private JButton aprrouver = new JButton("Approuver"), ignorer = new JButton("Ignorer"), valider = new JButton("Valider");
	public static JTextArea traison = new JTextArea(10,20);
	JScrollPane jScrollPane = new JScrollPane(), scrollTraison = new JScrollPane(traison);
	private JLabel lraison = new JLabel("Raison :");
	public static int idDemande,idEtudiant;
	public static String sujet;
	
	public VueTableDemandeDirecteur()
	{
		
		setLayout(new MigLayout("", "[577.00px]", "[427px][50.00][186px,grow][-7.00][]"));
		this.add(aprrouver, "flowx,cell 0 1,alignx left,aligny center");
		this.add(ignorer, "cell 0 1,alignx left,aligny center");
		this.add(jScrollPane, "cell 0 0 5 1,alignx left,aligny top");
		
		TableModel tableModelDemande = new TableModel(DemandeDAO.AfficherDemandesNonApprouves());
		tableDemandeNonapprouves.setModel(tableModelDemande);
		tableDemandeNonapprouves.setFillsViewportHeight(true);
		tableDemandeNonapprouves.setPreferredScrollableViewportSize(new Dimension(1000,400));
		jScrollPane.setViewportView(tableDemandeNonapprouves);
		
		ignorer.setEnabled(false);
		aprrouver.setEnabled(false);
		
		aprrouver.addActionListener
		(
		    new ActionListener() 
		    {
				
				public void actionPerformed(ActionEvent e) 
				{
					Object[] options = {"Oui", "Non"};
		            int n = JOptionPane.showOptionDialog(null, "Voulez vous vraiment approuver la demande avec l'id : " +
		            		idDemande + " ?", "Avertissement", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

		            if(n == JOptionPane.YES_OPTION) 
		            {	
		            	DemandeDAO.approuver();
		                JOptionPane.showMessageDialog(null, "La demande est bien approuvée.");
		   
		                if(sujet.equals("certificatPrésence"))
		                {
		                	EtudiantDAO.getEtudiantByIdForHTMLCertificat();
		                	HTMLGeneration.HTMLCertificat();
		                	
		                }
		                aprrouver.setEnabled(false);
		                ignorer.setEnabled(false);
		            }
		            else
		            {
		            	aprrouver.setEnabled(false);
		                ignorer.setEnabled(false);
		            }
				}
			}		
		);
	
		tableDemandeNonapprouves.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e) 
					{
	                    int line = tableDemandeNonapprouves.getSelectedRow();
	                    idDemande = Integer.parseInt(tableDemandeNonapprouves.getModel().getValueAt(line, 0).toString());
	                    sujet = tableDemandeNonapprouves.getModel().getValueAt(line, 1).toString();
	                    idEtudiant = Integer.parseInt(tableDemandeNonapprouves.getModel().getValueAt(line, 6).toString());
	                    aprrouver.setEnabled(true);
	                    ignorer.setEnabled(true);
					}
				}
		);
		
		ignorer.addActionListener
		(
		    new ActionListener() 
		    {
				
				public void actionPerformed(ActionEvent e) 
				{
					 	VueTableDemandeDirecteur.this.remove(lraison);
					 	VueTableDemandeDirecteur.this.remove(valider);
					 	VueTableDemandeDirecteur.this.remove(scrollTraison);
		        		VueTableDemandeDirecteur.this.add(lraison, "flowx,cell 0 2");
		        		VueTableDemandeDirecteur.this.add(valider, "flowx,cell 0 5");
		        		VueTableDemandeDirecteur.this.add(scrollTraison, "cell 0 2");
		        		VueTableDemandeDirecteur.this.revalidate();
		        		VueTableDemandeDirecteur.this.repaint();
		        		valider.addActionListener
		        		(
		        			new ActionListener()
		        			{
		        				public void actionPerformed(ActionEvent e)
		        				{
		        					if(traison.getText().equals("") == true)
		        					{
		        						JOptionPane.showMessageDialog(null, "Veuillez renseigner la raison !", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
		        					}
		        					else
		        					{
		        						Object[] options = {"Oui", "Non"};
		        			            int n = JOptionPane.showOptionDialog(null, "Voulez vous vraiment ignorer la demande avec l'id : " +
		        			            		idDemande + " ?", "Avertissement", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

		        			            if(n == JOptionPane.YES_OPTION) 
		        			            {
			        						DemandeDAO.ignorer();
			        						JOptionPane.showMessageDialog(null, "La demande est bien ignorée","Succés",JOptionPane.INFORMATION_MESSAGE);
		        			            }
		        			            else
		        			            {
		        			            	VueTableDemandeDirecteur.this.remove(lraison);
		        			            	VueTableDemandeDirecteur.this.remove(valider);
		        			            	VueTableDemandeDirecteur.this.remove(scrollTraison);
		        			            	VueTableDemandeDirecteur.this.revalidate();
		        			        		VueTableDemandeDirecteur.this.repaint();
		        			        		ignorer.setEnabled(false);
		        			        		aprrouver.setEnabled(false);
		        			            }
		        						
		        					}
		        				}
		        			}
		        		);		            		               
				}
			}		
		);
		
	}
	
	
}	