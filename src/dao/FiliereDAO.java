package dao;

import vue.DbConnexion;
import vue.VueAjoutEtudiant;
import vue.VueAjoutFiliere;
import vue.VueAssocierFiliereMatiere;
import vue.VueModifierClasse;
import vue.VueModifierEtudiant;
import vue.VueModifierFiliere;
import vue.VueSaisieNoteMatiere;
import vue.VueTableEtudiant;
import vue.VueTableFiliere;
import vue.VueAjoutClasse;
import java.sql.*;
import javax.swing.JTextField;

public class FiliereDAO 
{
	public static PreparedStatement pst;
	
	public static void addFiliere()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{
			String requete = "insert into filiere"
					   + " (nom) values"
					   + "(?)";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setString(1,VueAjoutFiliere.tprefixe.getText()+VueAjoutFiliere.tnom.getText());
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

	public static void getAllFilieresForGestionEtudiant()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String requete = "select nom from filiere where"
					   + " id in (select id_filiere "
					   + "from classe)";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAjoutEtudiant.defaultComboBoxModelFiliere.addElement(rs.getString("nom"));
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
	
	public static void getAllFilieresForGestionClasse()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String requete = "select nom from filiere";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAjoutClasse.defaultComboBoxModelFiliere.addElement(rs.getString("nom"));
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

	public static void getIdFiliereByName()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String nomFiliere = (String)VueAjoutClasse.defaultComboBoxModelFiliere.getSelectedItem();
		
		String requete = "select id from filiere where nom='"+ nomFiliere +"'";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAjoutClasse.defaultComboBoxModelId.addElement(rs.getInt("id"));
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
		String nomFiliere = VueAjoutFiliere.tprefixe.getText()+VueAjoutFiliere.tnom.getText();
		boolean record= false;
		
		String requete = "select * from filiere where nom= '"+ nomFiliere +"' ";
					   
		
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
	
	public static ResultSet AfficherFilieres()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		ResultSet rs=null;
		
		String requete = "SELECT * from filiere";
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
	
	public static void getAllFilieresForModifierClasse()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String requete = "select nom from filiere";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueModifierClasse.defaultComboBoxModelFiliere.addElement(rs.getString("nom"));
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
	
	public static void getIdFiliereByNameForModifierClasse()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String nomFiliere = (String)VueModifierClasse.defaultComboBoxModelFiliere.getSelectedItem();
		
		String requete = "select id from filiere where nom='"+ nomFiliere +"'";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueModifierClasse.defaultComboBoxModelFiliereId.addElement(rs.getInt("id"));
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
	
	public static void updateFiliere()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{
			String requete = "update filiere set nom = ? where id= ?";
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setString(1,VueModifierFiliere.tprefixe.getText()+VueModifierFiliere.tnom.getText());
			pst.setInt(2,Integer.parseInt(VueModifierFiliere.defaultComboBoxModelIdFiliere.getSelectedItem().toString()));
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
		
		String requete = "select id from filiere ";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueModifierFiliere.defaultComboBoxModelIdFiliere.addElement(rs.getString("id"));
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
	
	public static void getFiliereById()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String idFiliere = VueModifierFiliere.comboIdFiliere.getSelectedItem().toString();
		
		String requete = "select nom from filiere where id='"+ idFiliere +"' ";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueModifierFiliere.tnom.setText(rs.getString("nom"));
				
				if(rs.getString("nom").contains("Licence"))
				{
					VueModifierFiliere.tprefixe.setText("Licence--");
					VueModifierFiliere.comboType.setSelectedItem("Licence");
				}
				else if(rs.getString("nom").contains("Préparatoire"))
				{
					VueModifierFiliere.tprefixe.setText("Préparatoire--");
					VueModifierFiliere.comboType.setSelectedItem("Préparatoire");
				}
				else if(rs.getString("nom").contains("Génie"))
				{
					VueModifierFiliere.tprefixe.setText("Génie--");
					VueModifierFiliere.comboType.setSelectedItem("Ingénieurie");
				}
				else if(rs.getString("nom").contains("Master"))
				{
					VueModifierFiliere.tprefixe.setText("Master--");
					VueModifierFiliere.comboType.setSelectedItem("Master");
				}
				
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
	
	public static boolean getRecordCountForModification()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String nomFiliere = VueModifierFiliere.tprefixe.getText()+VueModifierFiliere.tnom.getText();
		int idFiliere = Integer.parseInt(VueModifierFiliere.defaultComboBoxModelIdFiliere.getSelectedItem().toString());
		boolean record= false;
		
		String requete = "select * from filiere where nom= '"+ nomFiliere +"' and id != '"+ idFiliere +"' ";
					   
		
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
	
	public static void getAllFilieresForModifierEtudiant()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String requete = "select nom from filiere where"
					   + " id in (select id_filiere "
					   + "from classe)";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueModifierEtudiant.defaultComboBoxModelFiliere.addElement(rs.getString("nom"));
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
	
	public static void deleteFiliere()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{
			int idFiliere = Integer.parseInt(VueModifierFiliere.defaultComboBoxModelIdFiliere.getSelectedItem().toString());
			
			String requete = "delete from filiere where id = ? ";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setInt(1,idFiliere);
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
	
	public static void getAllFilieresForSaisieNote()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String requete = "select nom from filiere where"
					   + " id in (select id_filiere "
					   + "from classe)";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueSaisieNoteMatiere.dcmFiliere.addElement(rs.getString("nom"));
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
	
	public static void deleteFiliereFromTableFilieres()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		try 
		{
			int id = VueTableFiliere.idFiliere ;
			
			String requete = "delete from filiere where id = ? ";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setInt(1,id);
			pst.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void getAllFilieresForAssociation()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String requete = "select nom from filiere";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAssocierFiliereMatiere.dcmFiliere.addElement(rs.getString("nom"));
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
	
	public static void getIdFiliereByNameForAssociationFM()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String nomFiliere = (String)VueAssocierFiliereMatiere.dcmFiliere.getSelectedItem();
		
		String requete = "select id from filiere where nom='"+ nomFiliere +"'";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAssocierFiliereMatiere.tidF.setText(rs.getString("id"));
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
