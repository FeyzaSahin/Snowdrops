package flowerGame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;

import javax.imageio.ImageIO;

public class Snowdrop {
	enum Species{
		BASIC;
	}
	
	enum mainColor{
		BLACK(-16777216, 0),
		BLOOD(-8388608, 1),
		RED(-65536, 2),
		INDIGO(-16777088, 3),
		PLUM(-8388480, 4),
		MAGENTA(-65408, 5),
		LAPIS(-16776961, 6),
		AMETHYST(-8388353, 7),
		PINK(-65281, 8),
		FOREST(-16744448, 9),
		CLAY(-8355840, 10),
		ORANGE(-32768, 11),
		OCEAN(-16744320, 12),
		GRAY(-8355712, 13),
		CORA(-32640, 14),
		BLUE(-16744193, 15),
		LILAC(-8355585, 16),
		DREAMCANDY(-32513, 17),
		GREEN(-16711936, 18),
		LIME(-8323328, 19),
		YELLOW(-256, 20),
		SPROUT(-16711808, 21),
		SPRING(-8323200, 22),
		SUNWARM(-128, 23),
		CYAN(-16711681, 24),
		ARCTIC(-8323073, 25),
		WHITE(-1, 26),
		SPECTER(855638015, 27);
		
		public final int rgbVal;
		public final int index;
		
		private mainColor(int rgbVal, int index) {
			this.rgbVal = rgbVal;
			this.index = index;
		}
	}

	enum DecorC{
		
		TAR(-12566464, 0),
		SCARLET(-4177856, 1),
		LEAF(-12533696, 2),
		NAVY(-12566336, 3),
		HAY(-4145088, 4),
		PURPLE(-4177728, 5),
		MARINE(-12533568, 6),
		SILVER(-4144960, 7);
		
		public final int rgbVal;
		public final int index;
		
		private DecorC(int rgbVal, int index) {
			this.rgbVal = rgbVal;
			this.index = index;
		}
	}

	protected double age; //age'i türe göre değişen bir kesire dönüştür
	protected Species tur;
	protected mainColor color;
	protected DecorC decor;
	protected Habitat habitat;
	protected String name;
	protected BufferedImage flowerImg;
	protected String currentImagePath;
	
	public static HashSet<Snowdrop> getCurrentFrogs() {
		HashSet<Snowdrop> snowdrops = new HashSet<Snowdrop>();
		for (Habitat hab : Habitat.getHabitats()) {
			snowdrops.addAll(hab.getFlowers());
		}
		return snowdrops;
	}
	public static Snowdrop breeding(Snowdrop flower1, Snowdrop flower2, Habitat nursery) throws IOException { //plant breeding dynamic
		boolean breedingPossible = (flower1.age == 1 && flower2.age == 1 && flower1 != flower2);
		if (breedingPossible) {
			Random rand = new Random();
			Species newTur;
			if (rand.nextBoolean()) {
				newTur = flower1.tur;
			} else {
				newTur = flower2.tur;
			}
			mainColor newColor;
			DecorC newDecor;
			if (rand.nextBoolean()) {
				newColor = flower1.color;
			} else {
				newColor = flower2.color;
			}
			if (rand.nextBoolean()) {
				newDecor = flower1.decor;
			} else {
				newDecor = flower2.decor;
			}
			switch (newTur) {
			case BASIC:
				return new Basic(flower1, flower2, newColor, newDecor, nursery);
			}
		} else {
			System.out.println("These flowers cannot be breeded.");
		}
		return null;
	}
	
	public Snowdrop(double age, Species tur, String currentImagePath,
			mainColor color, DecorC decor, Habitat habitat) throws IOException {
		this.age = age;
		this.tur = tur;
		this.color = color;
		this.decor = decor;
		this.habitat = habitat;
		habitat.addFlower(this);
		this.name = color.name() + " " + decor.name() + " " + tur.name();
		this.flowerImg = ImageIO.read(new File(currentImagePath));
		colorImage();
	}
	
	public void colorImage() {
		int width = flowerImg.getWidth();
		int height = flowerImg.getHeight();
		int[] pixels = flowerImg.getRGB(0, 0, width, height, null, 0, width);
		int[] newPix = new int[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
	        int red = (pixels[i] & 0x00FF0000) >> 16;
	        int green = (pixels[i] & 0x0000FF00) >> 8;
	        int blue = (pixels[i] & 0x000000FF) >> 0;
	        
	        if (red > 200 && green > 200 && blue > 200) {
	        	newPix[i] = 0 << 24 | (red & 0xFF) << 16 | (green & 0xFF) << 8 | (blue & 0xFF);
	        } else if (red < 50 && green < 50 && blue < 50) {
	        	newPix[i] = color.rgbVal;
	        } else if (red > 200) {
	        	newPix[i] = decor.rgbVal;
	        } else {
	        	newPix[i] = pixels[i];
	        }
		}
		flowerImg.setRGB(0, 0, width, height, newPix, 0, width);
	}
	public String getName() {
		return name;
	}
	public double getAge() {
		return age;
	}
	public void increaseAge(double increment) {
		if (age + increment > 1) {
			age = 1;
		} else {
			age += increment;
		}
	}
	public Species getTur() {
		return tur;
	}
	public mainColor getColor() {
		return color;
	}
	public DecorC getDecor() {
		return decor;
	}
	public Habitat getHabitat() {
		return habitat;
	}
	
}
