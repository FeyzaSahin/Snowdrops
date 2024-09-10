package flowerGame;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class NotificationGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public NotificationGUI(String message, Habitat nursery) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                Logger.closeLogger();
            }
        });
		setBounds(300, 300, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		
		JLabel screen = new JLabel(message);
		contentPane.add(screen);
		
		JButton button = new JButton("Go to nursery");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							HabitatGUI frame = new HabitatGUI(nursery, nursery);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					});
			}
		});
		contentPane.add(button);
		
		
		JButton buttonmain = new JButton("OK");
		buttonmain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		contentPane.add(buttonmain);
		
		
	}

}
