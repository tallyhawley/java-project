package aoty;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
	private JButton rateButton = new JButton("RATE");
	private JTextField rate = new JTextField(5);
	private JTextArea review = new JTextArea();
	private JButton revButton = new JButton("Post Review");
	private boolean rated;
	private boolean reviewed;
	private int length;
	private Rating myRating;
	private Review myReview;
	private JButton artist;
	
	private int start = 655;
	
	private int start2;
	
	private int j;
	
	
	public AlbumPanel(Album a, User u, JFrame frame, ArrayList<Artist> allArtists) {
		album = a;
		currentUser = u;
		rated = false;
		reviewed = false;
		for(Rating r : album.getUserRatings()) {
			if(r.getSource().equals(currentUser)) {
				rated = true;
				myRating = r;
			}
		}
		for(Review r : album.getUserReviews()) {
			if(r.getSource().equals(currentUser)) {
				reviewed = true;
				myReview = r;
			}
		}
		
		this.setLayout(null);
		
		start2 = 655 + 65 + album.getCriticRatings().size()*45;
		
		length = start2 + album.getUserReviews().size()*45 + 50;
		
		//ADD ALL COMPONENTS
		
		//to artist page
		artist = new JButton();
		artist.setBounds(50,20,20+album.getArtist().getName().length()*7,15);
		artist.setOpaque(false);
		artist.setBorderPainted(false);
		artist.setContentAreaFilled(false);
		artist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ArtistPanel panel = new ArtistPanel(album.getArtist(),currentUser,frame,allArtists);
			}
			
		});
		this.add(artist);
		
		//rating box
		rate.setBounds(62, 492,40,15);
		this.add(rate);
	
		//add rate button
		rateButton.setBounds(107,492,50,15);
		rateButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				if(!rate.getText().equals("") && !rated) {
					currentUser.rate(album, Integer.parseInt(rate.getText()));
					myRating = currentUser.getRatings().get(currentUser.getRatings().size()-1);
					rated = true;
					repaint();
				}
			}	
		});
		this.add(rateButton);
		
		//review box
		review.setEditable(true); 
		review.setBounds(64,512,476,73);
		review.setLineWrap(true);
		review.setWrapStyleWord(true);
		if(reviewed) {
			review.setText(myReview.getReview());
		}
		this.add(review);
		
		//review button
		revButton.setBounds(440, 595, 100,15);
		revButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!review.getText().equals("") && !reviewed) {
					currentUser.review(album, review.getText());
					myReview = currentUser.getReviews().get(currentUser.getReviews().size()-1);
					reviewed = true;
					repaint();
					length = start2 + album.getUserReviews().size()*45 + 50;
					setPreferredSize(new Dimension(600,length));
					//add new review button
					JButton user = new JButton(myReview.getSource().getName());
					user.setBounds(105, start2-13+45*j,myReview.getSource().getName().length()*7 + 20, 15);
					user.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							UserPanel panel = new UserPanel((User)myReview.getSource(),currentUser,frame,allArtists);
						}
						
					});
					add(user);
					j++;
				}
			}
			
		});
		this.add(revButton);
		
		int i = 1;
		
		//add critic reviews
		for(Rating r : album.getCriticRatings()) {
			JButton critic = new JButton(r.getSource().getName());
			critic.setBounds(105,start-13+45*i, r.getSource().getName().length()*7+20, 15);
			critic.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						  Desktop desktop = java.awt.Desktop.getDesktop();
						  desktop.browse(((Critic) r.getSource()).getWebsite());
						} catch (Exception ex) {
						  ex.printStackTrace();
						}
				}
				
			});
			this.add(critic);
			i++;
		}
		
		j = 1;
		
		for(Review r : album.getUserReviews()) {
			JButton user = new JButton(r.getSource().getName());
			user.setBounds(105, start2-13+45*j,r.getSource().getName().length()*7 + 20, 15);
			user.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					UserPanel panel = new UserPanel((User)r.getSource(),currentUser,frame,allArtists);
					
				}
				
			});
			this.add(user);
			j++;
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
		window.fillRect(50, 435, 500, 185);
		window.setColor(Color.black);
		window.setFont(new Font("default", Font.BOLD, 13));
		window.drawString("YOUR REVIEW", 65, 460);
		
		window.drawString(currentUser.getName(), 65, 485);
		
		//add rating field
		if(!rated) {
			rate.setVisible(true);
			rateButton.setVisible(true);
		}else {
			rate.setVisible(false);
			rate.setEditable(false);
			rateButton.setVisible(false);
			window.setColor(Color.DARK_GRAY);
			window.drawString(""+ myRating.getRating(), 65, 503);
		}
		
		//add review
		if(!reviewed) {
			review.setVisible(true);
		}
		else {
			review.setBackground(new Color(230,230,230));
			review.setEditable(false);
		}
		
		//display critic reviews
		window.setColor(new Color(230,230,230));
		window.fillRect(50, 645, 500, 40 + album.getCriticRatings().size()*45);
		window.setColor(Color.black);
		window.setFont(new Font("default", Font.BOLD, 13));
		if(album.getCriticRatings().size() > 0) window.drawString("CRITIC REVIEWS", 65, 670);
		else window.drawString("NO CRITIC REVIEWS", 65, 670);
		
		int i = 1;
		
		for(Rating r : album.getCriticRatings()) {
			Review rev = getReview(r.getSource(),album.getCriticReviews());	
			window.setFont(new Font("default", Font.BOLD, 15));
			window.drawString("" + r.getRating(), 65, start+45*i);
			window.setFont(new Font("default", Font.PLAIN,12));
			if(rev != null) window.drawString(rev.getReview(),75,  start + 18 + 45*i);
			else window.drawString("[no review]", 75, start + 18 + 45*i);
			i++;
		}
		
		//display user reviews
		window.setColor(new Color(230,230,230));
		window.fillRect(50, 710 + album.getCriticRatings().size()*45, 500, 40 + album.getUserReviews().size()*45);
		window.setColor(Color.black);
		window.setFont(new Font("default", Font.BOLD, 13));
		if(album.getUserReviews().size() > 0) window.drawString("USER REVIEWS", 65, 735 + album.getCriticRatings().size()*45);
		else window.drawString("NO USER REVIEWS", 65, 735 + album.getCriticRatings().size()*45);
		
		int j = 1;
		
		for(Review r : album.getUserReviews()) {
			Rating ra = getRating(r.getSource(),album.getUserRatings());
			window.setFont(new Font("default", Font.BOLD, 13));
			if(ra != null) window.drawString("" + ra.getRating(), 65, start2 + 45*j);
			else window.drawString("NR", 65, start2 + 45*j);
			window.setFont(new Font("default", Font.PLAIN, 12));
			window.drawString(r.getReview(), 75, start2 + 18 + 45*j);
			j++;
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
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame("TEST");
//	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    
//	    Artist testArtist = new Artist("Tyler, the Creator");
//	    Album testAlbum = new Album("IGOR", testArtist, "2019-05-17",1,"Neo-Soul","Columbia");
//	    User user = new User("test user", "345678", "345678", "345678");
//	    user.review(testAlbum, "i liked it");
//	    user.rate(testAlbum, 90);
//	    Critic p4k = new Critic("Pitchfork", "https://pitchfork.com");
//	    p4k.rate(testAlbum, 67);
//	    p4k.review(testAlbum, "a valiant effort");
//	    Critic nme = new Critic("NME", "https://www.nme.com");
//	    nme.rate(testAlbum, 95);
//	    nme.review(testAlbum, "good!!!");
//	    Critic uh = new Critic("idk", "https://www.google.com");
//	    uh.rate(testAlbum, 76);
//	    uh.review(testAlbum, "quality content!");
//	    Critic yeah = new Critic("ummmm", "https://www.google.com");
//	    yeah.rate(testAlbum, 90);
//	    yeah.review(testAlbum, "a quality record from an extraordinary musician");
//	    
//	    User me = new User("isaac's account", "24kofficial", "aasdffaasdff", "trsileneh@gmail.com");
//	    
//	    //me.review(testAlbum, "a very good album, i really liked it :)");
//	    	    
//	    AlbumPanel panel = new AlbumPanel(testAlbum, me);
//	    panel.display(frame);
//	}
	
	public Review getReview(Reviewer source, ArrayList<Review> reviews) {
		for(Review r : reviews) {
			if(r.getSource().equals(source)) {
				return r;
			}
		}
		return null;
	}
	
	public Rating getRating(Reviewer source, ArrayList<Rating> ratings) {
		for(Rating r : ratings) {
			if(r.getSource().equals(source)){
				return r;
			}
		}
		return null;
	}

}
