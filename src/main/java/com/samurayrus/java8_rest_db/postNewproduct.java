package com.samurayrus.java8_rest_db;


/**
 *
 * @author maxim
 */
 class postNewproduct {
     static String newproduct(String name)
    {
                
                if(name==null || name == "")  //Все проверки нужно перенести в парсер
                return "Error. Empty name";
                
                if(DB_use.DB_check_name(name)>0){System.out.println("--Newproduct ошибка");
                return "ERROR or Products already added";}
                else {
                System.out.println("--Newproduct записывает "+name);
                DB_use.DB_NewProduct(name);  //Записываем новый продукт
                System.out.println("--Newproduct записал");
                return "Has been created";}

    }
}
