package com.samurayrus.java8_rest_db;

import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnswerH {
       public void HttpAnswer(HttpExchange exchange,String respText ) //Антидублирование кода
   {
       try 
       {
           exchange.sendResponseHeaders(200, respText.getBytes().length);
           OutputStream output = exchange.getResponseBody();
           output.write(respText.getBytes());
           
           //Нужное мне
           System.out.println(exchange.getRequestURI() + " URI");
           System.out.println(exchange.getRequestURI().getRawQuery() + " Query");
           System.out.println("Отослан ответ: " + respText);
           //
           
           output.flush();
           exchange.close();
       } catch (IOException ex) {
           Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
}
