package flowerGame;

import java.io.FileWriter;
import java.io.IOException;

import flowerGame.Snowdrop.*;

import java.io.File;

public class Logger {
	public static Logger log = new Logger();
	private FileWriter logger;
	
	public static void closeLogger() {
		try {
			log.logger.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Logger() {
		try {
			logger = new FileWriter(new File("snowdrop\\flowerGame\\Log.txt"), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void newHab(Habitat newHab) {
		try {
			if (newHab.isNursery()) {
				log.logger.write("There is a new nursery with the capacity of " + newHab.getCapacity() + ".\n");
			} else {
				log.logger.write("There is a new habitat called " + newHab.getName() + " with the capacity of " + newHab.getCapacity() + ".\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void newFlower(Snowdrop flower) {//add a new argument about how the flower came and include it
		try {
			log.logger.write("There is a new " + flower.getAge() + " year old " + flower.getName() + " in " + flower.getHabitat().getName() + ".\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void breedingEvent(Snowdrop flower1, Snowdrop flower2, boolean success) {
		if (success) {
			try {
				log.logger.write(flower1.getName() + " and " + flower2.getName() + " produced a new seed.\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				log.logger.write(flower1.getName() + " and " + flower2.getName() + " cannot be breeded.\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void collectionCompleted(mainColor color, Species tur) {
		try {
			log.logger.write(color + " ? " + tur + " collection is completed.\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void collectionCompleted(DecorC color, Species tur) {
		try {
			log.logger.write("? " + color + " " + tur + " collection is completed.\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void collectionCompleted(Species tur) {
		try {
			log.logger.write(tur + " collection is completed.\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void move(Habitat source, Habitat target, Snowdrop snowdrop, boolean success) {
		try {
			if (success) {
				log.logger.write(snowdrop + " has been moved from + " + source + " to " + target + " successfully.\n");
			} else {
				log.logger.write(snowdrop + " cannot be moved from + " + source + " to " + target + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
