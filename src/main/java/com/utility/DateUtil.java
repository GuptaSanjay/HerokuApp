package com.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

  public static String currentDateExtentReport(){
    Date d  = new Date();
    //SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
    return String.format(String.valueOf(d)).replaceAll("[^A-Za-z0-9]","_");
  }
}
