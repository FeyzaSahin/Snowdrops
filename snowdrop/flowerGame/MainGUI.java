package flowerGame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		Habitat nursery = Main.nursery;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                Logger.closeLogger();
                File file = new File("snowdrop\\flowerGame\\saveFile.csv");
                try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(file));
					writer.write("");
					for (Habitat hab : Habitat.getHabitats()) {
						writer.append("Habitat," + hab.getCapacity() + "," + hab.isNursery() + "," + hab.getName() + "\n");
						for (Snowdrop sp : hab.getFlowers()) {
							writer.append("Snowdrop," + sp.tur.name + "," + sp.getAge() + "," + sp.getColor() + "," + sp.decor + "\n");
						}
						for (int i = 0; i < hab.getCapacity() - hab.getPopulation(); i++) {
							writer.append("None\n");
						}
					}
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
		setBounds(0, 0, 1450, 1300);
		setTitle("Main Menu");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton time = new JButton("Pass 0.1 years");
		time.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						for (Snowdrop fr : Snowdrop.getCurrentFrogs()) {
							fr.increaseAge(0.1);
						}
					}
				});}});
		contentPane.add(time);
		
		JButton collection = new JButton("View the Collection");
		collection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							CollectionGUI frame = new CollectionGUI(CollectionGUI.Type.GENERAL, "Flower Collection", null, null);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});}});
		contentPane.add(collection);
		
		
		int habCount = 0;
		Habitat currentHab = Habitat.getHabitats().get(habCount);
		final Habitat fcHab = currentHab;
		JButton btnNewButton = new JButton(currentHab.getName());
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							HabitatGUI frame0 = new HabitatGUI(fcHab, nursery);
							frame0.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		contentPane.add(btnNewButton);
		habCount++;
		
		if (habCount < Habitat.getHabCounter()) {//find a way to create new buttons in a loop
			currentHab = Habitat.getHabitats().get(habCount);
			final Habitat fcHab1 = currentHab;
		JButton Button1 = new JButton(currentHab.getName());
		Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							HabitatGUI frame1 = new HabitatGUI(fcHab1, nursery);
							frame1.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		contentPane.add(Button1);
		habCount ++;}
		
		if (habCount < Habitat.getHabCounter()) {
			currentHab = Habitat.getHabitats().get(habCount);
			final Habitat fcHab2 = currentHab;
			JButton Button2 = new JButton(Habitat.getHabitats().get(habCount).getName());
			Button2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								HabitatGUI frame2 = new HabitatGUI(fcHab2, nursery);
								frame2.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			});
			contentPane.add(Button2);
			habCount ++;}
		
		if (habCount < Habitat.getHabCounter()) {
			currentHab = Habitat.getHabitats().get(habCount);
			final Habitat fcHab3 = currentHab;
			JButton Button3 = new JButton(Habitat.getHabitats().get(habCount).getName());
			Button3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								HabitatGUI frame3 = new HabitatGUI(fcHab3, nursery);
								frame3.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			});
			contentPane.add(Button3);
			habCount ++;}
		
		if (habCount < Habitat.getHabCounter()) {
			currentHab = Habitat.getHabitats().get(habCount);
			final Habitat fcHab4 = currentHab;
			JButton Button4 = new JButton(Habitat.getHabitats().get(habCount).getName());
			Button4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								HabitatGUI frame0 = new HabitatGUI(fcHab4, nursery);
								frame0.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			});
			contentPane.add(Button4);
			habCount ++;}
		
		
	}

}
