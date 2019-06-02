package aoty;

import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MusicReviewApp extends JFrame  {

	private ArrayList<Artist> artists;
	private ArrayList<Critic> critics;
	private ArrayList<User> users;
	
	public MusicReviewApp() {
		setTitle("Album Review App");
		artists = new ArrayList<Artist>();
		critics = new ArrayList<Critic>();
		users = new ArrayList<User>();
		loadData();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		User me = new User("isaac", "is", "13","asdfsd");
		
		AlbumPanel panel = new AlbumPanel(getArtist("Tyler, the Creator").getAlbum("Goblin"), me);
		panel.display(this);
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
				String[] info = line.split("/(?!\\\\s)");
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
		
		//load user reviews
		
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
	
	public static void main(String[] args) {
		MusicReviewApp run = new MusicReviewApp();
	}
	
}
