package aoty;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

public class Album {
	
	//album info
	private String name;
	private Artist artist;
	private Date releaseDate;
	private int type; // 0 - EP, 1 - LP, 2 - single, 3 - mixtape
	private String genre;
	private String label;
	private BufferedImage cover;
	
	//reviews, ratings, and scores
	private ArrayList<Rating> criticRatings;
	private ArrayList<Review> criticReviews;
	private ArrayList<Rating> userRatings;
	private ArrayList<Review> userReviews;
	private Score criticScore;
	private Score userScore;
	
	public Album(String n, Artist a, String rd, int t, String g, String l) {
		name = n;
		artist = a;
		//album release date in YYYY-MM-DD format, converted from String input to Date
		try {
			releaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(rd);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		type = t;
		genre = g;
		label = l;
		
		criticRatings = new ArrayList<Rating>();
		criticReviews = new ArrayList<Review>();
		userRatings = new ArrayList<Rating>();
		userReviews = new ArrayList<Review>();
		criticScore = new Score(criticRatings);
		userScore = new Score(userRatings);
		
		
	}
	
	public void addCriticRating(Rating rating) {
		criticRatings.add(rating);
		criticScore.update(criticRatings);
	}
	
	public void addCriticReview(Review review) {
		criticReviews.add(review);
	}
	
	public void addUserRating(Rating rating) {
		userRatings.add(rating);
		userScore.update(userRatings);
	}
	
	public void addUserReview(Review review) {
		userReviews.add(review);
	}
	
	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public BufferedImage getCover() {
		return cover;
	}

	public void setCover(File img) {
		try {                
	          cover = ImageIO.read(img);
	       } catch (IOException e) {
	            e.printStackTrace();
	       }
	}
	
	public void setCover(String file) {
		try {                
	          cover = ImageIO.read(new File(file));
	       } catch (IOException e) {
	            e.printStackTrace();
	       }
	}

	public Score getCriticScore() {
		return criticScore;
	}

	public void setCriticScore(Score criticScore) {
		this.criticScore = criticScore;
	}

	public Score getUserScore() {
		return userScore;
	}

	public void setUserScore(Score userScore) {
		this.userScore = userScore;
	}

	public ArrayList<Rating> getCriticRatings() {
		return criticRatings;
	}

	public void setCriticRatings(ArrayList<Rating> criticRatings) {
		this.criticRatings = criticRatings;
	}

	public ArrayList<Review> getCriticReviews() {
		return criticReviews;
	}

	public void setCriticReviews(ArrayList<Review> criticReviews) {
		this.criticReviews = criticReviews;
	}

	public ArrayList<Rating> getUserRatings() {
		return userRatings;
	}

	public void setUserRatings(ArrayList<Rating> userRatings) {
		this.userRatings = userRatings;
	}

	public ArrayList<Review> getUserReviews() {
		return userReviews;
	}

	public void setUserReviews(ArrayList<Review> userReviews) {
		this.userReviews = userReviews;
	}
	
	

}
