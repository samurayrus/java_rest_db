# java_rest_db

Сделал получение данных из url запроса через String, что плохо. Нужно сделать через json (text->json), так было бы правильнее и намного проще. Также смущает обилие static методов, но маленькой программе вреда не наносят. Также по хорошему надо перенести все разрозненные проверки в парсер.

- Записывает животное в каталог:POST localhost:8000/api/newproduct?moloko
- Завоз на "склад":POST localhost:8000/api/purchase?moloko;25;1025;01.02.2020
- Продажу: POST localhost:8000/api/demand?moloko;25;1025;01.02.2020
- Рассчитывает прибыль до определенной даты: GET localhost:8000/api/salesreport?moloko;01.02.2020

Желательно пользоваться через командную строку : curl -X GET localhost....

Можно продать больше чем закупили (животные могут и размножаться внутри заведения), но чтобы закупить или продать необходимо добавить животное в каталог. 

 Для создания необходимых таблиц для базз данных в PostgreSQL можно вызвать из App -> DB_use.DB_create_Tables();
 Для удаления -> DB_use.DB_destroy_Tables();
 
 BD_use отвечает за все операции с базой данных.
 
 В ней есть вот эта великолепная команда: "SELECT dem.s1-pur.s2 FROM (SELECT SUM(qua*cost) s1 FROM demand where name =? AND DATE <=?::date) dem, (SELECT SUM(qua*cost) s2 FROM purchase where name =? AND DATE <=?::date) pur;", которая вычисляет прибыль по товару (животному) до определенной даты. qua -количество проданных, cost - цена.
 _______
HttpServer, PostgreSQL;
