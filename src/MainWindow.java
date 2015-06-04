import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainWindow extends JFrame {

	private JPanel contentPane;
	private MainCanvas stage;
	private double andriodx = -1.5, Evax = 1.5;
	
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
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnNewButton_3 = new JButton("Start dance");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stage.startRobotsDance();
			}
		});
		
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("Add Android");
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
		
		JButton btnNewButton = new JButton("Add eva");
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
		
		JButton btnNewButton_1 = new JButton("Stop dance");
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_4 = new JButton("Start Music");
		panel.add(btnNewButton_4);
	}

}
