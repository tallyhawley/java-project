package aoty;

import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MusicReviewApp extends JFrame  {

	private ArrayList<Artist> artists;
	private ArrayList<Critic> critics;
	private ArrayList<User> users;
	private User me;
	
	public MusicReviewApp() {
		setTitle("Album Review App");
		artists = new ArrayList<Artist>();
		critics = new ArrayList<Critic>();
		users = new ArrayList<User>();
		loadData();
				
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
		    public void run() {
		        saveData();
		    }
		});
		
		me = new User("3racha's manager", "24kofficial", "password", "trsileneh@gmail.com");
		
		HomePanel panel = new HomePanel(artists,me,this);
		
	}
	
	private void loadData() {
		//load artists
		try {
			BufferedReader readArtists = new BufferedReader(new FileReader("src/savedata/artists"));
			String line = readArtists.readLine();
			while(line != null) {
				artists.add(new Artist(line));
				line = readArtists.readLine();
			}
			readArtists.close();
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//load albums
		try {
			//albums saved as so: TITLE/ARTIST NAME/RELEASE yyyy-MM-dd/FORMAT/GENRE/LABEL
			BufferedReader readAlbums = new BufferedReader(new FileReader("src/savedata/albums"));
			String line = readAlbums.readLine();
			while(line != null) {
				String[] info = line.split("/(?!\\s)"); //lookahead to check that / is not part of album info when parsing
				//find artist
				Artist artist = getArtist(info[1]);
				//create album with parsed info
				Album al = new Album(info[0],artist,info[2],Integer.parseInt(info[3]),info[4],info[5]);
				artist.add(al);
				line = readAlbums.readLine();
			}
			readAlbums.close();
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//load critics
		try {
			//critics saved as NAME - URL
			BufferedReader readCritics = new BufferedReader(new FileReader("src/savedata/critics"));
			String line = readCritics.readLine();
			while(line != null) {
				String[] info = line.split(" - ");
				critics.add(new Critic(info[0], info[1]));
				line = readCritics.readLine();
			}
			readCritics.close();
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//load critic reviews
		try {
			//CRITIC///ALBUM///ARTIST///RATING///REVIEW LINK
			BufferedReader readCriticReviews = new BufferedReader(new FileReader("src/savedata/criticreviews"));
			String line = readCriticReviews.readLine();
			while(line != null) {
				String[] info = line.split("/(?!\\s)");
				Critic critic = getCritic(info[0]);
				Artist artist = getArtist(info[2]);
				Album album = artist.getAlbum(info[1]);
				critic.rate(album, Integer.parseInt(info[3]));
				critic.review(album, info[4]);
				line = readCriticReviews.readLine();
			}
			readCriticReviews.close();
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//load users
		try {
			//users saved as NAME/USERNAME/PW/EMAIL
			BufferedReader readUsers = new BufferedReader(new FileReader("src/savedata/users"));
			String line = readUsers.readLine();
			while(line != null) {
				String[] info = line.split("/");
				users.add(new User(info[0], info[1],info[2],info[3]));
				line = readUsers.readLine();
			}
			readUsers.close();
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//load user reviews
		
		try {
			//USERNAME/ALBUM/ARTIST/RATING/REVIEW
			BufferedReader readUserReviews = new BufferedReader(new FileReader("src/savedata/userreviews"));
			String line = readUserReviews.readLine();
			while(line != null) {
				String[] info = line.split("/(?!\\s)");
				User user = getUser(info[0]);
				Artist artist = getArtist(info[2]);
				Album album = artist.getAlbum(info[1]);
				if(info.length == 5) {
					if(!info[3].equals("NR")) { user.rate(album, Integer.parseInt(info[3])); }
					if(!info[4].equals("NR")) { user.review(album, info[4]); }
				}
				line = readUserReviews.readLine();
			}
			readUserReviews.close();
		}
		catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void saveData() {
		//save users
		try {
			FileWriter writeUsers = new FileWriter("src/savedata/users",false);
			for(User u : users) {
				writeUsers.write(u.getName() + "/" + u.getUsername() + "/" + u.getPassword() + "/" + u.getEmail() + "\n");
			}
			writeUsers.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//save user reviews
		try {
			FileWriter writeUserReviews = new FileWriter("src/savedata/userreviews",false);
			for(Artist a : artists) {
				for(Album album : a.getAlbums()) {
					for(Rating r : album.getUserRatings()) {
						Review rev = getReview(r.getSource(),album.getUserReviews());
						if(rev == null) {
							writeUserReviews.write(((User)r.getSource()).getUsername()+ "/" + album.getName() + "/" + a.getName() + "/" + r.getRating() + "/NR\n");
						} else {
							writeUserReviews.write(((User)r.getSource()).getUsername()+ "/" + album.getName() + "/" + a.getName() + "/" + r.getRating() + "/" + rev.getReview() + "\n");
						}
					}
					for(Review r : album.getUserReviews()) {
						Rating rat = getRating(r.getSource(),album.getUserRatings());
						if(rat == null) {
							writeUserReviews.write(((User)r.getSource()).getUsername()+ "/" + album.getName() + "/" + a.getName() + "/NR/" + r.getReview() + "\n");
						}
					}
				}
			}
			writeUserReviews.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private Artist getArtist(String name) {
		for(Artist a : artists) {
			if(a.getName().equals(name)){
				return a;
			}
		}
		return null;
	}
	
	private Critic getCritic(String name) {
		for(Critic c : critics) {
			if(c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}
	
	private User getUser(String username) {
		for(User u : users) {
			if(u.getUsername().contentEquals(username)) {
				return u;
			}
		}
		return null;
	}
	
	public ArrayList<Artist> getArtists() {
		return artists;
	}

	public void setArtists(ArrayList<Artist> artists) {
		this.artists = artists;
	}
	
	public Review getReview(Reviewer source, ArrayList<Review> reviews) {
		for(Review r : reviews) {
			if(r.getSource().equals(source)) {
				return r;
			}
		}
		return null;
	}
	
	public Rating getRating(Reviewer source, ArrayList<Rating> ratings) {
		for(Rating r : ratings) {
			if(r.getSource().equals(source)){
				return r;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		MusicReviewApp run = new MusicReviewApp();
	}
	
}
