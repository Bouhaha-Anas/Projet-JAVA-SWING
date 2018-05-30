package vue;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;

import javax.swing.*;

import dao.MatiereDAO;
import net.miginfocom.swing.MigLayout;

public class VueAjoutMatiere extends JPanel
{
	private JLabel lnom = new JLabel("Nom :"), lcoefficient = new JLabel("Coefficient :"), lsemestre = new JLabel("Semestre :");
	public static JTextField tnom = new JTextField(20), tcoefficient = new JTextField(20);
	private JButton ajouter = new JButton("Ajouter"), annuler = new JButton("Annuler");
	public static JComboBox comboSemestre = new JComboBox();
	
	public VueAjoutMatiere()
	{
		setLayout(new MigLayout("", "[59px][6px][181.00px]", "[20px][20px][][23px]"));
		this.add(lnom, "cell 0 0,alignx left,aligny center");
		this.add(tnom, "cell 2 0,alignx left,growy");
		this.add(lcoefficient, "cell 0 1,alignx left,aligny center");
		
		Calendar calendar = Calendar.getInstance(); 
		int month = calendar.get(Calendar.MONTH);
		
		if( month == 2 || month == 3 || month == 4 || month == 5 )
		{
			comboSemestre.addItem("2ème semestre");
		}
		else
		{
			comboSemestre.addItem("1ère semestre");
			comboSemestre.addItem("2ème semestre");
		}
		
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
		this.add(tcoefficient, "cell 2 1,alignx left,growy");
		
		ajouter.addActionListener
		(
			new ActionListener() 
			{
				
				public void actionPerformed(ActionEvent e) 
				{
					String nomMat = tnom.getText();
					String coef = tcoefficient.getText();
					MatiereDAO.getRecordCount(nomMat);
					if(MatiereDAO.getRecordCount(nomMat) == true)
					{
						if(nomMat.equals("") == true || coef.equals("") == true)
						{
							JOptionPane.showMessageDialog(null, "Veuillez remplir les champs vides", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
						}
						else if(nomMat.equals("") == false && coef.equals("") == false)
						{
							MatiereDAO.addMatiere();
							JOptionPane.showMessageDialog(null, "Ajout effectué avec succés !", "Succés",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Cette matière déjà existante !", "Erreur de saisi",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		);
		this.add(lsemestre, "cell 0 2");
		this.add(comboSemestre, "cell 2 2");
		this.add(ajouter, "flowx,cell 0 3 3 1,alignx left,aligny top");
		
		this.add(annuler, "cell 2 3,alignx left,aligny top");
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
