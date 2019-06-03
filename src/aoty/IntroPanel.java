package aoty;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class IntroPanel extends JPanel {
	
	private User user;
	private ArrayList<User> users;
	
	private JButton login;
	private JButton createUser;
	private JTextField username;
	private JPasswordField password;
	private JTextField name;
	private JTextField email;
	private JButton confirm;
	
	public IntroPanel(ArrayList<User> u,JFrame frame,ArrayList<Artist> allArtists) {
		this.users = u;
		
		this.setLayout(null);
		
		login = new JButton("LOGIN");
		createUser = new JButton("CREATE NEW USER");
		
		login.setBounds(50,135,50,20);
		createUser.setBounds(50,175,130,20);
		
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				remove(login);
				remove(createUser);
				repaint();
				username = new JTextField(30);
				password = new JPasswordField(30);
				confirm = new JButton("LOGIN");
				JLabel untext = new JLabel("username");
				JLabel pwtext = new JLabel("password");
				untext.setBounds(50, 115, 100, 20);
				pwtext.setBounds(50, 155, 100, 20);
				add(untext);
				add(pwtext);
				username.setBounds(50, 135, 100, 20);
				password.setBounds(50,175,100,20);
				confirm.setBounds(50,200,50,20);
				confirm.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						String un = username.getText();
						String pw = password.getText();
						for(User us : users) {
							if(us.getUsername().contentEquals(un) && us.getPassword().contentEquals(pw)) {
								user = us;
								HomePanel goHome = new HomePanel(allArtists,user,frame);
								break;
							}
						}
						JTextField incorrect = new JTextField("Incorrect username or password.");
						incorrect.setBounds(50, 230,230,20);
						incorrect.setEditable(false);
						incorrect.setBackground(Color.red);
						add(incorrect);
					}
					
				});
				add(username);
				add(password);
				add(confirm);
			}
			
		});
		createUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(login);
				remove(createUser);
				repaint();
				username = new JTextField(30);
				password = new JPasswordField(30);
				name = new JTextField(30);
				confirm = new JButton("CREATE USER");
				JLabel untext = new JLabel("username");
				JLabel pwtext = new JLabel("password");
				JLabel ntext = new JLabel("name");
				untext.setBounds(50, 115, 100, 20);
				pwtext.setBounds(50, 155, 100, 20);
				ntext.setBounds(50,195,100,20);
				add(ntext);
				add(untext);
				add(pwtext);
				username.setBounds(50, 135, 100, 20);
				password.setBounds(50,175,100,20);
				name.setBounds(50,215,100,20);
				confirm.setBounds(50,240,50,20);
				confirm.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String un = username.getText();
						String pw = password.getText();
						String n = name.getText();
						user = new User(n,un,pw,un + "@gmail.com");
						users.add(user);
						HomePanel goHome = new HomePanel(allArtists,user,frame);
					}
					
				});
				add(username);
				add(password);
				add(name);
				add(confirm);
			}
		});
		
		this.add(login);
		this.add(createUser);
		
		this.display(frame);
	}
	
	public void paintComponent(Graphics window) {
		window.setFont(new Font("default", Font.BOLD,15));
		window.drawString("MUSIC REVIEW APP", 50, 70);
		window.setFont(new Font("default", Font.PLAIN,14));
		window.drawString("Made by Isaac Hoffman", 50, 90);
		
		window.drawString("Log in or create a new user to start reviewing!", 50, 110);
	}
	
	public void display(JFrame frame) {
		setPreferredSize(new Dimension(600,500));
		
		
		//make the panel scrollable
		JScrollPane scrollPane = new JScrollPane(this);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 600, 500);
        
        //add scrollpane to contentpane
        JPanel contentPane = new JPanel(null);
	    contentPane.add(scrollPane);
	    contentPane.setPreferredSize(new Dimension(600,500));
	    
	    frame.setContentPane(contentPane);
	    
	    frame.pack();
	    frame.setVisible(true);
	}
	
}
