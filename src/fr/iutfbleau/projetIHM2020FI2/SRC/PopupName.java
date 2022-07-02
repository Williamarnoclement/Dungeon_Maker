package fr.iutfbleau.projetIHM2020FI2.SRC;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.*;
/**
* Cette classe permet l'affichage d'une popup afin d'écrire le nom que l'on veut donner au niveau.
* @author William-Arno CLEMENT
*/
public class PopupName extends JFrame {
  /**
   * Constructeur de la classe PopupName.
   * Le constructreur permet l'affichage d'une pop-up de nommage.
   * @param title on importe le titre de la fenêtre.
   * @param _controller on importe le controller.
   */
  public PopupName(String title, Controller _controller) {
    super(title);
    this.setSize(250, 200);
    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.out.println("closing");
      }
    });
    JLabel label = new JLabel(title);
    JTextArea textArea = new JTextArea();
    textArea.setEditable(true);
    textArea.requestFocusInWindow();
    this.setVisible(true);
    JButton submit = new JButton("Sauvegarder");
    this.add(label, BorderLayout.NORTH);
    this.add(textArea, BorderLayout.CENTER);
    this.add(submit, BorderLayout.SOUTH);
    submit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String s;
        s = textArea.getText();
        if (s.trim().equals("")) {
          label.setText("Le champs ne peut pas être vide ...");
        } else {
          _controller.setLevelName(s.trim());
          _controller._saveNiveau();
          dispose();
        }
      }
    });
  }
}
