package controleur;

import java.awt.event.MouseEvent;

import javax.swing.*;

import ihm.info.SeeInfoFrame;
import ihm.info.VueInfoTournoisEnCoursFrame;
import ihm.tournois.FrameClassementTournois;
import ihm.tournois.FrameInscriptionTournois;
import object.Ecurie;
import object.Equipe;
import object.Joueur;
import object.Tournoi;

public class ControleurJList extends MouseListenerImp {

	@Override
	public void mouseClicked(MouseEvent e) {
		JList<?> list = (JList<?>) e.getSource();
		int index = list.locationToIndex(e.getPoint());
		Object element = list.getModel().getElementAt(index);
		String classObject = element.getClass().toString();
		switch (classObject) {
		case "class Object.Equipe":
			SeeInfoFrame window = new SeeInfoFrame((Equipe) (element));
			window.setVisible(true);
			break;
		case "class Object.Ecurie":
			SeeInfoFrame windowEcurie = new SeeInfoFrame((Ecurie) element);
			windowEcurie.setVisible(true);
			break;
		case "class Object.Tournoi":
			Tournoi tournoiSelected = (Tournoi) element;
			try {
				tournoiSelected.getPhasesFromId();
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
			JFrame windowTournoi = null;
			switch (tournoiSelected.getEtat())  {
				case FINI :
					windowTournoi = new FrameClassementTournois(tournoiSelected);
				break;

				case INSC:
					windowTournoi = new FrameInscriptionTournois(tournoiSelected);
				break;

				case ENC:
					windowTournoi = new VueInfoTournoisEnCoursFrame(tournoiSelected);
				break;
			}
			if (windowTournoi != null) {
				windowTournoi.setVisible(true);
			}
			break;
		case "class Object.Joueur":
			SeeInfoFrame windowJoueur = new SeeInfoFrame((Joueur) element);
			windowJoueur.setVisible(true);
			break; 
		}

	}
}
