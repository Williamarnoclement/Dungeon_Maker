package fr.iutfbleau.projetIHM2020FI2.SRC;

import java.awt.event.*;
/**
* Cette classe permet de démarrer le mode d'ajout de pièces.
* @author William-Arno CLEMENT
*/
public class ModePiece implements ActionListener {
  /**
  * Le controleur.
  */
  private Controller _myController;
  /**
  * Constructeur de la classe ModePiece
  * @param  _controller on importe le controleur.
  */
	public ModePiece(Controller _controller) {
    this._myController = _controller;

	}
  /**
  * Cette classe permet de démarrer le mode manipuler sauf si il est déjo en cours d'ulilisation.
  * @param e de type actionevent
  */
	public void actionPerformed(ActionEvent e) {
		if (this._myController.getMode() != 1) {

      this._myController.setMode(1);
      System.out.println("Mode Ajout Pièces lancé");
    }
	}
}
