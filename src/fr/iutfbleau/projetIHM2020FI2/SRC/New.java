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
* Cette classe permet de créer un nouveau Donjon vierge.
* @author William-Arno CLEMENT
*/
public class New implements ActionListener {
	/**
	* Le controleur.
	*/
	private Controller _c;
	/**
	* Constructeur de la classe New
	* @param controller on importe le controller.
	*/
	public New(Controller controller) {
		this._c = controller;
	}
	/**
	* Permet de nettoyer le niveau courant et de le vider.
	* @param e l'évenement de type actionevent
	*/
	public void actionPerformed(ActionEvent e) {
		System.out.println("Nouveau !");
		this._c.clear();
	}
}
