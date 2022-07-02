package fr.iutfbleau.projetIHM2020FI2.SRC;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
/**
* Ceci est le module de manipulation de l'affichage du dongeon.
* @author William-Arno CLEMENT
*/
class ManipGrid implements MouseListener, MouseMotionListener
{
  /**
  * La grille que l'on utilise ( l'affichage du donjon).
  */
  private GridDraw _grid;
  /**
  * Le controleur.
  */
  private Controller _myController;
  /**
  * Variable qui stock l'ancienne position en x
  */
  private int oldMouseX;
  /**
  * Variable qui stock l'ancienne position en y
  */
  private int oldMouseY;

  /**
  * Constructeur de la classe ManipGrid
  * @param  _controller Le controleur est importé dans la classe.
  * @param  _grid  La grille est importée pour modifier l'affichage.
  */
  public ManipGrid(GridDraw myGrid, Controller _controller){
    this._grid = myGrid;
    this._grid.addMouseListener(this);
    this._grid.addMouseMotionListener(this);
    this._myController = _controller;
    this._grid.setOldGrille(this._grid.getGrille()[0] , this._grid.getGrille()[1]);
  }
  /**
  * Si la souris est préssée.
  * @param e l'évenement de type MouseEvent
  */
  public void mousePressed(MouseEvent e) {
    if (this._myController.getMode() == 0) {
      this.oldMouseX = e.getX();
      this.oldMouseY = e.getY();

      //System.out.println("mousePressed         " + e.getX() + " " + e.getY());
    }else if (this._myController.getMode() == 1) {

      int[] tab = getColRow(e.getX(), e.getY());

      Boolean b = this._myController.setNewPiece(tab[0], tab[1]);
      this._grid.repaint();
      if (b) {
        this._grid.setLog("Case ajoutu\u00e9e : " +tab[0]+ " " +tab[1] + " avec zoom "  + this._grid.getZoom() );
      } else {
        this._grid.setLog("Case cliqu\u00e9e : " +tab[0]+ " " +tab[1] + " avec zoom "  + this._grid.getZoom() );
      }
    } else if(this._myController.getMode() == 2){
      int[] tab = getColRow(e.getX(), e.getY());
      if (this._myController.isAlreadyPiece(tab[0], tab[1])) {
        new PopUpFleche("Case " + tab[0]+ " "+ tab[1], _myController, tab, this._grid);
      }
    } else if(this._myController.getMode() == 3){
      int[] tab = getColRow(e.getX(), e.getY());
      if (this._myController.isAlreadyPiece(tab[0], tab[1])) {
        new PopUpDelete("Supprimer un \u00e9l\u00e9ment de " + tab[0]+ " "+ tab[1], _myController, tab, this._grid);
      }
    }

  }
  /**
  * Si la souris est relachée.
  * @param e l'évenement de type MouseEvent
  */
  public void mouseReleased(MouseEvent e) {
    if (this._myController.getMode() == 0) {
      this._grid.setGrille(this._grid.getoldGrille()[0] + e.getX()- this.oldMouseX ,this._grid.getoldGrille()[1]  +  e.getY()- this.oldMouseY);
      this._grid.repaint();
    }
  }
  /**
  * Méthode qui recalcule les coordonnées de la pièce en focntion du clic
  * @param eX les coordonnées en x
  * @param eY les coordonnées en Y
  */
  private int[] getColRow(int eX, int eY){
    int cell = this._grid.getTailleCase()  * this._grid.getZoom();
    int width = cell * this._grid.getNbLigCol();
    int xOffset = this._grid.getGrille()[0];
    int yOffset = this._grid.getGrille()[1];
    System.out.println("offset " +xOffset + " " + yOffset);

    int column = (((eX - xOffset + cell) / cell)-1);
    int row = (((eY - yOffset + cell) / cell)-1);
    int[] tab = new int[2];
    tab[0] = column;
    tab[1] = row;
    return tab;
  }
  /**
  * Inutile
  * @param e l'évenement de type MouseEvent
  */
  public void mouseEntered(MouseEvent e) {
    //System.out.println("souris entrée");
  }
  /**
  * Inutile
  * @param e l'évenement de type MouseEvent
  */
  public void mouseExited(MouseEvent e) {
    //System.out.println("souris sortie");
  }
  /**
  * Inutile
  * @param e l'évenement de type MouseEvent
  */
  public void mouseClicked(MouseEvent e) {
    //System.out.println("souris cliquée");

  }
  /**
  * Si la souris est déplacée.
  * @param e l'évenement de type MouseEvent
  */
  public void mouseDragged(MouseEvent e){
    if (this._myController.getMode() == 0) {
      this._grid.setGrille(this._grid.getoldGrille()[0] + e.getX() - this.oldMouseX,this._grid.getoldGrille()[1] + e.getY() - this.oldMouseY);
      this._grid.setOldGrille(this._grid.getGrille()[0], this._grid.getGrille()[1]);
      this.oldMouseX = e.getX();
      this.oldMouseY = e.getY();
      this._grid.repaint();
    }
  }
  /**
  * Inutile
  * @param e l'évenement de type MouseEvent
  */
  public void mouseMoved(MouseEvent e){
    //
  }
}
