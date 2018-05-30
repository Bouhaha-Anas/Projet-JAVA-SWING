package vue;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import dao.ProfesseurMatiereDAO;
import tableModel.TableModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class VueTableProfesseurMatiere extends JPanel
{
	JTable tableProfesseurMatiere = new JTable();
	JScrollPane jScrollPane = new JScrollPane();
	private JButton dissocier = new JButton("Dissocier");
	public static int idAssoc, idProf,idMatiere;
	
	public VueTableProfesseurMatiere()
	{
		TableModel tableModelProfesseurMatiere = new TableModel(ProfesseurMatiereDAO.AfficherProfesseursMatieres());
		tableProfesseurMatiere.setModel(tableModelProfesseurMatiere);
		tableProfesseurMatiere.setFillsViewportHeight(true);
		tableProfesseurMatiere.setPreferredScrollableViewportSize(new Dimension(1000,400));
		
		jScrollPane.getViewport().add(tableProfesseurMatiere);
		this.add(jScrollPane);
		
		dissocier.setEnabled(false);
		this.add(dissocier);
		
		tableProfesseurMatiere.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e) 
					{
	                    int line = tableProfesseurMatiere.getSelectedRow();
	                    idAssoc = Integer.parseInt(tableProfesseurMatiere.getModel().getValueAt(line, 0).toString());
	                    idProf = Integer.parseInt(tableProfesseurMatiere.getModel().getValueAt(line, 1).toString());
	                    idMatiere = Integer.parseInt(tableProfesseurMatiere.getModel().getValueAt(line, 2).toString());
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
		            		idMatiere + " du professeur avec l'id :"+ idProf +" ?", "Avertissement", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

		            if(n == JOptionPane.YES_OPTION) 
		            {			       
		            	ProfesseurMatiereDAO.dissociate();
		                JOptionPane.showMessageDialog(null, "Dissociation effectuée avec succés.");
		                dissocier.setEnabled(false);
		            }
				}
			}		
		);
	}
	
	
}