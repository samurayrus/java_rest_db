package com.samurayrus.java8_rest_db;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

/**
 *
 * @author maxim
 */
public class App {
   public static void main(String[] args) throws IOException {
       
       int serverPort = 8000;

         /*
         Добавление животного в каталог - имя
         Закупка - имя, количество, цена за штуку, дата
         Продажа - аналог
         GET итог - имя + дата. Выход - прибыль
         */
         //Хранение данных пока сделаю через листы и массивы

       HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
       //ContextCreator
       //--------------------------
       ArrayList<String[]> urllist = new ArrayList<>(); 
       urllist.add(new String[]{"/newproduct","POST", "1"});
       urllist.add(new String[]{"/purchase","POST", "4"});
       urllist.add(new String[]{"/demand","POST", "4"});
       urllist.add(new String[]{"/salesreport","GET", "2"});
       
       for(String[] g: urllist)
       {
        Context con = new Context(server,g[0],g[1], Integer.valueOf(g[2]));
        con.context();
           System.out.println("Created Context" + g[0] + "With " + g[1]);
       }
       
       //--------------------------
       server.setExecutor(null); 
       server.start();
       //DB_use.DB_create_Tables(); //Создание таблиц
   }
   
   
}

