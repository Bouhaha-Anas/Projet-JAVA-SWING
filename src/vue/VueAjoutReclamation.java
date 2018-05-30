package vue;

import javax.swing.*;
import dao.EtudiantDAO;
import dao.ReclamationDAO;

import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import net.miginfocom.swing.MigLayout;

public class VueAjoutReclamation extends JPanel
{
	
	private JLabel lcontenu = new JLabel("Contenu :"),lnomprenom = new JLabel("Matricule :"),lclasse = new JLabel("Classe :"),
				   lfiliere = new JLabel("Filière :");
	public static JTextField tId = new JTextField(3),tclasse = new JTextField(20), tfiliere = new JTextField(20);
	public static JTextArea tcontenu = new JTextArea(10,20);
	private JComboBox comboMatricule = new JComboBox();
	public static DefaultComboBoxModel dcmMatricule;
	private JButton ajouter = new JButton("Ajouter"), annuler = new JButton("Annuler");
	private JScrollPane tcontenuScroll = new JScrollPane(tcontenu);

	public VueAjoutReclamation() 
	{
		setLayout(new MigLayout("", "[48px][63px][1px][15px][][][53px][5px][108px]", "[20px][20px][20px][131px][23px]"));
		
		this.add(comboMatricule, "flowx,cell 4 0,alignx left,growy");
		this.add(lfiliere, "cell 0 1 3 1,alignx left,aligny center");
		this.add(lnomprenom, "cell 0 0,alignx left,aligny center");
		this.add(tfiliere, "cell 4 1,alignx left,growy");
		this.add(lclasse, "cell 0 2,alignx left,aligny center");
		this.add(tclasse, "cell 4 2,alignx left,growy");
		this.add(lcontenu, "cell 0 3,alignx left,aligny center");
		this.add(tcontenuScroll, "cell 4 3,alignx right,growy");
		this.add(ajouter, "cell 1 4,alignx left,aligny top");
		this.add(annuler, "cell 4 4,alignx left,aligny top");
		this.add(tId, "cell 4 0");
		tfiliere.setEnabled(false);
		tclasse.setEnabled(false);
		tId.setVisible(false);
		
		dcmMatricule = new DefaultComboBoxModel();
		comboMatricule.setModel(dcmMatricule);
		EtudiantDAO.getNomPrenomEtudiantForReclamation();
		
		comboMatricule.addItemListener
		(
			new ItemListener()
			{
				public void itemStateChanged(ItemEvent e)
				{
					EtudiantDAO.getInfoEtudiantForReclamation();
					VueAjoutReclamation.this.revalidate();
					VueAjoutReclamation.this.repaint();
				}
			}
		);
		
		annuler.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					tfiliere.setText("");
					tclasse.setText("");
				}
			}
		);
		
		ajouter.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if( tcontenu.getText().equals("") == false )
					{
						if(tfiliere.getText().equals("") == true)
						{
							JOptionPane.showMessageDialog(null, "Veuillez selectionner un étudiant d'abord !", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							ReclamationDAO.addReclamation();
							JOptionPane.showMessageDialog(null, "Ajout effectué avec succés","Succés",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Veuillez remplir le champs vide !", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		);
		
	}

}
