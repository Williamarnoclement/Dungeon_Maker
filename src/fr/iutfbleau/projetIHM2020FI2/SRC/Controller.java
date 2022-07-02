package fr.iutfbleau.projetIHM2020FI2.SRC;

import fr.iutfbleau.projetIHM2020FI2.API.*;
import fr.iutfbleau.projetIHM2020FI2.MP.PassagePieceFactoryP;
import fr.iutfbleau.projetIHM2020FI2.MP.Connexion;
import java.util.Iterator;

import java.util.ArrayList;
import java.awt.geom.Point2D;
/**
 * Ceci est le controleur de l'application Dungeon Maker, il gère l'ensemble
 * des actions effectuées par l'utilisateur, et les transferts de données vers/depuis le SGBD.
 * @author William-Arno CLEMENT
 */
public class Controller{

  /**
   * Variable permettant de déterminer le mode d'utilisation courant du logiciel.
   * Il existe trois valeurs à Mode: 0, 1, 2, 3
   * Si Mode == 0, on est dans le mode manipulation de la grille
   * Si Mode == 1, on est dans le mode d’ajout de pièces
   * Si Mode == 2, on est dans le mode d’ajout de couloir
   * Si Mode == 3, alors on est dans le mode de suppression
   * La variable Mode est initialisée à -1 pour être dans aucun des modes.
  */
  private int Mode;
  /**
   * Variable qui stock le nombre de lignes et de colonnes dans la grille.
  */
  private int nb_ligcol = 100;
  /**
   * Liste dynamique permettant de stocker les pièces en local dans le logiciel.
  */
  private ArrayList<Piece> p = new ArrayList<Piece>();
  /**
   * Liste dynamique permettant de stocker les passages en local.
  */
  private ArrayList<Passage> pp = new ArrayList<Passage>();
  /**
   * PassagePieceFactory est une usine à pièce et à passages.
  */
  private PassagePieceFactory usinePassagePiece = new PassagePieceFactoryP();
  /**
   * Nom choisi du niveau actuellemtn ouvert.
  */
  private String currentLevelName;
  /**
   * L'instantiation de la classe Connexion qui permet le lien avec le SGBD.
  */
  private Connexion _c;
  /**
   * Paire de pièces à relier via un passage.
  */
  private Piece[] duo_p = new Piece[2];
  /**
   * Paire de directions des deux pièces stockées dans duo_p.
  */
  private Direction[] duo_d = new Direction[2];
  /**
   * Constructeur de la classe Controller.
   * @param connect
   De type Connexion, c'est le lien vers le mode persistant.
   */
  public Controller(Connexion connect) {
    this.Mode = -1;
    new View(this, nb_ligcol);
    reinitialiser_duo();
    System.out.print("Création du dongeon.\n");
    this._c = connect;
    this.currentLevelName = "";
  }
  /**Cette partie du code permet la gestion locale du niveau**/

  /**
   * Permet le changement de mode.
   * Il existe trois valeurs à Mode: 0, 1, 2, 3
   * Si Mode == 0, on est dans le mode manipulation de la grille
   * Si Mode == 1, on est dans le mode d’ajout de pièces
   * Si Mode == 2, on est dans le mode d’ajout de couloir
   * Si Mode == 3, alors on est dans le mode de suppression
   * La variable Mode est initialisée à -1 pour être dans aucun des modes.
   * @param i
   * Ceci est le mode, de type entier
   */
  public void setMode(int i){
    this.Mode = i;
  }
  /**
   * Permet de supprimer une pièce.
   * @param the_p r
   * La pièce que l'on veut supprimer
   */
  public void removePiece(Piece the_p){
    int pos_p_in_array = p.indexOf(the_p);
    p.remove(pos_p_in_array);
  }
  /**
   * Permet de supprimer un passage.
   * @param the_p Piece dont on veut supprimer le pasage.
   * @param d La direction du passage par rapport à la pièce donnée.
   */
  public void removePassage(Piece the_p, Direction d){
    Passage my_pp = the_p.getPassage(d);
    int pos_pp_in_array = pp.indexOf(my_pp);
    System.out.println("pos_pp_in_array " + pos_pp_in_array);
    Iterator it = my_pp.getPieces();
    Piece lAutrePiece = null;
    while(it.hasNext()){
      Piece cur_piece = (Piece) it.next();
      if (cur_piece != the_p) {
        lAutrePiece = cur_piece;
      }
    }
    QuelleDirection _qd = new QuelleDirection(lAutrePiece, my_pp);
    pp.remove(pos_pp_in_array);
    lAutrePiece.removePassage(_qd.getDirection());
    the_p.removePassage(d);
  }
  /**
   * Getter de la variable Mode
   * @return retourne la valeur de la variable Mode.
   */
  public int getMode(){
    return this.Mode;
  }
  /**
   * Cette méthode connecte la pièece à un passage. Elle sert dans la classe CroixDir
   * Elle permet de réceptionner dans les tableaux duo_d et duo_p les attributs des deux pièces à connecter.
   * Après leur connexion, on réinitialise les deux tableaux.
   * @param p récupère la pièce.
   * @param s récupère la direction.
   */
  public void ConnectPieceToPassage(Piece p, Direction s){
    //System.out.println(p.getPosition().getX() + " looool " +p.getPosition().getY());
    if (this.duo_p[0] == null && this.duo_p[1] == null) {
      this.duo_p[0] = p;
      this.duo_d[0] = s;
    } else if (this.duo_p[0] != null && this.duo_p[1] == null) {
      this.duo_p[1] = p;
      this.duo_d[1] = s;
      this.setCouloir(this.duo_p[0], this.duo_p[1], this.duo_d[0], this.duo_d[1]);
      //System.out.println(this.duo_p[0].getPosition().getX() + " looool " +this.duo_p[0].getPosition().getY());
      //System.out.println(this.duo_p[1].getPosition().getX() + " looool " +this.duo_p[1].getPosition().getY());
      this.reinitialiser_duo();
    } else {
      System.out.println("erreur ConnectPieceToPassage...");
    }
  }
  /**
   * Permet de créer une nouvelle pièce si la position n'est pas déjà occupée.
   * @param x récupère la coordonnée x de la pièce.
   * @param y récupère la coordonnée y de la pièce.
   */
  public Boolean setNewPiece(int X, int Y){
    if (isAlreadyPiece(X,Y) || isOutOfGrid(X,Y)) {
      return false;
    } else {
      Piece n = this.usinePassagePiece.newPiece();
      n.setPosition(new Point2D.Double(X,Y));
      this.p.add(n);
      System.out.println("Piece ajoutée");
      return true;
    }
  }
  /**
   * Permet de remettre à null les tableaux duo_p et duo_d.
   */
  public void reinitialiser_duo(){
    this.duo_d[0] = null;
    this.duo_d[1] = null;
    this.duo_p[0] = null;
    this.duo_p[1] = null;
  }
  /**
   * Permet de nettoyer les données locales en supprimant le contenu des listes pp et p.
   */
  public void clear(){
    this.pp.clear();
    this.p.clear();
    this.currentLevelName = "";
  }
  /**
   * Permet de vérifier si il y a déjà une places aux emplacements x et y suivants:
   * @param X
   * la coordonnée x à vérifier
   * @param Y
   * la coordonnée y à vérifier
   *  @return vrai si la pièce existe déjà, false sinon.
   */
  public Boolean isAlreadyPiece(int X, int Y){
    Double _X = Double.valueOf(X);
    Double _Y = Double.valueOf(Y);
    for (Piece e : this.p){
         if (e.getPosition().getX() == _X && e.getPosition().getY() == _Y){
           return true;
         }
    }
    return false;
  }
  /**
   * Permet de récuperer une pièce depuis les coordonnées X et Y données
   * @param X
   * les coordonnées x
   * @param Y
   * les coordonnées y
   * @return la pièce demandée.
   */
  public Piece getPieceFromCoord(int X, int Y){
    Double _X = Double.valueOf(X);
    Double _Y = Double.valueOf(Y);
    for (Piece e : this.getPiece()){
      if (e.getPosition().getX() == _X && e.getPosition().getY() == _Y){
        return e;
      }
    }
    return null;
  }
  /**
   * Permet de déterminer ce qui est en dehors de la grille.
   * @param x récupère la coordonnée x de la pièce.
   * @param y récupère la coordonnée y de la pièce.
   * @ return vrai si les coordonnées sont dans la grille, false sinon.
   */
  public Boolean isOutOfGrid(int X, int Y){
    if ((X < 0 || X > this.nb_ligcol) || (Y < 0 || Y > this.nb_ligcol)) {
      return true;
    }
    return false;
  }
  /**
   * Permet de récupérer l'ensemble des pièces sous forme d'Arraylist
   * @return la liste des pièces.
   */
  public ArrayList<Piece> getPiece(){
    return this.p;
  }
  /**
   * Permet de récupérer l'ensemble des passages sous forme d'ArrayList
   * @return la liste des passages.
   */
  public ArrayList<Passage> getPassage(){
    return this.pp;
  }
  /**
   * Setter de couloir, il créé un nouveau passage
   * @param p1 pièce 1
   * @param p2 pièce 2
   * @param d1 direction choisie sur la pièce 1
   * @param d2 direction choisie sur la pièce 1
   */
  public void setCouloir(Piece p1, Piece p2, Direction d1, Direction d2){
    Passage p1p2 = usinePassagePiece.newPassage(d1,p1,d2,p2);
    this.pp.add(p1p2);
    System.out.println("Passage créé !");
  }

  /**Cette partie du code permet l'implémentation du SGBD**/

  /**
   * Cette méthode permet la sauvegarde du niveau en fonction de la session en cours
   * Si le nom du niveau actuellement ouvert est vide, on choisi le nom du niveau,
   * sinon, on remplace un niveau existant dont le nom est sauvegardé dans la variable currentLevelName.
   */
  public void _levelNameDefine(){
    if (this.currentLevelName == "") {
      PopupName _savename = new PopupName("Choisir le nom du niveau", this);
    } else {
      _saveNiveau();
    }
  }
  /**
   * Cette méthode instantie la classe PopUpOpen, qui permet de choisir un niveau existant dans la base de données.
   */
  public void _levelOpener(){
    PopUpOpen popopen = new PopUpOpen("Choisir un niveau", this);
  }
  /**
   * Cette méthode permet l'import d'un niveau existant dans la base de données.
   * @param nom_Niveau c'est le nom du niveau choisit par l'utilisateur et qui va être généré.
   * Cette classe récupère les différentes données su SGBD et va créer leurs homologues dans le logiciel.
   */
  public void _import(String nom_niveau){
    clear();
    int cle_niveau = this._c.recupCleNiveau(nom_niveau);
    int[] tab_cle_pieces = this._c.recupListePiece(cle_niveau);
    int i;
    for (i = 0; i< tab_cle_pieces.length; i++) {
      int[] data = this._c.recupDataPiece(tab_cle_pieces[i]);
      setNewPiece(data[0],data[1]);
      System.out.println("Piece added");
    }
    int[] tab_cle_passages = this._c.recupListePassage(cle_niveau);
    for (i = 0; i< tab_cle_passages.length; i++) {
      System.out.println("entre dans la boucle");
      int[] datapassage = this._c.recupDataPassage(tab_cle_passages[i]);
      //setNewPassage
      Direction d1 = Direction.NORD;
      int[] dataPiece1 = this._c.recupDataPiece(datapassage[0]);
      if (dataPiece1[2] == tab_cle_passages[i]) {
        d1 = Direction.NORD;
      } else if (dataPiece1[3] == tab_cle_passages[i]) {
        d1 = Direction.SUD;
      } else if (dataPiece1[4] == tab_cle_passages[i]) {
        d1 = Direction.EST;
      } else if (dataPiece1[5] == tab_cle_passages[i]) {
        d1 = Direction.OUEST;
      }
      Direction d2 = Direction.NORD;
      int[] dataPiece2 = this._c.recupDataPiece(datapassage[1]);
      if (dataPiece2[2] == tab_cle_passages[i]) {
        d2 = Direction.NORD;
      } else if (dataPiece2[3] == tab_cle_passages[i]) {
        d2 = Direction.SUD;
      } else if (dataPiece2[4] == tab_cle_passages[i]) {
        d2 = Direction.EST;
      } else if (dataPiece2[5] == tab_cle_passages[i]) {
        d2 = Direction.OUEST;
      }
      Piece p1 = getPieceFromCoord(dataPiece1[0],dataPiece1[1]);
      Piece p2 = getPieceFromCoord(dataPiece2[0],dataPiece2[1]);
      System.out.println("Pieces : "+datapassage[0] + " "+ datapassage[1]);
      System.out.println("Passage1 : "+d1);
      System.out.println("Passage1 : "+d2);

      setCouloir(p1,p2,d1,d2);
      System.out.println("Passage added");
    }
  }
  /**
   * Cette methode est un Setter de la variable currentLevelName.
   * @param name
   * Le nom de notre niveau actuellement ouvert.
   */
  public void setLevelName(String name){
    this.currentLevelName = name;
  }
  /**
   * Cette méthode permet la sauvegarde du niveau.
   *  Si le niveau n'existe pas, on le créé, puis on sauvegarde l'ensemble des données dans le SGBD.
   * On utilise pour cela la clé du niveau.
   */
  public void _saveNiveau(){
    if (this._c.recupCleNiveau(this.currentLevelName) == -1) {
      //ici le niveau n'existe pas
      this._c.sauverNomNiveau(this.currentLevelName);
    }
    //ici on a créé le niveau, il suffit alors de récuperer sa clé.
    int cle_niveau = this._c.recupCleNiveau(this.currentLevelName);
    this._c.deleteNiveauData(cle_niveau);//on clean le niveau avant d'écrire dedans
    _ajouterPiecesAuNiveau(cle_niveau);
    _ajouterPassagesAuNiveau(cle_niveau);
  }
  /**
   * Méthode qui permet d'ajouter une pièce au niveau dans le SGBD.
   * @param cle_niveau la clé du niveau ( dans le SGBD) afin de sauvegarder la pièce au bon endroit.
   */
  public void _ajouterPiecesAuNiveau(int cle_niveau){
    for (Piece e : this.p){
      this._c.sauverPiece((int)e.getPosition().getX(),(int)e.getPosition().getY(), cle_niveau);
    }
  }
  /**
   * Méthode qui permet d'ajouter une pièce au niveau dans le SGBD.
   * @param cle_niveau la clé du niveau (dans le SGBD) afin de sauvegarder le passage au bon endroit.
   */
  public void _ajouterPassagesAuNiveau(int cle_niveau){
    for (Passage e : this.pp){
      Iterator it = e.getPieces();
      int incr = 0;
      Piece[] DuoPiece = new Piece[2];
      while(it.hasNext()){
        DuoPiece[incr] = (Piece) it.next();
        incr++;
      }
      if (DuoPiece[1] != null && DuoPiece[0] != null) {
        int cle_passage = this._c.sauverPassage(this._c.recupClePiece(DuoPiece[0]),this._c.recupClePiece(DuoPiece[1]), cle_niveau);
        QuelleDirection _qd1 = new QuelleDirection(DuoPiece[0], e);
        QuelleDirection _qd2 = new QuelleDirection(DuoPiece[0], e);
        //piece 1
        if (DuoPiece[0].getPassage(Direction.NORD) == e) {
          this._c.modifPieceNord(DuoPiece[0],cle_passage);
        } else if (DuoPiece[0].getPassage(Direction.SUD) == e) {
          this._c.modifPieceSud(DuoPiece[0],cle_passage);
        } else if (DuoPiece[0].getPassage(Direction.OUEST) == e) {
          this._c.modifPieceOuest(DuoPiece[0],cle_passage);
        } else if(DuoPiece[0].getPassage(Direction.EST) == e) {
          this._c.modifPieceEst(DuoPiece[0],cle_passage);
        }
        //piece 2
        if (DuoPiece[1].getPassage(Direction.NORD) == e) {
          this._c.modifPieceNord(DuoPiece[1],cle_passage);
        } else if (DuoPiece[1].getPassage(Direction.SUD) == e) {
          this._c.modifPieceSud(DuoPiece[1],cle_passage);
        } else if (DuoPiece[1].getPassage(Direction.OUEST) == e) {
          this._c.modifPieceOuest(DuoPiece[1],cle_passage);
        } else if(DuoPiece[1].getPassage(Direction.EST) == e) {
          this._c.modifPieceEst(DuoPiece[1],cle_passage);
        }
      }else{
        System.out.println("Les pièces fournies sont erronées.");
      }
    }
  }
  /**
   * Méthode qui permet la récupération de la liste des niveaux sauvegardés dans la base de données.
   * @return la liste des niveaux.
   */
  public String[] _getListeNiveau(){
    return this._c.recupListeNiveau();
  }
}
