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
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	public int getCoordY() {
		return coordY;
	}
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	public int getNbRooms() {
		return nbRooms;
	}
	public void setNbRooms(int nbRooms) {
		this.nbRooms = nbRooms;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
