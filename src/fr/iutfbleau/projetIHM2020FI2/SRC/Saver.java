package fr.iutfbleau.projetIHM2020FI2.SRC;
import java.awt.event.*;
/**
* Cette classe permet de créer un nouveau Donjon vierge.
* @author William-Arno CLEMENT
*/
public class Saver implements ActionListener {
  /**
  * Le controleur.
  */
  private Controller _c;
  /**
	* Constructeur de la classe Saver
	* @param controller On importe le controller.
	*/
	public Saver(Controller controller) {
    this._c = controller;
	}
  /**
	* Permet le lancement de la sauvegarde.
	* @param e l'évenement de type actionevent
	*/
	public void actionPerformed(ActionEvent e) {
    System.out.println("Sauvegarde en Cours !");
    this._c._levelNameDefine();
  }
}
