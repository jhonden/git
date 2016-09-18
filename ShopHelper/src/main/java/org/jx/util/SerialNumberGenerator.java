package org.jx.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 流水号生成器,单例
 * @author chenljj
 */
public class SerialNumberGenerator {
	/**
	 * 锁
	 */
	private static Object lock = new Object();	
	
	private static SerialNumberGenerator generator;
	
	/**
	 * 最大流水号
	 */
	public static final int MAX_SERIAL_NUMBER = 99999;
	
	public static final String DATE_FORMAT = "yyyyMMddHHmmss";
	
	public static final String NUMBER_FORMAT = "00000";
	
	private int currentNum = 0;
	
	private SerialNumberGenerator() {
	}
	
	/**
	 * 获得实例
	 * @return
	 */
	public static SerialNumberGenerator getInstance(){
		if(generator == null){
			synchronized (lock) {
				if(generator == null){
					generator = new SerialNumberGenerator();
				}
			}
		}
		return generator;
	}
	
	/**
	 * 获得下一个流水号
	 * @return
	 */
	public String next(){
		DecimalFormat formater = new DecimalFormat(NUMBER_FORMAT);
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		return sdf.format(new Date())+formater.format(getSerialNumber());
	}
	
	/**
	 * 流水号自增长
	 */
	private synchronized int getSerialNumber(){
		if(++currentNum > MAX_SERIAL_NUMBER){
			currentNum = 1;
		}
		return currentNum;
	}
	
}
