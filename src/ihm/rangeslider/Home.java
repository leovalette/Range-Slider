package ihm.rangeslider;

public class Home {

	private int coordX;
	private int coordY;
	private int nbRooms;
	private int price;

	public Home(int coordX, int coordY, int nbRooms, int price) {
		super();
		this.coordX = coordX;
		this.coordY = coordY;
		this.nbRooms = nbRooms;
		this.price = price;
	}

	public int getCoordX() {
		return coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public int getNbRooms() {
		return nbRooms;
	}

	public int getPrice() {
		return price;
	}
}
