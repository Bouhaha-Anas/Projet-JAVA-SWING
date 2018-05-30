package dao;

import vue.DbConnexion;
import vue.VueAjoutClasse;
import vue.VueAjoutEtudiant;
import vue.VueAjoutProfesseur;
import vue.VueAssocierProfesseurMatiere;
import vue.VueModifierEtudiant;
import vue.VueModifierFiliere;
import vue.VueModifierProfesseur;
import vue.VueTableEtudiant;
import vue.VueTableProfesseur;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ProfesseurDAO 
{
	public static PreparedStatement pst;
	
	public static void addProfesseur()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{
			String requete = "insert into professeur"
					   + " (nom,prenom,date_de_naissance,adresse,telephone,adresse_mail,cin,ville,sexe,grade,salaire) values"
					   + "(?,?,?,?,?,?,?,?,?,?,?)";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			String annee = VueAjoutProfesseur.comboYear.getSelectedItem().toString();
			String mois = VueAjoutProfesseur.comboMonth.getSelectedItem().toString();
			String jour = VueAjoutProfesseur.comboJour.getSelectedItem().toString();
			String sexe = VueAjoutProfesseur.comboSexe.getSelectedItem().toString();
			String ville = VueAjoutProfesseur.comboVille.getSelectedItem().toString();
			String nom = VueAjoutProfesseur.tnom.getText();
			String prenom = VueAjoutProfesseur.tprenom.getText();
			String adresse = VueAjoutProfesseur.tadresse.getText();
			String mail = VueAjoutProfesseur.tmail.getText();
			String grade = VueAjoutProfesseur.comboGrade.getSelectedItem().toString();
			Double salaire = Double.parseDouble(VueAjoutProfesseur.tsalaire.getText());			
			int tel = Integer.parseInt(VueAjoutProfesseur.ttelephone.getText());
			int cin = Integer.parseInt(VueAjoutProfesseur.tcin.getText());
			
			
			pst.setString(1,nom);
			pst.setString(2,prenom);
			pst.setString(3,annee+"/"+mois+"/"+jour);
			pst.setString(4,adresse);
			pst.setInt(5,tel);
			pst.setString(6,mail);
			pst.setInt(7,cin);
			pst.setString(8,ville);
			pst.setString(9,sexe);
			pst.setString(10,grade);
			pst.setDouble(11,salaire);
			pst.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbConnexion.deconnexion();
		}
		
	}
	
	public static boolean getRecordCount(String nom,String prenom)
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		boolean record= false;
		
		String requete ="select * from professeur where nom='"+ nom +"' and prenom='"+ prenom +"' ";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			if(rs.next())
			{
				record = false;
			}
			else
			{
				record = true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbConnexion.deconnexion();
		}
		return record;
	}
	
	public static ResultSet AfficherProfesseurs()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		ResultSet rs=null;
		
		String requete = "SELECT * from professeur";
		try
		{
			rs	= DbConnexion.st.executeQuery(requete);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return rs;
	}
	
	public static void updateProfesseur()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{
			
			String requete = "update professeur"
					   + " set nom =?, prenom =?, date_de_naissance =?, adresse =?, telephone =?, adresse_mail =?, cin =?, ville =?, sexe =? ,salaire =?, grade =?"
					   + " where id =? ";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			String annee = VueModifierProfesseur.comboYear.getSelectedItem().toString();
			String mois = VueModifierProfesseur.comboMonth.getSelectedItem().toString();
			String jour = VueModifierProfesseur.comboJour.getSelectedItem().toString();
			String adresse = VueModifierProfesseur.tadresse.getText();
			String nom = VueModifierProfesseur.tnom.getText();
			String prenom = VueModifierProfesseur.tprenom.getText();
			String mail = VueModifierProfesseur.tmail.getText();
			String sexe = VueModifierProfesseur.comboSexe.getSelectedItem().toString();
			String ville = VueModifierProfesseur.comboVille.getSelectedItem().toString();
			String grade = VueModifierProfesseur.comboGrade.getSelectedItem().toString();
			double salaire = Double.parseDouble(VueModifierProfesseur.tsalaire.getText());
			int telephone = Integer.parseInt(VueModifierProfesseur.ttelephone.getText());
			int cin = Integer.parseInt(VueModifierProfesseur.tcin.getText());
			int idProf = Integer.parseInt(VueModifierProfesseur.defaultComboBoxModelIdProf.getSelectedItem().toString());
			
			pst.setString(1,nom);
			pst.setString(2,prenom);
			pst.setString(3,annee+"/"+mois+"/"+jour);
			pst.setString(4,adresse);
			pst.setInt(5,telephone);
			pst.setString(6,mail);
			pst.setInt(7,cin);
			pst.setString(8,ville);
			pst.setString(9,sexe);
			pst.setDouble(10,salaire);
			pst.setString(11,grade);
			pst.setInt(12,idProf);
			pst.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbConnexion.deconnexion();
		}
		
	}
	
	public static void getAllIds()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		String requete = "select id from professeur";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueModifierProfesseur.defaultComboBoxModelIdProf.addElement(rs.getString("id"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbConnexion.deconnexion();
		}
	}
	
	public static void getProfesseurById()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		int idProf = Integer.parseInt(VueModifierProfesseur.defaultComboBoxModelIdProf.getSelectedItem().toString());
		
		String requete = "select nom,prenom, sexe, adresse, ville, cin, telephone, adresse_mail, salaire, grade from professeur where id= '"+ idProf +"' ";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueModifierProfesseur.tnom.setText(rs.getString("nom"));
				VueModifierProfesseur.tprenom.setText(rs.getString("prenom"));
				VueModifierProfesseur.comboSexe.setSelectedItem(rs.getString("sexe"));
				VueModifierProfesseur.tadresse.setText(rs.getString("adresse"));
				VueModifierProfesseur.comboVille.setSelectedItem(rs.getString("ville"));
				VueModifierProfesseur.tcin.setText(rs.getString("cin"));
				VueModifierProfesseur.ttelephone.setText(rs.getString("telephone"));
				VueModifierProfesseur.tmail.setText(rs.getString("adresse_mail"));
				VueModifierProfesseur.tsalaire.setText(rs.getString("salaire"));
				VueModifierProfesseur.comboGrade.setSelectedItem(rs.getString("grade"));
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbConnexion.deconnexion();
		}
	}

	public static boolean getRecordCountForModification(String nom,String prenom)
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		boolean record= false;
		int idPROF = Integer.parseInt(VueModifierProfesseur.defaultComboBoxModelIdProf.getSelectedItem().toString());
		
		String requete ="select * from professeur where nom='"+ nom +"' and prenom='"+ prenom +"' and id !='"+ idPROF +"' ";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			if(rs.next())
			{
				record = false;
			}
			else
			{
				record = true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbConnexion.deconnexion();
		}
		return record;
	}
	
	public static void deleteProfesseur()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{
			int idProf = Integer.parseInt(VueModifierProfesseur.defaultComboBoxModelIdProf.getSelectedItem().toString());
			
			String requete = "delete from professeur where id = ? ";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setInt(1,idProf);
			pst.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbConnexion.deconnexion();
		}
		
	}
	
	public static void deleteProfesseurFromTableProfesseurs()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		try 
		{
			int id = VueTableProfesseur.idProfesseur;
			
			String requete = "delete from professeur where id = ? ";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setInt(1,id);
			pst.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void getAllIdForAssociationPM()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		String requete = "select id from professeur";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAssocierProfesseurMatiere.dcmIdProf.addElement(rs.getString("id"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbConnexion.deconnexion();
		}
	}
	
	public static void getNomPrenomByIdForAssocaitionPM()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		int idProf = Integer.parseInt(VueAssocierProfesseurMatiere.dcmIdProf.getSelectedItem().toString());
		
		String requete = "select nom,prenom from professeur where id = '"+ idProf +"' ";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAssocierProfesseurMatiere.tnomprenom.setText( rs.getString("nom") + " " + rs.getString("prenom") );
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			DbConnexion.deconnexion();
		}
	}
}
