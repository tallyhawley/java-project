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
	
	public MusicReviewApp() {
		artists = new ArrayList<Artist>();
		loadData();
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
			BufferedReader readAlbums = new BufferedReader(new FileReader("src/savedata/albums"));
			String line = readAlbums.readLine();
			while(line != null) {
				Artist current = null;
				for(Artist a : artists) {
					if(a.getName().equals(line)) current = a;
				}
				while(line != "") {
					String[] info = line.split("/");
					current.add(new Album(info[0],current,info[1],Integer.parseInt(info[2]), info[3],info[4]));
					line = readAlbums.readLine();
				}
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
		
	}
	
}
