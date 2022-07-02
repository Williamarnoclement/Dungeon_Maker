package fr.iutfbleau.projetIHM2020FI2.SRC;

import java.awt.event.*;
/**
* Cette classe permet de démarrer le mode manipuler.
* @author William-Arno CLEMENT
*/
public class ModeButton implements ActionListener {
  /**
  * Le controleur.
  */
  private Controller _myController;
  /**
  * Constructeur de la classe ModeButton
  * @param  _controller on importe le controleur.
  */
  public ModeButton(Controller _controller) {
    this._myController = _controller;
  }
  /**
  * Cette classe permet de démarrer le mode manipuler sauf si il est déjo en cours d'ulilisation.
  * @param e de type actionevent
  */
  public void actionPerformed(ActionEvent e) {
    if (this._myController.getMode() != 0) {

      this._myController.setMode(0);
      System.out.println("Mode Manip lancé");
    }
  }
}
