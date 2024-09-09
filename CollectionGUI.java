package flowerGame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import flowerGame.Snowdrop.DecorC;
import flowerGame.Snowdrop.Species;
import flowerGame.Snowdrop.mainColor;

public class CollectionGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	enum Type{
		GENERAL,
		SPECIES,
		COLOR;
	}
	public CollectionGUI(Type type, String name, Species species, mainColor color) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle(name);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton backButton = new JButton("Go back");
		backButton.setBounds(10, 10, 100, 30);
		backButton.setBorder(new EmptyBorder(10, 10, 10, 10));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						setVisible(false);
					}
					});
			}
		});
		contentPane.setLayout(null);
		contentPane.add(backButton);
		
		switch(type) {
			case COLOR:
				int k = 0;
				for (DecorC oColor : DecorC.values()) {
					JLabel label = new JLabel(oColor.name() + " " + color.name() + " " + species.name());
					label.setBounds(100, 50*k+ 100, 300, 50);
					
					contentPane.add(label);
					k++;
				}
				break;
			case GENERAL:
				int i = 0;
				for (Species tur : Species.values()) {
					JButton button = new JButton(tur.name());
					button.setBounds(100 + 100*i, 100, 100, 100);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										CollectionGUI frame = new CollectionGUI(Type.SPECIES, tur.name() +  " Collection", tur, null);
										frame.setVisible(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
						}
					});
					contentPane.add(button);
					i++;
				}
				break;
			case SPECIES:
				int j = 0;
				for (mainColor frcolor : mainColor.values()) {
					JButton button = new JButton(frcolor.name());
					button.setBounds(100 + 100*j, 100, 100, 100);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										CollectionGUI frame = new CollectionGUI(Type.COLOR, frcolor.name() +  " " + species.name() +  "Collection", species, frcolor);
										frame.setVisible(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
						}
					});
					contentPane.add(button);
					j++;
				}
				break;
		}
	}

}
