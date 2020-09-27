package com.note.testlibrary.tool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.note.testlibrary.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 说明：时间格式化，时间选择TimePickerView
 * 作者：王贵才
 * 邮箱：786722510@qq.com
 */
public class DataTimeUtils {

    private static DataTimeUtils dataTimeUtils;
    private static Date date;
    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("MM月");
    @SuppressLint("SimpleDateFormat")
    private static SimpleDateFormat sdf3 = new SimpleDateFormat("EEEE");
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat yy = new SimpleDateFormat("yyyy");
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat mm = new SimpleDateFormat("MM");
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat dd = new SimpleDateFormat("dd");
    private static String f1, f2, f3, f4;
    private static final int[] startYear = new int[1];
    private static final int[] startMonth = new int[1];
    private static final int[] startDay = new int[1];
    private static final int[] endYear = new int[1];
    private static final int[] endMonth = new int[1];
    private static final int[] endDay = new int[1];
    private TimePickerView timePickerView;


    public static DataTimeUtils getInstance() {
        if (dataTimeUtils == null) {
            dataTimeUtils = new DataTimeUtils();
        }
        return dataTimeUtils;
    }

    public String ms2Date(long ms) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(new Date(ms * 1000));
    }

    public String sdf(long ms) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date(ms * 1000));
    }

    public String sdf2(long ms) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date(ms * 1000));
    }


    /**
     * 获取两个月时间会议
     * @param context
     * @param textView
     */
    public void setTimePicker(Context context, TextView textView) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        // 0 就是获取当前月份，后面1就是在获取当前页后面加上一个可以选择的月份
        c.add(Calendar.MONTH, 1);
        Date m = c.getTime();
        int end_Year = (int) Double.parseDouble(yy.format(m));
        int end_Month = (int) Double.parseDouble(mm.format(m));
        int end_Data = (int) Double.parseDouble(dd.format(m));
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.set(end_Year, end_Month - 1, end_Data);
        timePickerView = new TimePickerView.Builder(context, (date, v) -> {
            String start = getTimes(date);
            textView.setText(start);
        }).setType(new boolean[]{true, true, true, true, true, false})
//                .setCancelColor(context.getResources().getColor(R.color.white),null)
                .setSubCalSize(16)
//                .setSubmitColor(context.getResources().getColor(R.color.white))
                .setContentSize(16)
//                .setTitleColor(context.getResources().getColor(R.color.white))
                .setTitleText("预定会议时间")
                .setTitleSize(16)
//                .setTitleBgColor(context.getResources().getColor(R.color.blue))
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setDate(Calendar.getInstance())//设置参数
                .setRangDate(startDate, endDate)
                .build();
        timePickerView.show();
    }

    private String getTimes(Date date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /**
     * 获得月份
     * @param textView
     */
    public void getTimeMonth(TextView textView) {
        try {
            String timeData = textView.getText().toString();
            date = sdf.parse(timeData);
            f2 = sdf2.format(date);
            if (f2.indexOf("0") != -1) {
                f2 = f2.substring(1, f2.length());
            }

            textView.setText(f2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得星期
     * @param textView
     */
    public void getTimeWeek(TextView textView) {
        String now = textView.getText().toString();         //现在时间
        Calendar yesterdayCalendar = Calendar.getInstance();
        yesterdayCalendar.add(Calendar.DATE, -1);
        @SuppressLint("SimpleDateFormat") String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(yesterdayCalendar.getTime());

        Calendar nowadaysCalendar = Calendar.getInstance();
        nowadaysCalendar.add(Calendar.DATE, 0);
        @SuppressLint("SimpleDateFormat") String nowadays = new SimpleDateFormat("yyyy-MM-dd").format(nowadaysCalendar.getTime());

        Calendar tomorrowCalendar = Calendar.getInstance();
        tomorrowCalendar.add(Calendar.DATE, +1);
        @SuppressLint("SimpleDateFormat") String tomorrow = new SimpleDateFormat("yyyy-MM-dd").format(tomorrowCalendar.getTime());
        if (now.equals(nowadays)) {  // 今天
            textView.setText("今天");
        } else if (now.equals(yesterday)) { // 昨天
            textView.setText("昨天");
        }else if (now.equals(tomorrow)){ // 明天
            textView.setText("明天");
        } else {
            try {
                date = sdf.parse(now);
                f4 = sdf3.format(date);
                textView.setText(f4);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获得天数
     * @param textView
     */
    public void getTimeDay(TextView textView) {
        try {
            String timeData = textView.getText().toString();
            date = sdf.parse(timeData);
            f3 = dd.format(date);
            textView.setText(f3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void getTimeYear(Activity activity, TextView textView) {
        try {
            String timeData = textView.getText().toString();
            date = sdf.parse(timeData);
            f1 = yy.format(date);
            f2 = sdf2.format(date);
            f3 = dd.format(date);
            startYear[0] = Integer.parseInt(f1);
            startMonth[0] = Integer.parseInt(f2);
            startDay[0] = Integer.parseInt(f3);
            new DatePickerDialog(activity, 0, (datePicker, year, month, dayOfMonth) -> {

                startYear[0] = year;
                startMonth[0] = month;
                startDay[0] = dayOfMonth;
                final String data = year + "";
                textView.setText(data);
            }, startYear[0], startMonth[0] - 1, startDay[0]).show();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
