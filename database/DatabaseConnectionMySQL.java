package database;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectionMySQL implements DatabaseConnection {
    
    /**
     * Creates a Connection object, it is used to connect to the database.
     */
    private Connection connection = null;

    /**
     * Creates a Statement object, it is used to send statements and queries.
     */
    private Statement statement = null;

    private String host;

    private String port;

    private String user;

    private String database;

    private String password;

	public DatabaseConnectionMySQL(String host, String port, String user, String database, String password) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.database = database;
        this.password = password;
	}

    @Override
    public boolean connect() {
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
            return false;
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?" + "user=" + user + "&password=" + password);
		} catch (SQLException e) {
			e.printStackTrace();
            return false;
		}
		
		try {
			statement = connection.createStatement(
				ResultSet.TYPE_SCROLL_SENSITIVE, 
				ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}

        return true;
    }

	public void execute(String sql) {
		try {
			statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet query(String query) {
		try {
			return statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public PreparedStatement prepareStatement(String sql) {
		try {
			return connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Blob createBlob(byte[] data) {
		try {
			Blob blob = connection.createBlob();
			blob.setBytes(1, data);
			return blob;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}

/* GRADLE
dependencies {}
    implementation 'mysql:mysql-connector-java:8.0.27'
}
*/