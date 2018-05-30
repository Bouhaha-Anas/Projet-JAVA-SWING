package vue;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import dao.ClasseDAO;
import dao.FiliereDAO;
import net.miginfocom.swing.MigLayout;

public class VueModifierClasse extends JPanel
{
	private JLabel lniveau = new JLabel("Niveau :"), lgroupe = new JLabel("Groupe :"), lfiliere = new JLabel("Filière :"),lId = new JLabel("Id du Classe :");
	public static JComboBox comboNiveau = new JComboBox(), comboFiliere, comboFiliereId = new JComboBox(), comboIdClasse;
	private JButton enregistrer = new JButton("Enregistrer"), annuler = new JButton("Annuler"), supprimer = new JButton("Supprimer");
	public static JTextField tgroupe = new JTextField(20);
	public static DefaultComboBoxModel defaultComboBoxModelFiliere, defaultComboBoxModelFiliereId,defaultComboBoxAllIds;
	
	public VueModifierClasse() 
	{
			setLayout(new MigLayout("", "[66px][155.00px][165.00]", "[20px][][][][]"));
			this.add(lId, "cell 0 0,alignx left,aligny center");
			
			defaultComboBoxModelFiliere = new DefaultComboBoxModel();
			comboFiliere = new JComboBox();
			comboFiliere.setModel(defaultComboBoxModelFiliere);
			FiliereDAO.getAllFilieresForModifierClasse();
			
			defaultComboBoxAllIds = new DefaultComboBoxModel();
			comboIdClasse = new JComboBox();
			comboIdClasse.setModel(defaultComboBoxAllIds);
			ClasseDAO.getAllIds();
			
			defaultComboBoxModelFiliereId = new DefaultComboBoxModel();
			ClasseDAO.getClasseById();
			comboFiliereId.setModel(defaultComboBoxModelFiliereId);
									
			
			this.add(comboIdClasse, "flowx,cell 1 0,alignx left,aligny top");
			
			comboIdClasse.addItemListener
			(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						if(tgroupe.getText().equals("") == false)
						{
							tgroupe.setText("");
							VueModifierClasse.this.remove(comboFiliereId);
							VueModifierClasse.this.remove(comboNiveau);
							VueModifierClasse.this.remove(comboFiliere);
						}
						defaultComboBoxModelFiliere = new DefaultComboBoxModel();
						comboFiliere.setModel(defaultComboBoxModelFiliere);
						FiliereDAO.getAllFilieresForModifierClasse();
						
						defaultComboBoxModelFiliereId = new DefaultComboBoxModel();
						ClasseDAO.getClasseById();
						comboFiliereId.setModel(defaultComboBoxModelFiliereId);
						comboFiliereId.setVisible(false);
						
						VueModifierClasse.this.add(comboFiliere, "flowx,cell 1 1");
						VueModifierClasse.this.add(comboFiliereId, "cell 1 1");
						VueModifierClasse.this.add(comboNiveau, "cell 1 2");
						VueModifierClasse.this.validate();
						VueModifierClasse.this.repaint();
					}
				}
			);
			
			comboFiliere.addItemListener
			(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						if(comboNiveau != null && comboFiliereId != null)
						{
							VueModifierClasse.this.remove(comboNiveau);
							VueModifierClasse.this.remove(comboFiliereId);
						}
						
						defaultComboBoxModelFiliereId = new DefaultComboBoxModel();
						comboFiliereId = new JComboBox();
						comboFiliereId.setModel(defaultComboBoxModelFiliereId);
						FiliereDAO.getIdFiliereByNameForModifierClasse();
						comboNiveau = new JComboBox();
						comboFiliereId.setVisible(false);
						
						String nomF = (String)comboFiliere.getSelectedItem();
						if( nomF.contains("Préparatoire--") || nomF.contains("Master--") )
						{
							comboNiveau.addItem("1ère année");
							comboNiveau.addItem("2ème année");
						}
						else if( nomF.contains("Licence--") )
						{
							comboNiveau.addItem("1ère année");
							comboNiveau.addItem("2ème année");
							comboNiveau.addItem("3ème année");
						}
						else if( nomF.contains("Génie--") )
						{
							comboNiveau.addItem("3ème année");
							comboNiveau.addItem("4ème année");
							comboNiveau.addItem("5ème année");
						}
						VueModifierClasse.this.add(comboNiveau, "cell 1 2");
						VueModifierClasse.this.add(comboFiliereId, "cell 1 1");
						VueModifierClasse.this.validate();
						VueModifierClasse.this.repaint();
					}
				}
			);
			
			this.add(lfiliere, "cell 0 1");
			this.add(lniveau, "cell 0 2");
			
			comboNiveau.addItem("1ère année");
			comboNiveau.addItem("2ème année");
			comboNiveau.addItem("3ème année");
			comboNiveau.addItem("4ème année");
			comboNiveau.addItem("5ème année");
			
			enregistrer.addActionListener
			(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String nomGroupe = tgroupe.getText();
						
						if(nomGroupe.equals("") == false)
						{
							ClasseDAO.getRecordCountForModificationClasse();
							if(ClasseDAO.getRecordCountForModificationClasse() == true)
							{
								ClasseDAO.updateClasse();
								JOptionPane.showMessageDialog(null, "Modification effectué avec succés","Succés",JOptionPane.INFORMATION_MESSAGE);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Cette classe existe dèja !", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Vous devez spécifier le nom du groupe !", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			);
			
			comboFiliereId.setVisible(false);
			this.add(comboNiveau, "cell 1 2");
			this.add(lgroupe, "cell 0 3");
			this.add(tgroupe, "cell 1 3");
			this.add(comboFiliere, "flowx,cell 1 1");
			this.add(comboFiliereId, "cell 1 1");
			this.add(enregistrer, "cell 0 4");
			
			supprimer.addActionListener
			(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(comboIdClasse != null)
						{
							Object[] options = {"Oui", "Non"};
				            int n = JOptionPane.showOptionDialog(null, "Voulez vous vraiment supprimer la classe avec l'id : " +
				                    defaultComboBoxAllIds.getSelectedItem().toString() + " ?", "Avertissement", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

				            if(n == JOptionPane.YES_OPTION) 
				            {			       
				                ClasseDAO.deleteClasse();
				                JOptionPane.showMessageDialog(null, "L'enregistrement est supprimé de la base de données.");
				            }
					
						}
					}
				}	
			);
			this.add(supprimer, "flowx,cell 1 4");
			this.add(annuler, "cell 1 4");
	}

}
