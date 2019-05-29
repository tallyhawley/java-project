package aoty;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AlbumPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Album album;
	private User currentUser;
	
	public AlbumPanel(Album a, User u) {
		album = a;
		currentUser = u;
	}
	
	public void paintComponent(Graphics window) {
		this.setLayout(null);
		super.paintComponent(window);
		//draw album cover
		window.drawImage(album.getCover(), 50, 70, 200, 200, this);
		//print artist name
		window.drawString(album.getArtist().getName(), 50, 35);
		//set text to bold and print album name
		window.setFont(window.getFont().deriveFont(Font.BOLD));
		window.drawString(album.getName(), 50, 55);
		
		//to display scores
		window.setColor(new Color(230,230,230));
		window.fillRect(300,70,250,150);
		//critic score
		window.setColor(Color.BLACK);
		window.setFont(new Font("default", Font.BOLD, 30));
		window.drawString(album.getCriticScore().toString(), 335, 125);
		//user score
		window.drawString(album.getUserScore().toString(), 335, 195);
		
		//bells and whistles for score box
		window.setFont(new Font("default", Font.PLAIN, 10));
		window.drawString("CRITIC SCORE", 325, 92);
		window.drawString("USER SCORE", 325, 162);
		if(album.getCriticRatings().size() == 1) window.drawString("Based on " + album.getCriticRatings().size() + " review", 400, 115);
		else window.drawString("Based on " + album.getCriticRatings().size() + " reviews", 400, 115);
		
		if(album.getUserRatings().size() == 1) window.drawString("Based on " + album.getUserRatings().size() + " review", 400, 185);
		else window.drawString("Based on " + album.getUserRatings().size() + " reviews", 400, 185);
		
		//album info box
		window.setColor(new Color(230,230,230));
		window.fillRect(50, 300, 500, 110);
		window.setColor(Color.black);
		window.setFont(new Font("default", Font.BOLD, 13));
		window.drawString("DETAILS", 65, 325);
		window.setFont(new Font("default", Font.PLAIN, 12));
		DateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
		window.drawString(df.format(album.getReleaseDate()) + " / release date", 65, 345);
		switch(album.getType()) {
		case 0: 
			window.drawString("EP / format", 65, 360);
			break;
		case 1:
			window.drawString("LP / format", 65, 360);
			break;
		case 2:
			window.drawString("Single / format", 65, 360);
			break;
		case 3:
			window.drawString("Mixtape / format", 65, 360);
			break;
		}
		window.drawString(album.getLabel() + " / label", 65, 375);
		window.drawString(album.getGenre() + " / genre", 65, 390);
		
		//enter your review
		window.setColor(new Color(230,230,230));
		window.fillRect(50, 435, 500, 165);
		window.setColor(Color.black);
		window.setFont(new Font("default", Font.BOLD, 13));
		window.drawString("YOUR REVIEW", 65, 460);
		
		window.drawString(currentUser.getName(), 65, 485);
		
		//add rating field
		JTextField rate = new JTextField(5);
		rate.setBounds(62, 492,40,15);
		this.add(rate);
		
		//add rate button
		JButton rateButton = new JButton("RATE");
		rateButton.setBounds(107,492,50,15);
		this.add(rateButton);
		
		//add review
		JTextArea review = new JTextArea();
		review.setEditable(true);
		review.setBounds(64,512,476,73);
		this.add(review);
	}
	
	public void display(JFrame frame) {
		this.setPreferredSize(new Dimension(600,1000));
		
		//make the panel scrollable
		JScrollPane scrollPane = new JScrollPane(this);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 600, 400);
        
        //add scrollpane to contentpane
        JPanel contentPane = new JPanel(null);
	    contentPane.add(scrollPane);
	    contentPane.setPreferredSize(new Dimension(600,400));
	    
	    frame.setContentPane(contentPane);
	    
	    frame.pack();
	    frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("TEST");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    Artist nct127 = new Artist("NCT 127");
	    Album weAreSuperhuman = new Album("We Are Superhuman", nct127, "2019-05-24", 0, "K-Pop", "SM Entertainment");
	    weAreSuperhuman.setCover("src/covers/We Are Superhuman.png");
	    User user = new User("test user", "345678", "345678", "345678");
	    user.review(weAreSuperhuman, "i liked it");
	    user.rate(weAreSuperhuman, 90);
	    user.rate(weAreSuperhuman, 86);
	    Critic p4k = new Critic("Pitchfork", "www.pitchfork.com");
	    p4k.rate(weAreSuperhuman, 67);
	    p4k.review(weAreSuperhuman, "a valiant effort");
	    
	    User me = new User("isaac's account", "24kofficial", "aasdffaasdff", "trsileneh@gmail.com");
	    
	    AlbumPanel panel = new AlbumPanel(weAreSuperhuman, me);
	    panel.display(frame);
	}

}
