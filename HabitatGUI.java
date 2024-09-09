package flowerGame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HabitatGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public HabitatGUI(Habitat hab, Habitat nursery) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1450, 1300);
		setTitle(hab.getName());
		contentPane = new JPanel();

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
		
		JLabel lblNewLabel = new JLabel(hab.getName());
		lblNewLabel.setBounds(750, 0, 100, 100);
		contentPane.add(lblNewLabel);
		
		int i = 0;
		for (Snowdrop snowdrop : hab.getFlowers()) {
			JButton buttoni = new JButton(snowdrop.getName());
			buttoni.setBounds(300 + 300*i, 300, 100, 100);
			buttoni.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								FlowerGUI flowerGUI1 = new FlowerGUI(snowdrop, nursery);
								flowerGUI1.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						});
				}
			});
			contentPane.add(buttoni);
			i++;
		}
	}

}
