/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samurayrus.java8_rest_db;

import java.time.LocalDate;


/**
 *
 * @author maxim
 */
 class GetSales {
       
     static String Sales(String name, String dateS)
    {
        String[] datePars;
        LocalDate dateD;
        
        if(name==null || "".equals(name) ||dateS==null )
        return "Error. Empty name";
        
        datePars=dateS.split("-");  //Перенести в парсер для даты
        dateD = LocalDate.of(Integer.parseInt(datePars[2]),Integer.parseInt(datePars[1]) , Integer.parseInt(datePars[0]));
        
        return DB_use.DB_check_time(dateD, name); 
    }
}
