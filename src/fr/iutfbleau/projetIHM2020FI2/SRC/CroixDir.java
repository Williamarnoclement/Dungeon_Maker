package fr.iutfbleau.projetIHM2020FI2.SRC;
import fr.iutfbleau.projetIHM2020FI2.API.*;

import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * Cette classe permet d'actionner la création d'un passage.
 * @author William-Arno CLEMENT
*/
public class CroixDir implements ActionListener {
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
	 * Constructeur de la classe CroixDir
	 * @param  _controller Le controleur est importé dans la classe.
	 * @param  _frame On récupère la fenêtre pour pouvoir la fermer après validation de l'action de l'utilisateur.
   * @param  _coord les coordonneés de la pièce.
   * @param  _grid  La grille est importée pour modifier l'affichage.
	 */
  public CroixDir(Controller _controller, JFrame _frame, int [] _coord, GridDraw _grid) {
    this._myController = _controller;
    this._myFrame = _frame;
    this._myCoord = _coord;
    this._g = _grid;
  }
  /**
   * Lorsque l'utilisateur appuie sur le bouton, on va ajouter la pièce à une paire afin de les replier par un passage.Ò
   * @param e type actionevent, permet de savoir sur quel bouton on a cliqué.
  */
  public void actionPerformed(ActionEvent e) {
    JButton button = (JButton) e.getSource();
    String command = button.getActionCommand();
    Direction d = Direction.NORD;
    if (command == "NORD") {
      System.out.println("NORD");
      d = Direction.NORD;
    } else if (command == "SUD") {
      System.out.println("SUD");
      d = Direction.SUD;
    } else if (command == "OUEST") {
      d = Direction.OUEST;
      System.out.println("OUEST");
    } else if (command == "EST") {
      d = Direction.EST;
      System.out.println("EST");
    }
    Piece pToConnect = this._myController.getPieceFromCoord(this._myCoord[0], this._myCoord[1]);
    this._myController.ConnectPieceToPassage(pToConnect, d);
    this._g.repaint();
    this._myFrame.dispose();
  }
}
