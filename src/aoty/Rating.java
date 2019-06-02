package aoty;

import java.util.Date;

public class Rating implements Comparable<Rating> {
	private int rating;
	private int type; // 0 - user, 1 - critic
	private Date timestamp;
	private Reviewer source;
	
	public Rating(int r, int t, Reviewer s) {
		rating = r;
		type = t;
		timestamp = new Date(System.currentTimeMillis());
		setSource(s);
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
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
	public int compareTo(Rating r) {
		return this.timestamp.compareTo(r.getTimestamp());
	}

	public Reviewer getSource() {
		return source;
	}

	public void setSource(Reviewer source) {
		this.source = source;
	}
	
	public String toString() {
		return "" + rating;
	}
}
