package flowerGame;

import java.awt.EventQueue;

import java.io.*;
import java.io.FileInputStream;
import java.util.Scanner;

import flowerGame.Snowdrop.*;

public class Main {
	
	public static Habitat nursery;

	public static void moveFlower(Habitat source, Habitat target, Snowdrop snowdrop) {
		if (source.containsFlower(snowdrop) && target.hasRoom()) {
			source.removeFlower(snowdrop);
			target.addFlower(snowdrop);
			Logger.move(source, target, snowdrop, true);
		}
		else {
			//add a warning that that didn't happen
			Logger.move(source, target, snowdrop, false);
			}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("snowdrop\\flowerGame\\saveFile.csv"));
		while (in.hasNext()) {
			String currentLine = in.nextLine();
			if (currentLine.equals("")) {
				continue;
			}
			String[] splitLine = currentLine.split(",");
			if (splitLine[0].equals("Habitat")) {
				int habCap = Integer.parseInt(splitLine[1]);
				boolean habNursery = Boolean.parseBoolean(splitLine[2]);
				String habName = splitLine[3];
				Habitat currentHab = new Habitat(habCap, habNursery, habName);
				for (int i = 0; i < habCap; i++) {
					currentLine = in.nextLine();
					if (currentLine.equals("")) {
						continue;
					}
					splitLine = currentLine.split(",");
					if (splitLine[0].equals("Snowdrop")) {
						Species currentSp = Snowdrop.Species.fromStringToSpecies(splitLine[1]);
						double readAge = Double.parseDouble(splitLine[2]);
						mainColor readColor = Snowdrop.mainColor.fromStringToMainColor(splitLine[3]);
						DecorC readDecor = Snowdrop.DecorC.fromStringToDecor(splitLine[4]);
						switch (currentSp) {
							case BASIC:
								new Basic(readAge, readColor, readDecor, currentHab);
						}
					}
				}
			}
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			});
	}

}
