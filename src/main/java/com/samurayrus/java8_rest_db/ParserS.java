package com.samurayrus.java8_rest_db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
public class ParserS {
        public static String[] parser(String ent)
   {
        System.out.println("Парсер работает");
       String[] words = ent.split(";");
      // if(words.length!=leng) {return null;} 

       switch(words.length)
       {
           case 4:   words[3]=words[3];
           if(parser_int(words[1])==false || parser_int(words[2])==false || words[3]==null) return null; 
           break;
           
           case 2:   words[1]=words[1];
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
                       
        try {int ark2 = Integer.valueOf(ent); if(ark2>0) return true; return false;}
        catch(java.lang.NumberFormatException e){System.out.println(e +" StrToInt "+ent); return false;}
       
        }
        
        private static String parser_Date(String ent) // переводит в превычный вид. Можно будет убрать
        {
            
        SimpleDateFormat SDF=new SimpleDateFormat("dd.MM.yyyy");
         SDF.setLenient(false);  //- мягкий режим

             try {java.util.Date retDate=SDF.parse(ent.trim());//- пробелы. Надо будет везде поставить
             String Ss = SDF.format(retDate);//  Date_String к общему 01.02. и тд
             return Ss;
             }   
             catch(ParseException e) {System.out.println(e +" StrToDate "+ent); return null;}
             
        
       
        }
}
