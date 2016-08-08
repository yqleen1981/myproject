package com.tds;

import java.sql.Connection;
import java.util.Properties;

public class StrategyThread extends Thread{
	static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(StrategyThread.class);
	public Connection strategyConn; 
	public Properties systemProperties;
	public Object waitFlag = new Object();
	
	public void init(Properties systemProperties){
//		this.strategyConn = strategyConn;
		this.systemProperties = systemProperties;
	}
	
	public void run(){
		Thread.currentThread().setName(this.getName());
		int i = 1;
		boolean firstFlag = true;
		while(true){
			log.debug(this.getName() + "execute " + i);
			//go to tds process;
			//use strategyConn get Oracle conn;
			//use systemProperties get product info;
			firstFlag = false;
			synchronized(this.waitFlag){
				try {
					this.waitFlag.wait();
					i++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
