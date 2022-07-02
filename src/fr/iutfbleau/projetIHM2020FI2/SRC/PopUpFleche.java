package fr.iutfbleau.projetIHM2020FI2.SRC;

import fr.iutfbleau.projetIHM2020FI2.API.Direction;
import fr.iutfbleau.projetIHM2020FI2.API.Piece;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.GridBagConstraints;
/**
* Cette classe permet l'affichage d'une popup afin de choisir en détail l'élement à supprimer.
* @author William-Arno CLEMENT
*/
public class PopUpFleche extends JFrame {
	private Controller _myController;
	private int[] _c = new int[2];
	private GridDraw _g;
	/**
	 * constructeur
	 * Le constructreur permet l'affichage d'une croix directionnel d'ajout.
	 * @param  title  récupère le titre.
	 * @param  _controller récupère le controller.
   	 * @param  _coord récupère les coordonnées de la pièce.
   	 * @param  _grid  récupère la grille.
	 */
	public PopUpFleche(String title, Controller _controller, int[] _coord, GridDraw _grid) {
		super(title);
		this.setSize(250, 200);
		this._myController = _controller;
		this._c = _coord;
		this._g = _grid;
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("closing");
				_controller.reinitialiser_duo();
			}
		});

		JPanel conteneur = new JPanel();

		conteneur.setLayout(new GridBagLayout());
		this.setLocation(0, 0);

		CroixDir _mc = new CroixDir(this._myController, this, this._c, this._g);

		JButton b1 = new JButton("NORD");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.SOUTHWEST;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(0, 0, 0, 0);
		b1.addActionListener(_mc);
		conteneur.add(b1, gbc);

		JButton b2 = new JButton("EST");
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.gridx = 2;
		gbc2.gridy = 1;
		gbc2.gridwidth = 1;
		gbc2.gridheight = 1;
		gbc2.fill = GridBagConstraints.BOTH;
		gbc2.anchor = GridBagConstraints.NORTHWEST;
		gbc2.weightx = 1.0;
		gbc2.weighty = 1.0;
		gbc2.insets = new Insets(0, 0, 0, 0);
		b2.addActionListener(_mc);
		conteneur.add(b2, gbc2);

		JButton b3 = new JButton("SUD");
		GridBagConstraints gbc3 = new GridBagConstraints();
		gbc3.gridx = 1;
		gbc3.gridy = 2;
		gbc3.gridwidth = 1;
		gbc3.gridheight = 1;
		gbc3.fill = GridBagConstraints.BOTH;
		gbc3.anchor = GridBagConstraints.NORTHEAST;
		gbc3.weightx = 1.0;
		gbc3.weighty = 1.0;
		gbc3.insets = new Insets(0, 0, 0, 0);
		b3.addActionListener(_mc);
		conteneur.add(b3, gbc3);

		JButton b4 = new JButton("OUEST");
		GridBagConstraints gbc4 = new GridBagConstraints();
		gbc4.gridx = 0;
		gbc4.gridy = 1;
		gbc4.gridwidth = 1;
		gbc4.gridheight = 1;
		gbc4.fill = GridBagConstraints.BOTH;
		gbc4.anchor = GridBagConstraints.SOUTHEAST;
		gbc4.weightx = 1.0;
		gbc4.weighty = 1.0;
		gbc4.insets = new Insets(0, 0, 0, 0);
		b4.addActionListener(_mc);
		conteneur.add(b4, gbc4);

		Piece pToConnect = this._myController.getPieceFromCoord(this._c[0], this._c[1]);

		if (pToConnect.getPassage(Direction.NORD) != null) {
			b1.setEnabled(false);
		}
		if (pToConnect.getPassage(Direction.SUD) != null) {
			b3.setEnabled(false);
		}
		if (pToConnect.getPassage(Direction.OUEST) != null) {
			b4.setEnabled(false);
		}
		if (pToConnect.getPassage(Direction.EST) != null) {
			b2.setEnabled(false);
		}

		this.add(conteneur);
		this.setVisible(true);
	}
}
