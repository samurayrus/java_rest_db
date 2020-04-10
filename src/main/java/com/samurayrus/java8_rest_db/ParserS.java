package com.samurayrus.java8_rest_db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
public class ParserS {
        public static String[] parser(String ent)
   {
        System.out.println("Парсер работает");
        String[] words = ent.split(";");

       switch(words.length)
       {
           case 4:  // words[3]=words[3];
           if(parser_int(words[1])==false || parser_int(words[2])==false || words[3]==null) return null; 
           break;
           
           case 2:  // words[1]=words[1];
           if(words[1]==null) return null;
           break;
           
           case 1:   break;
           default: return null;
       }
       //тестовый вывод
       for(String w:words)
       {
           System.out.println(w);
       }
       System.out.println("Парсер закончил");
       return words;
   }
        
        private static Boolean parser_int(String ent)
        {
            //Проверка чисел
                       
        try {int ark2 = Integer.valueOf(ent); if(ark2>0) return true; else return false;}
        catch(java.lang.NumberFormatException e){System.out.println(e +" StrToInt "+ent); return false;}
       
        }
}
