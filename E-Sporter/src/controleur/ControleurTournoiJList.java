package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

import IHM.info.SeeInfoFrame;
import Object.Ecurie;
import Object.Tournoi;

public class ControleurTournoiJList implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		JList<Tournoi> list = (JList<Tournoi>) e.getSource();
		int index = list.locationToIndex(e.getPoint());
		System.out.println(list.getModel().getElementAt(index).toString());
		SeeInfoFrame window = new SeeInfoFrame(list.getModel().getElementAt(index));
		window.setVisible(true); 
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
