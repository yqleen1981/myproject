package com.tds;

import java.sql.Connection;
import java.util.Properties;

public class TdsMain extends Thread{
	static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TdsMain.class);
	public PropertiesManager propertiesManager;
	public ConnectionManager connManager;
	public static void main(String[] args) {
		TdsMain tds = new TdsMain();
		tds.Init();
		Properties properties = tds.propertiesManager.getProperties();
		String[] productIds = new String[10];
		for (int i=0; i<10; i++){
			productIds[i] = properties.getProperty("productId"+i);
		}
		Connection marketConn = tds.connManager.getMarketConn();
		StrategyThread[] strategyThreads = new StrategyThread[10];
		for (int i= 0; i<10; i++){
			strategyThreads[i] = new StrategyThread();
			strategyThreads[i].setName("strategyThread_"+productIds[i]);
			strategyThreads[i].init(marketConn, properties);
			strategyThreads[i].start();
		}
		Connection strategyConn = tds.connManager.getStrategyConn();
		MarketData marketData = new MarketData();
		long[] seq_ = {0,0,0,0,0,0,0,0,0,0};
		while(true){
			try {
				tds.sleep(500);				
				for (int i=0; i<10; i++) {
					long newseq = marketData.getNewMarketDate(strategyConn, seq_[i], productIds[i]);
					if (newseq!=0) {
						seq_[i] = newseq;
						synchronized(strategyThreads[i].waitFlag){
							strategyThreads[i].waitFlag.notify();
						}
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		tds.connManager.getConn();
	}
	
	private void Init(){
		log.debug("begin Init tds:");		
		propertiesManager = new PropertiesManager();
		connManager = new ConnectionManager();
		log.debug("Init tds finish");
	}

}
