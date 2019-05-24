package aoty;

import java.util.Date;

public class Comment implements Comparable<Comment> {
	
	private String comment;
	private Date timestamp;
	private Reviewer source;
	
	public Comment(String c, Reviewer s) {
		comment = c;
		timestamp = new Date(System.currentTimeMillis());
		setSource(s);
	}

	@Override
	public int compareTo(Comment c) {
		return this.timestamp.compareTo(c.getTimestamp());
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Reviewer getSource() {
		return source;
	}

	public void setSource(Reviewer source) {
		this.source = source;
	}

}
