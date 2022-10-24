package Object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//Classe qui définit les fonctions d'une équipe
public class Equipe {


	private String nom;
	private int points;
	private int id_ecurie;
	private Jeu jeu;
	private List<Joueur> listeJoueurs;


	public Equipe(String nom, int points, int id_ecurie, Jeu jeu) {
		this.nom = nom;
		this.points = points;
		this.id_ecurie = id_ecurie;
		this.jeu = jeu;
		this.listeJoueurs = new ArrayList<Joueur>();
	}
	
	public Ecurie getEcurie(Connection connex) {
		return Ecurie.getEcurieFromId(connex, id_ecurie);
	}

	public void setIdEcurie(int id_ecurie) {
		this.id_ecurie = id_ecurie;
	}

	//Fonction qui permet de retourner le jeu auquel une équipe joue
	public Jeu getJeu() {
		return jeu;
	}

	//Fonction qui permet de changer le nom du jeu auquel une équipe joue
	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}
	
	//Fonction qui permet de récuperer le nom d'une équipe
	public String getNom() {
		return nom;
	}
	
	//Fonction qui permet de changer le nom d'une équipe
	public void setNom(String nom) {
		this.nom = nom;
	}

	//Fonction qui permet de récuperer les points gagnés par une équipe
	public int getPoints() {
		return points;
	}

	//Fonction qui permet de changer les points d'une équipe
	public void setPoints(int points) {
		this.points = points;
	}
	
	//Fonction qui permet de récuperer le dernier identifiant d'une équipe
	public int getLastId(Connection connex) {
		java.sql.Statement st = null;
		ResultSet rs;
		int r = 0;
		try {
			st = connex.createStatement();
			rs = st.executeQuery("Select id_equipes as id from LMN3783A.sae_equipe");
			while (rs.next()) {
				r = rs.getInt("id");
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return r;
	}
	
	//Fonction qui permet d'ajouter un joueur à une équipe
	public void addJoueur(Joueur joueur) {
		this.listeJoueurs.add(joueur);
	}

	//Fonction qui permet d'enregistrer une équipe dans la base de données
	public static int enregistrerEquipe(Connection connex, Equipe equipe) throws Exception {
		PreparedStatement pst;
		int lastId = equipe.getLastId(connex);
		try {
			pst = connex.prepareStatement("insert into LMN3783A.sae_equipe values(?,?,?,?,?,?)");
			pst.setInt(1, lastId+1);
			pst.setString(2, equipe.getNom());
			pst.setInt(3, equipe.listeJoueurs.size());
			pst.setInt(4, equipe.getPoints());
			pst.setInt(5,equipe.id_ecurie);
			pst.setInt(6,equipe.jeu.getId());
			pst.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return -1;
		}
		return 1;
	}
	
	public static List<Equipe> getAllEquipes(Connection connex) {
		List<Equipe> equipes = new ArrayList<Equipe>();
		java.sql.Statement st = null;
		ResultSet rs;
		Equipe e = null;
		try {
			st = connex.createStatement();
			rs = st.executeQuery("Select * from LMN3783A.sae_equipe");
			while (rs.next()) {
				e = new Equipe(rs.getString(0),rs.getInt(1),rs.getInt(2), Jeu.getJeuFromId(connex,rs.getInt(3)));
				equipes.add(e);
				//System.out.println(e.toString());
			}
			return equipes;
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return equipes;
	}

	public static List<Equipe> getEquipesFromEcurie(Connection connex, int id) {
		PreparedStatement pst = null;
		ResultSet rs;
		Equipe e = null;
		List<Equipe> r = new ArrayList<Equipe>();
		try {
			pst = connex.prepareStatement("Select nom, points, id_ecuries, id_jeu as id from LMN3783A.sae_equipe as eq, LMN3783A.SAE_Ecuries as ec where eq.id_ecuries = ec.id_ecuries and eq.id_ecuries = ?");
			pst.setInt(0, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				e = new Equipe(rs.getString(0),rs.getInt(1),rs.getInt(2),Jeu.getJeuFromId(connex, rs.getInt(3)));
				r.add(e);
			}
		} catch (SQLException ee) {
			ee.printStackTrace();
		}
		return r;
	}
}
