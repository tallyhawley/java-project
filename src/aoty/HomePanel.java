package aoty;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class HomePanel extends JPanel {
	
	private User currentUser;
	private ArrayList<Artist> artists;
	private ArrayList<Album> albums;
	
	private int height = 265;
	private int length;
	
	public HomePanel(ArrayList<Artist> a, User user, JFrame frame) {
		this.setLayout(null);
		
		this.artists = a;
		albums = new ArrayList<Album>();
		currentUser = user;
		
		for(Artist artist : artists) {
			for(Album album : artist.getAlbums()) {
				albums.add(album);
			}
		}
		
		Collections.sort(albums, new Comparator<Album>() {

			@Override
			public int compare(Album o1, Album o2) {
				return o2.getReleaseDate().compareTo(o1.getReleaseDate());
			}
			
		});
		
		length = 145 + (1+(albums.size()-1)/3)*height;
		
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
			button.setBounds(50+175*c,140+height*r,150,150);
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					AlbumPanel newPanel = new AlbumPanel(al,user,frame,artists);
				}
				
			});
			this.add(button);
			c++;
		}
		
		this.display(frame);
	}
	
	public void paintComponent(Graphics window) {
		window.setFont(new Font("default", Font.BOLD, 25));
		window.drawString("HOME", 50, 70);
		window.setFont(new Font("default", Font.BOLD, 13));
		window.drawString("ALBUMS BY MOST RECENT", 50, 100);
		
		//display albums
		
		int r = 0;
		int c = 0;
		
		for(Album a : albums) {
			if(c >= 3) {
				r++;
				c = 0;
			}
			window.setColor(Color.BLACK);
			window.setFont(new Font("default", Font.PLAIN, 10));
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			window.drawString(df.format(a.getReleaseDate()),95 + 175*c, 125 + height*r);
			//window.drawImage(a.getCover(), 50 + 175*c, 285 + height*r, 150,150,this);
			window.setFont(new Font("default", Font.PLAIN, 12));
			if(a.getName().length() < 25) window.drawString(a.getName(), 50 + 175*c, 305+height*r);
			else window.drawString(a.getName().substring(0,22) + "...", 50 + 175*c, 305+height*r);
			window.setColor(Color.GRAY);
			switch(a.getType()) {
			case 0:
				window.drawString("EP", 50 + 175 * c, 320 + height * r);
				break;
			case 1:
				window.drawString("LP", 50 + 175 * c, 320 + height * r);
				break;
			case 2:
				window.drawString("Single", 50 + 175 * c, 320 + height * r);
				break;
			case 3:
				window.drawString("Mixtape", 50 + 175 * c, 320 + height * r);
				break;
			}
			window.setColor(Color.BLACK);
			window.setFont(new Font("default", Font.BOLD, 14));
			window.drawString(a.getCriticScore().toString(), 50 + 175*c, 345 + height * r);
			window.drawString(a.getUserScore().toString(), 50 + 175*c, 370 + height * r);
			window.setColor(Color.GRAY);
			window.setFont(new Font("default", Font.PLAIN, 10));
			window.drawString("critic score (" + a.getCriticRatings().size() + ")", 80 + 175*c, 343+height*r);
			window.drawString("user score (" + a.getUserRatings().size() + ")", 80 + 175*c, 368+height*r);
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
