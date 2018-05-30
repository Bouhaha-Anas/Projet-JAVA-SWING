package vue;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ClasseDAO;
import dao.EtudiantDAO;
import dao.FiliereDAO;
import dao.FiliereMatiereDAO;
import dao.NoteDAO;
import dao.ProfesseurMatiereDAO;
import net.miginfocom.swing.MigLayout;

public class VueSaisieNoteMatiere extends JPanel 
{
	private JLabel lfiliere = new JLabel("Filière :"), lclasse = new JLabel("Classe :"), letudiant = new JLabel("Etudiant :"),
				   lds = new JLabel("Note DS :"), lexm = new JLabel("Note EXM :"), lcc = new JLabel("Note CC :"),
				   ltp = new JLabel("Note TP :"), lprofesseur = new JLabel("Professeur :"), lgroupe = new JLabel("Groupe :"),
				   lmatiere = new JLabel("Matière :");
	public static JTextField tcc = new JTextField(20), tds = new JTextField(20),ttp = new JTextField(20), texm = new JTextField(20);
	public static JComboBox comboFiliere = new JComboBox(),comboClasse = new JComboBox(),comboGroupe = new JComboBox(),comboEtudiant = new JComboBox(),comboMatiere = new JComboBox(),comboProfesseur = new JComboBox();
	public static DefaultComboBoxModel dcmFiliere, dcmClasse, dcmGroupe,dcmEtudiant,dcmMatiere,dcmProfesseur;
	private JCheckBox checkTp = new JCheckBox();
	private Icon icon = new ImageIcon("./assets/img/refresh.png");
	private JButton valider = new JButton("Valider"), ajouter = new JButton("Ajouter"),refaire = new JButton(icon);
	
	public static Double moyenne;
	
	public VueSaisieNoteMatiere() 
	{
		setLayout(new MigLayout("", "[78.00px][144.00px][131.00px][141.00px]", "[28.00px][28.00px][][][33.00][34.00][31.00px]"));
		
		dcmFiliere = new DefaultComboBoxModel();
		comboFiliere.setModel(dcmFiliere);
		FiliereDAO.getAllFilieresForSaisieNote();
		
		comboFiliere.addItemListener
		(
			new ItemListener()
			{
				public void itemStateChanged(ItemEvent e)
				{
					if(VueSaisieNoteMatiere.this.contains(comboClasse.getLocation()))
					{
						VueSaisieNoteMatiere.this.remove(comboClasse);
						VueSaisieNoteMatiere.this.remove(lclasse);
					}
					dcmClasse = new DefaultComboBoxModel();
					comboClasse.setModel(dcmClasse);
					ClasseDAO.getClassesByFiliereForSaisieNote();
					VueSaisieNoteMatiere.this.add(lclasse, "flowx,cell 0 1,alignx left,growy");
					VueSaisieNoteMatiere.this.add(comboClasse, "cell 1 1,alignx left,aligny center");
					VueSaisieNoteMatiere.this.revalidate();
					VueSaisieNoteMatiere.this.repaint();
					comboClasse.addItemListener
					(
						new ItemListener()
						{
							public void itemStateChanged(ItemEvent e)
							{
								if(VueSaisieNoteMatiere.this.contains(comboGroupe.getLocation()))
								{
									VueSaisieNoteMatiere.this.remove(comboGroupe);
									VueSaisieNoteMatiere.this.remove(lgroupe);
								}
								dcmGroupe = new DefaultComboBoxModel();
								comboGroupe.setModel(dcmGroupe);
								ClasseDAO.getGroupeClasseByNiveauForSaisieNote();
								VueSaisieNoteMatiere.this.add(lgroupe, "cell 0 2,alignx left,growy");
								VueSaisieNoteMatiere.this.add(comboGroupe, "cell 1 2,alignx left,aligny center");
								VueSaisieNoteMatiere.this.revalidate();
								VueSaisieNoteMatiere.this.repaint();
								comboGroupe.addItemListener
								(
									new ItemListener()
									{
										public void itemStateChanged(ItemEvent e)
										{
											if(VueSaisieNoteMatiere.this.contains(letudiant.getLocation()))
											{
												VueSaisieNoteMatiere.this.remove(comboEtudiant);
												VueSaisieNoteMatiere.this.remove(letudiant);
											}
											dcmEtudiant = new DefaultComboBoxModel();
											comboEtudiant.setModel(dcmEtudiant);
											EtudiantDAO.getAllMatriculesForSaisieNote();
											VueSaisieNoteMatiere.this.add(letudiant, "cell 0 3,alignx left,growy");
											VueSaisieNoteMatiere.this.add(comboEtudiant, "cell 1 3,alignx left,aligny center");
											VueSaisieNoteMatiere.this.revalidate();
											VueSaisieNoteMatiere.this.repaint();
											comboEtudiant.addItemListener
											(
												new ItemListener()
												{
													public void itemStateChanged(ItemEvent e)
													{
														if(VueSaisieNoteMatiere.this.contains(valider.getLocation()))
														{
															VueSaisieNoteMatiere.this.remove(valider);
														}
														
														VueSaisieNoteMatiere.this.add(valider, "cell 0 4,alignx left,growy");
														VueSaisieNoteMatiere.this.revalidate();
														VueSaisieNoteMatiere.this.repaint();
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
		);
		
		comboClasse.addItemListener
		(
			new ItemListener()
			{
				public void itemStateChanged(ItemEvent e)
				{
					if(VueSaisieNoteMatiere.this.contains(comboGroupe.getLocation()))
					{
						VueSaisieNoteMatiere.this.remove(comboGroupe);
						VueSaisieNoteMatiere.this.remove(lgroupe);
					}
					dcmGroupe = new DefaultComboBoxModel();
					comboGroupe.setModel(dcmGroupe);
					ClasseDAO.getGroupeClasseByNiveauForSaisieNote();
					VueSaisieNoteMatiere.this.add(lgroupe, "cell 0 2,alignx left,growy");
					VueSaisieNoteMatiere.this.add(comboGroupe, "cell 1 2,alignx left,aligny center");
					VueSaisieNoteMatiere.this.revalidate();
					VueSaisieNoteMatiere.this.repaint();
					comboGroupe.addItemListener
					(
						new ItemListener()
						{
							public void itemStateChanged(ItemEvent e)
							{
								if(VueSaisieNoteMatiere.this.contains(letudiant.getLocation()))
								{
									VueSaisieNoteMatiere.this.remove(comboEtudiant);
									VueSaisieNoteMatiere.this.remove(letudiant);
								}
								dcmEtudiant = new DefaultComboBoxModel();
								comboEtudiant.setModel(dcmEtudiant);
								EtudiantDAO.getAllMatriculesForSaisieNote();
								VueSaisieNoteMatiere.this.add(letudiant, "cell 0 3,alignx left,growy");
								VueSaisieNoteMatiere.this.add(comboEtudiant, "cell 1 3,alignx left,aligny center");
								VueSaisieNoteMatiere.this.revalidate();
								VueSaisieNoteMatiere.this.repaint();
								comboEtudiant.addItemListener
								(
									new ItemListener()
									{
										public void itemStateChanged(ItemEvent e)
										{
											if(VueSaisieNoteMatiere.this.contains(valider.getLocation()))
											{
												VueSaisieNoteMatiere.this.remove(valider);
											}
											
											VueSaisieNoteMatiere.this.add(valider, "cell 0 4,alignx left,growy");
											VueSaisieNoteMatiere.this.revalidate();
											VueSaisieNoteMatiere.this.repaint();
										}
									}
								);
							}
						}
					);
				}
			}
		);
		
		comboGroupe.addItemListener
		(
			new ItemListener()
			{
				public void itemStateChanged(ItemEvent e)
				{
					if(VueSaisieNoteMatiere.this.contains(letudiant.getLocation()))
					{
						VueSaisieNoteMatiere.this.remove(comboEtudiant);
						VueSaisieNoteMatiere.this.remove(letudiant);
					}
					dcmEtudiant = new DefaultComboBoxModel();
					comboEtudiant.setModel(dcmEtudiant);
					EtudiantDAO.getAllMatriculesForSaisieNote();
					VueSaisieNoteMatiere.this.add(letudiant, "cell 0 3,alignx left,growy");
					VueSaisieNoteMatiere.this.add(comboEtudiant, "cell 1 3,alignx left,aligny center");
					VueSaisieNoteMatiere.this.revalidate();
					VueSaisieNoteMatiere.this.repaint();
					comboEtudiant.addItemListener
					(
						new ItemListener()
						{
							public void itemStateChanged(ItemEvent e)
							{
								if(VueSaisieNoteMatiere.this.contains(valider.getLocation()))
								{
									VueSaisieNoteMatiere.this.remove(valider);
								}
								
								VueSaisieNoteMatiere.this.add(valider, "cell 0 4,alignx left,growy");
								VueSaisieNoteMatiere.this.revalidate();
								VueSaisieNoteMatiere.this.repaint();
							}
						}
					);
				}
			}
		);
		
		comboEtudiant.addItemListener
		(
			new ItemListener()
			{
				public void itemStateChanged(ItemEvent e)
				{
					if(VueSaisieNoteMatiere.this.contains(valider.getLocation()))
					{
						VueSaisieNoteMatiere.this.remove(valider);
					}
					
					VueSaisieNoteMatiere.this.add(valider, "cell 0 4,alignx left,growy");
					VueSaisieNoteMatiere.this.revalidate();
					VueSaisieNoteMatiere.this.repaint();
				}
			}
		);
		
		valider.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					comboFiliere.setEnabled(false);
					comboClasse.setEnabled(false);
					comboGroupe.setEnabled(false);
					comboEtudiant.setEnabled(false);
					
					
					dcmMatiere = new DefaultComboBoxModel();
					comboMatiere.setModel(dcmMatiere);
					FiliereMatiereDAO.getAllMatieresByFiliere();
					VueSaisieNoteMatiere.this.add(lmatiere, "flowx,cell 2 0,alignx left,growy");
					VueSaisieNoteMatiere.this.add(comboMatiere, "cell 3 0,alignx left,aligny center");
					VueSaisieNoteMatiere.this.revalidate();
					VueSaisieNoteMatiere.this.repaint();
					
				}
			}
		);
		
		comboMatiere.addItemListener
		(
			new ItemListener()
			{
				public void itemStateChanged(ItemEvent e)
				{
					if(VueSaisieNoteMatiere.this.contains(lprofesseur.getLocation()))
					{
						VueSaisieNoteMatiere.this.remove(lprofesseur);
						VueSaisieNoteMatiere.this.remove(comboProfesseur);
						VueSaisieNoteMatiere.this.remove(lcc);
						VueSaisieNoteMatiere.this.remove(tcc);
						VueSaisieNoteMatiere.this.remove(lds);
						VueSaisieNoteMatiere.this.remove(tds);
						VueSaisieNoteMatiere.this.remove(lexm);
						VueSaisieNoteMatiere.this.remove(texm);
						VueSaisieNoteMatiere.this.remove(ltp);
						VueSaisieNoteMatiere.this.remove(checkTp);
						VueSaisieNoteMatiere.this.remove(ttp);
						VueSaisieNoteMatiere.this.remove(ajouter);
					}
					
					tcc.setText("");
					tds.setText("");
					texm.setText("");
					ttp.setText("");
					
					dcmProfesseur = new DefaultComboBoxModel();
					comboProfesseur.setModel(dcmProfesseur);
					ProfesseurMatiereDAO.getAllProfesseurByMatiere();
					
					ttp.setEnabled(false);
					
					VueSaisieNoteMatiere.this.add(lprofesseur, "flowx,cell 2 1,alignx left,growy");
					VueSaisieNoteMatiere.this.add(comboProfesseur, "cell 3 1,alignx left,aligny center");
					VueSaisieNoteMatiere.this.add(lcc, "flowx,cell 2 2,alignx left,growy");
					VueSaisieNoteMatiere.this.add(tcc, "flowx,cell 3 2,alignx left,aligny top");
					VueSaisieNoteMatiere.this.add(lds, "flowx,cell 2 3,alignx left,growy");
					VueSaisieNoteMatiere.this.add(tds, "cell 3 3,alignx left,aligny top");
					VueSaisieNoteMatiere.this.add(lexm, "flowx,cell 2 4,alignx left,growy");
					VueSaisieNoteMatiere.this.add(texm, "cell 3 4,alignx left");
					VueSaisieNoteMatiere.this.add(ltp, "flowx,cell 2 5,alignx left,growy");
					VueSaisieNoteMatiere.this.add(checkTp, "cell 2 5,alignx right,aligny center");
					VueSaisieNoteMatiere.this.add(ttp, "cell 3 5,alignx left,aligny top");
					VueSaisieNoteMatiere.this.add(ajouter, "cell 2 6,alignx left,growy");
					VueSaisieNoteMatiere.this.revalidate();
					VueSaisieNoteMatiere.this.repaint();
				}
			}
		);
		
		checkTp.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(checkTp.isSelected())
					{
						ttp.setEnabled(true);
					}
					else
					{
						ttp.setText("");
						ttp.setEnabled(false);
					}
				}
			}
		);
		
		tcc.addKeyListener
		(
			new KeyAdapter()
			{
				public void keyReleased(KeyEvent e) 
				{
		             
		            if(!isDouble(e.getKeyChar()))
		            {		        	
		            	if(e.getKeyChar() !='\b')
		           		{
		            		if(e.getKeyChar() !='.')
		            		{
		            			Toolkit tk = Toolkit.getDefaultToolkit();
					            tk.beep();
					            tcc.setText(tcc.getText().replace(String.valueOf(e.getKeyChar()), ""));
		            		}
		            	}		      
		            }
		        }
			}
		);
		
		tcc.addFocusListener
		(
			new FocusAdapter()
			{
				
				public void focusLost(FocusEvent e)
				{
					Double CC = Double.parseDouble(tcc.getText());
					String noteCC = tcc.getText();
					
					if(CC > 20 && noteCC.equals("") == false)
					{
						JOptionPane.showMessageDialog(null, "Note CC invalide ( >20 ). Veuillez la resaisir", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
						tcc.setText("");
					}
				}
			}
		);
		
		tds.addKeyListener
		(
			new KeyAdapter()
			{
				public void keyReleased(KeyEvent e) 
				{
		             
		            if(!isDouble(e.getKeyChar()))
		            {		        	
		            	if(e.getKeyChar() !='\b')
		           		{
		            		if(e.getKeyChar() !='.')
		            		{
		            			Toolkit tk = Toolkit.getDefaultToolkit();
					            tk.beep();
					            tds.setText(tds.getText().replace(String.valueOf(e.getKeyChar()), ""));
		            		}
		            	}		        
		            }
		        }
			}
		);
		
		tds.addFocusListener
		(
			new FocusAdapter()
			{
				
				public void focusLost(FocusEvent e)
				{
					Double DS = Double.parseDouble(tds.getText());
					String noteDS = tds.getText();
					
					if(DS > 20 && noteDS.equals("") == false)
					{
						JOptionPane.showMessageDialog(null, "Note DS invalide ( >20 ). Veuillez la resaisir", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
						tds.setText("");
					}
				}
			}
		);
		
		texm.addKeyListener
		(
			new KeyAdapter()
			{
				public void keyReleased(KeyEvent e) 
				{
		             
		            if(!isDouble(e.getKeyChar()))
		            {		        	
		            	if(e.getKeyChar() !='\b')
		           		{
		            		if(e.getKeyChar() !='.')
		            		{
		            			Toolkit tk = Toolkit.getDefaultToolkit();
					            tk.beep();
					            texm.setText(texm.getText().replace(String.valueOf(e.getKeyChar()), ""));
		            		}
		            	}		            
		            }
		        }
			}
		);
		
		texm.addFocusListener
		(
			new FocusAdapter()
			{
				
				public void focusLost(FocusEvent e)
				{
					Double EXM = Double.parseDouble(texm.getText());
					String noteEXM = texm.getText();
					
					if(EXM > 20 && noteEXM.equals("") == false)
					{
						JOptionPane.showMessageDialog(null, "Note EXAMEN invalide ( >20 ). Veuillez la resaisir", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
						texm.setText("");
					}
				}
			}
		);

		ttp.addKeyListener
		(
			new KeyAdapter()
			{
				public void keyReleased(KeyEvent e) 
				{
		             
		            if(!isDouble(e.getKeyChar()))
		            {		        	
		            	if(e.getKeyChar() !='\b')
		           		{
		            		if(e.getKeyChar() !='.')
		            		{
		            			Toolkit tk = Toolkit.getDefaultToolkit();
					            tk.beep();
					            ttp.setText(ttp.getText().replace(String.valueOf(e.getKeyChar()), ""));
		            		}
		            		
		            	}		            	
		            }
		        }
			}
		);
		
		ttp.addFocusListener
		(
			new FocusAdapter()
			{
				
				public void focusLost(FocusEvent e)
				{
					Double TP = Double.parseDouble(ttp.getText());
					String noteTP = ttp.getText();
					
					if(TP > 20 && noteTP.equals("") == false)
					{
						JOptionPane.showMessageDialog(null, "Note TP invalide ( >20 ). Veuillez la resaisir", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
						ttp.setText("");
					}
				}
			}
		);
				
		refaire.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(VueSaisieNoteMatiere.this.contains(lclasse.getLocation()))
					{
						VueSaisieNoteMatiere.this.remove(comboClasse);
						VueSaisieNoteMatiere.this.remove(lclasse);
						VueSaisieNoteMatiere.this.remove(comboGroupe);
						VueSaisieNoteMatiere.this.remove(lgroupe);
						VueSaisieNoteMatiere.this.remove(letudiant);
						VueSaisieNoteMatiere.this.remove(comboEtudiant);
						VueSaisieNoteMatiere.this.remove(valider);
						VueSaisieNoteMatiere.this.remove(lmatiere);
						VueSaisieNoteMatiere.this.remove(comboMatiere);
						VueSaisieNoteMatiere.this.remove(lprofesseur);
						VueSaisieNoteMatiere.this.remove(comboProfesseur);
						VueSaisieNoteMatiere.this.remove(lcc);
						VueSaisieNoteMatiere.this.remove(tcc);
						VueSaisieNoteMatiere.this.remove(lds);
						VueSaisieNoteMatiere.this.remove(tds);
						VueSaisieNoteMatiere.this.remove(lexm);
						VueSaisieNoteMatiere.this.remove(texm);
						VueSaisieNoteMatiere.this.remove(ltp);
						VueSaisieNoteMatiere.this.remove(checkTp);
						VueSaisieNoteMatiere.this.remove(ttp);
						VueSaisieNoteMatiere.this.remove(ajouter);
						comboFiliere.setEnabled(true);
						comboClasse.setEnabled(true);
						comboGroupe.setEnabled(true);
						comboEtudiant.setEnabled(true);
					}
					VueSaisieNoteMatiere.this.revalidate();
					VueSaisieNoteMatiere.this.repaint();
				}
			}
		);
		
		ajouter.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					validationEtAjout();
				}
			}
		);
		
		this.add(lfiliere, "cell 0 0,alignx left,growy");
		this.add(comboFiliere, "flowx,cell 1 0,alignx left,aligny center");
		this.add(refaire, "cell 1 0");	
	}
	
	private boolean isDouble(char caractère)
	{
        try 
        {
            Double.parseDouble(String.valueOf(caractère));
        } 
        catch (NumberFormatException e) 
        {
            return false;              
        }
        return true;
    }
	
	private Double calculMoyenneMatiereAvecTP()
	{
		
		Double CC = Double.parseDouble(tcc.getText());
		Double DS = Double.parseDouble(tds.getText());
		Double EXM = Double.parseDouble(texm.getText());
		Double TP = Double.parseDouble(ttp.getText());
		
		moyenne = ( CC * 0.1 ) + ( TP * 0.1) + ( DS * 0.2 ) + ( EXM * 0.6 );
		
		return moyenne;
	}
	
	private Double calculMoyenneMatiereSansTP()
	{
		Double CC = Double.parseDouble(tcc.getText());
		Double DS = Double.parseDouble(tds.getText());
		Double EXM = Double.parseDouble(texm.getText());
		Double TP = Double.parseDouble(ttp.getText());
		
		moyenne = ( CC * 0.1 ) + ( DS * 0.3 ) + ( EXM * 0.6 );
		
		return moyenne;
	}
	
	private void validationEtAjout()
	{
		Boolean stat = ttp.isEnabled();
		String CC = tcc.getText();
		String DS = tds.getText();
		String EXM = texm.getText();
		String TP = ttp.getText();
		
		if( CC.equals("") == true || DS.equals("") == true || EXM.equals("") == true )
		{
			JOptionPane.showMessageDialog(null, "Veuillez remplir les champs vides", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
		}
		else if( CC.equals("") == false || DS.equals("") == false || EXM.equals("") == false )
		{
			int idEt = NoteDAO.getIdEtudiantForRecordCount();
			int idMat = NoteDAO.getIdMatiereForRecordCount();
			Boolean record = NoteDAO.getRecordCountEtuMat(idEt, idMat);
			
			if(stat == true)
			{
				if( TP.equals("") == true )
				{
					JOptionPane.showMessageDialog(null, "Veuillez remplir les champs vides", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					calculMoyenneMatiereAvecTP();
					
					if( record == true )
					{
						NoteDAO.addNoteAvecTP();
						JOptionPane.showMessageDialog(null, "Ajout effectué avec succés","Succés",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Cette Note existe déjà dans la base", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			else if(stat == false && record == true)
			{
				calculMoyenneMatiereSansTP();
				NoteDAO.addNoteSansTP();
				JOptionPane.showMessageDialog(null, "Ajout effectué avec succés","Succés",JOptionPane.INFORMATION_MESSAGE);
			}
			else if(stat == false && record == false)
			{
				JOptionPane.showMessageDialog(null, "Cette Note existe déjà dans la base", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
