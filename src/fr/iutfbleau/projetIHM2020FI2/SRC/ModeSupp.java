package fr.iutfbleau.projetIHM2020FI2.SRC;

import java.awt.event.*;
/**
* Cette classe permet de démarrer le mode de suppression.
* @author William-Arno CLEMENT
*/
public class ModeSupp implements ActionListener {
  /**
  * Le controleur.
  */
  private Controller _myController;
  /**
  * Constructeur de la classe ModeSupp.
  * @param  _controller on importe le controleur.
  */
  public ModeSupp(Controller _controller) {
    this._myController = _controller;
  }
  /**
  * Cette classe permet de démarrer le mode manipuler sauf si il est déjo en cours d'ulilisation.
  * @param e de type actionevent
  */
  public void actionPerformed(ActionEvent e) {
    if (this._myController.getMode() != 3) {

      this._myController.setMode(3);
      System.out.println("Mode Suppression éléments lancé");
    }
  }
}
