package vue;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;

import javax.swing.*;

import dao.EtudiantDAO;
import dao.ClasseDAO;
import dao.FiliereDAO;
import model.Sexe;
import model.Ville;
import net.miginfocom.swing.MigLayout;

public class VueAjoutEtudiant extends JPanel
{
	private JLabel lnom = new JLabel("Nom :"), lprenom = new JLabel("Prénom :"), ldatenaissance = new JLabel("Date de naissance :"),
				   ladresse = new JLabel("Adresse :"), ltelephone = new JLabel("Telephone :"), lmail = new JLabel("E-mail :"),
				   lcin = new JLabel("CIN :"), lville = new JLabel("Ville :"), lsexe = new JLabel("Sexe :"), lmatricule = new JLabel("Matricule :"),
				   lclasse = new JLabel("Classe :"),lFiliere = new JLabel("Filière :"), lgroupe = new JLabel("Groupe :"),
				   lannee = new JLabel("Année"), lmois = new JLabel("Mois"), ljour = new JLabel("Jour");
	
	public static JTextField tnom = new JTextField(20), tprenom = new JTextField(20),ttelephone = new JTextField(20),
							 tmail = new JTextField(20), tcin = new JTextField(20), tmatricule = new JTextField(20);
	
	public static JTextArea tadresse = new JTextArea(10,20);		   
	public static JComboBox comboSexe = new JComboBox(Sexe.values()), comboVille = new JComboBox(Ville.values());
	public static JComboBox comboClasse, comboFiliere, comboGroupe,comboClasseId;
	private JButton ajouter = new JButton("Ajouter"), annuler = new JButton("Annuler");
	public static DefaultComboBoxModel defaultComboBoxModelClasse,defaultComboBoxModelFiliere, defaultComboBoxModelGroupe, defaultComboBoxModelId;
	private JPanel panelComboClasse, panelComboGroupe, panelHiddenIdClasse, panelJour;
	public static JComboBox comboYear = new JComboBox(), comboMonth = new JComboBox(), comboJour = new JComboBox();
	private JScrollPane tadresseScroll = new JScrollPane(tadresse);
	
	public VueAjoutEtudiant() 
	{
		setLayout(new MigLayout("", "[99.00px][-28.00px][-28.00px][9px][166px][][][][][]", "[20px][20px][20px][20px][131px][20px][20px][20px][20px][20px][23px][][]"));
		this.add(lsexe, "cell 0 2 3 1,growx,aligny center");
		this.add(lannee, "flowx,cell 4 3");
		this.add(ladresse, "cell 0 4 3 1,growx,aligny center");
		this.add(comboSexe, "cell 4 2,alignx left,aligny top");
		this.add(ldatenaissance, "cell 0 3 3 1,growx,aligny center");
		this.add(tprenom, "cell 4 1,alignx left,aligny top");
		this.add(lprenom, "cell 0 1 3 1,growx,aligny center");
		this.add(tnom, "cell 4 0,alignx left,aligny top");
		this.add(tadresseScroll, "cell 4 4,alignx left,growy");
		this.add(lville, "cell 0 5,alignx left,growy");
		this.add(comboVille, "cell 4 5,alignx left,aligny top");
		this.add(lcin, "cell 0 6,alignx left,growy");
		
		tcin.addKeyListener
		(
			new KeyAdapter()
			{
				public void keyReleased(KeyEvent e) 
				{
		             
		            if(!isNumeric(e.getKeyChar()))
		            {		        	
		    
		            	if(e.getKeyChar() !='\b')
		           		{
		            		Toolkit tk = Toolkit.getDefaultToolkit();
				            tk.beep();
				            tcin.setText(tcin.getText().replace(String.valueOf(e.getKeyChar()), ""));
		            	}		            	
		            }
		            else if(isNumeric(e.getKeyChar()))
		            {
		            	if(tcin.getText().length() > 8)
		            	{
		            		JOptionPane.showMessageDialog(null, "CIN est composée de 8 chiffres seulement", "Erreur de saisie",JOptionPane.ERROR_MESSAGE);
		            		tcin.selectAll();
		            	}
		            }
		        }
			}
		);
		this.add(tcin, "cell 4 6,alignx left,aligny top");
		this.add(ltelephone, "cell 0 7,growx,aligny center");
		ttelephone.addKeyListener
		(
			new KeyAdapter()
			{
				public void keyReleased(KeyEvent e) {
		             
					if(!isNumeric(e.getKeyChar()))
		            {	            	
						if(e.getKeyChar() !='\b')
		           		{
		            		Toolkit tk = Toolkit.getDefaultToolkit();
				            tk.beep();
				            ttelephone.setText(ttelephone.getText().replace(String.valueOf(e.getKeyChar()), ""));
		            	}
		            }
		            else if(isNumeric(e.getKeyChar()))
		            {
		            	if(ttelephone.getText().length() > 8)
		            	{
		            		JOptionPane.showMessageDialog(null, "Num télephone est composé de 8 chiffres seulement", "Erreur de saisie",JOptionPane.ERROR_MESSAGE);
		            		ttelephone.selectAll();
		            	}
		            }
		        }
			}
		);
		this.add(ttelephone, "flowx,cell 4 7,alignx left,aligny top");
		this.add(lmail, "cell 0 8,growx,aligny center");
		this.add(lnom, "cell 0 0,growx,aligny center");
		
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int i;
		for(i=1970; i< year-17 ;i++)
		{
			comboYear.addItem(i);
		}
		
		for(i=1;i<10;i++)
		{
			comboMonth.addItem("0"+i);
		}
		
		comboMonth.addItemListener
		(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						String selectedMonth = (String)comboMonth.getSelectedItem();
						if(panelJour != null)
						{
							VueAjoutEtudiant.this.remove(panelJour);
						}
						panelJour = new JPanel();
						ljour = new JLabel("Jour");
						comboJour = new JComboBox();
						int i ;
						if( selectedMonth.equals("01") ||  selectedMonth.equals("03") || selectedMonth.equals("05") || selectedMonth.equals("07") || selectedMonth.equals("08") || selectedMonth.equals("10") || selectedMonth.equals("12"))
						{
							for(i=1;i<32;i++)
							{
								comboJour.addItem(i);
							}
						}
						else if(selectedMonth.equals("04") ||  selectedMonth.equals("06") || selectedMonth.equals("09") || selectedMonth.equals("11"))
						{
							for(i=1;i<31;i++)
							{
								comboJour.addItem(i);
							}
						}
						else if(selectedMonth.equals("02"))
						{
							for(i=1;i<30;i++)
							{
								comboJour.addItem(i);
							}
						}
						panelJour.add(ljour);
						panelJour.add(comboJour);
						VueAjoutEtudiant.this.add(panelJour, "cell 4 3");
						VueAjoutEtudiant.this.validate();
						VueAjoutEtudiant.this.repaint();						
					}
				}
		);
		
		defaultComboBoxModelFiliere = new DefaultComboBoxModel();
		FiliereDAO.getAllFilieresForGestionEtudiant();
		tmail.addFocusListener
        (
            new FocusAdapter()
            {   
                public void focusLost(FocusEvent e) 
                {
                    if(tmail.getText().contains("@gmail.com") == false)
                    {
                    	if(tmail.getText().contains("@hotmail.com") == false)
                    	{
                    		if(tmail.getText().contains("@hotmail.fr") == false)
                    		{
                    			if(tmail.getText().contains("@outlook.com") == false)
                    			{
                    				if(tmail.getText().contains("@outlook.fr") == false)
                    				{
                    					if(tmail.getText().contains("@yahoo.com") == false)
                    					{
                    						if(tmail.getText().contains("@yahoo.fr") == false)
                    						{
                    							Toolkit tk = Toolkit.getDefaultToolkit();
                    			                tk.beep();
                    	                    	tmail.setText("");
                    						}
                    					}
                    				}
                    			}
                    		}
                    	}	
                    }
                }
            }   
        );
		
		this.add(tmail, "cell 4 8,alignx left,aligny top");
		this.add(lmatricule, "cell 0 9,alignx left,growy");
		tmatricule.setText("Géneration automatique du matricule");
		tmatricule.setHorizontalAlignment(JTextField.CENTER);
		tmatricule.setEnabled(false);
		this.add(tmatricule, "cell 4 9,alignx left,growy");
		this.add(lFiliere, "cell 0 10,alignx left,growy");
		comboFiliere = new JComboBox();
		comboFiliere.setModel(defaultComboBoxModelFiliere);
		this.add(comboFiliere, "flowx,cell 4 10,alignx left,growy");
		
		comboFiliere.addItemListener
		( 
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						if(panelComboClasse != null && panelComboGroupe != null && panelHiddenIdClasse !=null)
						{
							VueAjoutEtudiant.this.remove(panelComboClasse);
							VueAjoutEtudiant.this.remove(panelComboGroupe);
							VueAjoutEtudiant.this.remove(panelHiddenIdClasse);
						}
						panelComboClasse = new JPanel();
						panelComboClasse.add(lclasse);
						defaultComboBoxModelClasse = new DefaultComboBoxModel();
						comboClasse = new JComboBox();
						comboClasse.setModel(defaultComboBoxModelClasse);
						ClasseDAO.getClassesByFiliere();
						panelComboClasse.add(comboClasse);
						VueAjoutEtudiant.this.add(panelComboClasse, "cell 4 10");
						VueAjoutEtudiant.this.validate();
						VueAjoutEtudiant.this.repaint();
						comboClasse.addItemListener
						(
								new ItemListener()
								{
									public void itemStateChanged(ItemEvent e)
									{
										if(panelComboGroupe != null && panelHiddenIdClasse != null)
										{
											VueAjoutEtudiant.this.remove(panelComboGroupe);
											VueAjoutEtudiant.this.remove(panelHiddenIdClasse);
										}
										panelComboGroupe = new JPanel();
										panelComboGroupe.add(lgroupe);
										defaultComboBoxModelGroupe = new DefaultComboBoxModel();
										comboGroupe = new JComboBox();
										comboGroupe.setModel(defaultComboBoxModelGroupe);
										ClasseDAO.getGroupeClasseByNiveau();
										panelComboGroupe.add(comboGroupe);
										VueAjoutEtudiant.this.add(panelComboGroupe, "cell 6 10");
										VueAjoutEtudiant.this.validate();
										VueAjoutEtudiant.this.repaint();
										comboGroupe.addItemListener
										(
												new ItemListener()
												{
													public void itemStateChanged(ItemEvent e)
													{
														if(panelHiddenIdClasse != null)
														{
															VueAjoutEtudiant.this.remove(panelHiddenIdClasse);	
														}
														panelHiddenIdClasse = new JPanel();
														defaultComboBoxModelId = new DefaultComboBoxModel();
														comboClasseId = new JComboBox();
														comboClasseId.setModel(defaultComboBoxModelId);
														ClasseDAO.getIdClasseByGroupe();
														comboClasseId.setVisible(false);
														panelHiddenIdClasse.add(comboClasseId);
														VueAjoutEtudiant.this.add(panelHiddenIdClasse, "cell 7 10");
														VueAjoutEtudiant.this.validate();
														VueAjoutEtudiant.this.repaint();
													}
												}
										);
									}
								}
						);
					}
				}
		);
		this.add(ajouter, "cell 0 11,alignx left,growy");
		
		ajouter.addActionListener
		(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						String nom = tnom.getText().toUpperCase();
						String prenom = tprenom.getText().toUpperCase();
						tmatricule.setText("mat-"+nom+"."+prenom);
						String matricule = tmatricule.getText();
						
						String NOM = tnom.getText();
						String PRENOM = tprenom.getText();
						String ADRESSE = tadresse.getText();
						String CIN = tcin.getText();
						String TELEPHONE = ttelephone.getText();
						String MAIL = tmail.getText();
						
						int CINLENGTH = tcin.getText().length();
						int TELEPHONELENGTH = ttelephone.getText().length();
						
						if(NOM.equals("") == true || PRENOM.equals("") == true || ADRESSE.equals("") == true || CIN.equals("") || TELEPHONE.equals("") == true || MAIL.equals("") == true)
						{
							System.out.println("1");
							JOptionPane.showMessageDialog(null, "Veuillez remplir les champs vides !", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
						}
						else if(NOM.equals("") == false && PRENOM.equals("") == false && ADRESSE.equals("") == false && CIN.equals("") == false && TELEPHONE.equals("") == false && MAIL.equals("") == false)
						{
							if(CINLENGTH == 8 == false && TELEPHONELENGTH == 8 == false)
							{
								JOptionPane.showMessageDialog(null, "<html><font>CIN saisi inférieur à 8 chiffres<br>Télephone saisi inférieur à 8 chiffres</font></html>", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
							}
							else if(CINLENGTH == 8 == true && TELEPHONELENGTH == 8 == false)
							{
								JOptionPane.showMessageDialog(null, "Télephone saisi inférieur à 8 chiffres", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
							}
							else if(CINLENGTH == 8 == false && TELEPHONELENGTH == 8 == true)
							{
								JOptionPane.showMessageDialog(null, "CIN saisi inférieur à 8 chiffres", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
							}
							else if(CINLENGTH == 8 == true && TELEPHONELENGTH == 8 == true)
							{
								EtudiantDAO.getRecordCount(matricule);
								if(EtudiantDAO.getRecordCount(matricule) ==true)
								{
									EtudiantDAO.addEtudiant();
									JOptionPane.showMessageDialog(null, "Ajout effectué avec succés","Succés",JOptionPane.INFORMATION_MESSAGE);
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Cet étudiant est déjà existant", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
								}
								
							}
						}
						
						tmatricule.setText("Géneration automatique du matricule");
					}
				}
		);
		
		//Chargement du comboClasse selon la filière selectionnée
		panelComboClasse = new JPanel();
		panelComboClasse.add(lclasse);
		defaultComboBoxModelClasse = new DefaultComboBoxModel();
		comboClasse = new JComboBox();
		comboClasse.setModel(defaultComboBoxModelClasse);
		ClasseDAO.getClassesByFiliere();
		
		comboClasse.addItemListener
		(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						if(panelComboGroupe != null && panelHiddenIdClasse != null)
						{
							VueAjoutEtudiant.this.remove(panelComboGroupe);
							VueAjoutEtudiant.this.remove(panelHiddenIdClasse);
						}
						panelComboGroupe = new JPanel();
						panelComboGroupe.add(lgroupe);
						defaultComboBoxModelGroupe = new DefaultComboBoxModel();
						comboGroupe = new JComboBox();
						comboGroupe.setModel(defaultComboBoxModelGroupe);
						ClasseDAO.getGroupeClasseByNiveau();
						panelComboGroupe.add(comboGroupe);
						VueAjoutEtudiant.this.add(panelComboGroupe, "cell 6 10");
						VueAjoutEtudiant.this.validate();
						VueAjoutEtudiant.this.repaint();
						comboGroupe.addItemListener
						(
								new ItemListener()
								{
									public void itemStateChanged(ItemEvent e)
									{
										if(panelHiddenIdClasse != null)
										{
											VueAjoutEtudiant.this.remove(panelHiddenIdClasse);	
										}
										panelHiddenIdClasse = new JPanel();
										defaultComboBoxModelId = new DefaultComboBoxModel();
										comboClasseId = new JComboBox();
										comboClasseId.setModel(defaultComboBoxModelId);
										ClasseDAO.getIdClasseByGroupe();
										comboClasseId.setVisible(false);
										panelHiddenIdClasse.add(comboClasseId);
										VueAjoutEtudiant.this.add(panelHiddenIdClasse, "cell 7 10");
										VueAjoutEtudiant.this.validate();
										VueAjoutEtudiant.this.repaint();
									}
								}
						);
					}
				}	
		);
		
		panelComboClasse.add(comboClasse);
		this.add(panelComboClasse, "cell 4 10,alignx left,aligny center");
		
		//Chargement du comboGroupe selon la classe selectionnée
		panelComboGroupe = new JPanel();
		panelComboGroupe.add(lgroupe);
		defaultComboBoxModelGroupe = new DefaultComboBoxModel();
		comboGroupe = new JComboBox();
		comboGroupe.setModel(defaultComboBoxModelGroupe);
		ClasseDAO.getGroupeClasseByNiveau();
		
		comboGroupe.addItemListener
		(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						if(panelHiddenIdClasse != null)
						{
							VueAjoutEtudiant.this.remove(panelHiddenIdClasse);	
						}
						panelHiddenIdClasse = new JPanel();
						defaultComboBoxModelId = new DefaultComboBoxModel();
						comboClasseId = new JComboBox();
						comboClasseId.setModel(defaultComboBoxModelId);
						ClasseDAO.getIdClasseByGroupe();
						comboClasseId.setVisible(false);
						panelHiddenIdClasse.add(comboClasseId);
						VueAjoutEtudiant.this.add(panelHiddenIdClasse, "cell 7 10");
						VueAjoutEtudiant.this.validate();
						VueAjoutEtudiant.this.repaint();
					}
				}	
		);
		
		panelComboGroupe.add(comboGroupe);
		this.add(panelComboGroupe, "cell 4 10,alignx left,aligny center");
		
		//Chargement du comboId selon le groupe selectionné
		panelHiddenIdClasse = new JPanel();
		defaultComboBoxModelId = new DefaultComboBoxModel();
		comboClasseId = new JComboBox();
		comboClasseId.setModel(defaultComboBoxModelId);
		comboClasseId.setVisible(false);
		ClasseDAO.getIdClasseByGroupe();
		panelHiddenIdClasse.add(comboClasseId);
		this.add(panelHiddenIdClasse, "cell 4 10,alignx left,aligny center");
		
		this.add(annuler, "flowx,cell 4 11,alignx left,aligny top");
		this.add(comboYear, "cell 4 3");
		this.add(lmois, "cell 4 3");
		
		this.add(comboMonth, "cell 4 3");
		comboMonth.addItem("10");
		comboMonth.addItem("11");
		comboMonth.addItem("12");
		panelJour = new JPanel();
		panelJour.add(ljour);
		for(i=1;i<32;i++)
		{
			comboJour.addItem(i);
		}
		panelJour.add(comboJour);
		this.add(panelJour, "cell 4 3");
		
		annuler.addActionListener
		(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						tnom.setText("");
						tprenom.setText("");
						tadresse.setText("");
						ttelephone.setText("");
						tmail.setText("");
						tcin.setText("");
						tmatricule.setText("Géneration automatique du matricule");
						VueAjoutEtudiant.this.comboMonth.setSelectedItem("01");
						VueAjoutEtudiant.this.remove(panelComboClasse);
						VueAjoutEtudiant.this.remove(panelComboGroupe);
						VueAjoutEtudiant.this.remove(panelHiddenIdClasse);
						VueAjoutEtudiant.this.remove(panelJour);
						//Remise du comboJour au valeur par défaut
						panelJour = new JPanel();
						comboJour = new JComboBox();
						for(int i=1;i<32;i++)
						{
							comboJour.addItem(i);
						}
						ljour = new JLabel("Jour");
						panelJour.add(ljour);
						panelJour.add(comboJour);
						//Rechargement du comboClasse
						panelComboClasse = new JPanel();
						panelComboClasse.add(lclasse);
						defaultComboBoxModelClasse = new DefaultComboBoxModel();
						comboClasse = new JComboBox();
						comboClasse.setModel(defaultComboBoxModelClasse);
						ClasseDAO.getClassesByFiliere();
						panelComboClasse.add(comboClasse);
						comboClasse.addItemListener
						(
								new ItemListener()
								{
									public void itemStateChanged(ItemEvent e)
									{
										if(panelComboGroupe != null && panelHiddenIdClasse != null)
										{
											VueAjoutEtudiant.this.remove(panelComboGroupe);
											VueAjoutEtudiant.this.remove(panelHiddenIdClasse);
										}
										panelComboGroupe = new JPanel();
										panelComboGroupe.add(lgroupe);
										defaultComboBoxModelGroupe = new DefaultComboBoxModel();
										comboGroupe = new JComboBox();
										comboGroupe.setModel(defaultComboBoxModelGroupe);
										ClasseDAO.getGroupeClasseByNiveau();
										panelComboGroupe.add(comboGroupe);
										VueAjoutEtudiant.this.add(panelComboGroupe, "cell 6 10");
										VueAjoutEtudiant.this.validate();
										VueAjoutEtudiant.this.repaint();
										comboGroupe.addItemListener
										(
												new ItemListener()
												{
													public void itemStateChanged(ItemEvent e)
													{
														if(panelHiddenIdClasse != null)
														{
															VueAjoutEtudiant.this.remove(panelHiddenIdClasse);	
														}
														panelHiddenIdClasse = new JPanel();
														defaultComboBoxModelId = new DefaultComboBoxModel();
														comboClasseId = new JComboBox();
														comboClasseId.setModel(defaultComboBoxModelId);
														ClasseDAO.getIdClasseByGroupe();
														comboClasseId.setVisible(false);
														panelHiddenIdClasse.add(comboClasseId);
														VueAjoutEtudiant.this.add(panelHiddenIdClasse, "cell 7 10");
														VueAjoutEtudiant.this.validate();
														VueAjoutEtudiant.this.repaint();
													}
												}
										);
									}
								}	
						);
						
						//Rechargement du comboGroupe
						panelComboGroupe = new JPanel();
						panelComboGroupe.add(lgroupe);
						defaultComboBoxModelGroupe = new DefaultComboBoxModel();
						comboGroupe = new JComboBox();
						comboGroupe.setModel(defaultComboBoxModelGroupe);
						ClasseDAO.getGroupeClasseByNiveau();
						panelComboGroupe.add(comboGroupe);
						comboGroupe.addItemListener
						(
								new ItemListener()
								{
									public void itemStateChanged(ItemEvent e)
									{
										if(panelHiddenIdClasse != null)
										{
											VueAjoutEtudiant.this.remove(panelHiddenIdClasse);	
										}
										panelHiddenIdClasse = new JPanel();
										defaultComboBoxModelId = new DefaultComboBoxModel();
										comboClasseId = new JComboBox();
										comboClasseId.setModel(defaultComboBoxModelId);
										ClasseDAO.getIdClasseByGroupe();
										panelHiddenIdClasse.add(comboClasseId);
										comboClasseId.setVisible(false);
										VueAjoutEtudiant.this.add(panelHiddenIdClasse, "cell 7 10");
										VueAjoutEtudiant.this.validate();
										VueAjoutEtudiant.this.repaint();
									}
								}	
						);
						
						//Rechargement du comboId
						panelHiddenIdClasse = new JPanel();
						defaultComboBoxModelId = new DefaultComboBoxModel();
						comboClasseId = new JComboBox();
						comboClasseId.setModel(defaultComboBoxModelId);
						ClasseDAO.getIdClasseByGroupe();
						comboClasseId.setVisible(false);
						panelHiddenIdClasse.add(comboClasseId);
						
						VueAjoutEtudiant.this.add(panelComboClasse, "cell 4 10,alignx left,aligny center");
						VueAjoutEtudiant.this.add(panelComboGroupe, "cell 4 10,alignx left,aligny center");
						VueAjoutEtudiant.this.add(panelHiddenIdClasse, "cell 4 10,alignx left,aligny center");
						VueAjoutEtudiant.this.add(panelJour, "cell 4 3");
						VueAjoutEtudiant.this.validate();
						VueAjoutEtudiant.this.repaint();
					}
				}
		);
	}
	
	private boolean isNumeric(char caractère)
	{
        try 
        {
            Integer.parseInt(String.valueOf(caractère));
        } 
        catch (NumberFormatException e) 
        {
            return false;              
        }
        return true;
    }	
}
