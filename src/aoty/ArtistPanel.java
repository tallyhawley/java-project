package aoty;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ArtistPanel extends JPanel {
	
	private Artist artist;
	private ArrayList<Album> albums;
	private ArrayList<JButton> albumButtons;
	
	private int length;
	
	public ArtistPanel(Artist a) {
		artist = a;
		albums = artist.getAlbums();
		
		length = 1000;
	}
	
	public void paintComponent(Graphics window) {
		
		this.setLayout(null);
		super.paintComponent(window);
		
		window.setFont(new Font("default", Font.PLAIN, 13));
		window.drawString("ARTIST PAGE", 40, 70);
		
		window.setFont(new Font("default",Font.BOLD, 17));
		window.drawString(artist.getName(), 40, 150);

		//to display scores
		window.setColor(new Color(230,230,230));
		window.fillRect(300,70,250,150);
		//critic score
		window.setColor(Color.BLACK);
		window.setFont(new Font("default", Font.BOLD, 30));
		window.drawString(artist.getCriticScore().toString(), 335, 125);
		//user score
		window.drawString(artist.getUserScore().toString(), 335, 195);

		//bells and whistles for score box
		window.setFont(new Font("default", Font.PLAIN, 10));
		window.drawString("CRITIC SCORE", 325, 92);
		window.drawString("USER SCORE", 325, 162);
		if(artist.getCriticRatings().size() == 1) window.drawString("Based on " + artist.getCriticRatings().size() + " review", 400, 115);
		else window.drawString("Based on " + artist.getCriticRatings().size() + " reviews", 400, 115);

		if(artist.getUserRatings().size() == 1) window.drawString("Based on " + artist.getUserRatings().size() + " review", 400, 185);
		else window.drawString("Based on " + artist.getUserRatings().size() + " reviews", 400, 185);
		
		window.setFont(new Font("default", Font.BOLD,13));
		window.drawString("ALBUMS", 50, 245);
		
		int r = 0;
		int c = 1;
		
		for(Album a : albums) {
			if(c > 3) {
				r++;
				c = 1;
			}
			window.setFont(new Font("default", Font.PLAIN, 10));
			DateFormat df = new SimpleDateFormat("yyyy");
			window.drawString(df.format(a.getReleaseDate()), 50 + 145*c, 270 + 200*r);
			c++;
		}
	}
	
	public void display(JFrame frame) {
		setPreferredSize(new Dimension(600,length));
		
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
