package db.utils;

import java.io.IOException;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ResetTables {

	public void resetDB() throws IOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("domain-model-jpa");
		new RunSQLScript().runScript(emf, "META-INF/reset-tables.sql");
		emf.close();		
	}
	
	public static void main(String[] args) throws IOException, SQLException {
		new ResetTables().resetDB();
	}

}
