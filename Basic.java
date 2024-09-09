package flowerGame;

import java.io.IOException;

public class Basic extends Snowdrop{
	public static boolean[][] achieved = new boolean[mainColor.values().length][DecorC.values().length];
	private static String imagePath = "src\\flowerGame\\Snowdrop.png";
	
	public Basic(Snowdrop parent1, Snowdrop parent2, mainColor color, DecorC decor, Habitat habitat) throws IOException {
		super(0.0, Species.BASIC, imagePath, color, decor, habitat);
		achieved[color.index][decor.index] = true;
	}
	
	public Basic(double age, mainColor color, DecorC decor, Habitat habitat) throws IOException {
		super(age, Species.BASIC, imagePath, color, decor, habitat);
		achieved[color.index][decor.index] = true;
	}

	public static String getImagePath() {
		return imagePath;
	}
	
}
