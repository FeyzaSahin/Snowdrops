package flowerGame;

import java.awt.EventQueue;
import java.io.IOException;

import flowerGame.Snowdrop.*;

public class Main {
	
	public static Habitat nursery;
	
	public static void moveFlower(Habitat source, Habitat target, Snowdrop snowdrop) {
		if (source.containsFlower(snowdrop) && target.hasRoom()) {
			source.removeFlower(snowdrop);
			target.addFlower(snowdrop);
			//add a success message
		}
		else {
			//add a warning that that didn't happen
			}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		Habitat nursery = new Habitat(8, true, "");
		Main.nursery = nursery;
		Habitat hab1 = new Habitat();
		Snowdrop frog1 = new Basic(1, mainColor.CORA, DecorC.NAVY, hab1);
		Snowdrop frog0 = new Basic(1, mainColor.LAPIS, DecorC.LEAF, hab1);
		Snowdrop frog2 = Snowdrop.breeding(frog1, frog0, nursery);
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
