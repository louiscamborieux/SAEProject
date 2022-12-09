package controleur;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import IHM.add.AddPanel;
import Object.Ecurie;
import Object.Equipe;
import Object.Joueur;
import Object.Tournoi;

public class ControleurAjout implements ActionListener{

	private AddPanel vue;
	private ModeleESporter modele;
	
	public ControleurAjout(AddPanel vue) {
		this.vue = vue;
		this.modele = new ModeleESporter();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		switch(this.vue.getMode()) {
			case "Player":{
				Joueur obj = (Joueur) this.vue.getInfoToObject();
				System.out.println(obj);
				JFrame frame = this.modele.getPanelFrame(vue);
				frame.dispose();
				//this.modele.addPlayer(obj);
				break;
			}
			case "Team":{
				Equipe obj = (Equipe) this.vue.getInfoToObject();
				System.out.println(obj);
				JFrame frame = this.modele.getPanelFrame(vue);
				frame.dispose();
				//this.modele.addTeam(obj);
				break;
			}
			case "Orga":{
				Ecurie obj = (Ecurie) this.vue.getInfoToObject();
				System.out.println(obj);
				JFrame frame = this.modele.getPanelFrame(vue);
				frame.dispose();
				//this.modele.addOrga(obj);
				break;
			}
			case "Tournament":{
				Tournoi obj = (Tournoi) this.vue.getInfoToObject();
				System.out.println(obj);
				JFrame frame = this.modele.getPanelFrame(vue);
				frame.dispose();
				//this.modele.addTournament(obj);
				break;
			}
	}
		
	}

}