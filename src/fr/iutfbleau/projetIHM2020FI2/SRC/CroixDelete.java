package fr.iutfbleau.projetIHM2020FI2.SRC;
import fr.iutfbleau.projetIHM2020FI2.API.*;

import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * Cette classe permet d'actionner la suppression d'une pièce ou d'un passage.
 * @author William-Arno CLEMENT
*/
public class CroixDelete implements ActionListener {
  /**
   * Le controleur.
  */
  private Controller _myController;
  /**
   * La fenêtre.
  */
  private JFrame _myFrame;
  /**
   * Les coordonnées de la pièce à modifier
  */
  private int[] _myCoord;
  /**
   * La grille dont on doit modifier l'affichage.
  */
  private GridDraw _g;

  /**
	 * Constructeur de la classe CroixDelete
	 * @param  _controller Le controleur est importé dans la classe.
	 * @param  _frame On récupère la fenêtre pour pouvoir la fermer après validation de l'action de l'utilisateur.
   * @param  _coord les coordonneés de la pièce.
   * @param  _grid  La grille est importée pour modifier l'affichage.
	 */
  public CroixDelete(Controller _controller, JFrame _frame, int [] _coord, GridDraw _grid) {
    this._myController = _controller;
    this._myFrame = _frame;
    this._myCoord = _coord;
    this._g = _grid;
  }
  /**
   * Lorsque l'utilisateur appuie sur le bouton, on va supprmier l'élément demandé
   * @param e type actionevent, permet de savoir sur quel bouton on a cliqué.
  */
  public void actionPerformed(ActionEvent e) {
    JButton button = (JButton) e.getSource();
    String command = button.getActionCommand();
    Piece pToConnect = this._myController.getPieceFromCoord(this._myCoord[0], this._myCoord[1]);
    if (command == "NORD") {
      System.out.println("NORD");
      this._myController.removePassage(pToConnect, Direction.NORD);
    } else if (command == "SUD") {
      System.out.println("SUD");
      this._myController.removePassage(pToConnect, Direction.SUD);
    } else if (command == "OUEST") {
      this._myController.removePassage(pToConnect, Direction.OUEST);
      System.out.println("OUEST");
    } else if (command == "EST") {
      this._myController.removePassage(pToConnect, Direction.EST);
      System.out.println("EST");
    } else if (command == "SupprimerPiece") {
      if (pToConnect.getPassage(Direction.NORD) != null) {
        this._myController.removePassage(pToConnect, Direction.NORD);
      } else if (pToConnect.getPassage(Direction.SUD) != null) {
        this._myController.removePassage(pToConnect, Direction.SUD);
      } else if (pToConnect.getPassage(Direction.OUEST) != null) {
        this._myController.removePassage(pToConnect, Direction.OUEST);
      } else if (pToConnect.getPassage(Direction.EST) != null) {
        this._myController.removePassage(pToConnect, Direction.EST);
      }
      this._myController.removePiece(pToConnect);
      System.out.println("Supprimer Piece");
    }
    this._g.repaint();
    this._myFrame.dispose();
  }
}
