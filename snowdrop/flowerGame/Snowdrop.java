package flowerGame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.HashSet;
import java.util.Random;

import javax.imageio.ImageIO;

public class Snowdrop {
	enum Species{
		BASIC("BASIC", Basic.class);
		
		public final String name;
		@SuppressWarnings("rawtypes")
		public final Class correspondingClass;
		
		private Species(String name, @SuppressWarnings("rawtypes") Class correspondingClass) {
			this.name = name;
			this.correspondingClass = correspondingClass;
		}
		
		public static Species fromStringToSpecies(String input) {
			for (Species s : Species.values()){
				if (s.name.equalsIgnoreCase(input)) {
					return s;
				}
			}
			return null;
		}
	}
	
	enum mainColor{
		BLACK("BLACK", -16777216, 0),
		BLOOD("BLOOD", -8388608, 1),
		RED("RED", -65536, 2),
		INDIGO("INDIGO", -16777088, 3),
		PLUM("PLUM", -8388480, 4),
		MAGENTA("MAGENTA", -65408, 5),
		LAPIS("LAPIS", -16776961, 6),
		AMETHYST("AMETHYST", -8388353, 7),
		PINK("PINK", -65281, 8),
		FOREST("FOREST", -16744448, 9),
		CLAY("CLAY", -8355840, 10),
		ORANGE("ORANGE", -32768, 11),
		OCEAN("OCEAN", -16744320, 12),
		GRAY("GRAY", -8355712, 13),
		CORA("CORA", -32640, 14),
		BLUE("BLUE", -16744193, 15),
		LILAC("LILAC", -8355585, 16),
		DREAMCANDY("DREAMCANDY", -32513, 17),
		GREEN("GREEN", -16711936, 18),
		LIME("LIME", -8323328, 19),
		YELLOW("YELLOW", -256, 20),
		SPROUT("SPROUT", -16711808, 21),
		SPRING("SPRING", -8323200, 22),
		SUNWARM("SUNWARM", -128, 23),
		CYAN("CYAN", -16711681, 24),
		ARCTIC("ARCTIC", -8323073, 25),
		WHITE("WHITE", -1, 26),
		SPECTER("SPECTER", 855638015, 27);
		
		public final String name;
		public final int rgbVal;
		public final int index;
		
		private mainColor(String name, int rgbVal, int index) {
			this.name = name;
			this.rgbVal = rgbVal;
			this.index = index;
		}
		
		public static mainColor fromStringToMainColor(String input) {
			for (mainColor s : mainColor.values()){
				if (s.name.equalsIgnoreCase(input)) {
					return s;
				}
			}
			return null;
		}
	}

	enum DecorC{
		
		TAR("TAR", -12566464, 0),
		SCARLET("SCARLET", -4177856, 1),
		LEAF("LEAF", -12533696, 2),
		NAVY("NAVY", -12566336, 3),
		HAY("HAY", -4145088, 4),
		PURPLE("PURPLE", -4177728, 5),
		MARINE("MARINE", -12533568, 6),
		SILVER("SILVER", -4144960, 7);
		
		public final int rgbVal;
		public final int index;
		public final String name;
		
		private DecorC(String name, int rgbVal, int index) {
			this.rgbVal = rgbVal;
			this.index = index;
			this.name = name;
		}
		
		public static DecorC fromStringToDecor(String input) {
			for (DecorC s : DecorC.values()){
				if (s.name.equalsIgnoreCase(input)) {
					return s;
				}
			}
			return null;
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
	protected LocalDateTime creationTime;
	protected TemporalAmount timeToMaturity;
	
	public static HashSet<Snowdrop> getCurrentFrogs() {
		HashSet<Snowdrop> snowdrops = new HashSet<Snowdrop>();
		for (Habitat hab : Habitat.getHabitats()) {
			snowdrops.addAll(hab.getFlowers());
		}
		return snowdrops;
	}
	public static Snowdrop breeding(Snowdrop flower1, Snowdrop flower2) throws IOException { //plant breeding dynamic
		//idea for not immediately revealing the babies: create an unambiguous seed/sprout, wait for some time depending on species, then delete the seed/sprout and reveal the plant
		boolean breedingPossible = (flower1.age == 1 && flower2.age == 1 && flower1 != flower2);
		Logger.breedingEvent(flower1, flower2, breedingPossible);
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
				return new Basic(flower1, flower2, newColor, newDecor, flower1.getHabitat());
			}
		} else {
			System.out.println("These flowers cannot be breeded.");
		}
		return null;
	}
	
	public Snowdrop(double age, Species tur, String currentImagePath,
			mainColor color, DecorC decor, Habitat habitat, Duration timeToMaturity) throws IOException {
		this.age = age;
		this.tur = tur;
		this.color = color;
		this.decor = decor;
		this.habitat = habitat;
		habitat.addFlower(this);
		this.name = color.name() + " " + decor.name() + " " + tur.name();
		this.flowerImg = ImageIO.read(new File(currentImagePath));
		this.creationTime = LocalDateTime.now();
		this.timeToMaturity = timeToMaturity;
		colorImage();
		Logger.newFlower(this);
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
	public TemporalAmount getTimeToMaturity() {
		return timeToMaturity;
	}
}
