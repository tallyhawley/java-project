package aoty;

import java.util.ArrayList;

public class Artist {
	
	private String name;
	private ArrayList<Album> albums;
	private ArrayList<Rating> criticScores;
	private ArrayList<Rating> userScores;
	private Score criticScore;
	private Score userScore;
	
	public Artist(String n) {
		name = n;
		albums = new ArrayList<Album>();
		criticScores = new ArrayList<Rating>();
		userScores = new ArrayList<Rating>();
		criticScore = new Score(criticScores);
		userScore = new Score(userScores);
	}
	
	public void add(Album a) {
		albums.add(a);
		criticScores.add(new Rating(a.getCriticScore().getScore(),1,new Critic("bot", "www.google.com")));
		userScores.add(new Rating(a.getUserScore().getScore(), 0, new User("bot", "2812731928371283", "asdhf17281723ysdhf912", "nothing@nothing.com")));
		criticScore.update(criticScores);
		userScore.update(userScores);
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
	
}
