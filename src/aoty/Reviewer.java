package aoty;

public abstract class Reviewer {
	
	private String name;
	
	public Reviewer(String name) {
		this.name = name;
	}
	
	public abstract void rate(Album album, int rating);
	
	public abstract void review(Album album, String review);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
