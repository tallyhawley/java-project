package aoty;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ArtistPanel extends JPanel {
	
	private Artist artist;
	private ArrayList<Album> albums;
	private ArrayList<JButton> albumButtons;
	
	public ArtistPanel(Artist a) {
		artist = a;
		albums = artist.getAlbums();
	}
	
	public void paintComponent(Graphics window) {
		
	}

}
