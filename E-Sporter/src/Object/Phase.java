package Object;

//Classe qui définit les fonctions d'une phase
public class Phase {

	private boolean elim;
	private Tournoi tournoi;

	//Constructeur de la classe "Phase"
	public Phase(boolean elim, Tournoi tournoi) {
		this.elim = elim;
		this.tournoi = tournoi;
	}

    //Fonction qui permet de savoir si c'est une phase éliminatoire où pas 
	public boolean isElim() {
		return elim;
	}

    //Fonction qui permet de récuperer le tournoi d'une phase
	public Tournoi getTournoi() {
		return tournoi;
	}

}