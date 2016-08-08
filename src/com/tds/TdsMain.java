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
		//��ʼ��ϵͳ����
		tds.Init();
		Properties properties = tds.propertiesManager.getProperties();
		//��ʼ��Ʒ�ֲ���
		String[] productIds = new String[10];
		for (int i=0; i<10; i++){
			productIds[i] = properties.getProperty("productId"+i);
		}
		//��ʼ��Ʒ���߳�
		StrategyThread[] strategyThreads = new StrategyThread[10];
		for (int i= 0; i<10; i++){
			if (productIds[i]!=null){
				strategyThreads[i] = new StrategyThread();
				strategyThreads[i].setName("strategyThread_"+productIds[i]);
				strategyThreads[i].init(properties);
				strategyThreads[i].start();
			}			
		}
		//ʹ�����ݿⷽʽ��ȡ��������
		//MarketData marketData = new MarketDataDBImp();
		//ʹ���ļ���ʽ��ȡ��������
		MarketUtils marketUtils = new MarketTXTImp();
		long[] seq_ = {0,0,0,0,0,0,0,0,0,0};
		while(true){
			try {
				tds.sleep(500);
				for (int i=0; i<10; i++) {
					//��ȡ������������
					MarketData marketData = null;;
					try {
						marketData = marketUtils.getNewMarketDate(seq_[i], productIds[i]);
					} catch (IOException e) {
						log.error("�����������ļ��ж�ȡ��Ϣʧ�ܣ�");
						log.error(e.getLocalizedMessage());
						log.error(e);
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//�������ֵ��Ϊnull����������������
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
