JCC = javac
JFLAGS = -Xlint:unchecked -implicit:none -encoding UTF-8 -d build -classpath build -sourcepath "src"
SRC = src/fr/iutfbleau/projetIHM2020FI2/
BUILD = build/fr/iutfbleau/projetIHM2020FI2/

#javadoc -sourcepath ./src -classpath build -d ./docs -subpackages fr.iutfbleau.projetIHM2020FI2

default: $(BUILD)SRC/Main.class $(BUILD)SRC/View.class $(BUILD)SRC/Controller.class $(BUILD)SRC/GridDraw.class $(BUILD)SRC/ZoomGrid.class $(BUILD)SRC/ManipGrid.class $(BUILD)SRC/ModeButton.class $(BUILD)SRC/PopUpOpen.class $(BUILD)SRC/New.class $(BUILD)SRC/Saver.class $(BUILD)SRC/PopupName.class $(BUILD)SRC/Opener.class $(BUILD)SRC/ModeSupp.class $(BUILD)SRC/ModeCouloir.class $(BUILD)SRC/ModePiece.class $(BUILD)SRC/PopUpFleche.class $(BUILD)SRC/CroixDir.class $(BUILD)SRC/PopUpDelete.class $(BUILD)SRC/CroixDelete.class $(BUILD)SRC/QuelleDirection.class $(BUILD)MP/TrucFactoryP.class $(BUILD)MP/PieceP.class $(BUILD)MP/PassagePieceFactoryP.class $(BUILD)MP/PassageP.class $(BUILD)MP/JoueurP.class $(BUILD)MP/ContientTrucsP.class $(BUILD)MP/Connexion.class $(BUILD)MNP/TrucNP.class $(BUILD)MNP/TrucFactoryNP.class $(BUILD)MNP/PieceNP.class $(BUILD)MNP/PassagePieceFactoryNP.class $(BUILD)MNP/PassageNP.class $(BUILD)MNP/JoueurNP.class $(BUILD)MNP/ContientTrucsNP.class $(BUILD)API/TypeTruc.class $(BUILD)API/TrucFactory.class $(BUILD)API/Truc.class $(BUILD)API/Piece.class $(BUILD)API/PassagePieceFactory.class $(BUILD)API/Passage.class $(BUILD)API/Joueur.class $(BUILD)API/EtatPassage.class $(BUILD)API/Direction.class $(BUILD)API/Descriptible.class $(BUILD)API/ContientTrucs.class $(BUILD)API/Activable.class


$(BUILD)SRC/Main.class : $(SRC)SRC/Main.java
	$(JCC) $(JFLAGS) $(SRC)SRC/Main.java

$(BUILD)API/Activable.class: $(SRC)API/Activable.java
	$(JCC) $(JFLAGS) $(SRC)API/Activable.java
$(BUILD)API/ContientTrucs.class: $(SRC)API/ContientTrucs.java
	$(JCC) $(JFLAGS) $(SRC)API/ContientTrucs.java
$(BUILD)API/Descriptible.class: $(SRC)API/Descriptible.java
	$(JCC) $(JFLAGS) $(SRC)API/Descriptible.java
$(BUILD)API/Direction.class: $(SRC)API/Direction.java
	$(JCC) $(JFLAGS) $(SRC)API/Direction.java
$(BUILD)API/EtatPassage.class: $(SRC)API/EtatPassage.java
	$(JCC) $(JFLAGS) $(SRC)API/EtatPassage.java
$(BUILD)API/Joueur.class: $(SRC)API/Joueur.java
	$(JCC) $(JFLAGS) $(SRC)API/Joueur.java
$(BUILD)API/Passage.class: $(SRC)API/Passage.java
	$(JCC) $(JFLAGS) $(SRC)API/Passage.java
$(BUILD)API/PassagePieceFactory.class: $(SRC)API/PassagePieceFactory.java
	$(JCC) $(JFLAGS) $(SRC)API/PassagePieceFactory.java
$(BUILD)API/Piece.class: $(SRC)API/Piece.java
	$(JCC) $(JFLAGS) $(SRC)API/Piece.java
$(BUILD)API/Truc.class: $(SRC)API/Truc.java
	$(JCC) $(JFLAGS) $(SRC)API/Truc.java
$(BUILD)API/TrucFactory.class: $(SRC)API/TrucFactory.java
	$(JCC) $(JFLAGS) $(SRC)API/TrucFactory.java
$(BUILD)API/TypeTruc.class: $(SRC)API/TypeTruc.java
	$(JCC) $(JFLAGS) $(SRC)API/TypeTruc.java



$(BUILD)MNP/ContientTrucsNP.class: $(SRC)MNP/ContientTrucsNP.java
	$(JCC) $(JFLAGS) $(SRC)MNP/ContientTrucsNP.java
$(BUILD)MNP/JoueurNP.class: $(SRC)MNP/JoueurNP.java
	$(JCC) $(JFLAGS) $(SRC)MNP/JoueurNP.java
$(BUILD)MNP/PassageNP.class: $(SRC)MNP/PassageNP.java
	$(JCC) $(JFLAGS) $(SRC)MNP/PassageNP.java
$(BUILD)MNP/PassagePieceFactoryNP.class: $(SRC)MNP/PassagePieceFactoryNP.java
	$(JCC) $(JFLAGS) $(SRC)MNP/PassagePieceFactoryNP.java
$(BUILD)MNP/PieceNP.class: $(SRC)MNP/PieceNP.java
	$(JCC) $(JFLAGS) $(SRC)MNP/PieceNP.java
$(BUILD)MNP/TrucFactoryNP.class: $(SRC)MNP/TrucFactoryNP.java
	$(JCC) $(JFLAGS) $(SRC)MNP/TrucFactoryNP.java
$(BUILD)MNP/TrucNP.class: $(SRC)MNP/TrucNP.java
	$(JCC) $(JFLAGS) $(SRC)MNP/TrucNP.java



$(BUILD)MP/ContientTrucsP.class: $(SRC)MP/ContientTrucsP.java
	$(JCC) $(JFLAGS) $(SRC)MP/ContientTrucsP.java
$(BUILD)MP/JoueurP.class: $(SRC)MP/JoueurP.java
	$(JCC) $(JFLAGS) $(SRC)MP/JoueurP.java
$(BUILD)MP/PassageP.class: $(SRC)MP/PassageP.java
	$(JCC) $(JFLAGS) $(SRC)MP/PassageP.java
$(BUILD)MP/PassagePieceFactoryP.class: $(SRC)MP/PassagePieceFactoryP.java
	$(JCC) $(JFLAGS) $(SRC)MP/PassagePieceFactoryP.java
$(BUILD)MP/PieceP.class: $(SRC)MP/PieceP.java
	$(JCC) $(JFLAGS) $(SRC)MP/PieceP.java
$(BUILD)MP/TrucFactoryP.class: $(SRC)MP/TrucFactoryP.java
	$(JCC) $(JFLAGS) $(SRC)MP/TrucFactoryP.java
$(BUILD)MP/TrucP.class: $(SRC)MP/TrucP.java
	$(JCC) $(JFLAGS) $(SRC)MP/TrucP.java
$(BUILD)MP/Connexion.class: $(SRC)MP/Connexion.java
	$(JCC) $(JFLAGS) $(SRC)MP/Connexion.java


## SRC ##

##$(BUILD)SRC/Main.class : $(SRC)SRC/Main.java
	##$(JCC) $(JFLAGS) $(SRC)SRC/Main.java
$(BUILD)SRC/View.class : $(SRC)SRC/View.java
	$(JCC) $(JFLAGS) $(SRC)SRC/View.java
$(BUILD)SRC/Controller.class : $(SRC)SRC/Controller.java
	$(JCC) $(JFLAGS) $(SRC)SRC/Controller.java
$(BUILD)SRC/GridDraw.class : $(SRC)SRC/GridDraw.java
	$(JCC) $(JFLAGS) $(SRC)SRC/GridDraw.java
$(BUILD)SRC/ZoomGrid.class : $(SRC)SRC/ZoomGrid.java
	$(JCC) $(JFLAGS) $(SRC)SRC/ZoomGrid.java
$(BUILD)SRC/ManipGrid.class : $(SRC)SRC/ManipGrid.java
	$(JCC) $(JFLAGS) $(SRC)SRC/ManipGrid.java
$(BUILD)SRC/ModeButton.class : $(SRC)SRC/ModeButton.java
	$(JCC) $(JFLAGS) $(SRC)SRC/ModeButton.java
$(BUILD)SRC/PopUpOpen.class : $(SRC)SRC/PopUpOpen.java
	$(JCC) $(JFLAGS) $(SRC)SRC/PopUpOpen.java
$(BUILD)SRC/Saver.class : $(SRC)SRC/Saver.java
	$(JCC) $(JFLAGS) $(SRC)SRC/Saver.java
$(BUILD)SRC/PopupName.class : $(SRC)SRC/PopupName.java
	$(JCC) $(JFLAGS) $(SRC)SRC/PopupName.java
$(BUILD)SRC/ModeSupp.class : $(SRC)SRC/ModeSupp.java
	$(JCC) $(JFLAGS) $(SRC)SRC/ModeSupp.java
$(BUILD)SRC/ModeCouloir.class : $(SRC)SRC/ModeCouloir.java
	$(JCC) $(JFLAGS) $(SRC)SRC/ModeCouloir.java
$(BUILD)SRC/ModePiece.class : $(SRC)SRC/ModePiece.java
	$(JCC) $(JFLAGS) $(SRC)SRC/ModePiece.java
$(BUILD)SRC/PopUpFleche.class : $(SRC)SRC/PopUpFleche.java
	$(JCC) $(JFLAGS) $(SRC)SRC/PopUpFleche.java
$(BUILD)SRC/CroixDir.class : $(SRC)SRC/CroixDir.java
	$(JCC) $(JFLAGS) $(SRC)SRC/CroixDir.java
$(BUILD)SRC/PopUpDelete.class : $(SRC)SRC/PopUpDelete.java
	$(JCC) $(JFLAGS) $(SRC)SRC/PopUpDelete.java
$(BUILD)SRC/CroixDelete.class : $(SRC)SRC/CroixDelete.java
	$(JCC) $(JFLAGS) $(SRC)SRC/CroixDelete.java
$(BUILD)SRC/QuelleDirection.class : $(SRC)SRC/QuelleDirection.java
	$(JCC) $(JFLAGS) $(SRC)SRC/QuelleDirection.java
$(BUILD)SRC/Opener.class : $(SRC)SRC/Opener.java
	$(JCC) $(JFLAGS) $(SRC)SRC/Opener.java
$(BUILD)SRC/New.class : $(SRC)SRC/New.java
	$(JCC) $(JFLAGS) $(SRC)SRC/New.java

run:
		$(JV) fr.iutfbleau.projetIHM2020FI2.SRC.Main
jar:
	jar cvfe projet.jar fr.iutfbleau.projetIHM2020FI2.SRC.Main -C build fr -C build org -C res IMG
clean:
	rm -Rf $(BUILD)*
