package dvt.devint;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

import java.io.File;

/** classe pour gérer les préférences
 * 
 * TODO : donner la possibilité de mettre des styles dans le répertoire et
 *        lire le contenu du répertoire
 * @author helen
 *
 */
public final class Preferences {
	// gestion des couleurs
	private final static String[] styleColorSheet ={"colorStyle1.css","colorStyle2.css","colorStyle3.css","colorStyle4.css","colorStyle5.css","colorStyle6.css"} ;
	// gestion des fontes
	private final static String[] styleFontSheet ={"fontStyleMoyen.css","fontStyleGrand.css","fontStylePetit.css"} ;
	private static String STYLES_PATH="../ressources/styles/";
	private static int numberOfColorStyles=styleColorSheet.length;
	private static int currentColorStyle=0;
	private static int numberOfFontStyles=styleFontSheet.length;
	private static int currentFontStyle=0;
	// gestion de la souris
	private static int numberOfCursors=3;
	private static int currentCursor=0;
	
	public static String currentColorStyle() {
		File styleFile = new File(STYLES_PATH+styleColorSheet[currentColorStyle]);
	    return "file:///" + styleFile.getAbsolutePath().replace("\\", "/");
	}

	public static void nextColorStyle() {
		currentColorStyle = (currentColorStyle+1)%numberOfColorStyles;
	}
	
	public static String currentFontStyle() {
		File styleFile = new File(STYLES_PATH+styleFontSheet[currentFontStyle]);
	    return "file:///" + styleFile.getAbsolutePath().replace("\\", "/");
	}

	public static void nextFontStyle() {
		currentFontStyle = (currentFontStyle+1)%numberOfFontStyles;
	}
	
	public static Cursor currentCursor() {
		switch (currentCursor) {
		case 1: return new ImageCursor(new Image(ConstantesDevint.ressourcesFileName("images/cursor1.png")));
		case 2: return new ImageCursor(new Image(ConstantesDevint.ressourcesFileName("images/cursor2.png")));
		default: return Cursor.DEFAULT;
		}
	}
	
	public static void nextCursor() {
		currentCursor = (currentCursor+1)%numberOfCursors;
	}

}
