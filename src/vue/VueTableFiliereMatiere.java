package vue;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import dao.FiliereMatiereDAO;
import tableModel.TableModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class VueTableFiliereMatiere extends JPanel
{
	JTable tableFiliereMatiere = new JTable();
	JScrollPane jScrollPane = new JScrollPane();
	private JButton dissocier = new JButton("Dissocier");
	public static int idAssoc, idFiliere,idMatiere;
	
	public VueTableFiliereMatiere()
	{
		TableModel tableModelFiliereMatiere = new TableModel(FiliereMatiereDAO.AfficherFilieresMatieres());
		tableFiliereMatiere.setModel(tableModelFiliereMatiere);
		tableFiliereMatiere.setFillsViewportHeight(true);
		tableFiliereMatiere.setPreferredScrollableViewportSize(new Dimension(1000,400));
		
		jScrollPane.getViewport().add(tableFiliereMatiere);
		this.add(jScrollPane);
		
		dissocier.setEnabled(false);
		this.add(dissocier);
		
		tableFiliereMatiere.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e) 
					{
	                    int line = tableFiliereMatiere.getSelectedRow();
	                    idAssoc = Integer.parseInt(tableFiliereMatiere.getModel().getValueAt(line, 0).toString());
	                    idFiliere = Integer.parseInt(tableFiliereMatiere.getModel().getValueAt(line, 1).toString());
	                    idMatiere = Integer.parseInt(tableFiliereMatiere.getModel().getValueAt(line, 2).toString());
	                    dissocier.setEnabled(true);
					}
				}
		);
		
		dissocier.addActionListener
		(
		    new ActionListener() 
		    {
				
				public void actionPerformed(ActionEvent e) 
				{
					Object[] options = {"Oui", "Non"};
		            int n = JOptionPane.showOptionDialog(null, "Voulez vous vraiment dissocier la matière avec l'id : " +
		            		idMatiere + " du filière avec l'id :"+ idFiliere +" ?", "Avertissement", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

		            if(n == JOptionPane.YES_OPTION) 
		            {			       
		            	FiliereMatiereDAO.dissociate();
		                JOptionPane.showMessageDialog(null, "Dissociation effectuée avec succés.");
		                dissocier.setEnabled(false);
		            }
				}
			}		
		);
	}
	
	
}