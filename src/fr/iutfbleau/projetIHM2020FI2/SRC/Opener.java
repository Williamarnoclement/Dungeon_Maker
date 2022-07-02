package fr.iutfbleau.projetIHM2020FI2.SRC;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import fr.iutfbleau.projetIHM2020FI2.MP.*;
/**
* Cette classe permet l'ouverture de l'affichage de la liste des donjons existans dans le SGBD pour choix et ouverture.
* @author William-Arno CLEMENT
*/
public class Opener implements ActionListener {
	/**
	* Le controleur.
	*/
	private Controller _c;
	/**
	* Constructeur de la classe Opener
	* @param controller récupère le controller.
	*/
	public Opener(Controller controller) {
		this._c = controller;
	}
	/**
	* Permet de d'ouvrir la selection de niveaux.
	* @param e l'évenement de type actionevent
	*/
	public void actionPerformed(ActionEvent e) {
    System.out.println("Ouverture en Cours !");
		this._c._levelOpener();
  }
}
