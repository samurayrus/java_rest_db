/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samurayrus.java8_rest_db;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author maxim
 */
 class PostDemand_Purchase {
     
     static String purchase(String name, String QUA, String Cost, String dateS, String metS)
    {       
        try {
                String sql;
                String[] datePars;
                LocalDate dateD;
                
                if(name==null || name == "" || dateS==null)
                {return "Error. Empty"; }
                
                datePars=dateS.split("-");  //Перенести в парсер для даты
                System.out.println(datePars[0] +" " + datePars[1] + " " + datePars[2]);
                
                dateD = LocalDate.of(Integer.parseInt(datePars[2]),Integer.parseInt(datePars[1]) , Integer.parseInt(datePars[0])); 
                
                if(Integer.parseInt(Cost)<=0) {System.out.println("er12");return "Error - Cost<=0";}
                 System.out.println("--Парсер получил " + metS);
                 
                if(DB_use.DB_check_name(name)==0){System.out.println("--"+metS+" нет такого");
                return "ERROR or cant find this product";}

                return DB_use.DB_PurDem(metS, name, Integer.parseInt(QUA), Integer.parseInt(Cost), dateD);
            }
            catch(java.lang.NumberFormatException e){System.out.println("NumberFormatException");
            return "Error - Not Int";}
    
    
    }
}
