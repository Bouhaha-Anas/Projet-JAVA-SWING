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
import dao.MatiereDAO;
import model.Sexe;
import model.Ville;
import net.miginfocom.swing.MigLayout;

public class VueModifierEtudiant extends JPanel
{
	private JLabel lnom = new JLabel("Nom :"), lprenom = new JLabel("Prénom :"), ldatenaissance = new JLabel("Date de naissance :"),
				   ladresse = new JLabel("Adresse :"), ltelephone = new JLabel("Telephone :"), lmail = new JLabel("E-mail :"),
				   lcin = new JLabel("CIN :"), lville = new JLabel("Ville :"), lsexe = new JLabel("Sexe :"), lmatricule = new JLabel("Matricule :"),
				   lclasse = new JLabel("Classe :"),lFiliere = new JLabel("Filière :"), lgroupe = new JLabel("Groupe :"),
				   lannee = new JLabel("Année"), lmois = new JLabel("Mois"), ljour = new JLabel("Jour"), lMatriculeEtudiant= new JLabel("Matricule de l'étudiant :");
	
	public static JTextField tnom = new JTextField(20), tprenom = new JTextField(20),ttelephone = new JTextField(20),
							 tmail = new JTextField(20), tcin = new JTextField(20), tmatricule = new JTextField(20);
	
	public static JTextArea tadresse = new JTextArea(10,20);		   
	public static JComboBox comboSexe = new JComboBox(Sexe.values()), comboVille = new JComboBox(Ville.values());
	public static JComboBox comboClasse, comboFiliere, comboGroupe,comboClasseId,comboIdEtudiant;
	private JButton enregistrer = new JButton("Enregistrer"), annuler = new JButton("Annuler"), supprimer = new JButton("Supprimer");;
	public static DefaultComboBoxModel defaultComboBoxModelClasse,defaultComboBoxModelFiliere, defaultComboBoxModelGroupe, defaultComboBoxModelId, defaultComboBoxModelMatricule, defaultComboBoxModelIdEtudiant;
	private JPanel panelComboClasse, panelComboGroupe, panelHiddenIdClasse, panelJour;
	public static JComboBox comboYear = new JComboBox(), comboMonth = new JComboBox(), comboJour = new JComboBox(),comboMatricule;
	private JScrollPane tadresseScroll = new JScrollPane(tadresse);
	
	public VueModifierEtudiant() 
	{
		setLayout(new MigLayout("", "[99.00px][-28.00px][-28.00px][9px][268.00px][106.00]", "[20px][20px][20px][11.00px][43.00px][20px,grow][20px][20px][20px][20px][23px][][]"));
		this.add(lsexe, "cell 0 3,growx,aligny center");
		this.add(lannee, "flowx,cell 4 4");
		this.add(ladresse, "cell 0 5,growx,aligny center");
		this.add(comboSexe, "cell 4 3,alignx left,aligny top");
		this.add(ldatenaissance, "cell 0 4,growx,aligny center");
		this.add(lprenom, "cell 0 2,growx,aligny center");
		this.add(tprenom, "cell 4 2,alignx left,aligny top");
		this.add(lnom, "cell 0 1,growx,aligny center");
		this.add(tnom, "cell 4 1,alignx left,aligny top");
		this.add(tadresseScroll, "cell 4 5,alignx left,growy");
		this.add(lville, "cell 0 6,alignx left,growy");
		this.add(comboVille, "cell 4 6,alignx left,aligny top");
		this.add(lcin, "cell 0 7,alignx left,growy");
		this.add(ltelephone, "cell 0 8,growx,aligny center");
		this.add(lMatriculeEtudiant, "cell 0 0");
		
		supprimer.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(comboIdEtudiant != null)
					{
						Object[] options = {"Oui", "Non"};
			            int n = JOptionPane.showOptionDialog(null, "Voulez vous vraiment supprimer l'étudiant avec l'id : " +
			                    defaultComboBoxModelIdEtudiant.getSelectedItem().toString() + " ?", "Avertissement", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

			            if(n == JOptionPane.YES_OPTION) 
			            {			       
			                EtudiantDAO.deleteEtudiant();
			                JOptionPane.showMessageDialog(null, "L'enregistrement est supprimé de la base de données.");
			            }
				
					}
				}
			}	
		);
		
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
		this.add(tcin, "cell 4 7,alignx left,aligny top");
		
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
		this.add(ttelephone, "flowx,cell 4 8,alignx left,aligny top");
		this.add(lmail, "cell 0 9,growx,aligny center");
		
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
							VueModifierEtudiant.this.remove(panelJour);
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
						VueModifierEtudiant.this.add(panelJour, "cell 4 3");
						VueModifierEtudiant.this.validate();
						VueModifierEtudiant.this.repaint();						
					}
				}
		);
		
		defaultComboBoxModelFiliere = new DefaultComboBoxModel();
		FiliereDAO.getAllFilieresForModifierEtudiant();
		
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
		
		this.add(tmail, "cell 4 9,alignx left,aligny top");
		this.add(lmatricule, "cell 0 10,alignx left,growy");
		tmatricule.setText("Géneration automatique du matricule");
		tmatricule.setHorizontalAlignment(JTextField.CENTER);
		tmatricule.setEnabled(false);
		this.add(tmatricule, "cell 4 10,alignx left,growy");
		this.add(lFiliere, "cell 0 11,alignx left,growy");
		comboFiliere = new JComboBox();
		comboFiliere.setModel(defaultComboBoxModelFiliere);
		this.add(comboFiliere, "flowx,cell 4 11,alignx left,growy");
		
		comboFiliere.addItemListener
		( 
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						if(panelComboClasse != null && panelComboGroupe != null && panelHiddenIdClasse !=null)
						{
							VueModifierEtudiant.this.remove(panelComboClasse);
							VueModifierEtudiant.this.remove(panelComboGroupe);
							VueModifierEtudiant.this.remove(panelHiddenIdClasse);
						}
						panelComboClasse = new JPanel();
						panelComboClasse.add(lclasse);
						defaultComboBoxModelClasse = new DefaultComboBoxModel();
						comboClasse = new JComboBox();
						comboClasse.setModel(defaultComboBoxModelClasse);
						ClasseDAO.getClassesByFiliereForModificationEtudiant();
						panelComboClasse.add(comboClasse);
						VueModifierEtudiant.this.add(panelComboClasse, "cell 4 11,alignx left,aligny center");
						VueModifierEtudiant.this.validate();
						VueModifierEtudiant.this.repaint();
						comboClasse.addItemListener
						(
								new ItemListener()
								{
									public void itemStateChanged(ItemEvent e)
									{
										if(panelComboGroupe != null && panelHiddenIdClasse != null)
										{
											VueModifierEtudiant.this.remove(panelComboGroupe);
											VueModifierEtudiant.this.remove(panelHiddenIdClasse);
										}
										panelComboGroupe = new JPanel();
										panelComboGroupe.add(lgroupe);
										defaultComboBoxModelGroupe = new DefaultComboBoxModel();
										comboGroupe = new JComboBox();
										comboGroupe.setModel(defaultComboBoxModelGroupe);
										ClasseDAO.getGroupeClasseByNiveauForModificationEtudiant();
										panelComboGroupe.add(comboGroupe);
										VueModifierEtudiant.this.add(panelComboGroupe, "cell 4 11,alignx left,aligny center");
										VueModifierEtudiant.this.validate();
										VueModifierEtudiant.this.repaint();
										comboGroupe.addItemListener
										(
												new ItemListener()
												{
													public void itemStateChanged(ItemEvent e)
													{
														if(panelHiddenIdClasse != null)
														{
															VueModifierEtudiant.this.remove(panelHiddenIdClasse);	
														}
														panelHiddenIdClasse = new JPanel();
														defaultComboBoxModelId = new DefaultComboBoxModel();
														comboClasseId = new JComboBox();
														comboClasseId.setModel(defaultComboBoxModelId);
														ClasseDAO.getIdClasseByGroupeForModificationEtudiant();
														panelHiddenIdClasse.add(comboClasseId);
														VueModifierEtudiant.this.add(panelHiddenIdClasse, "cell 4 11");
														VueModifierEtudiant.this.validate();
														VueModifierEtudiant.this.repaint();
													}
												}
										);
									}
								}
						);
					}
				}
		);
		this.add(enregistrer, "cell 0 12,alignx left,growy");
		
		enregistrer.addActionListener
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
						
						if(NOM.equals("") == true || PRENOM.equals("") == true || ADRESSE.equals("") == true && CIN.equals("") || true && TELEPHONE.equals("") == true || MAIL.equals("") == true)
						{
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
								EtudiantDAO.getRecordCountForModificationEtudiant(matricule);
								if(EtudiantDAO.getRecordCountForModificationEtudiant(matricule) ==true)
								{
									EtudiantDAO.updateEtudiant();
									JOptionPane.showMessageDialog(null, "Modification effectué avec succés","Succés",JOptionPane.INFORMATION_MESSAGE);
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
		ClasseDAO.getClassesByFiliereForModificationEtudiant();
		
		comboClasse.addItemListener
		(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						if(panelComboGroupe != null && panelHiddenIdClasse != null)
						{
							VueModifierEtudiant.this.remove(panelComboGroupe);
							VueModifierEtudiant.this.remove(panelHiddenIdClasse);
						}
						panelComboGroupe = new JPanel();
						panelComboGroupe.add(lgroupe);
						defaultComboBoxModelGroupe = new DefaultComboBoxModel();
						comboGroupe = new JComboBox();
						comboGroupe.setModel(defaultComboBoxModelGroupe);
						ClasseDAO.getGroupeClasseByNiveauForModificationEtudiant();
						panelComboGroupe.add(comboGroupe);
						VueModifierEtudiant.this.add(panelComboGroupe, "cell 4 11,alignx left,aligny center");
						VueModifierEtudiant.this.validate();
						VueModifierEtudiant.this.repaint();
						comboGroupe.addItemListener
						(
								new ItemListener()
								{
									public void itemStateChanged(ItemEvent e)
									{
										if(panelHiddenIdClasse != null)
										{
											VueModifierEtudiant.this.remove(panelHiddenIdClasse);	
										}
										panelHiddenIdClasse = new JPanel();
										defaultComboBoxModelId = new DefaultComboBoxModel();
										comboClasseId = new JComboBox();
										comboClasseId.setModel(defaultComboBoxModelId);
										ClasseDAO.getIdClasseByGroupeForModificationEtudiant();
										panelHiddenIdClasse.add(comboClasseId);
										VueModifierEtudiant.this.add(panelHiddenIdClasse, "cell 4 11");
										VueModifierEtudiant.this.validate();
										VueModifierEtudiant.this.repaint();
									}
								}
						);
					}
				}	
		);
		panelComboClasse.add(comboClasse);
		this.add(panelComboClasse, "cell 4 11,alignx left,aligny center");
		
		//Chargement du comboGroupe selon la classe selectionnée
		panelComboGroupe = new JPanel();
		panelComboGroupe.add(lgroupe);
		defaultComboBoxModelGroupe = new DefaultComboBoxModel();
		comboGroupe = new JComboBox();
		comboGroupe.setModel(defaultComboBoxModelGroupe);
		ClasseDAO.getGroupeClasseByNiveauForModificationEtudiant();
		
		comboGroupe.addItemListener
		(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						if(panelHiddenIdClasse != null)
						{
							VueModifierEtudiant.this.remove(panelHiddenIdClasse);	
						}
						panelHiddenIdClasse = new JPanel();
						defaultComboBoxModelId = new DefaultComboBoxModel();
						comboClasseId = new JComboBox();
						comboClasseId.setModel(defaultComboBoxModelId);
						ClasseDAO.getIdClasseByGroupeForModificationEtudiant();
						panelHiddenIdClasse.add(comboClasseId);
						VueModifierEtudiant.this.add(panelHiddenIdClasse, "cell 4 11");
						VueModifierEtudiant.this.validate();
						VueModifierEtudiant.this.repaint();
					}
				}	
		);
		panelComboGroupe.add(comboGroupe);
		this.add(panelComboGroupe, "cell 4 11,alignx left,aligny center");
		defaultComboBoxModelId = new DefaultComboBoxModel();
		ClasseDAO.getIdClasseByGroupeForModificationEtudiant();
		this.add(comboYear, "cell 4 4");
		this.add(lmois, "cell 4 4");
		
		this.add(comboMonth, "cell 4 4");
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
		this.add(panelJour, "cell 4 4");
		
		defaultComboBoxModelMatricule = new DefaultComboBoxModel();
		comboMatricule = new JComboBox();
		comboMatricule.setModel(defaultComboBoxModelMatricule);
		EtudiantDAO.getAllMatricules();
		this.add(comboMatricule, "flowx,cell 4 0");
		
		comboMatricule.addItemListener
		(
			 new ItemListener()
			 {
				 public void itemStateChanged(ItemEvent e)
				 {
					 if(panelHiddenIdClasse != null && defaultComboBoxModelIdEtudiant !=null)
					 {
						 VueModifierEtudiant.this.remove(panelHiddenIdClasse);
						 VueModifierEtudiant.this.remove(panelComboGroupe);
						 VueModifierEtudiant.this.remove(comboIdEtudiant);
						 
					 }
					 defaultComboBoxModelIdEtudiant = new DefaultComboBoxModel();
					 comboIdEtudiant = new JComboBox();
					 comboIdEtudiant.setModel(defaultComboBoxModelIdEtudiant);
					 EtudiantDAO.getIdEtudiantByMatricule();
					 comboIdEtudiant.setVisible(false);
					 panelHiddenIdClasse = new JPanel();
					 panelComboGroupe = new JPanel();
					 panelComboGroupe.add(lgroupe);
					 defaultComboBoxModelGroupe = new DefaultComboBoxModel();
					 defaultComboBoxModelId = new DefaultComboBoxModel();
					 comboClasseId = new JComboBox();
					 comboClasseId.setModel(defaultComboBoxModelId);
					 comboGroupe = new JComboBox();
					 comboGroupe.setModel(defaultComboBoxModelGroupe);
					 EtudiantDAO.getEtudiantByMatricule();
					 panelHiddenIdClasse.add(comboClasseId);
					 panelComboGroupe.add(comboGroupe);
					 VueModifierEtudiant.this.add(comboIdEtudiant, "cell 4 0");
					 VueModifierEtudiant.this.add(panelComboGroupe, "cell 4 11,alignx left,aligny center");
					 VueModifierEtudiant.this.add(panelHiddenIdClasse, "cell 4 11");
					 VueModifierEtudiant.this.validate();
					 VueModifierEtudiant.this.repaint();
					 
				 }
			 }
		);
		
		//Chargement du comboId selon le groupe selectionné
		panelHiddenIdClasse = new JPanel();
		this.add(panelHiddenIdClasse, "cell 4 11");
		comboClasseId = new JComboBox();
		comboClasseId.setModel(defaultComboBoxModelId);
		panelHiddenIdClasse.add(comboClasseId);
		defaultComboBoxModelIdEtudiant = new DefaultComboBoxModel();
		comboIdEtudiant = new JComboBox();
		comboIdEtudiant.setModel(defaultComboBoxModelIdEtudiant);
		EtudiantDAO.getIdEtudiantByMatricule();
		comboIdEtudiant.setVisible(false);
		this.add(comboIdEtudiant, "cell 4 0");
		
		this.add(supprimer, "flowx,cell 4 12");
		
		this.add(annuler, "cell 4 12,alignx left,aligny top");			
		
		
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
						VueModifierEtudiant.this.comboMonth.setSelectedItem("01");
						VueModifierEtudiant.this.remove(panelComboClasse);
						VueModifierEtudiant.this.remove(panelComboGroupe);
						VueModifierEtudiant.this.remove(panelHiddenIdClasse);
						VueModifierEtudiant.this.remove(panelJour);
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
						ClasseDAO.getClassesByFiliereForModificationEtudiant();
						panelComboClasse.add(comboClasse);
						comboClasse.addItemListener
						(
								new ItemListener()
								{
									public void itemStateChanged(ItemEvent e)
									{
										if(panelComboGroupe != null && panelHiddenIdClasse != null)
										{
											VueModifierEtudiant.this.remove(panelComboGroupe);
											VueModifierEtudiant.this.remove(panelHiddenIdClasse);
										}
										panelComboGroupe = new JPanel();
										panelComboGroupe.add(lgroupe);
										defaultComboBoxModelGroupe = new DefaultComboBoxModel();
										comboGroupe = new JComboBox();
										comboGroupe.setModel(defaultComboBoxModelGroupe);
										ClasseDAO.getGroupeClasseByNiveauForModificationEtudiant();
										panelComboGroupe.add(comboGroupe);
										VueModifierEtudiant.this.add(panelComboGroupe, "cell 4 11,alignx left,aligny center");
										VueModifierEtudiant.this.validate();
										VueModifierEtudiant.this.repaint();
										comboGroupe.addItemListener
										(
												new ItemListener()
												{
													public void itemStateChanged(ItemEvent e)
													{
														if(panelHiddenIdClasse != null)
														{
															VueModifierEtudiant.this.remove(panelHiddenIdClasse);	
														}
														panelHiddenIdClasse = new JPanel();
														defaultComboBoxModelId = new DefaultComboBoxModel();
														comboClasseId = new JComboBox();
														comboClasseId.setModel(defaultComboBoxModelId);
														ClasseDAO.getIdClasseByGroupeForModificationEtudiant();
														panelHiddenIdClasse.add(comboClasseId);
														VueModifierEtudiant.this.add(panelHiddenIdClasse, "cell 4 11");
														VueModifierEtudiant.this.validate();
														VueModifierEtudiant.this.repaint();
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
						ClasseDAO.getGroupeClasseByNiveauForModificationEtudiant();
						panelComboGroupe.add(comboGroupe);
						comboGroupe.addItemListener
						(
								new ItemListener()
								{
									public void itemStateChanged(ItemEvent e)
									{
										if(panelHiddenIdClasse != null)
										{
											VueModifierEtudiant.this.remove(panelHiddenIdClasse);	
										}
										panelHiddenIdClasse = new JPanel();
										defaultComboBoxModelId = new DefaultComboBoxModel();
										comboClasseId = new JComboBox();
										comboClasseId.setModel(defaultComboBoxModelId);
										ClasseDAO.getIdClasseByGroupeForModificationEtudiant();
										panelHiddenIdClasse.add(comboClasseId);
										VueModifierEtudiant.this.add(panelHiddenIdClasse, "cell 4 11");
										VueModifierEtudiant.this.validate();
										VueModifierEtudiant.this.repaint();
									}
								}	
						);
						
						//Rechargement du comboId
						panelHiddenIdClasse = new JPanel();
						defaultComboBoxModelId = new DefaultComboBoxModel();
						comboClasseId = new JComboBox();
						comboClasseId.setModel(defaultComboBoxModelId);
						ClasseDAO.getIdClasseByGroupeForModificationEtudiant();
						panelHiddenIdClasse.add(comboClasseId);
						
						VueModifierEtudiant.this.add(panelComboClasse, "cell 4 11,alignx left,aligny center");
						VueModifierEtudiant.this.add(panelComboGroupe, "cell 4 11,alignx left,aligny center");
						VueModifierEtudiant.this.add(panelHiddenIdClasse, "cell 4 11");
						VueModifierEtudiant.this.add(panelJour, "cell 4 4");
						VueModifierEtudiant.this.validate();
						VueModifierEtudiant.this.repaint();
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
