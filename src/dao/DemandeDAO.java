package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import vue.DbConnexion;
import vue.VueAjoutDemande;
import vue.VueModifierMatiere;
import vue.VueTableDemande;
import vue.VueTableDemandeDirecteur;


public class DemandeDAO 
{
	public static PreparedStatement pst;
	
	public static void addDemandeCertificat()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		String sujet = VueAjoutDemande.comboSujet.getSelectedItem().toString();
		int idEtu1 = Integer.parseInt(VueAjoutDemande.tId1.getText());
		
		try 
		{
			String requete = "insert into demande"
					   + " (sujet, status, id_etudiant1,decision) values"
					   + "(?,?,?,?)";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setString(1,sujet);
			pst.setBoolean(2,false);
			pst.setInt(3,idEtu1);
			pst.setString(4,"En cours");
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
	
	public static void addDemandePermut()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		String sujet = VueAjoutDemande.comboSujet.getSelectedItem().toString();
		int idEtu1 = Integer.parseInt(VueAjoutDemande.tId1.getText());
		int idEtu2 = Integer.parseInt(VueAjoutDemande.tId2.getText());
		
		 
		
		try 
		{
			String requete = "insert into demande"
					   + " ( sujet, status, id_etudiant1, id_etudiant2,decision ) values"
					   + "(?,?,?,?,?)";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setString(1,sujet);
			pst.setBoolean(2,false);
			pst.setInt(3,idEtu1);
			pst.setInt(4,idEtu2);
			pst.setString(5,"En cours");
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
	
	public static void deleteDemandeFromTableDemandes()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		try 
		{
			int id = VueTableDemande.idDemande;
			
			String requete = "delete from demande where id = ? ";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setInt(1,id);
			pst.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static ResultSet AfficherDemandesNonApprouves()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		ResultSet rs=null;
		
		String requete = "SELECT * from demande where status = false ";
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
	
	public static ResultSet AfficherDemandesApprouvees()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		ResultSet rs=null;
		
		String requete = "SELECT * from demande where status = true and decision = 'Approuvée' ";
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
	
	public static ResultSet AfficherDemandesIgnorees()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		ResultSet rs=null;
		
		String requete = "SELECT * from demande where status = true and decision = 'Ignorée' ";
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
	
	public static void approuver()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{
			String requete = "update demande set status =? , decision =? "
					       + "where id =? ";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setBoolean(1,true);
			pst.setString(2,"Approuvée");
			pst.setInt(3,VueTableDemandeDirecteur.idDemande);
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
	
	public static void ignorer()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{
			String requete = "update demande set status =? , decision =?, raison=? "
					       + "where id =? ";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setBoolean(1,true);
			pst.setString(2,"Ignorée");
			pst.setString(3, VueTableDemandeDirecteur.traison.getText());
			pst.setInt(4,VueTableDemandeDirecteur.idDemande);
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
	
	public static int nombreDemande()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		int compteur =0;
		ResultSet rs = null;
		
		String requete = "SELECT count(*) from demande where status = false ";
		try
		{
			 rs = DbConnexion.st.executeQuery(requete);
			 rs.next();
			 compteur = rs.getInt(1);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return compteur;
	}
}
