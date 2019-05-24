package aoty;

import java.util.ArrayList;

public class Album {
	
	//album info
	private String name;
	private Artist artist;
	private int releaseDate; // YYYYMMDD format
	private int type; // 0 - EP, 1 - LP, 2 - single, 3 - mixtape
	private String genre;
	private String label;
	
	//reviews, ratings, and scores
	private ArrayList<Rating> criticRatings;
	private ArrayList<Review> criticReviews;
	private ArrayList<Rating> userRatings;
	private ArrayList<Review> userReviews;
	private Score criticScore;
	private Score userScore;
	
	public Album(String n, Artist a, int rd, int t, String g, String l) {
		name = n;
		artist = a;
		releaseDate = rd;
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

	public int getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(int releaseDate) {
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

}
