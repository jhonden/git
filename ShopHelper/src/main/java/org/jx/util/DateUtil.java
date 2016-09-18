package org.jx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
		public static String format(String formart){
			SimpleDateFormat sdf = new SimpleDateFormat(formart);
			return sdf.format(new Date());
		}
}
