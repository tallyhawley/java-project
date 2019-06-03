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
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class UserPanel extends JPanel {
	
	private User user;
	private ArrayList<Rating> ratings;
	private ArrayList<Album> rated;
	private ArrayList<Album> top9;
	
	private int length;
	
	private int height = 235;
	
	public UserPanel(User user, User currentUser, JFrame frame, ArrayList<Artist> allArtists) {
		this.user = user;
		this.setLayout(null);
		
		ratings = (ArrayList<Rating>)user.getRatings().clone();
		rated = (ArrayList<Album>)user.getRated().clone();
		top9 = new ArrayList<Album>();
		
		//keep original indices of ratings so we can find the albums after sorting
		Map<Rating, Integer> map = new HashMap<Rating, Integer>();
		for(int i = 0; i < ratings.size(); i++) {
			map.put(ratings.get(i), i);
		}
		
		//sort from best to worst rating
		Collections.sort(ratings, Collections.reverseOrder());
		
		//cut down to top 9
		if(ratings.size() > 9) {
			ratings.subList(9, ratings.size()-1).clear();
		}
		
		//find the original albums again
		for(Rating r : ratings) {
			top9.add(rated.get(map.get(r)));
		}
		
		length = 170 + height * (1+(ratings.size()/3));
		
		int r = 0;
		int c = 0;
		for(Album al : top9) {
			if(c >= 3) {
				r++;
				c = 0;
			}
			Image resized = al.getCover().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(resized);
			
			JButton button = new JButton(icon);
			button.setBounds(50+175*c,160+height*r,150,150);
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
				HomePanel goHome = new HomePanel(allArtists,currentUser,frame);
			}
			
		});
		this.add(home);
		
		this.display(frame);
	}
	
	public void paintComponent(Graphics window) {
		window.setFont(new Font("default", Font.BOLD, 25));
		window.drawString(user.getName(), 50, 90);
		window.setFont(new Font("default", Font.BOLD, 20));
		window.drawString(user.getRatings().size()+"", 425, 90);
		window.drawString(user.getReviews().size()+"", 500, 90);
		window.setFont(new Font("default", Font.PLAIN, 10));
		window.setColor(Color.GRAY);
		window.drawString("("+user.getUsername()+")", 50, 110);
		window.drawString("RATINGS", 413, 110);
		window.drawString("REVIEWS", 488, 110);
		
		window.setColor(Color.BLACK);
		window.setFont(new Font("default", Font.BOLD,13));
		window.drawString("TOP 9 ALBUMS", 50, 140);
		
		int r = 0;
		int c = 0;
		int i = 0;
		
		for(Album a : top9) {
			if(c >= 3) {
				r++;
				c = 0;
			}
			window.setColor(Color.BLACK);
			window.setFont(new Font("default", Font.PLAIN, 10));
//			DateFormat df = new SimpleDateFormat("yyyy");
//			window.drawString(df.format(a.getReleaseDate()),112 + 175*c, 160 + height*r);
			//window.drawImage(a.getCover(), 50 + 175*c, 285 + height*r, 150,150,this);
			window.setFont(new Font("default", Font.BOLD, 12));
			if(a.getName().length() < 25) window.drawString(a.getName(), 50 + 175*c, 330+height*r);
			else window.drawString(a.getName().substring(0,22) + "...", 50 + 175*c, 330+height*r);
			window.setFont(new Font("default", Font.PLAIN, 12));
			window.drawString(a.getArtist().getName(), 50 + 175 * c, 345 + height * r);
			window.setColor(Color.BLACK);
			window.setFont(new Font("default", Font.BOLD, 14));
			window.drawString(ratings.get(i).toString(), 50 + 175*c, 365 + height * r);
			window.setColor(Color.GRAY);
			window.setFont(new Font("default", Font.PLAIN, 10));
			DateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
			window.drawString(df.format(a.getReleaseDate()), 85 + 175*c, 363+height*r);
			c++;
			i++;
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
