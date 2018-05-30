package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vue.DbConnexion;
import vue.VueSaisieNoteMatiere;
import vue.VueTableNote;

public class NoteDAO 
{
	
	public static PreparedStatement pst;
	
	public static void addNoteAvecTP()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		Double CC = Double.parseDouble(VueSaisieNoteMatiere.tcc.getText());
		Double DS = Double.parseDouble(VueSaisieNoteMatiere.tds.getText());
		Double EXM = Double.parseDouble(VueSaisieNoteMatiere.texm.getText());
		Double TP = Double.parseDouble(VueSaisieNoteMatiere.ttp.getText());
		
		try 
		{	
			
			int idEtudiant = getIdEtudiantForRecordCount();
			int idMatiere = getIdMatiereForRecordCount();
			
			String requete = "insert into note (id_etudiant, id_matiere, cc, ds, exm, tp, moyenne) values"
					        + "(?,?,?,?,?,?,?)";
					
			pst = DbConnexion.con.prepareStatement(requete);
			pst.setDouble(1,idEtudiant);
			pst.setDouble(2,idMatiere);
			pst.setDouble(3,CC);
			pst.setDouble(4,DS);
			pst.setDouble(5,EXM);
			pst.setDouble(6,TP);
			pst.setDouble(7,VueSaisieNoteMatiere.moyenne);
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
	
	public static void addNoteSansTP()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		
		String matriculeEtudiant = VueSaisieNoteMatiere.dcmEtudiant.getSelectedItem().toString();
		String matiere = VueSaisieNoteMatiere.dcmMatiere.getSelectedItem().toString();
		
		Double CC = Double.parseDouble(VueSaisieNoteMatiere.tcc.getText());
		Double DS = Double.parseDouble(VueSaisieNoteMatiere.tds.getText());
		Double EXM = Double.parseDouble(VueSaisieNoteMatiere.texm.getText());
		
		try 
		{	
			
			String requete1 = "select id from etudiant where matricule = '"+ matriculeEtudiant +"' ";
			rs1 = DbConnexion.st.executeQuery(requete1);
			int idEtudiant = Integer.parseInt(rs1.getString("id"));
			
			String requete2 = "select id from matiere where nom = '"+ matiere +"' ";
			rs2 = DbConnexion.st.executeQuery(requete2);
			int idMatiere = Integer.parseInt(rs2.getString("id"));
			
			String requete3 = "insert into note (id_etudiant, id_matiere, cc, ds, exm, moyenne) values"
					        + "(?,?,?,?,?,?)";
					
			pst = DbConnexion.con.prepareStatement(requete3);
			pst.setDouble(1,idEtudiant);
			pst.setDouble(2,idMatiere);
			pst.setDouble(3,CC);
			pst.setDouble(4,DS);
			pst.setDouble(5,EXM);
			pst.setDouble(6,VueSaisieNoteMatiere.moyenne);
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
	
	public static int getIdEtudiantForRecordCount()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		int idEt = 0;
		String matriculeEtudiant = VueSaisieNoteMatiere.dcmEtudiant.getSelectedItem().toString();
		String requete = "select id from etudiant where matricule = '"+ matriculeEtudiant +"' ";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			
			while(rs.next())
			{
				idEt = Integer.parseInt(rs.getString("id"));
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return idEt;
	}
	
	public static int getIdMatiereForRecordCount()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		int idMat = 0;
		String matiere = VueSaisieNoteMatiere.dcmMatiere.getSelectedItem().toString();
		String requete = "select id from matiere where nom = '"+ matiere +"' ";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				idMat = Integer.parseInt(rs.getString("id"));
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return idMat;
	}
	
	public static boolean getRecordCountEtuMat(int idEt, int idMat)
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		boolean record= false;
		
		String requete ="select * from note where id_etudiant = '"+ idEt +"' and id_matiere = '"+ idMat +"' ";
		
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
	
	public static ResultSet AfficherNotes()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		ResultSet rs=null;
		
		String requete = "SELECT * from note";
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
	
	public static void deleteNoteFromTableNotes()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		try 
		{
			int id = VueTableNote.idNote;
			
			String requete = "delete from note where id = ? ";
			
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
