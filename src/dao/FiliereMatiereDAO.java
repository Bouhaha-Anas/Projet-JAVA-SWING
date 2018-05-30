package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vue.DbConnexion;
import vue.VueAjoutClasse;
import vue.VueAjoutEtudiant;
import vue.VueAssocierFiliereMatiere;
import vue.VueSaisieNoteMatiere;
import vue.VueTableFiliere;
import vue.VueTableFiliereMatiere;

public class FiliereMatiereDAO 
{
	public static PreparedStatement pst;
	
	public static void associate()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{	
			int idFiliere = Integer.parseInt(VueAssocierFiliereMatiere.tidF.getText());
			int idMatiere = Integer.parseInt(VueAssocierFiliereMatiere.tidM.getText());
			
			String requete = "insert into filiere_matiere"
					   + " (id_filiere, id_matiere) values"
					   + "(?,?)";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setInt(1,idFiliere);
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
	
	public static void getAllMatieresByFiliere()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		String nomFiliere = VueSaisieNoteMatiere.comboFiliere.getSelectedItem().toString();
		
		String requete = "select nom from matiere "
				       + "where id in (select id_matiere from filiere_matiere "
				       			   + "where id_filiere in (select id from filiere "
				       			   				       + "where nom = '"+ nomFiliere +"' ))";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueSaisieNoteMatiere.dcmMatiere.addElement(rs.getString("nom"));
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
	
	public static boolean getRecordCount()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		int idFiliere = Integer.parseInt(VueAssocierFiliereMatiere.tidF.getText());
		int idMatiere = Integer.parseInt(VueAssocierFiliereMatiere.tidM.getText());
		boolean record= false;
		
		String requete = "select * from filiere_matiere where id_filiere = '"+ idFiliere +"' and id_matiere = '"+ idMatiere +"' ";
		
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
	
	public static ResultSet AfficherFilieresMatieres()
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
			int id = VueTableFiliereMatiere.idAssoc ;
			
			String requete = "delete from filiere_matiere where id = ? ";
			
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
