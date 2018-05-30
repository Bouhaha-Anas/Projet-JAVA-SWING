package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.FiliereDAO;
import dao.FiliereMatiereDAO;
import dao.MatiereDAO;
import net.miginfocom.swing.MigLayout;

public class VueAssocierFiliereMatiere extends JPanel 
{
	private JLabel lfiliere = new JLabel("Filière :"), lmatiere = new JLabel("Matière :");
	public static JTextField tidF = new JTextField(5),tidM = new JTextField(5);
	public static JComboBox comboFiliere = new JComboBox(), comboMatiere = new JComboBox();
	public static DefaultComboBoxModel dcmFiliere, dcmMatiere ;
	private JButton associer = new JButton("Associer"), annuler = new JButton("Annuler") ;
	
	public VueAssocierFiliereMatiere() 
	{
		setLayout(new MigLayout("", "[108.00px][327.00px]", "[23px][][][]"));
		
		this.add(lfiliere, "cell 0 0,alignx left,aligny center");
		this.add(comboFiliere, "flowx,cell 1 0,alignx left,aligny center");
		this.add(lmatiere, "cell 0 1,alignx left,aligny center");
		this.add(comboMatiere, "flowx,cell 1 1,alignx left,aligny center");
		this.add(associer, "cell 0 2,alignx left,growy");
		this.add(annuler, "alignx left,growy");
		
		tidF.setVisible(false);
		tidM.setVisible(false);
		
		dcmFiliere = new DefaultComboBoxModel();
		comboFiliere.setModel(dcmFiliere);
		FiliereDAO.getAllFilieresForAssociation();
		
		dcmMatiere = new DefaultComboBoxModel();
		comboMatiere.setModel(dcmMatiere);
		this.add(tidF, "cell 1 0");
		this.add(tidM, "cell 1 1");
		MatiereDAO.getAllMatieresForAssociation();
		
		comboFiliere.addItemListener
		(
			new ItemListener()
			{
				public void itemStateChanged(ItemEvent e)
				{
					FiliereDAO.getIdFiliereByNameForAssociationFM();
				}
			}
		);
		
		comboMatiere.addItemListener
		(
			new ItemListener()
			{
				public void itemStateChanged(ItemEvent e)
				{
					MatiereDAO.getIdMatiereByNameForAssociationMF();	
				}
			}
		);
		
		associer.addActionListener
		(
			new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					if(tidF.getText().equals("") == false && tidM.getText().equals("") == false)
					{
						boolean record = FiliereMatiereDAO.getRecordCount();
						if(record == true)
						{
							FiliereMatiereDAO.associate();
							JOptionPane.showMessageDialog(null, "L'association effectuée avec succés","Succés",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Cette matière est dèjà associée à cette filière", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
						}
					}
					else if(tidF.getText().equals("") == true && tidM.getText().equals("") == false)
					{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner une filière", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
					}
					else if(tidF.getText().equals("") == false && tidM.getText().equals("") == true)
					{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner une matière", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
					}
					else if(tidF.getText().equals("") == true && tidM.getText().equals("") == true)
					{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner une filière et une matière", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		);
		
		annuler.addActionListener
		(
			new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					tidF.setText("");
					tidM.setText("");
				}
			}
		);
		
	}
					

}
