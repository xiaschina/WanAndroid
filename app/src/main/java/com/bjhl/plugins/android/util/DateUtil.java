package com.bjhl.plugins.android.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by XIAS on 2018/6/25.
 */

public class DateUtil {

    public static String format(long date) {
        String times = Constants.UNKNOW_TIME;
        if (date > 0) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date time = new Date(date);
                String str = format.format(new Date(date));
                String s = format.format(new Date());
                if (str.equals(s))
                    times = Constants.TODAY_TIME;
                else
                    times = str;
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
        return times;
    }
}
