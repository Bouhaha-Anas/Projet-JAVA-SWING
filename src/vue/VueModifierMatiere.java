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

import javax.swing.*;

import dao.ClasseDAO;
import dao.MatiereDAO;
import net.miginfocom.swing.MigLayout;

public class VueModifierMatiere extends JPanel
{
	private JLabel lnom = new JLabel("Nom :"), lcoefficient = new JLabel("Coefficient :"), lIdMatiere = new JLabel("id Matière :"),lsemestre = new JLabel("Semestre :");
	public static JTextField tnom = new JTextField(30), tcoefficient = new JTextField(30);
	private JButton enregistrer = new JButton("Enregistrer"), annuler = new JButton("Annuler"), supprimer = new JButton("Supprimer");
	public static JComboBox comboId, comboSemestre = new JComboBox();
	public static DefaultComboBoxModel defaultComboBoxModelId;
	
	public VueModifierMatiere()
	{
		setLayout(new MigLayout("", "[59px][176.00px][]", "[20px][20px][23px][][]"));
		
		defaultComboBoxModelId = new DefaultComboBoxModel();
		comboId = new JComboBox();
		comboId.setModel(defaultComboBoxModelId);
		MatiereDAO.getAllIds();
		
		this.add(lIdMatiere, "cell 0 0");
		this.add(comboId, "cell 1 0");
		this.add(lnom, "cell 0 1,alignx left,aligny center");
		this.add(tnom, "cell 1 1,growx,aligny top");
		this.add(lcoefficient, "flowx,cell 0 2,alignx left,aligny center");
		this.add(tcoefficient, "cell 1 2,growx,aligny top");
		this.add(lsemestre, "cell 0 3");
		this.add(comboSemestre, "cell 1 3");
		this.add(enregistrer, "cell 0 4,alignx right,aligny top");
		this.add(supprimer, "flowx,cell 1 4");
		this.add(annuler, "cell 1 4,alignx left,aligny top");
		
		comboSemestre.addItem("1ère semestre");
		comboSemestre.addItem("2ème semestre");
		
		
		comboId.addItemListener
		(
			new ItemListener() 
			{
				public void itemStateChanged(ItemEvent e)
				{
					if(tnom.getText().equals("") == false || tcoefficient.getText().equals("") == false)
					{
						tnom.setText("");
						tcoefficient.setText("");
					}
					MatiereDAO.getMatiereById();
				}
			}
		);
		
		enregistrer.addActionListener
		(
			new ActionListener() 
			{
				
				public void actionPerformed(ActionEvent e) 
				{
					String nomMat = tnom.getText();
					String coef = tcoefficient.getText();
					
					if(nomMat.equals("") == true || coef.equals("") == true)
					{
						JOptionPane.showMessageDialog(null, "Veuillez remplir les champs vides", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
					}
					else if(nomMat.equals("") == false && coef.equals("") == false)
					{
						MatiereDAO.getRecordCountForModifier(nomMat);
						if(MatiereDAO.getRecordCountForModifier(nomMat) == true)
						{
							MatiereDAO.updateMatiere();
							JOptionPane.showMessageDialog(null, "Modfication effectué avec succés !", "Succés",JOptionPane.INFORMATION_MESSAGE);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Cette matière déjà existante !", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
						}
						
					}
					
					
				}
			}
		);
		
		tcoefficient.addKeyListener
		(
			new KeyAdapter()
			{
				public void keyReleased(KeyEvent e) 
				{
		             
		            if(!isNumeric(e.getKeyChar()))
		            {
		            	if(e.getKeyChar() !='.')
		            	{
		            		if(e.getKeyChar() !='\b')
		            		{
		            			Toolkit tk = Toolkit.getDefaultToolkit();
				                tk.beep();
				                tcoefficient.setText(tcoefficient.getText().replace(String.valueOf(e.getKeyChar()), ""));
		            		}
		            	}		            	
		            }
		        }
			}
		);
		
		tcoefficient.addFocusListener
        (
            new FocusAdapter()
            {   
                public void focusLost(FocusEvent e) 
                {
                    if(tcoefficient.getText().contains(".") == false)
                    {              
		                JOptionPane.showMessageDialog(null, "<html><font>le coefficient doit etre sous la forme :<br>XX.XX</font></html>", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);		                
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
					if(comboId != null)
					{
						Object[] options = {"Oui", "Non"};
			            int n = JOptionPane.showOptionDialog(null, "Voulez vous vraiment supprimer la matière avec l'id : " +
			                    defaultComboBoxModelId.getSelectedItem().toString() + " ?", "Avertissement", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

			            if(n == JOptionPane.YES_OPTION) 
			            {			       
			                MatiereDAO.deleteMatiere();
			                JOptionPane.showMessageDialog(null, "L'enregistrement est supprimé de la base de données.");
			            }
				
					}
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
