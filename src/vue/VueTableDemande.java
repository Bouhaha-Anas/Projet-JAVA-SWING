package vue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import dao.DemandeDAO;
import tableModel.TableModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import net.miginfocom.swing.MigLayout;


public class VueTableDemande extends JPanel
{
	JTable tableDemandeNonApp = new JTable() , tableDemandeApp = new JTable(), tableDemandeIgn = new JTable();
	JScrollPane jScrollPaneNonApp = new JScrollPane(), jScrollPaneApp = new JScrollPane(), jScrollPaneIgn = new JScrollPane();
	private JButton supprimer = new JButton("Supprimer");
	private JPanel panelNonApprouvee = new JPanel(), panelApprouvee = new JPanel(),panelIgnoree = new JPanel();
	public static int idDemande;
	
	public VueTableDemande()
	{
		//Table Demande non approuvées
		TableModel tableModelDemandeNonApp = new TableModel(DemandeDAO.AfficherDemandesNonApprouves());
		setLayout(new MigLayout("", "[850px]", "[260px][260px][260px]"));
		tableDemandeNonApp.setModel(tableModelDemandeNonApp);
		tableDemandeNonApp.setFillsViewportHeight(true);
		tableDemandeNonApp.setPreferredScrollableViewportSize(new Dimension(800,200));
		
		panelNonApprouvee.setBorder(BorderFactory.createTitledBorder("Demandes en cours "));
		jScrollPaneNonApp.setViewportView(tableDemandeNonApp);
		panelNonApprouvee.add(jScrollPaneNonApp);
		this.add(panelNonApprouvee, "cell 0 0,alignx left,aligny top");
		
	
		//Table Demande approuvées
		TableModel tableModelDemandeApp = new TableModel(DemandeDAO.AfficherDemandesApprouvees());
		tableDemandeApp.setModel(tableModelDemandeApp);
		tableDemandeApp.setFillsViewportHeight(true);
		tableDemandeApp.setPreferredScrollableViewportSize(new Dimension(800,200));
		
		panelApprouvee.setBorder(BorderFactory.createTitledBorder("Demandes Approuvées "));
		jScrollPaneApp.setViewportView(tableDemandeApp);
		panelApprouvee.add(jScrollPaneApp);
		this.add(panelApprouvee, "cell 0 1,alignx left,aligny top");
		
		//Table Demande ignorées
		TableModel tableModelDemandeIgn = new TableModel(DemandeDAO.AfficherDemandesIgnorees());
		tableDemandeIgn.setModel(tableModelDemandeIgn);
		tableDemandeIgn.setFillsViewportHeight(true);
		tableDemandeIgn.setPreferredScrollableViewportSize(new Dimension(800,200));
		
		panelIgnoree.setBorder(BorderFactory.createTitledBorder("Demandes Ignorées "));
		jScrollPaneIgn.setViewportView(tableDemandeIgn);
		panelIgnoree.add(jScrollPaneIgn);
		this.add(panelIgnoree, "cell 0 2,alignx left,aligny top");
		
		
}
	
	
}	