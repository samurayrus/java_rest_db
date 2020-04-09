# java_rest_db

Сделал получение данных из url запроса через String, что плохо. Нужно сделать через json (text->json), так было бы правильнее и намного проще.

- Записывает животное в каталог:POST localhost:8000/api/newproduct?moloko
- Завоз на "склад":POST localhost:8000/api/purchase?moloko;25;1025;01.02.2020
- Продажу: POST localhost:8000/api/demand?moloko;25;1025;01.02.2020
- Рассчитывает прибыль до определенной даты: GET localhost:8000/api/salesreport?moloko;01.02.2020

Желательно пользоваться через командную строку : curl -X GET localhost....

Можно продать больше чем закупили (животные могут и размножаться внутри заведения), но чтобы закупить или продать необходимо добавить животное в каталог. 

 Для создания необходимых таблиц для базз данных в PostgreSQL можно вызвать из App -> DB_use.DB_create_Tables();
 Для удаления -> DB_use.DB_destroy_Tables();
 
 _______
HttpServer, PostgreSQL;
