package com.tds.market;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.tds.vo.MarketData;

public interface MarketUtils {
	
	public MarketData getNewMarketDate(Long current_seq, String productId) throws FileNotFoundException, IOException;
}
