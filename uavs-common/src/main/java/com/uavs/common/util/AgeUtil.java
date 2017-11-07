package com.uavs.common.util;

import java.util.Calendar;
import java.util.Date;

public class AgeUtil {


    public static Date getBirthday(String IDnumber){
        int leh = IDnumber.length();
        if (leh != 18 && leh != 15) {
            return new Date();
        }
        else {
            String dates;
            if (leh == 18) {
//                int se = Integer.valueOf(IDnumber.substring(leh - 1)) % 2;
                dates = IDnumber.substring(6, 10) + "-" + IDnumber.substring(10, 12) + "-" + IDnumber.substring(12, 14);
                System.out.println(dates);
                String sex = "";
                if (leh == 0) {
                    sex = "M";
                }else {
                    sex = "F";
                }
                System.out.println(sex + "\t" + dates);
            }
            else {
                dates = "19" + IDnumber.substring(6, 8) + "-" + IDnumber.substring(8, 10) + "-" + IDnumber.substring(10, 12);
                System.out.println(dates);
            }
            Date parse = DateUtils.parse(dates, "yyyy-MM-dd");
            return parse;
        }
    }

    public static int getAge(Date birthDay) {
        //获取当前系统时间
        Calendar cal = Calendar.getInstance();
        //如果出生日期大于当前时间，则抛出异常
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        //取出系统当前时间的年、月、日部分
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        //将日期设置为出生日期
        cal.setTime(birthDay);
        //取出出生日期的年、月、日部分
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        //当前年份与出生年份相减，初步计算年龄
        int age = yearNow - yearBirth;
        //当前月份与出生日期的月份相比，如果月份小于出生月份，则年龄上减1，表示不满多少周岁
        if (monthNow <= monthBirth) {
            //如果月份相等，在比较日期，如果当前日，小于出生日，也减1，表示不满多少周岁
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            }else{
                age--;
            }
        }
        return age;
    }


}
