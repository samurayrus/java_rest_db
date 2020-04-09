package com.samurayrus.java8_rest_db;
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
       //_____________
           String post = exchange.getRequestMethod();
           String respText = "111";
           if(param.equals(post)){
               System.out.println("Запрос "+param);
               String s = exchange.getRequestURI().getRawQuery();
               String [] parS=ParserS.parser(s);
               if(parS==null) {respText="Error_Format";}
               else{
               switch(name)
               {
                   case "/newproduct": 
                       if(parS.length!=1) respText="Null Param"; else  //
                       respText=postNewproduct.newproduct(parS[0]);
                       break;
                   case "/purchase":
                       if(parS.length!=4) respText="Null Param"; else 
                       respText=PostDemand_Purchase.purchase(parS[0],parS[1],parS[2],parS[3], "Other");
                       break;
                   case "/demand":
                       if(parS.length!=4) respText="Null Param"; else 
                       respText=PostDemand_Purchase.purchase(parS[0],parS[1],parS[2],parS[3], "DEMAND");
                       break;
                   case "/salesreport":
                       if(parS.length!=2) respText="Null Param"; else 
                       respText=GetSales.Sales(parS[0],parS[1]);
                       break;
                       default: respText="error name context "+ Context.class.getName(); break;
               }}
               
           }
            else{
            System.out.println("Ошибочный формат запроса...");
            respText="ERROR. Use only '"+param+"' for this";
           }
               AnswerH.HttpAnswer(exchange, respText);
       }));
    }
}
