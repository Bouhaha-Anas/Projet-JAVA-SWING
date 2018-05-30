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
import dao.ProfesseurDAO;
import dao.ProfesseurMatiereDAO;
import net.miginfocom.swing.MigLayout;

public class VueAssocierProfesseurMatiere extends JPanel 
{
	private JLabel lprofesseur = new JLabel("Professeur :"), lmatiere = new JLabel("Matière :");
	public static JTextField tidM = new JTextField(3), tnomprenom = new JTextField(10);
	public static JComboBox comboIdProfesseur = new JComboBox(), comboMatiere = new JComboBox();
	public static DefaultComboBoxModel dcmIdProf, dcmMatiere ;
	private JButton associer = new JButton("Associer"), annuler = new JButton("Annuler") ;
	
	public VueAssocierProfesseurMatiere() 
	{
		setLayout(new MigLayout("", "[108.00px][327.00px]", "[23px][][][][]"));
		
		this.add(lprofesseur, "cell 0 0,alignx left,aligny center");
		this.add(comboIdProfesseur, "flowx,cell 1 0,alignx left,aligny center");
		this.add(lmatiere, "cell 0 1,alignx left,aligny center");
		this.add(comboMatiere, "flowx,cell 1 1,alignx left,aligny center");
		this.add(associer, "cell 0 2,alignx left,growy");
		this.add(annuler, "cell 1 2,alignx left,growy");
		this.add(tidM, "cell 1 1");
		this.add(tnomprenom, "cell 1 0");
		tidM.setVisible(false);
		
		tnomprenom.setEnabled(false);
		//tidM.setVisible(false);
		
		dcmIdProf = new DefaultComboBoxModel();
		comboIdProfesseur.setModel(dcmIdProf);
		ProfesseurDAO.getAllIdForAssociationPM();
		
		dcmMatiere = new DefaultComboBoxModel();
		comboMatiere.setModel(dcmMatiere);
		MatiereDAO.getAllMatieresForAssociationMP();
		
			
		comboIdProfesseur.addItemListener
		(
			new ItemListener()
			{
				public void itemStateChanged(ItemEvent e)
				{
					ProfesseurDAO.getNomPrenomByIdForAssocaitionPM();
				}
			}
		);
		
		comboMatiere.addItemListener
		(
			new ItemListener()
			{
				public void itemStateChanged(ItemEvent e)
				{
					MatiereDAO.getIdMatiereByNameForAssociationMP();	
				}
			}
		);
		
		associer.addActionListener
		(
			new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					if(tnomprenom.getText().equals("") == false && tidM.getText().equals("") == false)
					{
						boolean record = ProfesseurMatiereDAO.getRecordCount();
						if(record == true)
						{
							ProfesseurMatiereDAO.associate();
							JOptionPane.showMessageDialog(null, "L'association effectuée avec succés","Succés",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Cette matière est dèjà associée à cette filière", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
						}
					}
					else if(tnomprenom.getText().equals("") == true && tidM.getText().equals("") == false)
					{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner un professeur", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
					}
					else if(tnomprenom.getText().equals("") == false && tidM.getText().equals("") == true)
					{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner une matière", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
					}
					else if(tnomprenom.getText().equals("") == true && tidM.getText().equals("") == true)
					{
						JOptionPane.showMessageDialog(null, "Veuillez selectionner un professeur et une matière", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
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
					tnomprenom.setText("");
					tidM.setText("");
					tidM.setVisible(false);
				}
			}
		);
		
	}
					

}
