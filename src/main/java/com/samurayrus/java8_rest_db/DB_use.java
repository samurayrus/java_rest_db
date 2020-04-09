package com.samurayrus.java8_rest_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

// Products Purchase Demand Salesreport

public class DB_use {
      static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/Pg_DB";
      static final String USER = "postgres";
      static final String PASS = "1234";
      

      public static String DB_create_Tables()  //Создание таблиц. Можно вызвать
      {
          String sql1, sql2, sql3;
          String ex;
          System.out.println("--Начало создания таблиц");
                sql1 = "CREATE TABLE PRODUCTS (NAME TEXT PRIMARY KEY NOT NULL)";
                 ex = DB_Upd(sql1);
                 
                sql2 = "CREATE TABLE PURCHASE " +
                "(NAME TEXT    NOT NULL," +
                " QUA  INT     NOT NULL, " +
                " COST INT     NOT NULL,  " +
                " DATE DATE    NOT NULL )";
                ex += DB_Upd(sql2);       
                
                sql3 = "CREATE TABLE DEMAND " +
                "(NAME TEXT    NOT NULL," +
                " QUA  INT     NOT NULL, " +
                " COST INT     NOT NULL,  " +
                " DATE DATE    NOT NULL )";
                ex += DB_Upd(sql3);
                System.out.println("--Конец создания таблиц " + ex);
                if(ex==null){return null;}
                else{return ex;}
                

      }
      //DROP TABLE CUSTOMERS;]
       static String DB_destroy_Tables()  //Удаление таблиц. Для теста
      {
          String sql1, sql2, sql3;
          String ex;
          System.out.println("--Начало создания таблиц");
                sql1 = "DROP TABLE PURCHASE";
                 ex = DB_Upd(sql1);
                sql1 = "DROP TABLE DEMAND";
                 ex = DB_Upd(sql1);
                 sql1 = "DROP TABLE PRODUCTS";
                 ex = DB_Upd(sql1);
                System.out.println("--Конец удаления таблиц " + ex);
                if(ex==null){return null;}
                else{return ex;}
      }
      
      //_________________
      private static String DB_Upd(String sql)  //Выполнение sql запроса на создание таблиц (Только)
      {
        try {  
            Connection con;
            Statement statement;
            if(sql=="" || sql==null) return "Empty param";
            
            con= DB_connection();
            statement = con.createStatement();
            
            if(statement==null) {return "PSQ_EX";}
        
             statement.executeUpdate(sql);

             statement.close();
             System.out.println("--Конец создания таблиц");
            return "Complete";
        } 
        catch (SQLException ex2) {System.out.println("-ERROR ");return "ERROR_SQL";}
      
      }
      
       static Connection DB_connection() //Проверка подключения + само подключение
      {
          try {
              Class.forName("org.postgresql.Driver");
             
              Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             // Statement statement = con.createStatement();
              return con;
          } catch (ClassNotFoundException | SQLException ex) {
             // Logger.getLogger(DB_use.class.getName()).log(Level.SEVERE, null, ex);
              return null;
          }
          
       }

       static String DB_NewProduct(String name)  //Добавление нового животного в каталог
      {
          //
        try {  
            
            Connection con= DB_connection();
            
            PreparedStatement ps = con.prepareStatement(
            "INSERT INTO PRODUCTS (NAME) VALUES (?)");
            ps.setString(1, name);
            ps.executeUpdate();
            
           // "INSERT INTO "+metS+" (NAME, QUA, COST, DATE) VALUES ('"+name+"',"+QUA+","+Cost+",'"+retDate+"');"
           
            System.out.println("--Конец создания таблиц Проц 2");
            return "Complete";
        } 
          catch (SQLException ex2) {System.out.println("-ERROR ");return "ERROR_SQL ";}
      
      }
       static String DB_PurDem(String metS, String name, int QUA, int Cost, LocalDate date)  //Запись продажа или закупка.
      {
           String sql="Err";
          //
        try {  
            
            Connection con;
            PreparedStatement ps;
            
            if(metS.equals("DEMAND"))
            { sql="INSERT INTO DEMAND (NAME, QUA, COST, DATE) VALUES (?,?,?,?)";}
            
            if(metS.equals("PURCHASE"))
            { sql="INSERT INTO PURCHASE (NAME, QUA, COST, DATE) VALUES (?,?,?,?)";}
            
            con= DB_connection();
            
            ps = con.prepareStatement(
            sql);
            ps.setString(1, name);
            ps.setInt(2, QUA);
            ps.setInt(3, Cost);
            java.sql.Date sqlDate = java.sql.Date.valueOf(date);
            ps.setDate(4, sqlDate);
            ps.executeUpdate();
            
           
           
            System.out.println("--Конец создания таблиц Проц 2");
            return "Complete";
        } 
          catch (SQLException ex2) {System.out.println("-ERROR " + sql);return "ERROR_SQL " + sql;}
      
      }
            
      //_________________
       static int DB_check_name(String name)   //Проверка, существует ли товар или нет
      {
        try { 
        int sc=0;
        Connection con;
        System.out.println("--DB_check_name начал проверку");
        
        con= DB_connection();
        PreparedStatement ps = con.prepareStatement(
        "SELECT * FROM PRODUCTS where NAME=?");
        
        if(ps==null) {return 3;}
        
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        
        
        while (rs.next()) {
        System.out.println("name = '" + rs.getString("name"));
        sc++;
        }
        
        rs.close();
        ps.close();
        
        System.out.println("--DB_check_name закончил проверку");
        if(sc>0) return 1; else return 0;  // 1 - Уже есть, 0 - еще нет
        }
          catch (SQLException ex2) {return 3;}
      }

       static String DB_check_time(LocalDate date, String name)  //Вычисление прибыли до определенной даты
      {
            int sc=0; //Прибыль
            
            String sql1, sql2;
            sql1="SELECT * FROM PURCHASE where NAME =? AND DATE <=?::date";
            sql2= "SELECT * FROM DEMAND where NAME =? AND DATE <=?::date";
            
            System.out.println("--DB_check_time начал проверку");
            
            try{
            sc += Integer.parseInt(DB_GetSales_sql(sql2, date, name));
            sc -= Integer.parseInt(DB_GetSales_sql(sql1, date, name));
            }
            catch(NumberFormatException ex) {return "ERROR";}

           return String.valueOf(sc);
      }
       

       private static String DB_GetSales_sql(String sql, LocalDate date, String name)  //Разбил на две части
      {
        try { 
            int sc=0;
            ResultSet rs;
            
            Connection con= DB_connection();
            PreparedStatement ps = con.prepareStatement(
            sql);
            
            if(ps==null) {return "PSQ_EX";}
            
            java.sql.Date sqlDate = java.sql.Date.valueOf( date);
               
            ps.setString(1, name);
            ps.setDate(2, sqlDate);
       
            rs = ps.executeQuery();
        
            while ( rs.next() ) {
            sc+=rs.getInt("QUA")*rs.getInt("COST");
            System.out.println("--DB_check_PURC НАШЕЛ "+ sc);
            }
            
        ps.close();
        rs.close();


        return String.valueOf(sc);
        }
          catch (SQLException ex2) {return "ERR";}
       }
}
