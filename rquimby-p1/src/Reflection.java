import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A class to extract the names, types and values of instance fields of a class
 * and formats these attributes for creating tables and inserting values in a
 * database
 * 
 * @author Randy Quimby
 * @version 1.0
 * 
 *          COP4027 Project#: 1 File Name: Reflection.java
 */
public class Reflection {
	/**
	 * A constant for the number of Vehicle instances created
	 */
	private static final int INSTANCES = 10;
	/**
	 * Used to obtain the class object
	 */
	private Class<?> vehicle;
	/**
	 * ArrayList of Vehicle instances/objects
	 */
	private ArrayList<Vehicle> instances;
	/**
	 * VehicleGeneration object for use in acquiring random field values
	 */
	private VehicleGeneration auto;
	/**
	 * Array of Field objects to acquire their attributes
	 */
	private Field[] fields;
	/**
	 * Counter to keep track of and access the Field array
	 */
	private int counter;
	/**
	 * The format of field attributes necessary for database commands
	 */
	private String fieldFormat;

	/**
	 * Constructs a Reflection object that instantiates new objects and provides
	 * default values
	 */
	public Reflection() throws ClassNotFoundException {
		this.vehicle = Class.forName("Vehicle");
		this.instances = new ArrayList<Vehicle>();
		this.auto = new VehicleGeneration();
		this.fields = vehicle.getDeclaredFields();
		this.counter = 0;
		this.fieldFormat = "";
	}

	/**
	 * Acquires field names and types using reflection and formats them for proper
	 * database entry when creating tables. Fields not included in the database are
	 * logged.
	 * 
	 * @param log the log for recording database events
	 * @return the formatted string of field names and types for the database
	 */
	public String createTableHeaders(Log log) throws NoSuchFieldException, SecurityException, IOException {
		counter = 1;

		for (Field oneField : fields) {
			Field field = vehicle.getDeclaredField(oneField.getName());
			String fieldname = field.getName();
			Object fieldType = field.getType();

			if (fieldType.toString().equals("class java.lang.String")) {
				fieldType = "CHAR(20)";
			} else if (fieldType.toString().equals("boolean")) {
				fieldType = "CHAR(1)";
			} else if (fieldType.toString().equals("int")) {
				fieldType = "INTEGER";
			} else if (fieldType.toString().equals("double")) {
				fieldType = "DOUBLE";
			} else {
				log.write("Database table header cannot include " + fieldType.toString());
				fieldname = "";
				fieldType = "";
			}

			fieldFormat += fieldname + " ";
			int j = counter;
			formatString(j, fieldType);
		}

		return fieldFormat;
	}

	/**
	 * Method formats field attributes for database entry
	 * 
	 * @param j the counter to check field types at the end of an instance field
	 * list in a class
	 * @param field the instance field value, name or type
	 */
	private void formatString(int j, Object field) {
		// decides if a substring requires a comma or not. No commas
		// needed for the last addition of a substring to the string or for field
		// attributes not included in database. Considers if multiple fields not
		// included in the database follows a field that is to be included at the end of
		// the list (checks the last 4), and avoids a comma if that is the case.
		if (fields.length == counter
				|| !fields[counter - 1].getType().toString().matches("int|double|boolean|class java.lang.String")
				|| !fields[counter].getType().toString().matches("int|double|boolean|class java.lang.String")
						&& fields.length == j++
				|| fields.length == j++
						&& !fields[counter].getType().toString().matches("int|double|boolean|class java.lang.String")
				|| fields.length == j++
						&& !fields[counter].getType().toString().matches("int|double|boolean|class java.lang.String")
				|| fields.length == j++
						&& !fields[counter].getType().toString().matches("int|double|boolean|class java.lang.String")) {
			fieldFormat += field;
		} else {
			fieldFormat += field + ", ";
		}
		

		counter++;
	}

	/**
	 * Sets the values of 10 Vehicle objects and puts those objects into an
	 * ArrayList
	 */
	private void generateInstances() {
		for (int i = 0; i < INSTANCES; i++) {
			Vehicle temp = new Vehicle();
			temp.setMake(auto.make());
			temp.setModel(auto.model());
			temp.setWeight(auto.weight());
			temp.setEngineSize(auto.engineSize());
			temp.setIsImports(auto.isImport());
			temp.setNumOfDoors(auto.numOfDoors());
			instances.add(temp);
		}
	}

	/**
	 * Acquires private field values using reflection and formats them for proper
	 * database entry when inserting them into the rows. Builds a list of field
	 * types not included in the database.
	 * 
	 * @param log the log for recording database events
	 * @param db the Database object containing database commands/methods 
	 */
	public void analyzeFieldValues(Log log, Database db) throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException, SQLException, IOException {
		generateInstances();
		String wrongType = "";
		String lastWrongType = "";

		for (Vehicle element : instances) {
			counter = 1;
			fieldFormat = "";
			String fieldValue = "";
			int i = 0;
			
			// generates the INSERT database commands for every instance of the class
			for (Field field : fields) {
				Field privateField = vehicle.getDeclaredField(field.getName());
				privateField.setAccessible(true);

				if (privateField.getType().toString().equals("class java.lang.String")) {
					fieldValue = "'" + (String) privateField.get(element) + "'";
				} else if (privateField.getType().toString().equals("double")) {
					fieldValue = String.valueOf(privateField.get(element));
				} else if (privateField.getType().toString().equals("int")) {
					fieldValue = String.valueOf(privateField.get(element));
				} else if (privateField.getType().toString().equals("boolean")) {
					if (privateField.get(element).toString().equals("true")) {
						fieldValue = "'T'";
					} else {
						fieldValue = "'F'";
					}
				} else {
					fieldValue = "";
					i++;
					if (i > 1) {
						wrongType += ", " + privateField.getType().toString();
					} else
						wrongType = privateField.getType().toString();
				}

				int j = counter;
				formatString(j, fieldValue);
			}

			db.insert(fieldFormat);
			
			// ensures the log is only written to once with the types of the values not included in the database
			if (!wrongType.equals(lastWrongType)) {
				log.write("The values of the following were not added to the database: \n" + wrongType + ".");
				lastWrongType = wrongType;
			}
		}
	}

}
