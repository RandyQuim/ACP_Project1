import java.util.Random;

/**
 * A class to generate random values for Vehicle instances
 * 
 * @author Randy Quimby
 * @version 1.0
 * 
 *          COP4027 Project#: 1 File Name: VehicleGeneration.java
 */
public class VehicleGeneration {
	/**
	 * The starting value of a range of values
	 */
	private int base;
	/**
	 * The highest value of a range of values
	 */
	private int highest;
	/**
	 * The random value chosen within a range of values
	 */
	private int result;
	/**
	 * The model of a vehicle
	 */
	private String model;
	/**
	 * The make of a vehicle
	 */
	private String make;

	/**
	 * Constructs a VehicleGeneration object with default values as instance fields
	 */
	public VehicleGeneration() {
		this.base = 0;
		this.highest = 0;
		this.result = 0;
		this.model = "";
		this.make = "";
	}

	/**
	 * Generates a vehicle's make randomly
	 * 
	 * @return the make of the vehicle
	 */
	public String make() {
		base = 1;
		highest = 5;
		result = randomGenerator();

		switch (result) {
		case 1:
			make = "Chevy";
			break;
		case 2:
			make = "Ford";
			break;
		case 3:
			make = "Toyota";
			break;
		case 4:
			make = "Nissan";
			break;
		case 5:
			make = "Hyundai";
			break;
		default:
			make = "";
			break;
		}

		return make;
	}

	/**
	 * Generates a vehicle's model randomly 
	 * 
	 * @return the model of a vehicle
	 */
	public String model() {
		base = 1;
		highest = 6;
		result = randomGenerator();
	
		switch (result) {
		case 1:
			model = "compact";
			break;
		case 2:
			model = "intermediate";
			break;
		case 3:
			model = "fullSized";
			break;
		case 4:
			model = "van";
			break;
		case 5:
			model = "suv";
			break;
		case 6:
			model = "pickup";
			break;
		default:
			model = "";
			break;
		}

		return model;
	}

	/**
	 * Generates a vehicle's weight randomly. Ranges are dependent on vehicle model.
	 * 
	 * @return the weight of a vehicle
	 */
	public double weight() {
		if (model.equals("compact")) {
			base = 1500;
			highest = 2000;
		} else if (model.equals("intermediate")) {
			base = 2000;
			highest = 2500;
		} else {
			base = 2500;
			highest = 4000;
		}

		return randomGenerator();
	}

	/**
	 * Generates a vehicle's engine size randomly.  Ranges are dependent of vehicle model.
	 * 
	 * @return the size of the engine
	 */
	public double engineSize() {
		if (model.equals("compact")) {
			base = 90;
			highest = 150;
		} else if (model.equals("intermediate")) {
			base = 150;
			highest = 275;
		} else {
			base = 275;
			highest = 400;
		}

		return randomGenerator();
	}

	/**
	 * Determines if a vehicle is an import or not. Whether it is an import or not
	 * is dependent on vehicle make.
	 * 
	 * @return the boolean value of a vehicle's import condition
	 */
	public boolean isImport() {
		if (make.equals("Chevy") || make.equals("Ford")) {
			return false;
		} else
			return true;
	}

	/**
	 * Determines if a vehicle has two or four doors. Only compact vehicles have two
	 * doors, all others have four.
	 * 
	 * @return the number of doors
	 */
	public int numOfDoors() {
		int numOfDoors = 0;
		if (model.equals("compact")) {
			numOfDoors = 2;
		} else
			numOfDoors = 4;
		return numOfDoors;
	}

	/**
	 * Randomly generates a value within a range
	 * 
	 * @return the randomly generated value
	 */
	private int randomGenerator() {
		Random rand = new Random();
		return rand.nextInt((highest + 1) - base) + base;
	}
}
