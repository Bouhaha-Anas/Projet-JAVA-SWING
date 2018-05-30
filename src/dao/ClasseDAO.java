package dao;

import vue.Authentification;
import vue.DbConnexion;
import vue.VueAjoutClasse;
import vue.VueAjoutEtudiant;
import vue.VueModifierClasse;
import vue.VueModifierEtudiant;
import vue.VueSaisieNoteMatiere;
import vue.VueTableClasse;
import vue.VueTableNote;

import java.sql.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ClasseDAO 
{
	public static PreparedStatement pst;
	
	public static void addClasse()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{
			String requete = "insert into classe"
					   + " (niveau, groupe, id_filiere) values"
					   + "(?,?,?)";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setString(1,VueAjoutClasse.comboNiveau.getSelectedItem().toString());
			pst.setString(2,VueAjoutClasse.tgroupe.getText());
			pst.setInt(3,(Integer)VueAjoutClasse.defaultComboBoxModelId.getSelectedItem());
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
	
	public static void getClassesByFiliere()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String nomFiliere = (String)VueAjoutEtudiant.defaultComboBoxModelFiliere.getSelectedItem();
		
		String requete = "select distinct niveau from classe C,filiere F "
					   + "where C.id_filiere= F.id and F.nom='"+ nomFiliere +"'";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAjoutEtudiant.defaultComboBoxModelClasse.addElement(rs.getString("niveau"));
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
	
	public static void getGroupeClasseByNiveau()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String niveau = (String)VueAjoutEtudiant.defaultComboBoxModelClasse.getSelectedItem();
		String nomFiliere = (String)VueAjoutEtudiant.defaultComboBoxModelFiliere.getSelectedItem();
		
		String requete = "select C.groupe from classe C, filiere F"
					   + " where C.id_filiere = F.id and C.niveau='"+ niveau +"' and F.nom='"+ nomFiliere +"'";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAjoutEtudiant.defaultComboBoxModelGroupe.addElement(rs.getString("C.groupe"));
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
	
	public static void getIdClasseByGroupe()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String niveau = (String)VueAjoutEtudiant.defaultComboBoxModelClasse.getSelectedItem();
		String nomFiliere = (String)VueAjoutEtudiant.defaultComboBoxModelFiliere.getSelectedItem();
		String groupe = (String)VueAjoutEtudiant.defaultComboBoxModelGroupe.getSelectedItem();
		
		String requete = "select C.id from classe C, filiere F "
					   + "where C.id_filiere = F.id and C.groupe='"+ groupe +"' and C.niveau='"+ niveau +"' and F.nom='"+ nomFiliere +"' ";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAjoutEtudiant.defaultComboBoxModelId.addElement(rs.getInt("id"));
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
		String nomFiliere = (String)VueAjoutClasse.defaultComboBoxModelFiliere.getSelectedItem();
		String nomGroupe = VueAjoutClasse.tgroupe.getText();
		String niveau = (String)VueAjoutClasse.comboNiveau.getSelectedItem();
		boolean record= false;
		
		String requete = "select * from classe C,filiere F "
					   + "where C.id_filiere= F.id "
					   + "and F.nom='"+ nomFiliere +"'"
					   + "and C.niveau='"+ niveau +"'"
					   + "and C.Groupe='"+ nomGroupe +"'";
		
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
	
	public static ResultSet AfficherClasses()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		ResultSet rs=null;
		
		String requete = "SELECT * from classe";
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
	
	public static void updateClasse()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{
			String requete = "update classe set niveau = ? , groupe = ? , id_filiere= ?  where id= ?";
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setString(1,VueModifierClasse.comboNiveau.getSelectedItem().toString());
			pst.setString(2,VueModifierClasse.tgroupe.getText());
			pst.setInt(3,Integer.parseInt(VueModifierClasse.defaultComboBoxModelFiliereId.getSelectedItem().toString()));
			pst.setInt(4,Integer.parseInt(VueModifierClasse.defaultComboBoxAllIds.getSelectedItem().toString()));
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
		
		String requete = "select id from classe ";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueModifierClasse.defaultComboBoxAllIds.addElement(rs.getString("id"));
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
	
	public static void getClasseById()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		int id_CLasse = Integer.parseInt(VueModifierClasse.defaultComboBoxAllIds.getSelectedItem().toString());
		
		String requete = "select C.niveau, C.groupe, C.id_filiere,F.nom from classe C, filiere F"
				+ " where C.id_filiere = F.id"
				+ " and C.id='"+id_CLasse+"' ";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			if(rs.next())
			{
				VueModifierClasse.tgroupe.setText(rs.getString("C.groupe"));
				VueModifierClasse.defaultComboBoxModelFiliereId.addElement(rs.getString("C.id_filiere"));
				VueModifierClasse.defaultComboBoxModelFiliere.setSelectedItem(rs.getString("F.nom"));
				VueModifierClasse.comboNiveau.setSelectedItem(rs.getString("C.niveau"));
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
	
	public static boolean getRecordCountForModificationClasse()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String nomFiliere = (String)VueModifierClasse.defaultComboBoxModelFiliere.getSelectedItem();
		String nomGroupe = VueModifierClasse.tgroupe.getText();
		String niveau = (String)VueModifierClasse.comboNiveau.getSelectedItem();
		boolean record= false;
		
		String requete = "select * from classe C,filiere F "
					   + "where C.id_filiere= F.id "
					   + "and F.nom='"+ nomFiliere +"'"
					   + "and C.niveau='"+ niveau +"'"
					   + "and C.Groupe='"+ nomGroupe +"'";
		
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
	
	public static void getClassesByFiliereForModificationEtudiant()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String nomFiliere = (String)VueModifierEtudiant.defaultComboBoxModelFiliere.getSelectedItem();
		
		String requete = "select distinct niveau from classe C,filiere F "
					   + "where C.id_filiere= F.id and F.nom='"+ nomFiliere +"'";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueModifierEtudiant.defaultComboBoxModelClasse.addElement(rs.getString("niveau"));
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
	
	public static void getGroupeClasseByNiveauForModificationEtudiant()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String niveau = (String)VueModifierEtudiant.defaultComboBoxModelClasse.getSelectedItem();
		String nomFiliere = (String)VueModifierEtudiant.defaultComboBoxModelFiliere.getSelectedItem();
		
		String requete = "select groupe from classe C, filiere F"
					   + " where C.id_filiere = F.id and C.niveau='"+ niveau +"' and F.nom='"+ nomFiliere +"'";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueModifierEtudiant.defaultComboBoxModelGroupe.addElement(rs.getString("groupe"));
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
	
	public static void getIdClasseByGroupeForModificationEtudiant()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String niveau = (String)VueModifierEtudiant.defaultComboBoxModelClasse.getSelectedItem();
		String nomFiliere = (String)VueModifierEtudiant.defaultComboBoxModelFiliere.getSelectedItem();
		String groupe = (String)VueModifierEtudiant.defaultComboBoxModelGroupe.getSelectedItem();
		
		String requete = "select C.id from classe C, filiere F "
					   + "where C.id_filiere = F.id and C.groupe='"+ groupe +"' and C.niveau='"+ niveau +"' and F.nom='"+ nomFiliere +"' ";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueModifierEtudiant.defaultComboBoxModelId.addElement(rs.getInt("id"));
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
	
	public static void deleteClasse()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{
			int idClasse= Integer.parseInt(VueModifierClasse.defaultComboBoxAllIds.getSelectedItem().toString());
			
			String requete = "delete from classe where id = ? ";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setInt(1,idClasse);
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
	
	public static void getClassesByFiliereForSaisieNote()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String nomFiliere = (String)VueSaisieNoteMatiere.dcmFiliere.getSelectedItem();
		
		String requete = "select distinct niveau from classe C,filiere F "
					   + "where C.id_filiere= F.id and F.nom='"+ nomFiliere +"'";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueSaisieNoteMatiere.dcmClasse.addElement(rs.getString("niveau"));
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
	
	public static void getGroupeClasseByNiveauForSaisieNote()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String niveau = (String)VueSaisieNoteMatiere.dcmClasse.getSelectedItem();
		String nomFiliere = (String)VueSaisieNoteMatiere.dcmFiliere.getSelectedItem();
		
		String requete = "select C.groupe from classe C, filiere F"
					   + " where C.id_filiere = F.id and C.niveau='"+ niveau +"' and F.nom='"+ nomFiliere +"'";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueSaisieNoteMatiere.dcmGroupe.addElement(rs.getString("C.groupe"));
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
	
	public static void deleteClasseFromTableClasses()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		try 
		{
			int id = VueTableClasse.idClasse;
			
			String requete = "delete from classe where id = ? ";
			
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