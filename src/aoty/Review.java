package aoty;

import java.util.ArrayList;
import java.util.Date;

public class Review implements Comparable<Review> {
	private String review;
	private int type; // 0 - user, 1 - critic
	private Date timestamp;
	private Reviewer source;
	
	public Review(String r, int t, Reviewer s) {
		review = r;
		type = t;
		timestamp = new Date(System.currentTimeMillis());
		setSource(s);
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int compareTo(Review r) {
		return this.timestamp.compareTo(r.getTimestamp());
	}

	public Reviewer getSource() {
		return source;
	}

	public void setSource(Reviewer source) {
		this.source = source;
	}
}
