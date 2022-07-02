package fr.iutfbleau.projetIHM2020FI2.SRC;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.JList;
/**
* Cette classe permet l'affichage d'une popup d'affichage de la liste des niveaux du SGBD pour choix et ouverture.
* @author William-Arno CLEMENT
*/
public class PopUpOpen extends JFrame {
  /**
   * Constructeur de la classe PopUpOpen.
   * Cette fenetre permet la sélection de l'un des niveau disponible.
   * @param title on importe le titre de la fenetre
   * @param _controller on importe le controller.
   */
  public PopUpOpen(String title, Controller _controller) {
    super(title);
    this.setSize(250, 200);
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.out.println("closing");
      }
    });
    JLabel label = new JLabel(title);
    JList<String> l = new JList<>(_controller._getListeNiveau());
    JButton submit = new JButton("Ouvrir");
    this.add(label, BorderLayout.NORTH);
    this.add(l, BorderLayout.CENTER);
    this.add(submit, BorderLayout.SOUTH);
    submit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String selected = l.getSelectedValue().toString();
        if (selected != "") {
          System.out.println(selected);
          _controller._import(selected);
          dispose();
        }
      }
    });

    this.setVisible(true);

  }
}
