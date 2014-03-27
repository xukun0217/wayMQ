package ananas.waymq.droid.util;

import java.util.Calendar;

public class Util {

	public static String timeToString(long time) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		int yy, mm, dd, h, m, s;
		yy = cal.get(Calendar.YEAR);
		mm = cal.get(Calendar.MONTH) + 1;
		dd = cal.get(Calendar.DAY_OF_MONTH);
		h = cal.get(Calendar.HOUR_OF_DAY);
		m = cal.get(Calendar.MINUTE);
		s = cal.get(Calendar.SECOND);
		StringBuilder sb = new StringBuilder();
		sb.append(yy);
		sb.append("-");
		sb.append(mm);
		sb.append("-");
		sb.append(dd);
		sb.append(" ");
		sb.append(h);
		sb.append(":");
		sb.append(m);
		sb.append(":");
		sb.append(s);
		return sb.toString();
	}
}
