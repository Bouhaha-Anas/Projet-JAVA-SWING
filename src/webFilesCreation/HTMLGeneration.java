package webFilesCreation;

import java.io.BufferedWriter;
import java.io.FileWriter;

import vue.VueAjoutDemande;

public class HTMLGeneration 
{
	public static String nomPrenom;
	public static String nom;
	public static String prenom;
	public static String dateNaissance;
	public static String ville;
	public static String cin;
	public static String niveau;
	public static String filiere;
	public static String matricule;
	
	public static void HTMLCertificat()
	{
		
		
		FileWriter fWriter = null;
		BufferedWriter writer = null;
		
		try {
		    fWriter = new FileWriter("D:/"+ nomPrenom +".html");
		    writer = new BufferedWriter(fWriter);
		    writer.write("<!DOCTYPE html>");
		    writer.newLine();
		    writer.write("<html>");
		    writer.newLine();
		    writer.write("<head>");
		    writer.newLine();
		    writer.write("<meta charset='UTF-8'>");
		    writer.newLine();
		    writer.write("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
		    writer.newLine();
		    writer.write("<meta http-equiv='X-UA-Compatible' content='ie=edge'>");
		    writer.newLine();
		    writer.write("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u' crossorigin='anonymous'>");
		    writer.newLine();
		    writer.write("<style>");
		    writer.newLine();
		    writer.write("hr{");
		    writer.newLine();
		    writer.write("border-width: 3px;");
		    writer.newLine();
		    writer.write("border-style: double;");
		    writer.newLine();
		    writer.write("}");
		    writer.newLine();
		    writer.write("</style>");
		    writer.newLine();
		    writer.write("<title>Certificat de présence</title>");
		    writer.newLine();
		    writer.write("</head>");
		    writer.newLine();
		    writer.write("<body>");
		    writer.newLine();
		    writer.write("<div class='row'>");
		    writer.newLine();
		    writer.write("<div class='col-sm-4'>");
		    writer.newLine();
		    writer.write("<br/>");
		    writer.newLine();
		    writer.write("<p>");
		    writer.newLine();
		    writer.write("<strong style='padding-left: 90px'>République Tunisienne</strong><br>");
		    writer.newLine();
		    writer.write("<strong style='padding-left: 150px'>******</strong><br>");
		    writer.newLine();
		    writer.write("<strong style='padding-left: 40px'>Ministère de l'Enseignement Supérieur</strong><br>");
		    writer.newLine();
		    writer.write("<strong style='padding-left: 60px'>et de la Recherche Scientifique</strong><br>");
		    writer.newLine();
		    writer.write("<strong style='padding-left: 150px'>******</strong><br>");
		    writer.newLine();
		    writer.write("<strong style='padding-left: 90px'>Univeristé de Sousse</strong>");
		    writer.newLine();
		    writer.write("</p>");
		    writer.newLine();
		    writer.write("</div>");
		    writer.newLine();
		    writer.write("<div class='col-sm-4'>");
		    writer.newLine();
		    writer.write("<br/>");
		    writer.newLine();
		    writer.write("<img style='padding-left: 100px' src='../img/EPI.png'>");
		    writer.newLine();
		    writer.write("</div>");
		    writer.newLine();
		    writer.write("<div class='col-sm-4'></div>");
		    writer.newLine();
		    writer.write("</div>");
		    writer.newLine();
		    writer.write("<div class='row'>");
		    writer.newLine();
		    writer.write("<div class='col-sm-4'></div>");
		    writer.newLine();
		    writer.write("<div class='col-sm-4'>");
		    writer.newLine();
		    writer.write("<h2 style='padding-left: 50px'><strong><u>Certificat de présence</u></strong></h2>");
		    writer.newLine();
		    writer.write("</div>");
		    writer.newLine();
		    writer.write("<div class='col-sm-4'></div>");
		    writer.newLine();
		    writer.write("</div>");
		    writer.newLine();
		    writer.write("<div class='row'>");
		    writer.newLine();
		    writer.write("<div class='col-sm-4'></div>");
		    writer.newLine();
		    writer.write("<div class='col-sm-4'>");
		    writer.newLine();
		    writer.write("<h2><strong style='padding-left: 130px'>2017-2018</strong></h2>");
		    writer.newLine();
		    writer.write("</div>");
		    writer.newLine();
		    writer.write("<div class='col-sm-4'></div>");
		    writer.newLine();
		    writer.write("</div>");
		    writer.newLine();
		    writer.write("<br>");
		    writer.newLine();
		    writer.write("<p style='padding-left: 120px'>");
		    writer.newLine();
		    writer.write("Le secrétaire général de L'Ecole Pluridisciplinaire Internationale de Sousse");
		    writer.newLine();
		    writer.write("</p>");
		    writer.newLine();
		    writer.write("<p style='padding-left: 80px'>");
		    writer.newLine();
		    writer.write("soussigné, certifie que l'étudiant :");
		    writer.newLine();
		    writer.write("</p>");
		    writer.newLine();
		    writer.write("<p style='padding-left: 156px'>");
		    writer.newLine();
		    writer.write("Nom : "+nom);
		    writer.newLine();
		    writer.write("</p>");
		    writer.newLine();
		    writer.write("<p style='padding-left: 156px'>");
		    writer.newLine();
		    writer.write("Prénom : "+prenom);
		    writer.newLine();
		    writer.write("</p>");
		    writer.newLine();
		    writer.write("<p style='padding-left: 156px'>");
		    writer.newLine();
		    writer.write("Né le : "+dateNaissance+" à : "+ville);
		    writer.newLine();
		    writer.write("</p>");
		    writer.newLine();
		    writer.write("<p style='padding-left: 156px'>");
		    writer.newLine();
		    writer.write("Titulaire de la Carte d'Identité Nationale N° : "+cin);
		    writer.newLine();
		    writer.write("</p>");
		    writer.newLine();
		    writer.write("<p style='padding-left: 80px'>");
		    writer.newLine();
		    writer.write("est inscrit en : "+niveau);
		    writer.newLine();
		    writer.write("</p>");
		    writer.newLine();
		    writer.write("<p style='padding-left: 80px'>");
		    writer.newLine();
		    writer.write("Filière : "+filiere);
		    writer.newLine();
		    writer.write("</p>");
		    writer.newLine();
		    writer.write("<p style='padding-left: 80px'>");
		    writer.newLine();
		    writer.write("sous le matricule : "+matricule);
		    writer.newLine();
		    writer.write("</p>");
		    writer.newLine();
		    writer.write("<p style='padding-left: 80px'>");
		    writer.newLine();
		    writer.write("Pour l'année universitaire 2017/2018");
		    writer.newLine();
		    writer.write("</p>");
		    writer.newLine();
		    writer.write("<p style='padding-left: 80px'>");
		    writer.newLine();
		    writer.write("Le présent certificat est délivré à la demande de l'intéressé pour servir et valoir ce que de droit.");
		    writer.newLine();
		    writer.write("</p>");
		    writer.newLine();
		    writer.write("<br><br>");
		    writer.newLine();
		    writer.write("<p style='padding-left: 420px'>");
		    writer.newLine();
		    writer.write("<strong>Le secrétaire général</strong>");
		    writer.newLine();
		    writer.write("</p>");
		    writer.newLine();
		    writer.write("<p style='padding-left: 450px'>");
		    writer.newLine();
		    writer.write("<strong>Zaied Amira</strong>");
		    writer.newLine();
		    writer.write("</p>");
		    writer.newLine();
		    writer.write("<hr>");
		    writer.newLine();
		    writer.write("<p style='padding-left: 500px'>");
		    writer.newLine();
		    writer.write("<strong>Adresse : Route de Ceinture 4021, Sahloul Sousse</strong>");
		    writer.newLine();
		    writer.write("</p>");
		    writer.newLine();
		    writer.write("<p style='padding-left: 400px'>");
		    writer.newLine();
		    writer.write("<strong>Tél : (+216) 73 296 060/272 - Fax : (+216) 73 296 900 - Email : contact@episousse.com</strong>");
		    writer.newLine();
		    writer.write("</p>");
		    writer.newLine();
		    writer.write("</body>");
		    writer.newLine();
		    writer.write("</html>");
		    
		    writer.close(); 
		} catch (Exception e) 
		{
			e.getStackTrace();
		}
	}
	
	public static void HTMLPermutation()
	{
		
	}
}
