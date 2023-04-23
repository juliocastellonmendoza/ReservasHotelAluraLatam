package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Conexion {
	
	private DataSource datasource;
	
	public Conexion() {
		var pooledDataSource = new ComboPooledDataSource();//La clase de c3p0
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3310/db_alurahotel?useTimeZone=true&serverTimeZone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("");
		pooledDataSource.setMaxPoolSize(20);
		
		this.datasource = pooledDataSource;
	}
	
	public Connection conectar() {
		try {
			return this.datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
