package flowerGame;

import java.io.IOException;
import java.io.Serializable;
import java.time.Duration;
import java.time.temporal.TemporalAmount;

public class Basic extends Snowdrop implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5003366959183080610L;
	public static boolean[][] achieved = new boolean[mainColor.values().length][DecorC.values().length];
	public static int[] colorCollectedBefore = new int[mainColor.values().length];
	public static int[] decorCollectedBefore = new int[DecorC.values().length];
	public static int totalCollectedBefore = 0;
	private static String imagePath = "snowdrop\\flowerGame\\Snowdrop.png";
	private static Duration BasicTimeToMaturity = Duration.ofMinutes(3);
	
	public Basic(Snowdrop parent1, Snowdrop parent2, mainColor color, DecorC decor, Habitat habitat) throws IOException {
		super(0.0, Species.BASIC, imagePath, color, decor, habitat, BasicTimeToMaturity);
		this.timeToMaturity = BasicTimeToMaturity;
		if (!achieved[color.index][decor.index]) {
			achieved[color.index][decor.index] = true;
			
			colorCollectedBefore[color.index]++;
			if (colorCollectedBefore[color.index] == DecorC.values().length) {
				Logger.collectionCompleted(color, tur);
			}
			
			decorCollectedBefore[decor.index]++;
			if (decorCollectedBefore[decor.index] == mainColor.values().length) {
				Logger.collectionCompleted(decor, tur);
			}

			totalCollectedBefore++;
			if (totalCollectedBefore == mainColor.values().length*DecorC.values().length) {
				Logger.collectionCompleted(tur);
			}
		}
	}
	
	public Basic(double age, mainColor color, DecorC decor, Habitat habitat) throws IOException {
		super(age, Species.BASIC, imagePath, color, decor, habitat, BasicTimeToMaturity);
		if (!achieved[color.index][decor.index]) {
			achieved[color.index][decor.index] = true;
			
			totalCollectedBefore++;
			if (totalCollectedBefore == mainColor.values().length*DecorC.values().length) {
				Logger.collectionCompleted(tur);
			}
			
			colorCollectedBefore[color.index]++;
			if (colorCollectedBefore[color.index] == DecorC.values().length) {
				Logger.collectionCompleted(color, tur);
			}
			
			decorCollectedBefore[decor.index]++;
			if (decorCollectedBefore[decor.index] == mainColor.values().length) {
				Logger.collectionCompleted(decor, tur);
			}
		}
	}

	public static String getImagePath() {
		return imagePath;
	}
	
	public static TemporalAmount getTurTimeToMaturity() {
		return BasicTimeToMaturity;
	}
}
