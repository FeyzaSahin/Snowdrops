package flowerGame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.Color;

public class FlowerGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public FlowerGUI(Snowdrop snowdrop) throws IOException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                Logger.closeLogger();
            }
        });
		setBounds(0, 0, 1450, 1300);
		setTitle(snowdrop.getName());
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
		
		JLabel nameDisplay = new JLabel("Name: " + snowdrop.getName());
		nameDisplay.setBounds(750, 70, 1000, 20);
		contentPane.add(nameDisplay);
		
		JLabel ageDisplay = new JLabel("Age: " + String.format("%.2f", snowdrop.getAge()));//add time left until maturity
		ageDisplay.setBounds(750, 100, 100, 20);
		contentPane.add(ageDisplay);
		
		JButton breed = new JButton("Breed this flower with: ");
		breed.setBounds(500, 300, 300, 40);
		breed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							BreedingGUI frame = new BreedingGUI(snowdrop);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					});
			}
		});
		contentPane.add(breed);
		JLabel flowerImage = new JLabel(new ImageIcon(snowdrop.flowerImg));
		flowerImage.setForeground(Color.RED);
		flowerImage.setBounds(500, 70, 200, 200);
		contentPane.add(flowerImage);
	}
}
