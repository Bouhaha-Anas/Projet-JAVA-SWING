package dao;

import vue.DbConnexion;
import vue.VueAjoutMatiere;
import vue.VueAssocierFiliereMatiere;
import vue.VueAssocierProfesseurMatiere;
import vue.VueModifierMatiere;
import vue.VueTableMatiere;
import java.sql.*;


public class MatiereDAO 
{
	public static PreparedStatement pst;
	
	public static void addMatiere()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{
			String requete = "insert into matiere"
					   + " (nom, coefficient,semestre) values"
					   + "(?,?,?)";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			String nom = VueAjoutMatiere.tnom.getText();
			double coefficient = Double.parseDouble(VueAjoutMatiere.tcoefficient.getText());
			String semestre = VueAjoutMatiere.comboSemestre.getSelectedItem().toString();
			
			pst.setString(1,nom);
			pst.setDouble(2,coefficient);
			pst.setString(3,semestre);
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
	
	public static boolean getRecordCount(String nomMat)
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String nom = VueAjoutMatiere.tnom.getText();
		boolean record= false;
			
		String requete ="select * from matiere where nom='"+ nom +"'";
		
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
	
	public static ResultSet AfficherMatieres()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		ResultSet rs=null;
		
		String requete = "SELECT * from matiere";
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
	
	public static void updateMatiere()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{
			String requete = "update matiere set nom =? , coefficient =?, semestre=? "
					+ "where id =? ";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			String nom = VueModifierMatiere.tnom.getText();
			double coefficient = Double.parseDouble(VueModifierMatiere.tcoefficient.getText());
			int id = Integer.parseInt(VueModifierMatiere.defaultComboBoxModelId.getSelectedItem().toString());
			String semestre = VueModifierMatiere.comboSemestre.getSelectedItem().toString();
			
			pst.setString(1,nom);
			pst.setDouble(2,coefficient);
			pst.setString(3,semestre);
			pst.setInt(4,id);
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
		
		String requete = "select id from matiere ";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueModifierMatiere.defaultComboBoxModelId.addElement(rs.getString("id"));
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
	
	public static void getMatiereById()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String idMatiere = VueModifierMatiere.comboId.getSelectedItem().toString();
		
		String requete = "select nom, coefficient from matiere where id='"+ idMatiere +"' ";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueModifierMatiere.tnom.setText(rs.getString("nom"));
				VueModifierMatiere.tcoefficient.setText(rs.getString("coefficient"));
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
	
	public static boolean getRecordCountForModifier(String nomMat)
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String nom = VueModifierMatiere.tnom.getText();
		int idMatiere = Integer.parseInt(VueModifierMatiere.defaultComboBoxModelId.getSelectedItem().toString());
		boolean record= false;
			
		String requete ="select * from matiere where nom='"+ nom +"' and id != '"+ idMatiere +"' ";
		
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
	
	public static void deleteMatiere()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{
			int idMatiere= Integer.parseInt(VueModifierMatiere.defaultComboBoxModelId.getSelectedItem().toString());
			
			String requete = "delete from matiere where id = ? ";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setInt(1,idMatiere);
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
	
	public static void deleteMatiereFromTableMatieres()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		try 
		{
			int id = VueTableMatiere.idMatiere;
			
			String requete = "delete from matiere where id = ? ";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setInt(1,id);
			pst.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void getAllMatieresForAssociation()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String requete = "select nom from matiere";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAssocierFiliereMatiere.dcmMatiere.addElement(rs.getString("nom"));
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
	
	public static void getIdMatiereByNameForAssociationMF()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String nomMatiere = (String)VueAssocierFiliereMatiere.dcmMatiere.getSelectedItem();
		
		String requete = "select id from matiere where nom='"+ nomMatiere +"'";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAssocierFiliereMatiere.tidM.setText(rs.getString("id"));
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
	
	public static void getIdMatiereByNameForAssociationMP()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String nomMatiere = (String)VueAssocierProfesseurMatiere.dcmMatiere.getSelectedItem();
		
		String requete = "select id from matiere where nom='"+ nomMatiere +"'";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAssocierProfesseurMatiere.tidM.setText(rs.getString("id"));
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
	
	public static void getAllMatieresForAssociationMP()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String requete = "select nom from matiere";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAssocierProfesseurMatiere.dcmMatiere.addElement(rs.getString("nom"));
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
