package fr.iutfbleau.projetIHM2020FI2.SRC;


import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
/**
* Ceci est le système de zoom de l'application.
* @author William-Arno CLEMENT
*/
class ZoomGrid implements MouseWheelListener
{
  /**
  * La barre de menu.
  */
  private GridDraw _grid;
  /**
  * Constructeur de la classe ZoomGrid
  * @param myGrid On importe la grille dont on va modifier l'affichage (via le zoom).
  */
  public ZoomGrid(GridDraw myGrid){
    this._grid = myGrid;
    this._grid.addMouseWheelListener(this);
  }
  /**
  * Permet de changer la valeur du zoom dans le controleur.
  * @param e c'est l'évenement de type mousewheelevent qui permet d'executer le zoom.
  */
  @Override
  public void mouseWheelMoved(MouseWheelEvent e)
  {
    if (e.getWheelRotation() < 0)
    {
      if (this._grid.getZoom() < 20) {
        this._grid.setZoom(this._grid.getZoom() + 1);
      }
    }
    else
    {
      if (this._grid.getZoom() > 2) {
        this._grid.setZoom(this._grid.getZoom() - 1);
      }
    }

    this._grid.repaint();
  }
}
