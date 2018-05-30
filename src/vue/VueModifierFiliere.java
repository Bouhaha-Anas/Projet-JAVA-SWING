package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import dao.FiliereDAO;
import dao.MatiereDAO;
import net.miginfocom.swing.MigLayout;

public class VueModifierFiliere extends JPanel
{
	private JLabel lnom = new JLabel("Nom :"), ltype = new JLabel("Type Fili�re :"),lIdFiliere = new JLabel("id Fili�re :");
	public static JTextField tnom = new JTextField(20), tprefixe;
	private JButton enregistrer = new JButton("Enregistrer"), annuler = new JButton("Annuler"), supprimer = new JButton("Supprimer");
	public static JComboBox comboType = new JComboBox();
	public static DefaultComboBoxModel defaultComboBoxModelIdFiliere;
	public static JComboBox comboIdFiliere;
	private JPanel panelPrefixe;

	public VueModifierFiliere() 
	{
		setLayout(new MigLayout("", "[58px][100.00px][132.00][42px]", "[20px][23px][][][]"));
		tprefixe = new JTextField(10);
		comboType.addItem("Pr�paratoire");
		comboType.addItem("Licence");
		comboType.addItem("Master");
		comboType.addItem("Ing�nieurie");
		this.add(lIdFiliere, "cell 0 0");	
		
		defaultComboBoxModelIdFiliere = new DefaultComboBoxModel();
		comboIdFiliere = new JComboBox();
		comboIdFiliere.setModel(defaultComboBoxModelIdFiliere);
		FiliereDAO.getAllIds();
		FiliereDAO.getFiliereById();
		
		
		this.add(comboIdFiliere, "cell 1 0,alignx left");
		this.add(ltype, "cell 0 1,alignx left,growy");
		this.add(comboType, "cell 1 1");
		
		comboIdFiliere.addItemListener
		(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						if(tprefixe.getText().equals("") == false && panelPrefixe != null)
						{
							tnom.setText("");
							VueModifierFiliere.this.remove(panelPrefixe);
							VueModifierFiliere.this.remove(comboType);
						}
						comboType = new JComboBox();
						comboType.addItem("Pr�paratoire");
						comboType.addItem("Licence");
						comboType.addItem("Master");
						comboType.addItem("Ing�nieurie");
						panelPrefixe = new JPanel();
						panelPrefixe.add(tprefixe);
						FiliereDAO.getFiliereById();
						VueModifierFiliere.this.add(panelPrefixe, "cell 1 2,alignx left,growy");
						VueModifierFiliere.this.add(comboType, "cell 1 1");
						VueModifierFiliere.this.validate();
						VueModifierFiliere.this.repaint();
					}
				}
		);
		
		comboType.addItemListener
		(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						String choixType = (String)comboType.getSelectedItem();
						if(panelPrefixe !=null)
						{
							VueModifierFiliere.this.remove(panelPrefixe);
						}
						panelPrefixe = new JPanel();
						tprefixe = new JTextField(10);
						switch (choixType)
						{
							case "Pr�paratoire" :
								tprefixe.setText("Pr�paratoire--");
								break;
							case "Licence" :
								tprefixe.setText("Licence--");
								break;
							case "Master" :
								tprefixe.setText("Master--");
								break;
							case "Ing�nieurie" :
								tprefixe.setText("G�nie--");
								break;	
						}
						tprefixe.setHorizontalAlignment(JTextField.CENTER);
						tprefixe.setEnabled(false);
						tprefixe.setVisible(true);
						panelPrefixe.add(tprefixe);
						VueModifierFiliere.this.add(panelPrefixe, "cell 1 2,alignx left,growy");
						VueModifierFiliere.this.validate();
						VueModifierFiliere.this.repaint();
					}
				}
		);
		this.add(lnom, "cell 0 2,alignx left,growy");
		panelPrefixe = new JPanel();
		this.add(panelPrefixe, "cell 1 2,alignx left,growy");
		
		
		panelPrefixe.add(tprefixe);
		tprefixe.setHorizontalAlignment(JTextField.CENTER);
		tprefixe.setEnabled(false);
		tprefixe.setVisible(true);
		this.add(tnom, "cell 2 2,alignx left,aligny center");
		
		enregistrer.addActionListener
		(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						String nom_Filiere = tnom.getText();
						if(nom_Filiere.equals("") == false)
						{
							FiliereDAO.getRecordCountForModification();
							if( FiliereDAO.getRecordCountForModification() == true)
							{	
								FiliereDAO.updateFiliere();
								JOptionPane.showMessageDialog(null, "Modification effectu� avec succ�s","Succ�s",JOptionPane.INFORMATION_MESSAGE);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Cette fili�re existe d�ja !","Erreur",JOptionPane.ERROR_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Veuiller saisir le nom de fili�re","Erreur",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
		);
		
		supprimer.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(comboIdFiliere != null)
					{
						Object[] options = {"Oui", "Non"};
			            int n = JOptionPane.showOptionDialog(null, "Voulez vous vraiment supprimer la mati�re avec l'id : " +
			                    defaultComboBoxModelIdFiliere.getSelectedItem().toString() + " ?", "Avertissement", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

			            if(n == JOptionPane.YES_OPTION) 
			            {			       
			                FiliereDAO.deleteFiliere();
			                JOptionPane.showMessageDialog(null, "L'enregistrement est supprim� de la base de donn�es.");
			            }
					}
				}
			}	
		);
		
		this.add(enregistrer, "cell 0 3,grow");
		this.add(supprimer, "flowx");
		
		annuler.addActionListener
		(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						tnom.setText("");
						comboType.setSelectedItem("Pr�paratoire");
					}
				}
		);
		this.add(annuler, "cell 2 3,grow");
	}

}
