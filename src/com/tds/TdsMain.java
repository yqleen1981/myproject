package com.tds;

import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import com.tds.market.MarketDBImp;
import com.tds.market.MarketTXTImp;
import com.tds.market.MarketUtils;
import com.tds.vo.MarketData;

public class TdsMain extends Thread{
	static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TdsMain.class);
	public PropertiesManager propertiesManager;
	public ConnectionManager connManager;
	public static void main(String[] args) {
		TdsMain tds = new TdsMain();
		//初始化系统参数
		tds.Init();
		Properties properties = tds.propertiesManager.getProperties();
		//初始化品种参数
		String[] productIds = new String[10];
		for (int i=0; i<10; i++){
			productIds[i] = properties.getProperty("productId"+i);
		}
		//初始化品种线程
		StrategyThread[] strategyThreads = new StrategyThread[10];
		for (int i= 0; i<10; i++){
			if (productIds[i]!=null){
				strategyThreads[i] = new StrategyThread();
				strategyThreads[i].setName("strategyThread_"+productIds[i]);
				strategyThreads[i].init(properties);
				strategyThreads[i].start();
			}			
		}
		//使用数据库方式获取最新行情
		//MarketData marketData = new MarketDataDBImp();
		//使用文件方式获取最新行情
		MarketUtils marketUtils = new MarketTXTImp();
		long[] seq_ = {0,0,0,0,0,0,0,0,0,0};
		while(true){
			try {
				tds.sleep(500);
				for (int i=0; i<10; i++) {
					//获取最新行情数据
					MarketData marketData = null;;
					try {
						marketData = marketUtils.getNewMarketDate(seq_[i], productIds[i]);
					} catch (IOException e) {
						log.error("从行情数据文件中读取信息失败！");
						log.error(e.getLocalizedMessage());
						log.error(e);
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//如果返回值不为null，代表有最新行情
					if (marketData != null) {
						seq_[i] = marketData.getCurrentLine();
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
