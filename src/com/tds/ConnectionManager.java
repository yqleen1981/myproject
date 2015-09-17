package com.tds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ConnectionManager.class);
	private Connection marketConn;
	private Connection strategyConn;
	private Connection InitConnetion(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.111.150:1521:qhdb","qh","oracle");
			return conn;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			log.error("InitConnection error:");
			log.error(e.getMessage());
			log.error(e.getStackTrace().toString());
			e.printStackTrace();
			return null;
		}
	}
	public Connection getMarketConn() {
		if (marketConn != null){			
			return marketConn;
		} else {
			marketConn = this.InitConnetion();
			return marketConn;
		}
	}
	public Connection getStrategyConn() {
		if (strategyConn != null){			
			return strategyConn;
		} else {
			strategyConn = this.InitConnetion();
			return strategyConn;
		}
	}
	
	
	
	
}
