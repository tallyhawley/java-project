package aoty;

import java.util.ArrayList;

public class Score {
	
	private ArrayList<Rating> ratings;
	private int score;
	
	public Score(ArrayList<Rating> s) {
		ratings = s;
		if(ratings.size() >= 1) {
			score = 0;
			for(Rating r : ratings) {
				score += r.getRating();
			}
			score = score/ratings.size();
		}
	}
	
	public void update(ArrayList<Rating> ratings) {
		score = 0;
		for(Rating r : ratings) {
			score += r.getRating();
		}
		score = score/ratings.size();
	}

}
