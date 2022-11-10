package com.example.demo.common.cornutil;

import com.example.demo.common.datautil.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * @author yu.zhang
 */
public class QhNetworkHealthTimingTools implements Runnable   {
	private static final Logger logger = LoggerFactory.getLogger(QhNetworkHealthTimingTools.class);

	int i =10;

	@Override
	public void run() {
		start();
	}

	public void start(){
		String format = DateUtils.format(new Date());
		try {


			int a =10/i;
			i--;
			System.out.print("--"+a+"--");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("调度器轮询中！" + format+"--");
	}
}
