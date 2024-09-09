package flowerGame;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BreedingGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public BreedingGUI(Snowdrop snowdrop, Habitat nursery) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1450, 1300);
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
		
		int i = 0;
		for (Snowdrop otherflower : snowdrop.getHabitat().getFlowers()) {
			if (otherflower != snowdrop) {
			JButton buttoni = new JButton(otherflower.getName());
			buttoni.setBounds(300 + 300*i, 300, 100, 100);
			buttoni.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Snowdrop.breeding(otherflower, snowdrop, nursery);
								NotificationGUI success = new NotificationGUI("Breeding successful", nursery);
								success.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						});
				}
			});
			contentPane.add(buttoni);
			i++;
		}}
	}

}
