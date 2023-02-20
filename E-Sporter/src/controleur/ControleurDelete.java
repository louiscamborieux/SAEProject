package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import IHM.MainPanel;
import IHM.info.VueInfoPanel;
import IHM.modif.VueModifFrame;
import Object.Ecurie;
import Object.Equipe;
import Object.Joueur;
import Object.Tournoi;

public class ControleurDelete implements ActionListener{

	private JPanel vue;
	private Equipe eq;
	private Ecurie ec;
	private Tournoi t;
	private Joueur j;
	private ModeleESporter modele;
	private VueModifFrame v;
	private String nature;
	
	public ControleurDelete(Equipe eq,JPanel vue) {
		this.eq = eq;
		this.vue = vue;
		this.modele = new ModeleESporter();
		this.nature = "Equipe";
		
	}
	
	public ControleurDelete(Ecurie ec,JPanel vue) {
		this.ec = ec;
		this.vue = vue;
		this.modele = new ModeleESporter();
		this.nature = "Ecurie";
	}
	
	public ControleurDelete(Tournoi t,JPanel vue) {
		this.t = t;
		this.vue = vue;
		this.modele = new ModeleESporter();
		this.nature = "Tournoi";
		
	}
	
	public ControleurDelete(Joueur j,JPanel vue) {
		this.j = j;
		this.vue = vue;
		this.modele = new ModeleESporter();
		this.nature = "Joueur";
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b.getText().equals("Supprimer")) {
			
			int resultat = JOptionPane.showConfirmDialog(this.vue, "Êtes-vous sûr de vouloir vraiment supprimer ceci ?","Confirm", JOptionPane.YES_NO_OPTION);
			if (resultat == JOptionPane.YES_OPTION) {
				switch(this.nature) {
				case "Equipe":
					modele.supprimerEquipe(eq);
					MainPanel.updateListEquipe();
					VueInfoPanel.updateListEquipe();
					MainPanel.updateListEcuries();
					this.modele.getPanelFrame(vue).dispose();
					break;
				case "Ecurie":
					modele.supprimerEcurie(ec);
					MainPanel.updateListEcuries();
					JOptionPane.showMessageDialog(this.vue, "Ecurie supprimer avec succès");
					this.modele.getPanelFrame(vue).dispose();
					break;
				case "Tournoi":
					modele.supprimerTournoi(t);
					MainPanel.updateListTournoi();
					this.modele.getPanelFrame(vue).dispose();
					break;
				case "Joueur":
					System.out.println(j);
					modele.supprimerJoueur(j);
					VueInfoPanel.updateListJoueur();
					MainPanel.updateListEquipe();
					this.modele.getPanelFrame(vue).dispose();
					break;
				}
			}
		}
	}

	
	
}
