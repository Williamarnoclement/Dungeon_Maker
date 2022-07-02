package fr.iutfbleau.projetIHM2020FI2.MP;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import org.mariadb.jdbc.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
/**
* Ceci est la classe permettant l'implémentation du modèle persistant.
* @author William-Arno CLEMENT
*/
public class Connexion{
	/**
	* La barre de menu.
	*/
	private Connection conn = null;
	/**
	* Constructeur de la classe Connexion
	*/
	public static void Connexion() {
	}
	/**
	* Permet l'initialisation de la connexion avec la base de donnée.
	*/
	public void initialisation() {


		String driver = "org.mariadb.jdbc.Driver";
		String db = "clement";
		String url = "jdbc:mariadb://dwarves.iut-fbleau.fr/"+db;
		String user = "clement";
		String pass = "ankubi99";

		System.out.println("Launching connection");

		try{
			Class.forName(driver);
			this.conn = DriverManager.getConnection(url,user,pass);
			System.out.println("Connected!");
		} catch (SQLException e){
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLSate: " + e.getSQLState());
			System.out.println("Vendor Error: " + e.getErrorCode());
		} catch (ClassNotFoundException e){
			System.out.println("ClassNotFoundException Error..");
		}
	}
	/**
	* Permet de sauvegarder le nom du niveau
	* @param nom_niveau le nom du niveau à sauvegarder
	* @return vrai si la sauvegarde réussie, faux sinon.
	*/
	public Boolean sauverNomNiveau(String nom_niveau){
		try{
			Statement statement = this.conn.createStatement();
			if ( nom_niveau != null) {
				int insertingPage = statement.executeUpdate( "INSERT INTO DJ_Niveau (nom) "
				+ "VALUES ('" + nom_niveau +"');" );
				return true;
			}
		} catch (SQLException e){
			System.out.println("Le niveau existe déjà");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLSate: " + e.getSQLState());
			System.out.println("Vendor Error: " + e.getErrorCode());
		}
		return false;
	}
	/**
	* Permet de sauvegarder la pièce en avec ses coordonnées et la clé du niveau.
	* @param pos_x coodonnées en x
	* @param pos_y coodonnées en y
	* @param cle_niveau la clé du niveau auquel appartient la pièce.
	* @return vrai si la sauvegarde réussie, faux sinon.
	*/
	public Boolean sauverPiece(int pos_x, int pos_y, int cle_niveau){
		try{
			Statement statement = this.conn.createStatement();
			if ( cle_niveau != 0) {
				int insertingPage = statement.executeUpdate( "INSERT INTO DJ_Piece (pos_x, pos_y, cle_niveau) "
				+ "VALUES ("+ pos_x +","+ pos_y +","+cle_niveau +");" );
				return true;
			}
		} catch (SQLException e){
			System.out.println("Le niveau existe déjà");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLSate: " + e.getSQLState());
			System.out.println("Vendor Error: " + e.getErrorCode());
		}
		return false;
	}
	/**
	* Permet de sauvegarder le passage avec les clés des pièces connectées et la clé niveau
	* @param cle_piece_1 clé de la première pièce
	* @param cle_piece_2 clé de la deuxième pièce
	* @param cle_niveau la clé du niveau auquel appartient le passage.
	* @return clé passage
	*/
	public int sauverPassage(int cle_piece_1, int cle_piece_2, int cle_niveau){
		try{
			ResultSet result;
			PreparedStatement statement;
			String query = "INSERT INTO DJ_Passage (p_1, p_2, cle_niveau)"
			+ "VALUES ("+ cle_piece_1 +","+ cle_piece_2 +"," + cle_niveau +");";
			statement = this.conn.prepareStatement(query ,Statement.RETURN_GENERATED_KEYS);
			statement.execute();
			result = statement.getGeneratedKeys();
			if(result.next() && result != null){
				int cle = result.getInt(1);
				System.out.println("clé passage retournée : " + cle);
				return cle;
			} else {
				System.out.println("Erreur aucune cle retournée");
			}
		} catch (SQLException e){
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLSate: " + e.getSQLState());
			System.out.println("Vendor Error: " + e.getErrorCode());
		}
		return -1;
	}
	/**
	* Modifie la clé du passage nord de la pièce
	* @param p cla pièce dont on veut modifier/ajouter/supprimer un passage
	* @param cle_passage clé du passage
	*/
	public void modifPieceNord(Piece p, int cle_passage){
		try{
			Statement statement = this.conn.createStatement();
			statement.executeUpdate( "UPDATE DJ_Piece set pas_nord="+cle_passage+" where cle=" + recupClePiece(p) + ";" );
		} catch (SQLException e){
			System.out.println("La pièce n'existe pas");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLSate: " + e.getSQLState());
			System.out.println("Vendor Error: " + e.getErrorCode());
		}
	}
	/**
	* Modifie la clé du passage sud de la pièce
	* @param p cla pièce dont on veut modifier/ajouter/supprimer un passage
	* @param cle_passage clé du passage
	*/
	public void modifPieceSud(Piece p, int cle_passage){
		try{
			Statement statement = this.conn.createStatement();
			statement.executeUpdate( "UPDATE DJ_Piece set pas_sud="+cle_passage+" where cle=" + recupClePiece(p) + ";" );
		} catch (SQLException e){
			System.out.println("La pièce n'existe pas");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLSate: " + e.getSQLState());
			System.out.println("Vendor Error: " + e.getErrorCode());
		}
	}
	/**
	* Modifie la clé du passage est de la pièce
	* @param p cla pièce dont on veut modifier/ajouter/supprimer un passage
	* @param cle_passage clé du passage
	*/
	public void modifPieceEst(Piece p, int cle_passage){
		try{
			Statement statement = this.conn.createStatement();
			statement.executeUpdate( "UPDATE DJ_Piece set pas_est="+cle_passage+" where cle=" + recupClePiece(p) + ";" );
		} catch (SQLException e){
			System.out.println("La pièce n'existe pas");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLSate: " + e.getSQLState());
			System.out.println("Vendor Error: " + e.getErrorCode());
		}
	}
	/**
	* Modifie la clé du passage ouest de la pièce
	* @param p cla pièce dont on veut modifier/ajouter/supprimer un passage
	* @param cle_passage clé du passage
	*/
	public void modifPieceOuest(Piece p, int cle_passage){
		try{
			Statement statement = this.conn.createStatement();
			statement.executeUpdate( "UPDATE DJ_Piece set pas_ouest="+cle_passage+" where cle=" + recupClePiece(p) + ";" );
		} catch (SQLException e){
			System.out.println("La pièce n'existe pas");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLSate: " + e.getSQLState());
			System.out.println("Vendor Error: " + e.getErrorCode());
		}
	}
	/**
	* Permet de récupérer la clé du niveau
	* @param nom_niveau cle nom du niveau dont on veut récupérer la clé.
	* @return clé du niveau
	*/
	public int recupCleNiveau(String nom_niveau){
		try{
			String prestation = "SELECT cle FROM DJ_Niveau WHERE nom=?;";
			try (PreparedStatement statement = this.conn.prepareStatement(prestation);) {
				statement.setString(1, nom_niveau);
				/* Exécution d'une requête de lecture */
				ResultSet resultat = statement.executeQuery();
				/* Récupération des données du résultat de la requête de lecture */
				int cle = -1;
				if (resultat.next()) {
					cle = resultat.getInt( 1 );
				}
				System.out.println("cle niveau retournée: " + cle);
				return cle;
			}
		} catch (SQLException e){
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLSate: " + e.getSQLState());
			System.out.println("Vendor Error: " + e.getErrorCode());
		}
		return -1;
	}
	/**
	* Supprime l'ensemble des données du niveau.
	* @param cle la clé du niveau
	* @return vrai si suppression réussié, false sinon.
	*/
	public Boolean deleteNiveauData(int cle){
		try{
			Statement statement = this.conn.createStatement();
			statement.executeUpdate( "DELETE FROM DJ_Passage WHERE cle_niveau=" + cle + ";" );
			statement.executeUpdate( "DELETE FROM DJ_Piece WHERE cle_niveau=" + cle + ";" );
			return true;
		} catch (SQLException e){
			System.out.println("Aucun élément à supprimer");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLSate: " + e.getSQLState());
			System.out.println("Vendor Error: " + e.getErrorCode());
		}
		return false;
	}
	/**
	* Supprime un niveau
	* @param cle la clé du niveau
	* @return vrai si suppression réussié, false sinon.
	*/
	public Boolean deleteNiveau(int cle){
		try{
			Statement statement = this.conn.createStatement();
			statement.executeUpdate( "DELETE FROM DJ_Niveau WHERE cle=" + cle + ";" );
			return true;
		} catch (SQLException e){
			System.out.println("Le niveau n'existe pas");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLSate: " + e.getSQLState());
			System.out.println("Vendor Error: " + e.getErrorCode());
		}
		return false;
	}
	/**
	* Supprime la pièce.
	* @param cle la clé de la pièce
	* @return vrai si suppression réussié, false sinon.
	*/
	public Boolean deletePiece(int cle_piece){
		try{
			Statement statement = this.conn.createStatement();
			statement.executeUpdate( "DELETE FROM DJ_Piece WHERE cle=" + cle_piece + ";" );
			return true;
		} catch (SQLException e){
			System.out.println("La pièce n'existe pas");
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLSate: " + e.getSQLState());
			System.out.println("Vendor Error: " + e.getErrorCode());
		}
		return false;
	}
	/**
	* Supprime le passage.
	* @param cle_piece_1 la clé du de la première pièce connectée par le passage
	* @param cle_piece_1 la clé du de la seconde pièce connectée par le passage
	* @param cle la clé du niveau
	* @return vrai si suppression réussié, false sinon.
	*/
	public Boolean deletePassage(int cle_piece_1, int cle_piece_2, int cle_niveau){
		try{
			Statement statement = this.conn.createStatement();
			statement.executeUpdate( "DELETE FROM DJ_Niveau WHERE p_1=" + cle_piece_1 + "AND p_2="+cle_piece_2+" AND cle_niveau="+cle_niveau+";" );
			statement.executeUpdate( "DELETE FROM DJ_Niveau WHERE p_1=" + cle_piece_2 + "AND p_2="+cle_piece_1+" AND cle_niveau="+cle_niveau+";" );
			return true;
		} catch (SQLException e){
			System.out.println("NORMAL: l'une des deux requêtes n'a pas abouti");
		}
		return false;
	}
	/**
	* Récupère la clé de la pièce.
	* @param p la pièce
	* @return la clé de la pièce
	*/
	public int recupClePiece(Piece p){
		try{
			String prestation = "SELECT cle FROM DJ_Piece WHERE pos_x=? AND pos_y=?;";
			try (PreparedStatement statement = this.conn.prepareStatement(prestation);) {
				int x = (int) p.getPosition().getX();
				int y = (int) p.getPosition().getY();
				statement.setInt(1, x);
				statement.setInt(2, y);
				/* Exécution d'une requête de lecture */
				ResultSet resultat = statement.executeQuery();
				/* Récupération des données du résultat de la requête de lecture */
				int cle = -1;
				if (resultat.next()) {
					cle = resultat.getInt( 1 );
				}
				System.out.println("cle piece retournée: " + cle);
				return cle;
			}
		} catch (SQLException e){
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLSate: " + e.getSQLState());
			System.out.println("Vendor Error: " + e.getErrorCode());
		}
		return -1;
	}
	/**
	* Récupère les données de la pièce
	* @param cle_piece la clé de la pièce dans le SGBD
	* @return les coordonnées et les clés des passages sous forme de tableau d'entier.
	*/
	public int[] recupDataPiece(int cle_piece){
		try{
			String prestation = "SELECT pos_x,pos_y,pas_nord,pas_sud,pas_est,pas_ouest FROM DJ_Piece WHERE cle=?;";
			try (PreparedStatement statement = this.conn.prepareStatement(prestation);) {
				statement.setInt(1, cle_piece);
				/* Exécution d'une requête de lecture */
				ResultSet resultat = statement.executeQuery();
				/* Récupération des données du résultat de la requête de lecture */
				int[] tab = new int[6];
				if (resultat.next()) {
					tab[0] = resultat.getInt( 1 );
					tab[1] = resultat.getInt( 2 );
					tab[2] = resultat.getInt( 3 );
					tab[3] = resultat.getInt( 4 );
					tab[4] = resultat.getInt( 5 );
					tab[5] = resultat.getInt( 6 );
				}
				return tab;
			}
		} catch (SQLException e){
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLSate: " + e.getSQLState());
			System.out.println("Vendor Error: " + e.getErrorCode());
		}
		return null;
	}
	/**
	* Récupère la liste des pièces d'un niveau
	* @param cle_niveau la clé du niveau dont on veut récupérer les pièces
	* @return la liste des clés des pièces.
	*/
	public int[] recupListePiece(int cle_niveau){
		try{
			String prestation = "SELECT cle FROM DJ_Piece WHERE cle_niveau=?;";
			try (PreparedStatement statement = this.conn.prepareStatement(prestation);) {
				statement.setInt(1, cle_niveau);
				/* Exécution d'une requête de lecture */
				ResultSet resultat = statement.executeQuery();
				/* Récupération des données du résultat de la requête de lecture */
				ArrayList<Integer> list = new ArrayList<Integer>();
				while(resultat.next()) {
					list.add(resultat.getInt( 1 ));
				}
				return convertIntegers(list);
			}
		} catch (SQLException e){
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLSate: " + e.getSQLState());
			System.out.println("Vendor Error: " + e.getErrorCode());
		}
		return null;
	}
	/**
	* Récupère la liste des passages d'un niveau
	* @param cle_niveau la clé du niveau dont on veut récupérer les passages
	* @return la liste des clés des passages.
	*/
	public int[] recupListePassage(int cle_niveau){
		try{
			String prestation = "SELECT cle FROM DJ_Passage WHERE cle_niveau=?;";
			try (PreparedStatement statement = this.conn.prepareStatement(prestation);) {
				statement.setInt(1, cle_niveau);
				/* Exécution d'une requête de lecture */
				ResultSet resultat = statement.executeQuery();
				/* Récupération des données du résultat de la requête de lecture */
				ArrayList<Integer> list = new ArrayList<Integer>();
				while(resultat.next()) {
					list.add(resultat.getInt( 1 ));
				}
				return convertIntegers(list);
			}
		} catch (SQLException e){
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLSate: " + e.getSQLState());
			System.out.println("Vendor Error: " + e.getErrorCode());
		}
		return null;
	}
	/**
	* Récupère les données de l'un passage
	* @param cle_passage la clé du passage dans le SGBD
	* @return les clés des pièces connectées par le passage sous forme de tableau d'entier.
	*/
	public int[] recupDataPassage(int cle_passage){
		try{
			String prestation = "SELECT p_1,p_2 FROM DJ_Passage WHERE cle=?;";
			try (PreparedStatement statement = this.conn.prepareStatement(prestation);) {
				statement.setInt(1, cle_passage);
				/* Exécution d'une requête de lecture */
				ResultSet resultat = statement.executeQuery();
				/* Récupération des données du résultat de la requête de lecture */
				int[] tab = new int[2];
				if (resultat.next()) {
					tab[0] = resultat.getInt( 1 );//P1
					tab[1] = resultat.getInt( 2 );//P2
				}
				return tab;
			}
		} catch (SQLException e){
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLSate: " + e.getSQLState());
			System.out.println("Vendor Error: " + e.getErrorCode());
		}
		return null;
	}
	/**
	* Récupère la liste des noms des niveaux
	* @return la liste des noms des niveaux sous forme de tableau de chaines de caractères.
	*/
	public String[] recupListeNiveau(){
		try{
			String prestation = "SELECT nom FROM DJ_Niveau;";
			try (PreparedStatement statement = this.conn.prepareStatement(prestation);) {
				/* Exécution d'une requête de lecture */
				ResultSet resultat = statement.executeQuery();
				/* Récupération des données du résultat de la requête de lecture */
				ArrayList<String> list = new ArrayList<String>();
				while(resultat.next()) {
					list.add(resultat.getString( 1 ));
				}
				return convertString(list);
			}
		} catch (SQLException e){
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLSate: " + e.getSQLState());
			System.out.println("Vendor Error: " + e.getErrorCode());
		}
		return null;
	}
	/**
	* Convertit les ArrayList de string en tableaux de String.
	* @param la liste à convertir
	* @return le tableau de string
	*/
	private static String[] convertString(ArrayList<String> liste){
		String[] j = new String[liste.size()];
		for (int i=0; i < j.length; i++){
			j[i] = liste.get(i);
		}
		return j;
	}
	/**
	* Convertit les ArrayList d'entier en tableaux d'entiers.
	* @param la liste à convertir
	* @return le tableau d'entiers
	*/
	private static int[] convertIntegers(ArrayList<Integer> liste){
		int[] j = new int[liste.size()];
		for (int i=0; i < j.length; i++){
			j[i] = liste.get(i).intValue();
		}
		return j;
	}
}
