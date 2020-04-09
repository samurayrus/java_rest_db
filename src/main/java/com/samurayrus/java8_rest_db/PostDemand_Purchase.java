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
    {        try {
        
                    System.out.println(name +" "+QUA +" "+ Cost +" "+ dateS  );
                System.out.println("com.samurayrus.java8_rest_db.PostDemand_Purchase.purchase()");
                if(name==null || name == "" || dateS==null)
                {System.out.println("er1"); return "Error. Empty"; }
             System.out.println("er133");
                String[] datePars=dateS.split("-");  //Перенести в парсер для даты
                System.out.println(datePars[0] +" " + datePars[1] + " " + datePars[2]);
                LocalDate dateD = LocalDate.of(Integer.parseInt(datePars[2]),Integer.parseInt(datePars[1]) , Integer.parseInt(datePars[0]));
               // LocalDate dateD = LocalDate.of(2020,11,01);
                System.out.println("com.samurayrus.java8_rest_db.PostDemand_Purchase.purchase()2222");
                  if(Integer.parseInt(Cost)<=0) {System.out.println("er12");return "Error - Cost<=0";}
                 System.out.println("--Парсер получил " + metS);
                 System.out.println("com.samurayrus.java8_rest_db.PostDemand_Purchase.purchase()2222");
                 
                if(DB_use.DB_check_name(name)==0){System.out.println("--"+metS+" нет такого");
                return "ERROR or cant find this product";}
System.out.println("com.samurayrus.java8_rest_db.PostDemand_Purchase.purchase()2222");
                //DB_use.DB_Upd("INSERT INTO "+metS+" (NAME, QUA, COST, DATE) VALUES ('"+name+"',"+QUA+","+Cost+","+retDate+");");  //Делаем запись
                System.out.println("После записи");
                System.out.println(dateS);
                String sql;
                if(metS.equals("DEMAND"))
                { sql="INSERT INTO DEMAND (NAME, QUA, COST, DATE) VALUES (?,?,?,?)";  System.out.println("DEMAND");}
                else
                {  sql="INSERT INTO PURCHASE (NAME, QUA, COST, DATE) VALUES (?,?,?,?)";System.out.println("other");}
                    //"INSERT INTO DEMAND (NAME, QUA, COST, DATE) VALUES (?,?,?,?)"
                return DB_use.DB_PurDem(sql, name, Integer.parseInt(QUA), Integer.parseInt(Cost), dateD);
            }
            catch(java.lang.NumberFormatException e){System.out.println("ошибка");
            return "Error - Not Int";}
    
    
    }
}
