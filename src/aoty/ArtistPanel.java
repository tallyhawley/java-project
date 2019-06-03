package aoty;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ArtistPanel extends JPanel {
	
	private Artist artist;
	private ArrayList<Album> albums;
	private ArrayList<JButton> albumButtons;
	private User user;
	
	private int length;
	
	private int height = 265;
	
	public ArtistPanel(Artist a, User u, JFrame frame, ArrayList<Artist> allArtists) {
		this.setLayout(null);
		artist = a;
		user = u;
		albums = artist.getAlbums();
		
		Collections.sort(albums, new Comparator<Album>() {

			@Override
			public int compare(Album o1, Album o2) {
				// TODO Auto-generated method stub
				return o2.getReleaseDate().compareTo(o1.getReleaseDate());
			}
			
		});
		
		length = 280 + height * (1+((albums.size()-1)/3));
		
		int r = 0;
		int c = 0;
		for(Album al : albums) {
			if(c >= 3) {
				r++;
				c = 0;
			}
			Image resized = al.getCover().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(resized);
			
			JButton button = new JButton(icon);
			button.setBounds(50+175*c,285+height*r,150,150);
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					AlbumPanel newPanel = new AlbumPanel(al,user,frame,allArtists);
				}
				
			});
			this.add(button);
			c++;
		}
		
		//add home button
		JButton home = new JButton("HOME");
		home.setBounds(500,30,50,20);
		home.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HomePanel goHome = new HomePanel(allArtists,user,frame);
			}

		});
		this.add(home);
		
		this.display(frame);
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
		int c = 0;
		
		for(Album a : albums) {
			if(c >= 3) {
				r++;
				c = 0;
			}
			window.setColor(Color.BLACK);
			window.setFont(new Font("default", Font.PLAIN, 10));
			DateFormat df = new SimpleDateFormat("yyyy");
			window.drawString(df.format(a.getReleaseDate()),112 + 175*c, 270 + height*r);
			//window.drawImage(a.getCover(), 50 + 175*c, 285 + height*r, 150,150,this);
			window.setFont(new Font("default", Font.PLAIN, 12));
			if(a.getName().length() < 25) window.drawString(a.getName(), 50 + 175*c, 450+height*r);
			else window.drawString(a.getName().substring(0,22) + "...", 50 + 175*c, 450+height*r);
			window.setColor(Color.GRAY);
			switch(a.getType()) {
			case 0:
				window.drawString("EP", 50 + 175 * c, 465 + height * r);
				break;
			case 1:
				window.drawString("LP", 50 + 175 * c, 465 + height * r);
				break;
			case 2:
				window.drawString("Single", 50 + 175 * c, 465 + height * r);
				break;
			case 3:
				window.drawString("Mixtape", 50 + 175 * c, 465 + height * r);
				break;
			}
			window.setColor(Color.BLACK);
			window.setFont(new Font("default", Font.BOLD, 14));
			window.drawString(a.getCriticScore().toString(), 50 + 175*c, 490 + height * r);
			window.drawString(a.getUserScore().toString(), 50 + 175*c, 515 + height * r);
			window.setColor(Color.GRAY);
			window.setFont(new Font("default", Font.PLAIN, 10));
			window.drawString("critic score (" + a.getCriticRatings().size() + ")", 80 + 175*c, 488+height*r);
			window.drawString("user score (" + a.getUserRatings().size() + ")", 80 + 175*c, 513+height*r);
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
