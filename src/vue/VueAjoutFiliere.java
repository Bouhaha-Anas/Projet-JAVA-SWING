package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import dao.FiliereDAO;
import net.miginfocom.swing.MigLayout;

public class VueAjoutFiliere extends JPanel
{
	private JLabel lnom = new JLabel("Nom :"), ltype = new JLabel("Type Filière :");
	public static JTextField tnom = new JTextField(20), tprefixe;
	private JButton ajouter = new JButton("Ajouter"), annuler = new JButton("Annuler");
	private JComboBox comboType = new JComboBox();
	private JPanel panelPrefixe;

	public VueAjoutFiliere() 
	{
		setLayout(new MigLayout("", "[58px][100.00px][95.00][42px][10px][114px][][]", "[20px][23px][]"));
		panelPrefixe = new JPanel();
		this.add(ltype, "cell 0 0,alignx left,growy");
		this.add(comboType, "cell 1 0,alignx left,growy");
		
		comboType.addItem("Préparatoire");
		comboType.addItem("Licence");
		comboType.addItem("Master");
		comboType.addItem("Ingénieurie");
		
		
		comboType.addItemListener
		(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						String choixType = (String)comboType.getSelectedItem();
						if(panelPrefixe !=null)
						{
							VueAjoutFiliere.this.remove(panelPrefixe);
						}
						panelPrefixe = new JPanel();
						tprefixe = new JTextField(10);
						switch (choixType)
						{
							case "Préparatoire" :
								tprefixe.setText("Préparatoire--");
								break;
							case "Licence" :
								tprefixe.setText("Licence--");
								break;
							case "Master" :
								tprefixe.setText("Master--");
								break;
							case "Ingénieurie" :
								tprefixe.setText("Génie--");
								break;	
						}
						tprefixe.setHorizontalAlignment(JTextField.CENTER);
						tprefixe.setEnabled(false);
						tprefixe.setVisible(true);
						panelPrefixe.add(tprefixe);
						VueAjoutFiliere.this.add(panelPrefixe, "cell 1 1,grow");
						VueAjoutFiliere.this.validate();
						VueAjoutFiliere.this.repaint();
					}
				}
		);
		this.add(tnom, "cell 2 1,alignx left,growy");
		this.add(lnom, "cell 0 1,alignx left,growy");
		
		tprefixe = new JTextField(10);
		tprefixe.setText("Préparatoire--");
		tprefixe.setHorizontalAlignment(JTextField.CENTER);
		tprefixe.setEnabled(false);
		tprefixe.setVisible(true);
		panelPrefixe.add(tprefixe);
		this.add(panelPrefixe, "cell 1 1,grow");
		
		ajouter.addActionListener
		(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						String nom_Filiere = tnom.getText();
						if(nom_Filiere.equals("") == false)
						{
							FiliereDAO.getRecordCount();
							if( FiliereDAO.getRecordCount() == true)
							{	
								FiliereDAO.addFiliere();
								JOptionPane.showMessageDialog(null, "Ajout effectué avec succés","Succés",JOptionPane.INFORMATION_MESSAGE);
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Cette filière existe dèja !","Erreur",JOptionPane.ERROR_MESSAGE);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Veuiller saisir le nom de filière","Erreur",JOptionPane.ERROR_MESSAGE);
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
						tnom.setText("");
						comboType.setSelectedItem("Préparatoire");
					}
				}
		);
		
		this.add(ajouter, "cell 0 2,alignx left,growy");
		this.add(annuler, "cell 2 2,alignx left,growy");
	}

}
