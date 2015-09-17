package com.tds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MarketData {
	static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MarketData.class);
	
	public long getNewMarketDate(Connection strategyConn, long seq_, String productId) {
		long new_seq = 0;
		String sql = "select max(seq_) as seq_ from C_DepthMarketData where seq_>? and INSTRUMENTID=?";
		log.debug("excute sql: " + sql);		
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = strategyConn.prepareStatement(sql);
			preparedStatement.setLong(1, seq_);
			log.debug("put paremeter 1 : " + seq_);
			preparedStatement.setString(2, productId);
			log.debug("put paremeter 2 : " + productId);
			rs = preparedStatement.executeQuery();
			while (rs.next()){
				new_seq = rs.getLong("seq_");
			}
		} catch (SQLException e) {
			log.error("excute sql error");
			log.error(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return new_seq;
//		return 1;
	}

}
