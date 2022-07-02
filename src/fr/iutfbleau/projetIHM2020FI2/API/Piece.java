package fr.iutfbleau.projetIHM2020FI2.API;
import java.util.*;
import java.awt.geom.Point2D;
/**
 * Une piece a potentiellement un passage dans chaque direction
 * Une piece contient des objets (qu'on appelle des trucs pour bien faire la différence avec Object).
 */
public interface Piece extends ContientTrucs, Descriptible{
    /**
     * permet de renvoyer le passage dans la direction proposée
     * @param  d direction du passage recherché
     * @return   un passage si il y en a un et null sinon.
     */
    public Passage getPassage(Direction d);

    /**
     * permet d'associer le passage passé en paramètre dans la direction proposée.
     * En dehors de l'étape de création des pièces et passages, il faut que les choix soient cohérents.
     * Les deux arguments ne doivent pas être null
     * @throws NullPointerException si un argument est null.
     * @param p passage à associer
     * @param d direction du passage recherché
     */
    public void setPassage(Direction d, Passage p);

    /**
     * Permet de supprimer un passage (mais seulement pour la Piece).
     * C'est-à-dire que getPassage(d) retournera null après un appel de cette méthode.
     * @param d Direction dans laquelle on veut "annuler" le passage.
     */
    public void removePassage(Direction d);

     /**
     * le nombre de passages (0 n'est pas recommandé pour l'intérêt du jeu, mais est une valeur légale).
     * @return un entier positif ou nul, au maximum le nombre de directions possibles.
     *
     * @see Direction
     */
    public int combienPassages();

    /**
     * Permet de récupérer les coordonnées 2D de la pièce pour faciliter la mise en oeuvre d'un dessin.
     *
     * Comme il s'agit d'une extension de l'API originale, nous utilisons le mécanisme des méthodes par défaut
     * pour assurer la rétro-compatibilité.
     *
     * @return Point2D
     */
    default public Point2D getPosition(){
        return null;
    }

    /**
     * Permet de màj les coordonnées 2D de la pièce pour faciliter la mise en oeuvre d'un dessin.
     *
     * Comme il s'agit d'une extension de l'API originale, nous utilisons le mécanisme des méthodes par défaut
     * pour assurer la rétro-compatibilité.
     *
     */
    default public void setPosition(Point2D p){
        // default method does nothing
    }
}
