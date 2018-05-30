package vue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import dao.EtudiantDAO;
import dao.ProfesseurDAO;
import tableModel.TableModel;

import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class VueTableProfesseur extends JPanel
{
	JTable tableProfesseur = new JTable();
	JScrollPane jScrollPane = new JScrollPane();
	private JButton supprimer = new JButton("Supprimer");
	public static int idProfesseur;
	
	public VueTableProfesseur()
	{
		TableModel tableModelProfesseur = new TableModel(ProfesseurDAO.AfficherProfesseurs());
		tableProfesseur.setModel(tableModelProfesseur);
		tableProfesseur.setFillsViewportHeight(true);
		tableProfesseur.setPreferredScrollableViewportSize(new Dimension(1000,400));
		
		jScrollPane.getViewport().add(tableProfesseur);
		this.add(jScrollPane);
		
		supprimer.setEnabled(false);
		this.add(supprimer);
		
		tableProfesseur.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e) 
					{
	                    int line = tableProfesseur.getSelectedRow();
	                    idProfesseur = Integer.parseInt(tableProfesseur.getModel().getValueAt(line, 0).toString());
	                    supprimer.setEnabled(true);
					}
				}
		);
		
		supprimer.addActionListener
		(
		    new ActionListener() 
		    {
				
				public void actionPerformed(ActionEvent e) 
				{
					Object[] options = {"Oui", "Non"};
		            int n = JOptionPane.showOptionDialog(null, "Voulez vous vraiment supprimer le professeur avec l'id : " +
		            		idProfesseur + " ?", "Avertissement", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

		            if(n == JOptionPane.YES_OPTION) 
		            {			       
		            	ProfesseurDAO.deleteProfesseurFromTableProfesseurs();
		                JOptionPane.showMessageDialog(null, "L'enregistrement est supprimé de la base de données.");
		                supprimer.setEnabled(false);
		            }
				}
			}		
		);
	}
	
	
}	