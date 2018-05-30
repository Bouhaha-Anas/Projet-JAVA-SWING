package dao;

import vue.DbConnexion;
import vue.VueAjoutClasse;
import vue.VueAjoutDemande;
import vue.VueAjoutEtudiant;
import vue.VueAjoutReclamation;
import vue.VueModifierClasse;
import vue.VueModifierEtudiant;
import vue.VueModifierFiliere;
import vue.VueModifierMatiere;
import vue.VueSaisieNoteMatiere;
import vue.VueTableClasse;
import vue.VueTableDemandeDirecteur;
import vue.VueTableEtudiant;
import webFilesCreation.HTMLGeneration;

import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class EtudiantDAO 
{
	public static PreparedStatement pst;
	
	public static void addEtudiant()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{
			String requete = "insert into etudiant"
					   + " (nom,prenom,date_de_naissance,adresse,telephone,adresse_mail,cin,ville,sexe,matricule,id_classe) values"
					   + "(?,?,?,?,?,?,?,?,?,?,?)";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			String annee = VueAjoutEtudiant.comboYear.getSelectedItem().toString();
			String mois = VueAjoutEtudiant.comboMonth.getSelectedItem().toString();
			String jour = VueAjoutEtudiant.comboJour.getSelectedItem().toString();
			String matricule = VueAjoutEtudiant.tmatricule.getText();
			
			pst.setString(1,VueAjoutEtudiant.tnom.getText());
			pst.setString(2,VueAjoutEtudiant.tprenom.getText());
			pst.setString(3,annee+"/"+mois+"/"+jour);
			pst.setString(4,VueAjoutEtudiant.tadresse.getText());
			pst.setInt(5,Integer.parseInt(VueAjoutEtudiant.ttelephone.getText()));
			pst.setString(6,VueAjoutEtudiant.tmail.getText());
			pst.setInt(7,Integer.parseInt(VueAjoutEtudiant.tcin.getText()));
			pst.setString(8,VueAjoutEtudiant.comboVille.getSelectedItem().toString());
			pst.setString(9,VueAjoutEtudiant.comboSexe.getSelectedItem().toString());
			pst.setString(10,matricule);
			pst.setInt(11,(Integer)VueAjoutEtudiant.defaultComboBoxModelId.getSelectedItem());
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
	
	public static boolean getRecordCount(String matricule)
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		boolean record= false;
		
		String requete ="select * from etudiant where matricule='"+ matricule +"'";
		
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
	
	public static ResultSet AfficherEtudiants()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		ResultSet rs=null;
		
		String requete = "SELECT * from etudiant";
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
	
	public static void updateEtudiant()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{
			
			String requete = "update etudiant"
						   + " set nom =?, prenom =?, date_de_naissance =?, adresse =?, telephone =?, adresse_mail =?, cin =?, ville =?, sexe =? ,matricule =?, id_classe =?"
						   + " where matricule =? ";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			String annee = VueModifierEtudiant.comboYear.getSelectedItem().toString();
			String mois = VueModifierEtudiant.comboMonth.getSelectedItem().toString();
			String jour = VueModifierEtudiant.comboJour.getSelectedItem().toString();
			String matricule = VueModifierEtudiant.tmatricule.getText();
			String adresse = VueModifierEtudiant.tadresse.getText();
			String nom = VueModifierEtudiant.tnom.getText();
			String prenom = VueModifierEtudiant.tprenom.getText();
			String mail = VueModifierEtudiant.tmail.getText();
			String sexe = VueModifierEtudiant.comboSexe.getSelectedItem().toString();
			String ville = VueModifierEtudiant.comboVille.getSelectedItem().toString();
			String matriculeCombo = VueModifierEtudiant.defaultComboBoxModelMatricule.getSelectedItem().toString();
			int telephone = Integer.parseInt(VueModifierEtudiant.ttelephone.getText());
			int cin = Integer.parseInt(VueModifierEtudiant.tcin.getText());
			int id_Classe = Integer.parseInt(VueModifierEtudiant.defaultComboBoxModelId.getSelectedItem().toString());
			
			pst.setString(1,nom);
			pst.setString(2,prenom);
			pst.setString(3,annee+"/"+mois+"/"+jour);
			pst.setString(4,adresse);
			pst.setInt(5,telephone);
			pst.setString(6,mail);
			pst.setInt(7,cin);
			pst.setString(8,ville);
			pst.setString(9,sexe);
			pst.setString(10,matricule);
			pst.setInt(11,id_Classe);
			pst.setString(12,matriculeCombo);
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
	
	public static void getAllMatricules()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		String requete = "select matricule from etudiant ";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueModifierEtudiant.defaultComboBoxModelMatricule.addElement(rs.getString("matricule"));
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

	public static void getEtudiantByMatricule()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String matricule = VueModifierEtudiant.defaultComboBoxModelMatricule.getSelectedItem().toString();
		
		String requete ="select E.id, E.nom, E.prenom, E.sexe, E.adresse, E.ville, E.cin, E.telephone, E.adresse_mail, E.id_classe, C.niveau, C.groupe, F.nom from etudiant E, classe C, filiere F where E.id_classe = C.id and C.id_filiere = F.id and matricule='"+ matricule +"'";
				
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			if(rs.next())
			{
				VueModifierEtudiant.tnom.setText(rs.getString("E.nom"));
				VueModifierEtudiant.tprenom.setText(rs.getString("E.prenom"));
				VueModifierEtudiant.comboSexe.setSelectedItem(rs.getString("E.sexe"));
				VueModifierEtudiant.tadresse.setText(rs.getString("E.adresse"));
				VueModifierEtudiant.comboVille.setSelectedItem(rs.getString("E.ville"));
				VueModifierEtudiant.tcin.setText(rs.getString("E.cin"));
				VueModifierEtudiant.ttelephone.setText(rs.getString("E.telephone"));
				VueModifierEtudiant.tmail.setText(rs.getString("E.adresse_mail"));
				VueModifierEtudiant.defaultComboBoxModelId.addElement(rs.getString("E.id_classe"));
				VueModifierEtudiant.comboFiliere.setSelectedItem(rs.getString("F.nom"));
				VueModifierEtudiant.comboClasse.setSelectedItem(rs.getString("C.niveau"));
				VueModifierEtudiant.defaultComboBoxModelGroupe.addElement(rs.getString("C.groupe"));
				VueModifierEtudiant.defaultComboBoxModelIdEtudiant.addElement(rs.getString("E.id"));
				
				
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

	public static boolean getRecordCountForModificationEtudiant(String matricule)
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		boolean record= false;
		int idEtudiant = Integer.parseInt(VueModifierEtudiant.defaultComboBoxModelIdEtudiant.getSelectedItem().toString());
		
		String requete ="select * from etudiant where matricule='"+ matricule +"' and id != '"+ idEtudiant +"'";
		
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
	
	public static void getIdEtudiantByMatricule()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		String matricule = (String)VueModifierEtudiant.defaultComboBoxModelMatricule.getSelectedItem();
		
		String requete = "select id from etudiant where matricule='"+ matricule +"'";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueModifierEtudiant.defaultComboBoxModelIdEtudiant.addElement(rs.getInt("id"));
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
	
	public static void deleteEtudiant()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		try 
		{
			int idEtudiant= Integer.parseInt(VueModifierEtudiant.defaultComboBoxModelIdEtudiant.getSelectedItem().toString());
			
			String requete = "delete from etudiant where id = ? ";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setInt(1,idEtudiant);
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
	
	public static void getAllMatriculesForDemande()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		String requete = "select matricule from etudiant ";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAjoutDemande.defaultComboBoxModelMat1.addElement(rs.getString("matricule"));
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
	
	public static void getInformationForDemandeCertificat()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		String matriculeEtudiant = VueAjoutDemande.defaultComboBoxModelMat1.getSelectedItem().toString();
		
		String requete = "select E.id,E.nom,E.prenom,E.date_de_naissance,E.cin,E.ville,C.niveau, C.groupe, F.nom from etudiant E,classe C, filiere F "
				+ "where E.id_classe = C.id and C.id_filiere = F.id "
				+ "and matricule = '"+ matriculeEtudiant +"' ";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAjoutDemande.tId1.setText(rs.getString("E.id"));
				VueAjoutDemande.tnom1.setText(rs.getString("E.nom"));
				VueAjoutDemande.tprenom1.setText(rs.getString("E.prenom"));
				VueAjoutDemande.tfiliere.setText(rs.getString("F.nom"));
				VueAjoutDemande.tniveau.setText(rs.getString("C.niveau"));
				VueAjoutDemande.tdate.setText(rs.getString("E.date_de_naissance"));
				VueAjoutDemande.tville.setText(rs.getString("E.ville"));
				VueAjoutDemande.tgroupe.setText(rs.getString("C.groupe"));
				VueAjoutDemande.tcin.setText(rs.getString("E.cin"));
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
	
	public static void getSecondMatriculesForDemandePermutation()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		String nomFiliere = VueAjoutDemande.tfiliere.getText();
		String niveau = VueAjoutDemande.tniveau.getText();
		String groupe = VueAjoutDemande.tgroupe.getText();
		
		String requete = "select E.matricule from etudiant E, classe C, filiere F "
				+ "where E.id_classe = C.id and C.id_filiere = F.id "
				+ "and F.nom = '"+ nomFiliere +"' and C.niveau = '"+ niveau +"' and C.groupe != '"+ groupe +"' ";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAjoutDemande.defaultComboBoxModelMat2.addElement(rs.getString("E.matricule"));
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
	
	public static void getInformationByMatricule2()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		String matriculeEtudiant = VueAjoutDemande.defaultComboBoxModelMat2.getSelectedItem().toString();
		
		String requete = "select E.id,E.nom,E.prenom,C.groupe from etudiant E,classe C "
				+ "where E.id_classe = C.id "
				+ "and matricule = '"+ matriculeEtudiant +"' ";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAjoutDemande.tId2.setText(rs.getString("E.id"));
				VueAjoutDemande.tnom2.setText(rs.getString("E.nom"));
				VueAjoutDemande.tprenom2.setText(rs.getString("E.prenom"));
				VueAjoutDemande.tgroupe2.setText(rs.getString("C.groupe"));
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
	
	public static void getAllMatriculesForSaisieNote()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		String filiere = VueSaisieNoteMatiere.dcmFiliere.getSelectedItem().toString();
		String niveau = VueSaisieNoteMatiere.dcmClasse.getSelectedItem().toString();
		String groupe = VueSaisieNoteMatiere.dcmGroupe.getSelectedItem().toString();
		
		String requete = "select E.matricule from etudiant E, classe C, filiere F "
				+ "where E.id_classe = C.id and C.id_filiere = F.id "
				+ "and C.niveau = '"+ niveau +"' and C.groupe ='"+ groupe +"' and F.nom = '"+ filiere +"' ";
		
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueSaisieNoteMatiere.dcmEtudiant.addElement(rs.getString("matricule"));
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
	
	public static void deleteEtudiantFromTableEtudiants()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		try 
		{
			int id = VueTableEtudiant.idEtudiant;
			
			String requete = "delete from etudiant where id = ? ";
			
			pst = DbConnexion.con.prepareStatement(requete);
			
			pst.setInt(1,id);
			pst.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void getNomPrenomEtudiantForReclamation()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		String requete = "select matricule from etudiant ";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAjoutReclamation.dcmMatricule.addElement( rs.getString("matricule"));
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
	
	public static void getInfoEtudiantForReclamation()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		String matricule = VueAjoutReclamation.dcmMatricule.getSelectedItem().toString();
		
		String requete = "select E.id, F.nom, C.niveau, C.groupe from etudiant E, classe C, filiere F "
					   + "where E.id_classe = C.id and C.id_filiere = F.id "
					   + "and E.matricule = '"+ matricule +"' ";
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			while(rs.next())
			{
				VueAjoutReclamation.tfiliere.setText(rs.getString("F.nom"));
				VueAjoutReclamation.tclasse.setText( rs.getString("C.niveau")+ " -- " + rs.getString("C.groupe") );
				VueAjoutReclamation.tId.setText(rs.getString("E.id"));
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
	
	public static void getEtudiantByIdForHTMLCertificat()
	{
		DbConnexion.loadDriver();
		DbConnexion.connect();
		
		String requete ="select E.nom, E.prenom, E.date_de_naissance, E.ville, E.cin, E.matricule, C.niveau, F.nom "
					  + "from etudiant E, classe C, filiere F "
					  + "where E.id_classe = C.id and C.id_filiere = F.id "
					  + "and E.id = '"+ VueTableDemandeDirecteur.idEtudiant +"' ";
				
		ResultSet rs=null;
		try
		{
			rs = DbConnexion.st.executeQuery(requete);
			if(rs.next())
			{
				HTMLGeneration.nomPrenom = rs.getString("E.nom") + " " + rs.getString("E.prenom");
				HTMLGeneration.nom = rs.getString("E.nom");
				HTMLGeneration.prenom = rs.getString("E.prenom");
				HTMLGeneration.dateNaissance = rs.getString("E.date_de_naissance");
				HTMLGeneration.ville = rs.getString("E.ville");
				HTMLGeneration.cin = rs.getString("E.cin");
				HTMLGeneration.matricule = rs.getString("E.matricule");
				HTMLGeneration.niveau = rs.getString("C.niveau");
				HTMLGeneration.filiere = rs.getString("F.nom");
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
