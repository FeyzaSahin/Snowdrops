package flowerGame;

import java.io.Serializable;
import java.util.ArrayList;

public class Habitat implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7073721610831195784L;
	private static int habitatCounter = 0;
	private String name;
	private int capacity;
	private int population;
	private boolean isNursery;
	private ArrayList<Snowdrop> snowdrops;
	private static ArrayList<Habitat> habitats = new ArrayList<Habitat>();
	
	/*public Habitat(int capacity, int population, boolean isNursery, String name) {
		this.capacity = capacity;
		this.population = population;
		this.isNursery = isNursery;
		if (isNursery) {this.name = "Nursery";}
		else {this.name = name;}
		habitatCounter++;
		snowdrops = new ArrayList<Snowdrop>();
		habitats.add(this);
	}*/
	
	public Habitat(int capacity, boolean isNursery, String name) {
		this.capacity = capacity;
		this.population = 0;
		this.isNursery = isNursery;
		if (isNursery) this.name = "Nursery";
		else {this.name = name;}
		habitatCounter++;
		snowdrops = new ArrayList<Snowdrop>();
		habitats.add(this);
		Logger.newHab(this);
	}
	
	public Habitat() {
		this.capacity = 8;
		this.population = 0;
		this.isNursery = false;
		this.name = "Habitat " + habitatCounter;
		habitatCounter++;
		snowdrops = new ArrayList<Snowdrop>();
		habitats.add(this);
		Logger.newHab(this);
	}
	
	public boolean containsFlower(Snowdrop flower1) {
		if (snowdrops.contains(flower1)) return true;
		else return false;
	}
	
	public boolean hasRoom() {
		if (population < capacity) return true;
		else return false;
	}
	
	public void addFlower(Snowdrop flower1) {
		if (hasRoom()) {
			population ++;
			snowdrops.add(flower1);
		}
	}
	
	public void removeFlower(Snowdrop flower1) {
		snowdrops.remove(flower1);
		population--;
	}
	public int getCapacity() {
		return capacity;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}
	
	public boolean isNursery() {
		return isNursery;
	}

	public static ArrayList<Habitat> getHabitats() {
		return habitats;
	}	
	
	public String getName() {
		return name;
	}
	
	public static int getHabCounter() {
		return habitatCounter;
	}
	
	public ArrayList<Snowdrop> getFlowers(){
		return snowdrops;
	}
}