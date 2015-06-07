import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.j3d.utils.universe.SimpleUniverse;


public class MainWindow extends JFrame {

	private JPanel contentPane;
	private MainCanvas stage;
	private double andriodx = -1.5, Evax = 1.5;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	
	public MainWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		stage = new MainCanvas(config);
		Application.getInstance().getAnimationManager().registerCanvas(stage);
		
		getContentPane().add(BorderLayout.CENTER, stage);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 4, 150, 150));
		
		btnNewButton_3 = new JButton(this.getScaledImageIcon(Resources.playDanceButtonPath));
		
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(stage.isDancingMode()) {
					stage.stopRobotsDance();
					btnNewButton_3.setIcon(getScaledImageIcon(Resources.playDanceButtonPath));
				}
				else {
					stage.startRobotsDance();
					btnNewButton_3.setIcon(getScaledImageIcon(Resources.stopDanceButtonPath));
				}
			}
		});
		
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton(this.getScaledImageIcon(Resources.androidButtonPath));
		btnNewButton_2.setBackground(Color.WHITE);
		
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setPreferredSize(new Dimension(40, 40));
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Android a = new Android();
				a.setDance(new AndroidDance(a));
				
				stage.addRobot(a);
				stage.registerAnimatableObjects(a);
				
				a.branch.transition(andriodx, 0, 0);
				
				andriodx -= 3;
			}
		});
		panel.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton(this.getScaledImageIcon(Resources.evaButtonPath));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eva a = new Eva();
				a.setDance(new EvaDance(a));
				
				stage.addRobot(a);
				stage.registerAnimatableObjects(a);
				
				a.branch.transition(Evax, 0, 0);
				
				Evax += 3;
			}
		});
		panel.add(btnNewButton);
		
		btnNewButton_4 = new JButton(this.getScaledImageIcon(Resources.playMusicButtonPath));
		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.setContentAreaFilled(false);
		
		btnNewButton_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Resources.backgroundSound.isPaused() || Resources.backgroundSound.isStopped()) {
					btnNewButton_4.setIcon(MainWindow.this.getScaledImageIcon(Resources.stopMusicButtonPath));
					Resources.backgroundSound.play();
					Resources.backgroundSound.endHandler = new MusicEndHandler() {
						
						@Override
						public void musicEnded() {
							// TODO Auto-generated method stub
							btnNewButton_4.setIcon(MainWindow.this.getScaledImageIcon(Resources.playMusicButtonPath));
						}
					};
				}
				else {
					btnNewButton_4.setIcon(MainWindow.this.getScaledImageIcon(Resources.playMusicButtonPath));
					Resources.backgroundSound.pause();
				}
			}
		});
		
		panel.add(btnNewButton_4);
	}

	public ImageIcon getScaledImageIcon(String imagePath) {
		
		int scale = 3; // 3 times smaller for icon image
		
		ImageIcon imageIcon = new ImageIcon(imagePath);
		int width = imageIcon.getIconWidth();
		int newWidth = width / scale;
		return  new ImageIcon(imageIcon.getImage().getScaledInstance(newWidth, -1,
		            java.awt.Image.SCALE_SMOOTH));
	}
	
}
