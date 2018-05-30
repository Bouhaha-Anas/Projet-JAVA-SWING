package vue;

import javax.swing.*;

import dao.DemandeDAO;
import dao.EtudiantDAO;

import java.awt.BorderLayout;
import java.awt.event.*;

import model.Sujet;
import net.miginfocom.swing.MigLayout;
import webFilesCreation.HTMLGeneration;

public class VueAjoutDemande extends JPanel
{
	private JLabel lsujet = new JLabel("Sujet :");
	public static JComboBox comboSujet = new JComboBox(Sujet.values());

	private JLabel lnom1 = new JLabel("Nom :"), lprenom1 = new JLabel("prénom :"), lmatricule1 = new JLabel("Matricule :"),lfiliere = new JLabel("Filière :"),
				   lniveau = new JLabel("Niveau :"), lgroupe = new JLabel("Groupe :");
	public static JTextField tnom1 = new JTextField(20), tprenom1 = new JTextField(20),tId1 = new JTextField(10),tId2 = new JTextField(2),
							 tfiliere = new JTextField(20),tgroupe = new JTextField(20), tniveau = new JTextField(20),
							 tcin = new JTextField(5),tdate = new JTextField(5), tville = new JTextField(5);
	
	private JLabel lnom2 = new JLabel("Nom :"), lmatricule2 = new JLabel("Matricule :"), lprenom2 = new JLabel("Prénom :"),
				   lgroupe2 = new JLabel("Groupe :");
	public static JTextField tnom2 = new JTextField(20), tprenom2 = new JTextField(20), tgroupe2 = new JTextField(20);
	
	public static JComboBox comboMat1 = new JComboBox(), comboMat2 = new JComboBox();
	public static DefaultComboBoxModel defaultComboBoxModelMat1, defaultComboBoxModelMat2;
	private JButton valider = new JButton("Valider"),ajouterCertif = new JButton("Ajouter"), ajouterPermut = new JButton("Ajouter");
	
	public VueAjoutDemande() 
	{
		setLayout(new MigLayout("", "[32px][173.00px][][][61.00][]", "[20px][][][][][][][]"));
	
		this.add(lsujet, "cell 0 0,alignx left,aligny center");
		this.add(comboSujet, "cell 1 0,alignx left,aligny top");
				
		comboSujet.addItemListener
		( 
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						
							VueAjoutDemande.this.remove(lmatricule1);
							VueAjoutDemande.this.remove(comboMat1);
							VueAjoutDemande.this.remove(lnom1);
							VueAjoutDemande.this.remove(tnom1);
							VueAjoutDemande.this.remove(tcin);
							VueAjoutDemande.this.remove(lprenom1);
							VueAjoutDemande.this.remove(tprenom1);
							VueAjoutDemande.this.remove(tville);
							VueAjoutDemande.this.remove(lfiliere);
							VueAjoutDemande.this.remove(tfiliere);
							VueAjoutDemande.this.remove(tdate);
							VueAjoutDemande.this.remove(lniveau);
							VueAjoutDemande.this.remove(tniveau);
							VueAjoutDemande.this.remove(lgroupe);
							VueAjoutDemande.this.remove(tgroupe);
							VueAjoutDemande.this.remove(ajouterCertif);
							VueAjoutDemande.this.remove(valider);
							VueAjoutDemande.this.remove(tId1);
							VueAjoutDemande.this.remove(lmatricule2);
							VueAjoutDemande.this.remove(comboMat2);
							VueAjoutDemande.this.remove(lnom2);
							VueAjoutDemande.this.remove(tnom2);
							VueAjoutDemande.this.remove(lprenom2);
							VueAjoutDemande.this.remove(tprenom2);
							VueAjoutDemande.this.remove(lgroupe2);
							VueAjoutDemande.this.remove(tgroupe2);
							VueAjoutDemande.this.remove(ajouterPermut);
					
						
						defaultComboBoxModelMat1 = new DefaultComboBoxModel();
						comboMat1 = new JComboBox();
						comboMat1.setModel(defaultComboBoxModelMat1);
						EtudiantDAO.getAllMatriculesForDemande();
						VueAjoutDemande.this.add(comboMat1, "flowx,cell 1 1");
						VueAjoutDemande.this.add(lmatricule1, "cell 0 1");
						VueAjoutDemande.this.revalidate();
						VueAjoutDemande.this.repaint();
						comboMat1.addItemListener
						(
							new ItemListener() 
							{
								public void itemStateChanged(ItemEvent e) 
								{
									
										VueAjoutDemande.this.remove(lnom1);
										VueAjoutDemande.this.remove(tnom1);
										VueAjoutDemande.this.remove(tcin);
										VueAjoutDemande.this.remove(lprenom1);
										VueAjoutDemande.this.remove(tprenom1);
										VueAjoutDemande.this.remove(tville);
										VueAjoutDemande.this.remove(lfiliere);
										VueAjoutDemande.this.remove(tfiliere);
										VueAjoutDemande.this.remove(tdate);
										VueAjoutDemande.this.remove(lniveau);
										VueAjoutDemande.this.remove(tniveau);
										VueAjoutDemande.this.remove(lgroupe);
										VueAjoutDemande.this.remove(tgroupe);
										VueAjoutDemande.this.remove(ajouterCertif);
										VueAjoutDemande.this.remove(valider);
										VueAjoutDemande.this.remove(tId1);
									
									
									tId1.setVisible(false);
									tcin.setVisible(false);
									tville.setVisible(false);
									tdate.setVisible(false);
									
									tnom1.setEnabled(false);
									tprenom1.setEnabled(false);
									tfiliere.setEnabled(false);
									tniveau.setEnabled(false);
									tgroupe.setEnabled(false);
									
									
									VueAjoutDemande.this.add(lnom1, "cell 0 2");
									VueAjoutDemande.this.add(tnom1, "cell 1 2");
									VueAjoutDemande.this.add(tcin, "cell 2 2");
									VueAjoutDemande.this.add(lprenom1, "cell 0 3");
									VueAjoutDemande.this.add(tprenom1, "cell 1 3");
									VueAjoutDemande.this.add(tville, "cell 2 3");
									VueAjoutDemande.this.add(lfiliere, "cell 0 4");
									VueAjoutDemande.this.add(tfiliere, "cell 1 4");
									VueAjoutDemande.this.add(tdate, "cell 2 4");
									VueAjoutDemande.this.add(lniveau, "cell 0 5");
									VueAjoutDemande.this.add(tniveau, "cell 1 5");
									VueAjoutDemande.this.add(lgroupe, "cell 0 6");
									VueAjoutDemande.this.add(tgroupe, "cell 1 6");
									VueAjoutDemande.this.add(tId1, "cell 1 1");
									EtudiantDAO.getInformationForDemandeCertificat();
									
									if(comboSujet.getSelectedItem().toString().equals("permutation"))
									{
										permutationSelected();
									}
									else
									{
										cetificatSelected();
									}								
								}
							}
						);
					}
				}
		);
				
	}
	
	public void cetificatSelected()
	{		
		this.add(ajouterCertif, "cell 1 7");
		this.revalidate();
		this.repaint();
		
		ajouterCertif.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					DemandeDAO.addDemandeCertificat();
					JOptionPane.showMessageDialog(null, "La demande est bien ajoutée","Succés",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		);
		
	}
	
	public void permutationSelected()
	{
		this.add(valider, "cell 1 7");
		this.revalidate();
		this.repaint();
		
		valider.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(VueAjoutDemande.this.contains(lmatricule2.getLocation()))
					{
						VueAjoutDemande.this.remove(lmatricule2);
						VueAjoutDemande.this.remove(comboMat2);
					}
					
					comboMat2 = new JComboBox();
					comboMat2.setVisible(true);
					defaultComboBoxModelMat2 = new DefaultComboBoxModel();
					comboMat2.setModel(defaultComboBoxModelMat2);
					EtudiantDAO.getSecondMatriculesForDemandePermutation();
					VueAjoutDemande.this.add(comboMat2, "cell 5 1");
					VueAjoutDemande.this.add(lmatricule2, "cell 4 1");
					VueAjoutDemande.this.revalidate();
					VueAjoutDemande.this.repaint();
					comboMat2.addItemListener
					(
						new ItemListener() 
						{
							public void itemStateChanged(ItemEvent e) 
							{

								if(VueAjoutDemande.this.contains(lnom2.getLocation()))
								{
									VueAjoutDemande.this.remove(tId2);
									VueAjoutDemande.this.remove(lnom2);
									VueAjoutDemande.this.remove(tnom2);
									VueAjoutDemande.this.remove(lprenom2);
									VueAjoutDemande.this.remove(tprenom2);
									VueAjoutDemande.this.remove(lgroupe2);
									VueAjoutDemande.this.remove(ajouterPermut);
								}
								
								tId2.setVisible(false);
								tnom2.setEnabled(false);
								tprenom2.setEnabled(false);
								tgroupe2.setEnabled(false);
								
								VueAjoutDemande.this.add(lnom2, "cell 4 2");
								VueAjoutDemande.this.add(tnom2, "cell 5 2");
								VueAjoutDemande.this.add(lprenom2, "cell 4 3");
								VueAjoutDemande.this.add(tprenom2, "cell 5 3");
								VueAjoutDemande.this.add(lgroupe2, "cell 4 4");
								VueAjoutDemande.this.add(tgroupe2, "cell 5 4");
								VueAjoutDemande.this.add(tId2, "cell 5 1");
								VueAjoutDemande.this.add(ajouterPermut, "cell 5 7");
								EtudiantDAO.getInformationByMatricule2();
								VueAjoutDemande.this.revalidate();
								VueAjoutDemande.this.repaint();
								ajouterPermut.addActionListener
								(
									new ActionListener()
									{
										public void actionPerformed(ActionEvent e)
										{
											DemandeDAO.addDemandePermut();
											JOptionPane.showMessageDialog(null, "La demande est bien ajoutée","Succés",JOptionPane.INFORMATION_MESSAGE);
										}
									}
								);
								
							}
						}
					);
										
				}
			}
		);
	}
}
