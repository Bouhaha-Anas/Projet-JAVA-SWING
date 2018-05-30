package vue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import dao.EtudiantDAO;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class Authentification extends JFrame
{
	private JButton bSeConnecter= new JButton("Se connecter"), bAnnuler = new JButton("Annuler");
	private JLabel jLabelNom_utilisateur = new JLabel("Nom d'utilisateur :"), jLabelMot_de_passe = new JLabel("Mot de passe :");
	private JTextField jTextFieldNom_utilisateur = new JTextField(20);
	private JPasswordField jPasswordField = new JPasswordField(20);
	private JComboBox comboType = new JComboBox();
	private JLabel typeUtil = new JLabel("Espace :");
	private ResultSet rs;
	
	public Authentification()
	{
	
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		this.setTitle("Authentification");
		this.setSize(400,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		typeUtil.setBounds(27, 27, 171, 14);
		
		this.getContentPane().add(typeUtil);
		comboType.setBounds(169, 24, 82, 20);
		comboType.addItem("agent");
		comboType.addItem("directeur");
		
		
		this.getContentPane().add(comboType);		
		jLabelNom_utilisateur.setBounds(27, 54, 138, 14);
		getContentPane().add(jLabelNom_utilisateur);
		jTextFieldNom_utilisateur.setBounds(169, 49, 138, 25);
		
		jTextFieldNom_utilisateur.addFocusListener
		(
				new FocusAdapter()
	            {
	                public void focusGained(FocusEvent e) 
	                {
	                	if(jTextFieldNom_utilisateur.getText().equals("") == false)
	                    {
	                		jTextFieldNom_utilisateur.selectAll();
	                    }      
	                }
	            }
		);
		
		this.getContentPane().add(jTextFieldNom_utilisateur);
		jLabelMot_de_passe.setBounds(27, 86, 146, 14);
		
		this.getContentPane().add(jLabelMot_de_passe);
		jPasswordField.setBounds(169, 80, 138, 26);
		this.getContentPane().add(jPasswordField);
		bSeConnecter.setBounds(27, 112, 117, 23);
		
		this.getContentPane().add(bSeConnecter);
		bAnnuler.setBounds(183, 112, 105, 23);
		
		this.getContentPane().add(bAnnuler);
		bAnnuler.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					jTextFieldNom_utilisateur.setText("");
					jPasswordField.setText("");
				}
			}	
		);
		bSeConnecter.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String login = jTextFieldNom_utilisateur.getText();
					String password = jPasswordField.getText();
					String type = comboType.getSelectedItem().toString();
					
					if(login.equals("") == false && password.equals("") == false )
					{
						
						rs= DbConnexion.executeQuery("select type from utilisateur where nom_utilisateur = '" +login+ "' and mot_de_passe = '" +password+ "' and type= '"+ type +"' ");
						
						try
						{
							if(rs.next())
							{
								if(rs.getString("type").equals("agent"))
								{
									MainFrame mainFrame = new MainFrame();
									try
									{
										mainFrame.displayTray();
									}
									catch(Exception ex)
									{
										ex.printStackTrace();
									}
									mainFrame.setVisible(true);
									Authentification.this.dispose();
								}
								else
								{
									DirecteurFrame directeurFrame = new DirecteurFrame();
									try
									{
										directeurFrame.displayTray();
									}
									catch(Exception ex)
									{
										ex.printStackTrace();
									}
									directeurFrame.setVisible(true);
									Authentification.this.dispose();
								}	
							}
							
							else
							{
								JOptionPane.showMessageDialog(null, "Nom ou Mot de passe ou Espce incorrect","Error",JOptionPane.ERROR_MESSAGE);
							}
						}
						
						catch(SQLException E)
						{
							E.printStackTrace();
						}
						
					}
					
					else if(login.equals("") && password.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Saisir votre nom et mot de passe","Error",JOptionPane.ERROR_MESSAGE);
					}
					
					else if(login.equals("") && password.equals("") == false)
					{
						JOptionPane.showMessageDialog(null, "Saisir votre nom","Error",JOptionPane.ERROR_MESSAGE);
					}
					
					else if(login.equals("") == false && password.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Saisir votre mot de passe","Error",JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		);
	}
	
	public static void main(String [] args)
	{
		Authentification authentification = new Authentification();
		authentification.setVisible(true);		
	}
	
}
