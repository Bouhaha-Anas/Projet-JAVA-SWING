package vue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import dao.EtudiantDAO;
import dao.MatiereDAO;
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


public class VueTableMatiere extends JPanel
{
	JTable tableMatiere = new JTable();
	JScrollPane jScrollPane = new JScrollPane();
	private JButton supprimer = new JButton("Supprimer");
	public static int idMatiere;
	
	public VueTableMatiere()
	{
		TableModel tableModelMatiere = new TableModel(MatiereDAO.AfficherMatieres());
		tableMatiere.setModel(tableModelMatiere);
		tableMatiere.setFillsViewportHeight(true);
		tableMatiere.setPreferredScrollableViewportSize(new Dimension(1000,400));
		
		jScrollPane.getViewport().add(tableMatiere);
		this.add(jScrollPane);
		
		supprimer.setEnabled(false);
		this.add(supprimer);
		
		tableMatiere.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e) 
					{
	                    int line = tableMatiere.getSelectedRow();
	                    idMatiere = Integer.parseInt(tableMatiere.getModel().getValueAt(line, 0).toString());
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
		            int n = JOptionPane.showOptionDialog(null, "Voulez vous vraiment supprimer la mati�re avec l'id : " +
		            		idMatiere + " ?", "Avertissement", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

		            if(n == JOptionPane.YES_OPTION) 
		            {			       
		            	MatiereDAO.deleteMatiereFromTableMatieres();
		                JOptionPane.showMessageDialog(null, "L'enregistrement est supprim� de la base de donn�es.");
		                supprimer.setEnabled(false);
		            }
				}
			}		
		);
	}
	
	
}	