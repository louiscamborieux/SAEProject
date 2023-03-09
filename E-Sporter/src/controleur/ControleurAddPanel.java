package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import IHM.MainPanel;
import IHM.add.AddPanel;
import IHM.info.VueInfoPanel;
import Object.Ecurie;
import Object.Equipe;
import Object.Joueur;
import Object.Tournoi;

public class ControleurAddPanel implements ActionListener {

	private AddPanel vue;
	private ModeleESporter modele;

	public ControleurAddPanel(AddPanel vue) {
		this.vue = vue;
		this.modele = new ModeleESporter();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JDialog dialog = dialog();
		switch (this.vue.getMode()) {
		case "Player": {
			Joueur obj = (Joueur) this.vue.getInfoToObject();
			// System.out.println(obj);
			JFrame frame = this.modele.getPanelFrame(vue);
			int i = this.modele.addPlayer(obj);
			System.out.println(i);
			VueInfoPanel.updateListJoueur();
			MainPanel.updateListEquipe();
			closeDialog(dialog);
			JOptionPane.showMessageDialog(this.vue, "Joueur ajouté avec succès");
			frame.dispose();
			break;
		}
		case "Team": {
			// JOptionPane.showMessageDialog(this.vue, "Ajout en cours...");
			Equipe obj = (Equipe) this.vue.getInfoToObject();
			System.out.println(obj);
			JFrame frame = this.modele.getPanelFrame(vue);
			this.modele.addTeam(obj);
			VueInfoPanel.updateListEquipe();
			MainPanel.updateListEquipe();
			MainPanel.updateListEcuries();
			closeDialog(dialog);
			JOptionPane.showMessageDialog(this.vue, "Equipe ajoutée avec succès");
			frame.dispose();
			break;
		}
		case "Orga": {
			Ecurie obj = (Ecurie) this.vue.getInfoToObject();
			int resultatRequete = this.modele.addOrga(obj);
			if (resultatRequete == 1) {
				// JOptionPane.showMessageDialog(this.vue, "Ajout en cours...");
				System.out.println(obj);
				JFrame frame = this.modele.getPanelFrame(vue);
				MainPanel.updateListEcuries();
				closeDialog(dialog);
				JOptionPane.showMessageDialog(this.vue, "Ecurie ajouté avec succès");
				frame.dispose();
			} else {
				JOptionPane.showMessageDialog(this.vue, "Une erreur est survenue lors de la création de l'écurie");
			}
			break;
		}
		case "Tournament": {
			// JOptionPane.showMessageDialog(this.vue, "Ajout en cours...");
			Tournoi obj = (Tournoi) this.vue.getInfoToObject();
			System.out.println(obj);
			JFrame frame = this.modele.getPanelFrame(vue);
			this.modele.addTournament(obj);
			MainPanel.updateListTournoi();
			closeDialog(dialog);
			JOptionPane.showMessageDialog(this.vue, "Tournoi ajouté  avec succès");
			frame.dispose();
			break;
		}
		}

	}

	public JDialog dialog() {
		JOptionPane popup = new JOptionPane("Suppression en cours...", JOptionPane.INFORMATION_MESSAGE,
				JOptionPane.DEFAULT_OPTION, null, new Object[] {}, this.vue);
		JDialog dialog = popup.createDialog("En cours d'exécution...");
		dialog.setModal(false);
		dialog.setVisible(true);
		return dialog;

	}

	public void closeDialog(JDialog dialog) {
		dialog.setVisible(false);
		dialog.dispose();
	}

}
