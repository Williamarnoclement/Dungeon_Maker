package fr.iutfbleau.projetIHM2020FI2.SRC;

import javax.swing.JComponent;
import javax.swing.JLabel;
import java.util.Iterator;
import java.awt.*;
import java.awt.geom.Line2D;

import fr.iutfbleau.projetIHM2020FI2.API.*;
/**
 * Ceci est le module d'affichage du dongeon.
 * @author William-Arno CLEMENT
 */
public class GridDraw extends JComponent {
  /**
   * Variable qui va stocker la valeur du zoom.
  */
  private int zoom;
  /**
   * Position actuelle de la grille
  */
  private int Position_Grille_X;
  /**
   * Position actuelle de la grille
  */
  private int Position_Grille_Y;
  /**
   * Position ancienne de la grille
  */
  private int old_Position_Grille_X;
  /**
   * Position ancienne de la grille
  */
  private int old_Position_Grille_Y;
  /**
   * Variable qui stock le nombre de lignes et de colonnes dans la grille.
  */
  private int nb_ligcol;
  /**
   * Taille d'une case de la grille.
  */
  private int taille = 5;
  /**
   * Ceci est le log qui permet d'affihcer la position courante de la grille et d'autres informations.
  */
  private JLabel my_log;
  /**
   * Le controleur.
  */
  private Controller _myController;
  /**
	 * constructeur de la classe GridDraw
	 * @param  _controller Le controleur est importé dans la classe.
   * @param  log Le log est importé dans la classe pour modification de son affichage.
   * @param  nb_lignecolonnes récupère le nombre de ligne et de colonne définis dans le Controleur.
	 */
  public GridDraw(Controller _controller, JLabel Log, int nb_lignecolonnes) {
    super();
    this.zoom = 10;
    this.nb_ligcol = nb_lignecolonnes;
    ZoomGrid _zoomer = new ZoomGrid(this);
    ManipGrid _clicker = new ManipGrid(this, _controller);
    this.Position_Grille_X = 0;
    this.Position_Grille_Y = 0;
    this.my_log = Log;
    this._myController = _controller;
  }

  /**
   * Setter de la variable zoom.
   * @param newZoom récupère le nouveau zoom.
   */
  public void setZoom(int newZoom){
    this.zoom = newZoom;
  }
  /**
   * Getter de la variable zoom.
   * @return retourne la valeur de la variable zoom.
   */
  public int getZoom(){
    return this.zoom;
  }

  /**
   * Getter de la position de la grille
   * @return lRetourne la position courrante de la grille en entier
   */
  public int[] getGrille(){
    int[] xy = new int[2];
    xy[0] = this.Position_Grille_X;
    xy[1] = this.Position_Grille_Y;
    return xy;
  }
  /**
   * Getter de la taille de la case
   * @return la valeur de la taille de la case en entier
   */
  public int getTailleCase(){
    return this.taille;
  }
  /**
   * Getter du nombre de lignes et de colonnes
   * @return le nombre de ligne et de colonne.
   */
  public int getNbLigCol(){
    return this.nb_ligcol;
  }

  /**
   * Setter de l'emplacement de la grille sur le graphique 2D.
   * @param x la coordonnée en x.
   * @param y la coordonnée en y.
   */
  public void setGrille(int x, int y){
    this.Position_Grille_X = x;
    this.Position_Grille_Y = y;
    this.setLog("Position de la Grille : "  + this.Position_Grille_X + " " + this.Position_Grille_Y);
  }
  /**
   * Setter du texte du log pour affichage.
   * @param s on récupère les chaines de caractères pour affichage.
   */
  public void setLog(String s){
    this.my_log.setText(s);
  }

  /**
   * Getter de l'ancienne position de la grille
   * @return les anciennes coordonnées sous forme d'un tableau d'entier.
   */
  public int[] getoldGrille(){
    int[] xy = new int[2];
    xy[0] = this.old_Position_Grille_X;
    xy[1] = this.old_Position_Grille_Y;
    return xy;
  }
  /**
   * Setter de l'ancienne position de la grille.
   * @param x la coordonnée en x.
   * @param y la coordonnée en y.
   */
  public void setOldGrille(int x, int y){
    this.old_Position_Grille_X = x;
    this.old_Position_Grille_Y = y;
  }

  /**
   * Cette méthode permet l'affichage du donjon sous forme de grille.
   * @param pinceau le graphique que nous allons utiliser pour l'affichage du donjon.
   */
  @Override
  protected void paintComponent(Graphics pinceau) {

    int indice_X;
    int indice_Y;
    int i ;
    Graphics secondPinceau = pinceau.create();

    for ( indice_X = 0; indice_X < this.nb_ligcol; indice_X++ ) {
      for ( indice_Y = 0; indice_Y < this.nb_ligcol; indice_Y++) {
        secondPinceau.setColor(Color.blue);
        secondPinceau.drawRect(this.Position_Grille_X + (indice_X * this.taille * this.zoom), this.Position_Grille_Y +(indice_Y * this.taille  * this.zoom), this.zoom *  this.taille, this.zoom * this.taille);
        for (i = 0; i < this._myController.getPiece().size(); i++) {
          if (indice_X == this._myController.getPiece().get(i).getPosition().getX() && indice_Y == this._myController.getPiece().get(i).getPosition().getY()) {
            secondPinceau.setColor(Color.red);
            secondPinceau.fillRect( this.Position_Grille_X + (indice_X * this.taille * this.zoom),  this.Position_Grille_Y +(indice_Y * this.taille * this.zoom),this.zoom * this.taille, this.zoom*this.taille);
          }
        }
      }
    }
    for (i = 0; i < this._myController.getPassage().size(); i++) {
      Piece[] DuoPiece = new Piece[2];
      int incr = 0;
      int[] i_X = new int[2];
      int[] i_Y = new int[2];
      int[] offsetX = new int[2];
      int[] offsetY = new int[2];
      Iterator it = this._myController.getPassage().get(i).getPieces();
      while(it.hasNext()){
        DuoPiece[incr] = (Piece) it.next();
        i_X[incr] = (int) DuoPiece[incr].getPosition().getX();
        i_Y[incr] = (int) DuoPiece[incr].getPosition().getY();

        QuelleDirection _qd = new QuelleDirection(DuoPiece[incr],this._myController.getPassage().get(i) );
        if (_qd.getDirection() == Direction.NORD) {
          offsetX[incr] = 2 * this.zoom ;
          offsetY[incr] = 0;
        } else if (_qd.getDirection() == Direction.SUD) {
          offsetX[incr] = 2 * this.zoom ;
          offsetY[incr] = this.taille * this.zoom ;
        } else if (_qd.getDirection() == Direction.OUEST) {
          offsetX[incr] = 0;
          offsetY[incr] = 2 * this.zoom ;
        } else if(_qd.getDirection() == Direction.EST) {
          offsetX[incr] = this.taille * this.zoom ;
          offsetY[incr] = 2 * this.zoom ;
        }
        incr++;
      }

      Graphics2D g2 = (Graphics2D) pinceau;
      if (i_X[0] == i_X[1] || i_Y[0] == i_Y[1]) {
        g2.setColor(Color.green);
        g2.setStroke(new BasicStroke(7));
        g2.draw(new Line2D.Float(this.Position_Grille_X + offsetX[0] + i_X[0] * this.zoom * this.taille,this.Position_Grille_Y + offsetY[0] + i_Y[0] * this.zoom * this.taille ,this.Position_Grille_X +  offsetX[1] + i_X[1] * this.zoom * this.taille ,this.Position_Grille_Y +  offsetY[1] + i_Y[1] * this.zoom * this.taille ));
      } else {
        int[] pInterm = new int[2];
        g2.setColor(Color.green);
        g2.setStroke(new BasicStroke(7));

        if(i_X[0] < i_X[1] && i_Y[0] < i_Y[1]){
          pInterm[0] = i_X[1];
          pInterm[1] = i_Y[0];
        } else if(i_X[0] > i_X[1] && i_Y[0] < i_Y[1]){
          pInterm[0] = i_X[1];
          pInterm[1] = i_Y[0];
        } else if(i_X[0] < i_X[1] && i_Y[0] > i_Y[1]){
          pInterm[0] = i_X[1];
          pInterm[1] = i_Y[0];
        } else if(i_X[0] > i_X[1] && i_Y[0] > i_Y[1]){
          pInterm[0] = i_X[0];
          pInterm[1] = i_Y[1];
        }
        g2.draw(new Line2D.Float(this.Position_Grille_X + offsetX[0] + i_X[0] * this.zoom * this.taille,this.Position_Grille_Y + offsetY[0] + i_Y[0] * this.zoom * this.taille ,this.Position_Grille_X +  offsetX[1] + pInterm[0] * this.zoom * this.taille ,this.Position_Grille_Y +  offsetY[1]/2 + pInterm[1] * this.zoom * this.taille ));
        g2.draw(new Line2D.Float(this.Position_Grille_X + offsetX[1] + pInterm[0] * this.zoom * this.taille,this.Position_Grille_Y +  offsetY[1]/2 + pInterm[1] * this.zoom * this.taille  ,this.Position_Grille_X +  offsetX[1] + i_X[1] * this.zoom * this.taille ,this.Position_Grille_Y +  offsetY[1] + i_Y[1] * this.zoom * this.taille ));

      }
    }
  }
}
