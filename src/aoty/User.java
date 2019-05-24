package aoty;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class User extends Reviewer {
	
	private String username;
	private String password;
	private String email;
	private ArrayList<Rating> ratings;
	private ArrayList<Review> reviews;
	private String location;
	private String bio;
	private URI website;

	public User(String name, String un, String pw, String mail) {
		super(name);
		username = un;
		password = pw;
		email = mail;
		
		ratings = new ArrayList<Rating>();
		reviews = new ArrayList<Review>();
	}

	@Override
	public void rate(Album album, int rating) {
		Rating rate = new Rating(rating, 0, this);
		album.addUserRating(rate);
		ratings.add(rate);
		
	}

	@Override
	public void review(Album album, String review) {
		Review rev = new Review(review,0,this);
		album.addUserReview(rev);
		reviews.add(rev);
	}

	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public URI getWebsite() {
		return website;
	}

	public void setWebsite(String url) {
		try {
			website = new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	

}
