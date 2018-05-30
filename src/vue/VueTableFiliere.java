package vue;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import dao.EtudiantDAO;
import dao.FiliereDAO;
import tableModel.TableModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class VueTableFiliere extends JPanel
{
	JTable tableFiliere = new JTable();
	JScrollPane jScrollPane = new JScrollPane();
	private JButton supprimer = new JButton("Supprimer");
	public static int idFiliere;
	
	public VueTableFiliere()
	{
		TableModel tableModelFiliere = new TableModel(FiliereDAO.AfficherFilieres());
		tableFiliere.setModel(tableModelFiliere);
		tableFiliere.setFillsViewportHeight(true);
		tableFiliere.setPreferredScrollableViewportSize(new Dimension(1000,400));
		
		jScrollPane.getViewport().add(tableFiliere);
		this.add(jScrollPane);
		
		supprimer.setEnabled(false);
		this.add(supprimer);
		
		tableFiliere.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e) 
					{
	                    int line = tableFiliere.getSelectedRow();
	                    idFiliere = Integer.parseInt(tableFiliere.getModel().getValueAt(line, 0).toString());
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
		            int n = JOptionPane.showOptionDialog(null, "Voulez vous vraiment supprimer la filière avec l'id : " +
		            		idFiliere + " ?", "Avertissement", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

		            if(n == JOptionPane.YES_OPTION) 
		            {			       
		            	FiliereDAO.deleteFiliereFromTableFilieres();
		                JOptionPane.showMessageDialog(null, "L'enregistrement est supprimé de la base de données.");
		                supprimer.setEnabled(false);
		            }
				}
			}		
		);
	}
	
	
}	