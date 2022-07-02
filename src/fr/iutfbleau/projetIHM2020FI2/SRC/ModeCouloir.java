package fr.iutfbleau.projetIHM2020FI2.SRC;

import java.awt.event.*;
/**
* Cette classe permet de démarrer le mode ajout de couloirs.
* @author William-Arno CLEMENT
*/
public class ModeCouloir implements ActionListener {
  /**
  * Le controleur.
  */
  private Controller _myController;
  /**
  * Constructeur de la classe ModeCouloir.
  * @param  _controller on importe le controleur.
  */
  public ModeCouloir(Controller _controller) {
    this._myController = _controller;
  }
  /**
  * Cette classe permet de démarrer le mode d'ajout de passages sauf si il est déjo en cours d'ulilisation.
  * @param e de type actionevent
  */
  public void actionPerformed(ActionEvent e) {
    if (this._myController.getMode() != 2) {

      this._myController.setMode(2);
      System.out.println("Mode Création Couloir lancé");
    }
  }
}
