package fr.iutfbleau.projetIHM2020FI2.SRC;

import fr.iutfbleau.projetIHM2020FI2.MP.*;
/**
 * Cette classe permet de lancer l'application.
 * @author William-Arno CLEMENT
 */
public class Main{
  public static void main(String[] args) {
    Connexion connect = new Connexion();
    connect.initialisation();
    Controller c = new Controller(connect);
  }
}
