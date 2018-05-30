package vue;


import java.awt.TextField;
import java.awt.Toolkit;
import javax.swing.*;

import dao.ClasseDAO;
import dao.ProfesseurDAO;
import model.Grade;
import model.Sexe;
import model.Ville;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;

public class VueModifierProfesseur extends JPanel
{
	private JLabel lnom = new JLabel("Nom :"), lprenom = new JLabel("Prénom :"), ldatenaissance = new JLabel("Date de naissance :"),
				   ladresse = new JLabel("Adresse :"), ltelephone = new JLabel("Telephone :"), lmail = new JLabel("E-mail :"),
				   lcin = new JLabel("CIN :"), lville = new JLabel("Ville :"), lsexe = new JLabel("Sexe :"),
				   lsalaire = new JLabel("Salaire :"), lgrade = new JLabel("Grade :"),
				   lannee = new JLabel("Année"), lmois = new JLabel("Mois"), ljour = new JLabel("Jour"),lIdProfesseur = new JLabel("Id du professeur :");
	
	public static JTextField tnom = new JTextField(20), tprenom = new JTextField(20),
					   ttelephone = new JTextField(20), tmail = new JTextField(20), tcin = new JTextField(20), tsalaire = new JTextField(20);
	
	public static JTextArea tadresse = new JTextArea(10,20);
	public static JComboBox comboYear = new JComboBox(), comboMonth = new JComboBox(), comboJour = new JComboBox();
					   
	public static JComboBox comboSexe = new JComboBox(Sexe.values()), comboVille = new JComboBox(Ville.values()), comboGrade = new JComboBox(Grade.values()), comboIdProfesseur;	
	private JButton enregistrer = new JButton("Ajouter"), annuler = new JButton("Annuler"), supprimer = new JButton("Supprimer");
	private JPanel panelJour;
	private JScrollPane tadresseScroll = new JScrollPane(tadresse);
	public static DefaultComboBoxModel defaultComboBoxModelIdProf ;
	
	public VueModifierProfesseur() 
	{
		setLayout(new MigLayout("", "[120px][5px][166px]", "[20px][20px][20px][23.00px][24.00px][152.00px][20px][20px][20px][20px][20px][23px][]"));
		
		defaultComboBoxModelIdProf = new DefaultComboBoxModel();
		ProfesseurDAO.getAllIds();
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int i;
		for(i=1970; i< year-17 ;i++)
		{
			comboYear.addItem(i);
		}
		for(i=1;i<32;i++)
		{
			comboJour.addItem(i);
		}
		
		for(i=1;i<10;i++)
		{
			comboMonth.addItem("0"+i);
		}
		
		enregistrer.addActionListener
		(
				new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) 
					{
						String nom = tnom.getText();
						String prenom = tprenom.getText();
						
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
								ProfesseurDAO.getRecordCountForModification(nom,prenom);
								if(ProfesseurDAO.getRecordCountForModification(nom,prenom) == true)
								{
									ProfesseurDAO.updateProfesseur();
									JOptionPane.showMessageDialog(null, "Modification effectué avec succés","Succés",JOptionPane.INFORMATION_MESSAGE);
								}
								else
								{
									JOptionPane.showMessageDialog(null, "Ce professeur est déjà existant", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
								}
								
							}
						}
					}
				}
		);
		
		this.add(lIdProfesseur, "cell 0 0");
		comboIdProfesseur = new JComboBox();
		comboIdProfesseur.setModel(defaultComboBoxModelIdProf);
		this.add(comboIdProfesseur, "cell 2 0");
		this.add(lnom, "cell 0 1,grow");
		this.add(tnom, "cell 2 1,grow");
		this.add(lprenom, "cell 0 2,grow");
		this.add(tprenom, "cell 2 2,grow");
		this.add(lsexe, "cell 0 3,grow");
		this.add(comboSexe, "cell 2 3,alignx left,growy");
		this.add(ldatenaissance, "cell 0 4,grow");
		this.add(lannee, "flowx,cell 2 4");
		this.add(ladresse, "cell 0 5,grow");
		this.add(tadresseScroll, "cell 2 5,growx,aligny top");
		this.add(lville, "cell 0 6,grow");
		this.add(comboVille, "cell 2 6,alignx left,growy");
		this.add(lcin, "cell 0 7,grow");
		
		comboIdProfesseur.addItemListener
		(
			new ItemListener()
			{
				public void itemStateChanged(ItemEvent e)
				{
					if(tnom.getText().equals("") == false)
					{
						tnom.setText("");
						tprenom.setText("");
						tadresse.setText("");
						ttelephone.setText("");
						tmail.setText("");
						tcin.setText("");
						tsalaire.setText("");
					}
					ProfesseurDAO.getProfesseurById();
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
		this.add(tcin, "cell 2 7,growx,aligny top");
		this.add(ltelephone, "cell 0 8,grow");
		
		ttelephone.addKeyListener
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
		
		this.add(ttelephone, "cell 2 8,grow");
		this.add(lmail, "cell 0 9,grow");
		
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
		this.add(tmail, "cell 2 9,grow");
		this.add(lsalaire, "cell 0 10,grow");
		this.add(tsalaire, "cell 2 10,grow");
		this.add(lgrade, "cell 0 11,grow");
		this.add(comboGrade, "cell 2 11,alignx left,growy");
		
		this.add(enregistrer, "cell 0 12,growx,aligny top");
		this.add(comboYear, "cell 2 4");
		this.add(lmois, "cell 2 4");
		comboMonth.addItem("10");
		comboMonth.addItem("11");
		comboMonth.addItem("12");
		
		this.add(comboMonth, "cell 2 4");
		panelJour = new JPanel();
		panelJour.add(ljour);
		panelJour.add(comboJour);
		this.add(panelJour, "cell 2 4");
		
		comboMonth.addItemListener
		(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						String selectedMonth = (String)comboMonth.getSelectedItem();
						if(panelJour != null)
						{
							VueModifierProfesseur.this.remove(panelJour);
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
						VueModifierProfesseur.this.add(panelJour, "cell 2 4");
						VueModifierProfesseur.this.validate();
						VueModifierProfesseur.this.repaint();						
					}
				}
		);
		
		supprimer.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(comboIdProfesseur != null)
					{
						Object[] options = {"Oui", "Non"};
			            int n = JOptionPane.showOptionDialog(null, "Voulez vous vraiment supprimer le professeur avec l'id : " +
			                    defaultComboBoxModelIdProf.getSelectedItem().toString() + " ?", "Avertissement", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

			            if(n == JOptionPane.YES_OPTION) 
			            {			       
			                ProfesseurDAO.deleteProfesseur();
			                JOptionPane.showMessageDialog(null, "L'enregistrement est supprimé de la base de données.");
			            }
				
					}
				}
			}	
		);
		this.add(supprimer, "flowx,cell 2 12");
		
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
						tsalaire.setText("");
						VueModifierProfesseur.this.remove(panelJour);
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
						VueModifierProfesseur.this.add(panelJour, "cell 2 4");
						VueModifierProfesseur.this.validate();
						VueModifierProfesseur.this.repaint();
					}			
				}
		);
		this.add(annuler, "cell 2 12,alignx left,aligny top");
		
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
