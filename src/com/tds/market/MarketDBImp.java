package com.tds.market;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tds.ConnectionManager;
import com.tds.vo.MarketData;

public class MarketDBImp implements MarketUtils {
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MarketDBImp.class);

	@Override
	public MarketData getNewMarketDate(Long current_seq, String productId) {
//		long new_seq = 0;
//		Connection strategyConn = ConnectionManager.getStrategyConn();
//		String sql = "select max(seq_) as seq_ from C_DepthMarketData where seq_>? and INSTRUMENTID=?";
//		log.debug("excute sql: " + sql);		
//		PreparedStatement preparedStatement = null;
//		ResultSet rs = null;
//		try {
//			preparedStatement = strategyConn.prepareStatement(sql);
//			preparedStatement.setLong(1, current_seq);
//			log.debug("put paremeter 1 : " + current_seq);
//			preparedStatement.setString(2, productId);
//			log.debug("put paremeter 2 : " + productId);
//			rs = preparedStatement.executeQuery();
//			while (rs.next()){
//				new_seq = rs.getLong("seq_");
//			}
//		} catch (SQLException e) {
//			log.error("excute sql error");
//			log.error(e);
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				preparedStatement.close();
//				rs.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//		return new_seq;
		return null;
	}

}
