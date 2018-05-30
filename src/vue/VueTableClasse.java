package vue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import dao.ClasseDAO;
import dao.NoteDAO;
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


public class VueTableClasse extends JPanel
{
	private JTable tableClasse = new JTable();
	private JScrollPane jScrollPane = new JScrollPane();
	private JButton supprimer = new JButton("Supprimer");
	public static int idClasse;
	
	public VueTableClasse()
	{
		TableModel tableModelClasse = new TableModel(ClasseDAO.AfficherClasses());
		tableClasse.setModel(tableModelClasse);
		tableClasse.setFillsViewportHeight(true);
		tableClasse.setPreferredScrollableViewportSize(new Dimension(1000,400));
		
		jScrollPane.getViewport().add(tableClasse);
		this.add(jScrollPane);
		
		supprimer.setEnabled(false);
		this.add(supprimer);
		
		tableClasse.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e) 
					{
	                    int line = tableClasse.getSelectedRow();
	                    idClasse = Integer.parseInt(tableClasse.getModel().getValueAt(line, 0).toString());
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
		            int n = JOptionPane.showOptionDialog(null, "Voulez vous vraiment supprimer la classe avec l'id : " +
		            		idClasse + " ?", "Avertissement", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

		            if(n == JOptionPane.YES_OPTION) 
		            {			       
		            	ClasseDAO.deleteClasseFromTableClasses();
		                JOptionPane.showMessageDialog(null, "L'enregistrement est supprimé de la base de données.");
		                supprimer.setEnabled(false);
		            }
				}
			}		
		);
	}
	
	
}	