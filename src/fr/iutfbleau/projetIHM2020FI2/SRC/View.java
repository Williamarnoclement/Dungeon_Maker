package fr.iutfbleau.projetIHM2020FI2.SRC;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Box;
import java.awt.Dimension;
/**
* Ceci est la vue de l'application. C'est ici que vont s'imbriquer les différents éléments graphiques.
* @author William-Arno CLEMENT
*/
public class View{
  /**
  * La barre de menu.
  */
  private JMenuBar menuBar = new JMenuBar();
  /**
  * Le menu Fichier.
  */
  private JMenu menu_1 = new JMenu("Fichier");
  /**
  * Le menu d'aide.
  */
  private JMenu menu_3 = new JMenu("Aide");

  /**
  * Le bouton nouveau.
  */
  private JMenuItem Item_1_1 = new JMenuItem("Nouveau");
  /**
  * Le bouton ouvrir.
  */
  private JMenuItem Item_1_4 = new JMenuItem("Ouvrir");
  /**
  * Le bouton sauvegarder.
  */
  private JMenuItem Item_1_3 = new JMenuItem("Sauvegarder");
  /**
  * Le bouton a propos.
  */
  private JMenuItem Item_3_1 = new JMenuItem("A propos");
  /**
  * resources images ajouter couloir
  */
  Icon add_passage_i = new ImageIcon("res/IMG/add_couloir.png");
  /**
  * resources images ajouter couloir
  */
  Icon add_piece_i = new ImageIcon("res/IMG/add_passage.png");
  /**
  * resources images ajouter passage
  */
  Icon move_i = new ImageIcon("res/IMG/move.png");
  /**
  * resources images suppression
  */
  Icon trash_i = new ImageIcon("res/IMG/delete.png");
  /**
  * bouton pour passer dans le mode Manipulation
  */
  private JButton Mode_Manipuler = new JButton(move_i);
  /**
  * bouton pour passer dans le mode d'ajout de pièces
  */
  private JButton Mode_Pieces = new JButton(add_passage_i);
  /**
  * bouton pour passer dans le mode d'ajout de Couloirs
  */
  private JButton Mode_Couloirs = new JButton(add_piece_i);
  /**
  * bouton pour passer dans le mode de suppression
  */
  private JButton Mode_Supprime = new JButton(trash_i);
  /**
  * Log dans lequels vont s'afficher des informations complémentaires
  */
  private JLabel Log = new JLabel("");
  /**
  * Le controleur.
  */
  private Controller _myController;

  /**
  * Constructeur de la classe Saver
  * @param controller On importe le controller.
  * @param controller Le nombre de ligne et de colonnes dans la grille.
  */
  public View(Controller _controller, int nb_ligcol) {
    // creation de la fenetre
    JFrame frame = new JFrame("Dungeon Maker");
    Mode_Manipuler.setSize(100,200);

    this.menu_1.add(Item_1_1);
    this.menu_1.add(Item_1_3);
    this.menu_1.add(Item_1_4);
    this.menu_3.add(Item_3_1);
    this.menuBar.add(menu_1);
    this.menuBar.add(menu_3);
    this._myController = _controller;
    frame.add(new GridDraw(this._myController, Log, nb_ligcol), BorderLayout.CENTER);

    frame.setJMenuBar(menuBar);

    Mode_Manipuler.setBorderPainted(false);
    Mode_Pieces.setBorderPainted(false);
    Mode_Couloirs.setBorderPainted(false);
    Mode_Supprime.setBorderPainted(false);

    Mode_Manipuler.setContentAreaFilled(false);
    Mode_Pieces.setContentAreaFilled(false);
    Mode_Couloirs.setContentAreaFilled(false);
    Mode_Supprime.setContentAreaFilled(false);

    JPanel controlBar = new JPanel();
    JPanel Button_Panel = new JPanel();
    Box box = Box.createHorizontalBox();
    Button_Panel.setLayout(new GridBagLayout());

    Saver _saver = new Saver(_controller);
    Item_1_3.addActionListener(_saver);

    Opener _open = new Opener(_controller);
    Item_1_4.addActionListener(_open);

    New _new = new New(_controller);
    Item_1_1.addActionListener(_new);

    box.add(Mode_Manipuler);
    ModeButton _mb = new ModeButton(this._myController);
    Mode_Manipuler.addActionListener(_mb);
    box.add(Mode_Pieces);
    ModePiece _mp = new ModePiece(this._myController);
    Mode_Pieces.addActionListener(_mp);
    ModeCouloir _mc = new ModeCouloir(this._myController);
    Mode_Couloirs.addActionListener(_mc);
    box.add(Mode_Couloirs);
    ModeSupp _ms = new ModeSupp(this._myController);
    Mode_Supprime.addActionListener(_ms);
    box.add(Mode_Supprime);
    Button_Panel.add(box);

    controlBar.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    gbc.fill = GridBagConstraints.NONE;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.weightx = 1.0;
    gbc.weighty = 1.0;
    gbc.insets = new Insets( 0, 0, 0 , 0);
    controlBar.add(Button_Panel, gbc);

    JPanel Log_Panel = new JPanel();
    Log_Panel.add(Log);
    Log_Panel.setSize(30, 300);
    GridBagConstraints gbc2 = new GridBagConstraints();
    gbc2.gridx = 2;
    gbc2.gridy = 0;
    gbc2.gridwidth = 1;
    gbc2.gridheight = 2;
    gbc2.fill = GridBagConstraints.BOTH;
    gbc2.anchor = GridBagConstraints.EAST;
    gbc2.weightx = 1.0;
    gbc2.weighty = 1.0;
    gbc2.insets = new Insets( 0, 0, 0 , 0);

    controlBar.add(Log_Panel, gbc2);

    controlBar.setPreferredSize(new Dimension(frame.getWidth(), 50));

    frame.add(controlBar, BorderLayout.SOUTH);

    /** FIN DE MODELE LANCEMENT DE LA FENETRE*/

    frame.setSize(960, 540 + 50);
    // fermeture de la JFrame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // rendre le tout visible
    frame.setVisible(true);
  }
}
