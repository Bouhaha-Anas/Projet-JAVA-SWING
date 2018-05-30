package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import dao.ClasseDAO;
import dao.EtudiantDAO;
import dao.FiliereDAO;
import model.Groupe;
import net.miginfocom.swing.MigLayout;

public class VueAjoutClasse extends JPanel
{
	private JLabel lniveau = new JLabel("Niveau :"), lgroupe = new JLabel("Groupe :"), lfiliere = new JLabel("Fili�re :");
	public static JComboBox comboNiveau = new JComboBox(), comboFiliere, comboFiliereId;
	private JButton ajouter = new JButton("Ajouter"), annuler = new JButton("Annuler");
	public static JTextField tgroupe = new JTextField(20);
	public static DefaultComboBoxModel defaultComboBoxModelFiliere, defaultComboBoxModelId;
	private JPanel panelHiddenIdFiliere,panelComboNiveau;
	
	public VueAjoutClasse() 
	{
		defaultComboBoxModelFiliere = new DefaultComboBoxModel();
		comboFiliere = new JComboBox();
		comboFiliere.setModel(defaultComboBoxModelFiliere);
		FiliereDAO.getAllFilieresForGestionClasse();
		
		panelHiddenIdFiliere = new JPanel();
		defaultComboBoxModelId = new DefaultComboBoxModel();
		comboFiliereId = new JComboBox();
		comboFiliereId.setModel(defaultComboBoxModelId);
		FiliereDAO.getIdFiliereByName();
		comboFiliereId.setVisible(false);
		panelHiddenIdFiliere.add(comboFiliereId);
		
		panelComboNiveau = new JPanel();
		panelComboNiveau.add(comboNiveau);
		setLayout(new MigLayout("", "[44px][151.00px][38.00px][14px][][97px]", "[20px][20px][23px][]"));
		this.add(lfiliere, "cell 0 0,alignx left,growy");
		this.add(panelHiddenIdFiliere, "cell 2 0,growx");
		this.add(lniveau, "cell 0 1,alignx left,growy");
		this.add(lgroupe, "cell 0 2,alignx left,growy");
		this.add(tgroupe, "cell 1 2,alignx left,growy");
		this.add(comboFiliere, "cell 1 0,alignx left,aligny center");
		this.add(panelComboNiveau, "cell 1 1,alignx left,growy");
		
		comboFiliere.addItemListener
		(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						if(panelHiddenIdFiliere != null && panelComboNiveau != null)
						{
							VueAjoutClasse.this.remove(panelHiddenIdFiliere);	
							VueAjoutClasse.this.remove(panelComboNiveau);
						}
						//R�cup�ration de l'id du fili�re selectionn�e
						panelHiddenIdFiliere = new JPanel();
						defaultComboBoxModelId = new DefaultComboBoxModel();
						comboFiliereId = new JComboBox();
						comboFiliereId.setModel(defaultComboBoxModelId);
						FiliereDAO.getIdFiliereByName();
						comboFiliereId.setVisible(false);
						panelHiddenIdFiliere.add(comboFiliereId);
						VueAjoutClasse.this.add(panelHiddenIdFiliere, "cell 2 0,alignx left");
						
						//Chargement de comboNiveau selon la fili�re selectionn�e
						panelComboNiveau = new JPanel();
						comboNiveau = new JComboBox();
						String nomF = (String)comboFiliere.getSelectedItem();
						if( nomF.contains("Pr�paratoire--") || nomF.contains("Master--") )
						{
							comboNiveau.addItem("1�re ann�e");
							comboNiveau.addItem("2�me ann�e");
						}
						else if( nomF.contains("Licence--") )
						{
							comboNiveau.addItem("1�re ann�e");
							comboNiveau.addItem("2�me ann�e");
							comboNiveau.addItem("3�me ann�e");
						}
						else if( nomF.contains("G�nie--") )
						{
							comboNiveau.addItem("3�me ann�e");
							comboNiveau.addItem("4�me ann�e");
							comboNiveau.addItem("5�me ann�e");
						}
	
						panelComboNiveau.add(comboNiveau);
						VueAjoutClasse.this.add(panelComboNiveau, "cell 1 1,alignx left,aligny baseline");
						VueAjoutClasse.this.validate();
						VueAjoutClasse.this.repaint();
					}
				}
		);
		
		
		ajouter.addActionListener
		(
				new ActionListener() 
				{
					
					public void actionPerformed(ActionEvent e) 
					{
						String nomGroupe = tgroupe.getText();
						
						if(nomGroupe.equals("") == false && panelHiddenIdFiliere != null)
						{
							ClasseDAO.getRecordCount();
							if(ClasseDAO.getRecordCount() == true)
							{
								ClasseDAO.addClasse();
								JOptionPane.showMessageDialog(null, "Ajout effectu� avec succ�s","Succ�s",JOptionPane.INFORMATION_MESSAGE);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Cette classe existe d�ja !", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
							}
						}
						else if(nomGroupe.equals("") == true && panelHiddenIdFiliere == null)
						{
							JOptionPane.showMessageDialog(null, "<html><font>Vous devez saisir le nom du groupe !</font><br><font>Vous devez s�lectionner une fili�re !</font></html>", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
						}
						else if(nomGroupe.equals("") == true && panelHiddenIdFiliere != null)
						{
							JOptionPane.showMessageDialog(null, "<html><font>Vous devez saisir le nom du groupe !</font></html>", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
						}
						else if(nomGroupe.equals("") == false && panelHiddenIdFiliere == null)
						{
							JOptionPane.showMessageDialog(null, "<html><font>Vous devez s�lectionner une fili�re !</font></html>", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
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
						tgroupe.setText("");
						VueAjoutClasse.this.remove(panelHiddenIdFiliere);
						VueAjoutClasse.this.remove(panelComboNiveau);
						panelComboNiveau = new JPanel();
						comboNiveau = new JComboBox();
						panelComboNiveau.add(comboNiveau);
						VueAjoutClasse.this.add(panelComboNiveau, "cell 1 1,alignx left,aligny baseline");
						VueAjoutClasse.this.validate();
						VueAjoutClasse.this.repaint();
					}
				}
		);
		
		this.add(ajouter, "cell 0 3,alignx left,growy");
		this.add(annuler, "flowx,cell 1 3,alignx left,growy");
						
	}

}
