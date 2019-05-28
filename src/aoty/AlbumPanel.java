package aoty;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AlbumPanel extends JPanel {
	
	private Album album;
	
	public AlbumPanel(Album a) {
		album = a;
	}
	
	public void paintComponent(Graphics window) {
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
		window.fillRect(50, 300, 500, 200);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("TEST");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    Artist nct127 = new Artist("NCT 127");
	    Album weAreSuperhuman = new Album("We Are Superhuman", nct127, "2019-05-24", 0, "K-Pop", "SM Entertainment");
	    weAreSuperhuman.setCover("src/covers/we-are-superhuman.png");
	    User user = new User("test user", "345678", "345678", "345678");
	    user.review(weAreSuperhuman, "i liked it");
	    user.rate(weAreSuperhuman, 90);
	    user.rate(weAreSuperhuman, 86);
	    Critic p4k = new Critic("Pitchfork", "www.pitchfork.com");
	    p4k.rate(weAreSuperhuman, 67);
	    p4k.review(weAreSuperhuman, "a valiant effort");
	    
	    AlbumPanel panel = new AlbumPanel(weAreSuperhuman);
	    frame.add(new JScrollPane(panel));

	    frame.setSize(600, 400);
	    frame.setVisible(true);
	}

}
