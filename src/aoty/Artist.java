package aoty;

import java.util.ArrayList;

public class Artist {
	
	private String name;
	private ArrayList<Album> albums;
	private ArrayList<Rating> criticRatings;
	private ArrayList<Rating> userRatings;
	private Score criticScore;
	private Score userScore;
	
	public Artist(String n) {
		name = n;
		albums = new ArrayList<Album>();
		criticRatings = new ArrayList<Rating>();
		userRatings = new ArrayList<Rating>();
		criticScore = new Score(criticRatings);
		userScore = new Score(userRatings);
		
	}
	
	public void add(Album a) {
		albums.add(a);
	}
	
	public void addCriticRating(Rating r) {
		criticRatings.add(r);
		criticScore.update(criticRatings);
	}
	
	public void addUserRating(Rating rating) {
		userRatings.add(rating);
		userScore.update(userRatings);
	}

	public String getName() {
		return name;
	}
	
	public Album getAlbum(String name) {
		for(Album a : albums) {
			if(a.getName().equals(name)) {
				return a;
			}
		}
		return null;
	}
	
	public ArrayList<Album> getAlbums(){
		return albums;
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

	public ArrayList<Rating> getUserRatings() {
		return userRatings;
	}

	public void setUserRatings(ArrayList<Rating> userRatings) {
		this.userRatings = userRatings;
	}
	
	
	
}
