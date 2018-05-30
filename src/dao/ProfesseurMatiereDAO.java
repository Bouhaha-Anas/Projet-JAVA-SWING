package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vue.DbConnexion;
import vue.VueAjoutEtudiant;
import vue.VueAssocierFiliereMatiere;
import vue.VueAssocierProfesseurMatiere;
import vue.VueSaisieNoteMatiere;
import vue.VueTableFiliereMatiere;
import vue.VueTableProfesseur;
import vue.VueTableProfesseurMatiere;

public class ProfesseurMatiereDAO 
{
	public static PreparedStatement pst;
	
	public static void associate()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{	
			int idProf = Integer.parseInt(VueAssocierProfesseurMatiere.dcmIdProf.getSelectedItem().toString());
			int idMatiere = Integer.parseInt(VueAssocierProfesseurMatiere.tidM.getText());
			
			String requete = "insert into professeur_matiere"
					   + " (id_professeur, id_matiere) values"
					   + "(?,?)";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setInt(1,idProf);
			pst.setInt(2,idMatiere);
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
	
	public static boolean getRecordCount()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		int idProf = Integer.parseInt(VueAssocierProfesseurMatiere.dcmIdProf.getSelectedItem().toString());
		int idMatiere = Integer.parseInt(VueAssocierProfesseurMatiere.tidM.getText());
		boolean record= false;
		
		String requete = "select * from professeur_matiere where id_professeur = '"+ idProf +"' and id_matiere = '"+ idMatiere +"' ";
		
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
	
	public static void getAllProfesseurByMatiere()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		String nomMatiere = VueSaisieNoteMatiere.comboMatiere.getSelectedItem().toString();
		
		String requete = "select nom,prenom from professeur "
				       + "where id in (select id_professeur from professeur_matiere "
				       			   + "where id_matiere in (select id from matiere "
				       			   				       + "where nom = '"+ nomMatiere +"' ))";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueSaisieNoteMatiere.dcmProfesseur.addElement(rs.getString("nom")+rs.getString("prenom"));
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
	
	public static ResultSet AfficherProfesseursMatieres()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		ResultSet rs=null;
		
		String requete = "SELECT * from filiere_matiere";
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
	
	public static void dissociate()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		try 
		{
			int id = VueTableProfesseurMatiere.idAssoc ;
			
			String requete = "delete from professeur_matiere where id = ? ";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setInt(1,id);
			pst.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
}