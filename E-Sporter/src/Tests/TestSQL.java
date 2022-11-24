package Tests;

import java.util.List;

import Application.Connexion;
import Object.*;

public class TestSQL {

	public static void main(String[] args) throws Exception {
		List<Equipe> liste = Equipe.getAllEquipes(Connexion.connexion());
		for (Equipe e : liste) {
			System.out.println(e.toString());
		}
		
		List<Equipe> liste2 = Equipe.getEquipesFromEcurie(Connexion.connexion(), "Faze Clan");
		for (Equipe e : liste2) {
			System.out.println(e.toString());
		}
		
		//Equipe.enregistrerEquipe(Connexion.connexion(), new Equipe("Vitality A", 0, 0, 1));
		
		Ecurie e = Ecurie.getEcurieFromNomAll(Connexion.connexion(), "Faze Clan").get(0);
		System.out.println(e);
		
		 //Ecurie.enregistrerEcurie(Connexion.connexion(), new Ecurie("Astralis"));
		
		int id = Jeu.getId(Connexion.connexion(), new Jeu("Valorant"));
		System.out.println(id);
		//Ecurie e1 = null;
		//e1 = Ecurie.getEcurieFromNomAll(Connexion.connexion(), "Faze Clan").get(0);
		System.out.println(Equipe.getEquipeFromNomAll(Connexion.connexion(),"Faze CSGO"));
	}
	

}
