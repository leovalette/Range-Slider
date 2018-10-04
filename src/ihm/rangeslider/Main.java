package ihm.rangeslider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class Main {

	final static int minRooms = 0;
	final static int maxRooms = 10;
	final static int minPrice = 1000;
	final static int maxPrice = 1000000;
	final static int minCoord = 0;
	final static int maxCoord = 500;
	final static int nbHomes = 20;

	private static List<Home> homeList = new ArrayList<Home>();

	public static void main(String[] args) {
		for (int i = 0; i < nbHomes; i++) {
			int randomRooms = minRooms + (int) (Math.random() * ((maxRooms - minRooms) + 1));
			int randomPrice = minPrice + (int) (Math.random() * ((maxPrice - minPrice) + 1));
			int randomXCoord = minCoord + (int) (Math.random() * ((maxCoord - minCoord) + 1));
			int randomYCoord = minCoord + (int) (Math.random() * ((maxCoord - minCoord) + 1));
			homeList.add(new Home(randomXCoord, randomYCoord, randomRooms, randomPrice));
		}
		
	    Window win = new Window(homeList);
		
		
	}

}
