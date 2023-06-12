package Code;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BackgroundFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackgroundFrame frame = new BackgroundFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setCheckerBackground() {
		JLabel availImg = new JLabel("");
		availImg.setIcon(new ImageIcon(GuestChecker.class.getResource("/IMAGES/AVAILABILITYCHECKING.png")));
		availImg.setBounds(0, 0, 984, 561);
		getContentPane().add(availImg);
		
		setTitle("Calendar");
        
	}
	
	/**
	 * Create the frame.
	 */
	public BackgroundFrame() {
		
        setBounds(100, 100, 1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        
        
	}

}
