
/**
 * A class to construct a Vehicle object with make, model, weight, engineSize,
 * isImport, and numOfDoors.
 * 
 * @author Randy Quimby
 * @version 1.0
 * 
 *          COP4027 Project#: 1 File Name: Vehicle.java
 */
public class Vehicle {
	/**
	 * The make of a vehicle
	 */
	private String make;
	/**
	 * The model of a vehicle
	 */
	private String model;
	/**
	 * The weight of a vehicle
	 */
	private double weight;
	/**
	 * The engine size of a vehicle
	 */
	private double engineSize;
	/**
	 * Whether a vehicle is an import or not
	 */
	private boolean isImport;
	/**
	 * The number of doors on a vehicle
	 */
	private int numOfDoors;
    
	/**
	 * Constructs a Vehicle object with default values as instance fields
	 */
	public Vehicle() {
		this.make = "";
		this.model = "";
		this.weight = 0;
		this.engineSize = 0;
		this.isImport = false;
		this.numOfDoors = 0;
	}

	/**
	 * Sets the make of a vehicle to the parameter value
	 * 
	 * @param make the make of a vehicle
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * Sets the model of a vehicle to the parameter value
	 * 
	 * @param model the model of a vehicle
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Sets the weight of a vehicle to the parameter value. Checks to ensure the
	 * weight is above 0 lbs
	 * 
	 * @param weight the weight of a vehicle
	 */
	public void setWeight(double weight) {
		if (weight > 0.0) {
			this.weight = weight;
		}
	}

	/**
	 * Sets the engine size of a vehicle to the parameter value. Checks to ensure
	 * the engine size is above 0 lbs
	 * 
	 * @param engineSize the engine size of a vehicle 
	 */
	public void setEngineSize(double engineSize) {
		if (engineSize > 0.0) {
			this.engineSize = engineSize;
		}
	}

	/**
	 * Sets whether a vehicle is an import to the parameter value
	 * 
	 * @param isImport whether a vehicle is an import
	 */
	public void setIsImports(boolean isImport) {
		this.isImport = isImport;
	}

	/**
	 * Sets the number of doors of a vehicle to the parameter value. A vehicle must
	 * have at least two doors
	 * 
	 * @param numOfDoors the number of doors on a vehicle
	 */
	public void setNumOfDoors(int numOfDoors) {
		if (numOfDoors >= 2) {
			this.numOfDoors = numOfDoors;
		}
	}

}
