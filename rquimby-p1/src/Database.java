import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A class to test a database installation by creating, inserting into, querying
 * and dropping a sample table.
 * 
 * @author Randy Quimby
 * @version 1.0
 * 
 *          COP4027 Project#: 1 File Name: Database.java
 */
public class Database {
	/**
	 * The name of the database table
	 */
	public static final String TABLE_NAME = "FIELDMODEL";
	/**
	 * The embedded driver
	 */
	private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	/**
	 * The database protocol
	 */
	private static final String protocol = "jdbc:derby:";
	/**
	 * The name of the database
	 */
	private static final String DB_NAME = "myDB";
	/**
	 * The Statement object to issue commands and queries
	 */
	private Statement state;
	/**
	 * The Connection object to establish a connection to the database
	 */
	private Connection conn;

	/**
	 * Constructs a Database object with default values as instance fields
	 */
	public Database() {
		this.state = null;
		this.conn = null;
	}

	/**
	 * Returns a class object for an embedded driver and produces an instance of
	 * that class
	 */
	public void init() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName(driver).newInstance();
	}

	/**
	 * Acquires a connection and creates the database
	 */
	public void getConnection() throws SQLException {
		conn = DriverManager.getConnection(protocol + DB_NAME + ";create=true");
		createStatement();
	}

	/**
	 * Creates a Statement object for running SQL statements
	 */
	private void createStatement() throws SQLException {
		state = conn.createStatement();
	}

	/**
	 * Acquires a ResultSet of database entries.  Selects all rows from the table.
	 * 
	 * @return the ResultSet of the database query
	 */
	public ResultSet getResultSet() throws SQLException  {
		return state.executeQuery("SELECT * FROM " + TABLE_NAME);
	}

	/**
	 * Creates the table with formatted table header names and types
	 * 
	 * @param tableHeaders the table headers that establishes each column type and name
	 */
	public void createTable(String tableHeaders) throws SQLException  {
		state.execute("CREATE TABLE " + TABLE_NAME + " (" + tableHeaders + ")");
	}

	/**
	 * Drops the table 
	 */
	public void dropTable() throws SQLException {
		state.execute("DROP TABLE " + TABLE_NAME);
	}

	/**
	 * Inserts formatted sample data/values into a row of the table
	 * 
	 * @param values the formatted values to be inserted into the table
	 */
	public void insert(String values) throws SQLException {
		state.execute("INSERT INTO " + TABLE_NAME + " VALUES (" + values + ")");
	}

	/**
	 * Closes the database connection
	 */
	public void close() throws SQLException {
		conn.close();
	}

}
