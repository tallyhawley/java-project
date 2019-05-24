package aoty;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Critic extends Reviewer {

	private URI website;
	private ArrayList<Rating> ratings;
	private ArrayList<Review> reviews;
	
	public Critic(String name, String url) {
		super(name);
		try {
			website = new URI(url);
		}catch(URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public URI getWebsite() {
		return website;
	}

	public void setWebsite(String url) {
		try {
			website = new URI(url);
		}catch(URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(ArrayList<Rating> ratings) {
		this.ratings = ratings;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public void rate(Album album, int rating) {
		Rating r = new Rating(rating,1, this);
		album.addCriticRating(r);
	}

	@Override
	public void review(Album album, String review) {
		album.addCriticReview(new Review(review,1,this));
	}

}
