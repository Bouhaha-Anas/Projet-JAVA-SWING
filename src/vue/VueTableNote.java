package vue;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import dao.MatiereDAO;
import dao.NoteDAO;
import tableModel.TableModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class VueTableNote extends JPanel
{
	JTable tableNote = new JTable();
	JScrollPane jScrollPane = new JScrollPane();
	private JButton supprimer = new JButton("Supprimer");
	public static int idNote;
	
	public VueTableNote()
	{
		TableModel tableModelNote = new TableModel(NoteDAO.AfficherNotes());
		tableNote.setModel(tableModelNote);
		tableNote.setFillsViewportHeight(true);
		tableNote.setPreferredScrollableViewportSize(new Dimension(1000,400));
		
		jScrollPane.getViewport().add(tableNote);
		this.add(jScrollPane);
		
		supprimer.setEnabled(false);
		this.add(supprimer);
		tableNote.addMouseListener
		(
				new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e) 
					{
	                    int line = tableNote.getSelectedRow();
	                    idNote = Integer.parseInt(tableNote.getModel().getValueAt(line, 0).toString());
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
		            int n = JOptionPane.showOptionDialog(null, "Voulez vous vraiment supprimer la note avec l'id : " +
		            		idNote + " ?", "Avertissement", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

		            if(n == JOptionPane.YES_OPTION) 
		            {			       
		            	NoteDAO.deleteNoteFromTableNotes();
		                JOptionPane.showMessageDialog(null, "L'enregistrement est supprimé de la base de données.");
		                supprimer.setEnabled(false);
		            }
				}
			}		
		);
	}
	
	
}	