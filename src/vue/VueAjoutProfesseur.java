package vue;


import java.awt.TextField;
import java.awt.Toolkit;

import javax.swing.*;

import dao.EtudiantDAO;
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

public class VueAjoutProfesseur extends JPanel
{
	private JLabel lnom = new JLabel("Nom :"), lprenom = new JLabel("Prénom :"), ldatenaissance = new JLabel("Date de naissance :"),
				   ladresse = new JLabel("Adresse :"), ltelephone = new JLabel("Telephone :"), lmail = new JLabel("E-mail :"),
				   lcin = new JLabel("CIN :"), lville = new JLabel("Ville :"), lsexe = new JLabel("Sexe :"),
				   lsalaire = new JLabel("Salaire :"), lgrade = new JLabel("Grade :"),
				   lannee = new JLabel("Année"), lmois = new JLabel("Mois"), ljour = new JLabel("Jour");
	
	public static JTextField tnom = new JTextField(20), tprenom = new JTextField(20),
					         ttelephone = new JTextField(20), tmail = new JTextField(20), tcin = new JTextField(20), tsalaire = new JTextField(20);
	
	public static JTextArea tadresse = new JTextArea(10,20);
	public static JComboBox comboYear = new JComboBox(), comboMonth = new JComboBox(), comboJour = new JComboBox();
					   
	public static JComboBox comboSexe = new JComboBox(Sexe.values()), comboVille = new JComboBox(Ville.values()), comboGrade = new JComboBox(Grade.values());	
	private JButton ajouter = new JButton("Ajouter"), annuler = new JButton("Annuler");
	private JPanel panelJour;
	
	private JScrollPane tadresseScroll = new JScrollPane(tadresse);
	
	public VueAjoutProfesseur() 
	{
		setLayout(new MigLayout("", "[120px][5px][25px][5px][166px]", "[20px][20px][20px][20px][184px][20px][20px][20px][20px][20px][20px][23px]"));
		this.add(lnom, "cell 0 0 3 1,grow");
		this.add(tnom, "cell 4 0,grow");
		this.add(lprenom, "cell 0 1 3 1,grow");
		this.add(tprenom, "cell 4 1,grow");
		this.add(lsexe, "cell 0 2 3 1,grow");
		this.add(comboSexe, "cell 4 2,alignx left,growy");
		this.add(ldatenaissance, "cell 0 3 3 1,grow");
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
		this.add(ladresse, "cell 0 4 3 1,grow");
		this.add(tadresseScroll, "cell 4 4,growx,aligny top");
		this.add(lville, "cell 0 5 3 1,grow");
		this.add(comboVille, "cell 4 5,alignx left,growy");
		this.add(lcin, "cell 0 6 3 1,grow");
		
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
		this.add(tcin, "cell 4 6,growx,aligny top");
		this.add(ltelephone, "cell 0 7 3 1,grow");
		
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
		
		this.add(ttelephone, "cell 4 7,grow");
		this.add(lmail, "cell 0 8 3 1,grow");
		
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
		this.add(tmail, "cell 4 8,grow");
		this.add(lsalaire, "cell 0 9 3 1,grow");
		this.add(tsalaire, "cell 4 9,grow");
		this.add(lgrade, "cell 0 10 3 1,grow");
		this.add(comboGrade, "cell 4 10,alignx left,growy");
		
		ajouter.addActionListener
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
								ProfesseurDAO.getRecordCount(nom,prenom);
								if(ProfesseurDAO.getRecordCount(nom,prenom) == true)
								{
									ProfesseurDAO.addProfesseur();
									JOptionPane.showMessageDialog(null, "Ajout effectué avec succés","Succés",JOptionPane.INFORMATION_MESSAGE);
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
						VueAjoutProfesseur.this.remove(panelJour);
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
					}			
				}
		);
		
		this.add(ajouter, "cell 0 11,growx,aligny top");
		this.add(annuler, "cell 2 11 3 1,alignx left,aligny top");
		this.add(lannee, "flowx,cell 4 3");
		this.add(comboYear, "cell 4 3");
		this.add(lmois, "cell 4 3");
		comboMonth.addItem("10");
		comboMonth.addItem("11");
		comboMonth.addItem("12");
		
		this.add(comboMonth, "cell 4 3");
		panelJour = new JPanel();
		panelJour.add(ljour);
		panelJour.add(comboJour);
		this.add(panelJour, "cell 4 3");
		
		comboMonth.addItemListener
		(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						String selectedMonth = (String)comboMonth.getSelectedItem();
						if(panelJour != null)
						{
							VueAjoutProfesseur.this.remove(panelJour);
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
						VueAjoutProfesseur.this.add(panelJour, "cell 4 3");
						VueAjoutProfesseur.this.validate();
						VueAjoutProfesseur.this.repaint();						
					}
				}
		);
		
		tsalaire.addKeyListener
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
					            tsalaire.setText(tsalaire.getText().replace(String.valueOf(e.getKeyChar()), ""));
		            		}
		            	}		      
		            }
		        }
			}
		);
		
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
