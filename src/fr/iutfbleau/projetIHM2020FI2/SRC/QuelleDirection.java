package fr.iutfbleau.projetIHM2020FI2.SRC;

import fr.iutfbleau.projetIHM2020FI2.API.*;
/**
* Cette classe permet de determiner la direction d'un passage en fonction de sa pièce.
* @author William-Arno CLEMENT
*/
public class QuelleDirection {
    private Passage pp;
    private Piece p;
    public QuelleDirection(Piece _myPiece, Passage _myPassage){
      this.p = _myPiece;
      this.pp = _myPassage;
    }
/**
 * Permet de récupérer la direction du passage en fonction de sa pièce d'arrivée.
 * @return Direction
 */
    public Direction getDirection(){
      if (this.p.getPassage(Direction.NORD) == this.pp) {
        return Direction.NORD;
      } else if (this.p.getPassage(Direction.SUD) == this.pp) {
        return Direction.SUD;
      } else if (this.p.getPassage(Direction.OUEST) == this.pp) {
        return Direction.OUEST;
      } else if(this.p.getPassage(Direction.EST) == this.pp) {
        return Direction.EST;
      }
      return null;
    }
}
