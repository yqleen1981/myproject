package com.tds.market;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tds.ConnectionManager;
import com.tds.vo.MarketData;

public class MarketTXTImp implements MarketUtils {
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MarketTXTImp.class);

	@Override
	public MarketData getNewMarketDate(Long current_seq, String productId) throws IOException {
		long new_seq = 0;
		//初始化文件源
		File file = new File("20151118.txt");
		BufferedReader reader  = new BufferedReader(new FileReader(file));
		LineNumberReader lineReader = new LineNumberReader(reader);
		String tempString = "";
		//从上一次行数开始继续读取
		int line = current_seq.intValue()+1;
		//读取下一行
		while(tempString != null){
			lineReader.setLineNumber(line);
			tempString = lineReader.readLine();
			if (tempString.contentEquals(productId)){
				String[] values = tempString.split(",");
				MarketData marketData = new MarketData();
				marketData.setProductId(values[1]);
				marketData.setCurrentPrice(Long.valueOf(values[2]));
				marketData.setCurrentLine(line);
				line++;
				return marketData;	
			}						
		}
		return null;
	}

}
