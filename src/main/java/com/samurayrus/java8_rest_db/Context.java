package com.samurayrus.java8_rest_db;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.util.ArrayList;

public class Context{
    private HttpServer server;
    private String name, param;
    
    public Context(HttpServer server, String name, String param)
    {
    this.server=server;
    this.name=name;
    this.param=param;
    }
    
    public void context()
    {       
    
            server.createContext("/api"+name, (exchange -> {
                
            String post;
            String respText;
            String s;
            String [] parS;
           //_____________
            post = exchange.getRequestMethod();
            respText = "111";
            
           if(param.equals(post)){       //Соответствие параметру
               System.out.println("Запрос "+param);
                s = exchange.getRequestURI().getRawQuery();
                parS=ParserS.parser(s);
               if(parS==null) {respText="Error_Format";}
               else{
               switch(name)
               {
                   case "/newproduct": 
                       if(parS.length!=1) respText="Null Param"; else  //Проверка на соответствие действия количеству переменных
                       respText=postNewproduct.newproduct(parS[0]);
                       break;
                       
                   case "/purchase":
                       if(parS.length!=4) respText="Null Param"; else 
                       respText=PostDemand_Purchase.purchase(parS[0],parS[1],parS[2],parS[3], "PURCHASE");
                       break;
                       
                   case "/demand":
                       if(parS.length!=4) respText="Null Param"; else 
                       respText=PostDemand_Purchase.purchase(parS[0],parS[1],parS[2],parS[3], "DEMAND");
                       break;
                       
                   case "/salesreport":
                       if(parS.length!=2) respText="Null Param"; else 
                       {
                       GetSales getSales = new GetSales(parS[0],parS[1]); //вариант с nonStatic
                       respText = getSales.Sales();
                       }
                       break;
                       default: respText="error name context "+ Context.class.getName(); break;
               }}
               
           }
            else{
            System.out.println("Ошибочный формат запроса...");
            respText="ERROR. Use only '"+param+"' for this";
           }
           
           AnswerH answerH = new AnswerH();
           answerH.HttpAnswer(exchange, respText); //Тут надо подумать
       }));
    }
}
