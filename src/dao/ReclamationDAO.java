package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vue.DbConnexion;
import vue.VueAjoutEtudiant;
import vue.VueAjoutReclamation;
import vue.VueTableEtudiant;
import vue.VueTableReclamation;

public class ReclamationDAO 
{
	public static PreparedStatement pst;
	
	public static ResultSet AfficherReclamations()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		ResultSet rs=null;
		
		String requete = "SELECT * from reclamation";
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
	
	public static void deleteReclamationFromTableReclamations()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		try 
		{
			int id = VueTableReclamation.idReclamation;
			
			String requete = "delete from reclamation where id = ? ";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setInt(1,id);
			pst.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void addReclamation()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		int idEtudiant = Integer.parseInt(VueAjoutReclamation.tId.getText());
		
		try 
		{
			String requete = "insert into reclamation"
					   + " (contenu, id_etudiant) values"
					   + "(?,?)";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setString(1,VueAjoutReclamation.tcontenu.getText());
			pst.setInt(2,idEtudiant);
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
}
