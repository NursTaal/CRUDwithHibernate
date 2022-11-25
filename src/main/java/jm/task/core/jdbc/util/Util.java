package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    public static final String URL = "jdbc:mysql://localhost:3306/nursdb";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            System.out.println("Connection OK");
            return connection;
         } catch (SQLException e) {
            System.out.println("Connection ERROR !!!");
            throw new RuntimeException(e);
        }
    }
}
/*

3) Дальше по порядку идём в метод, имплементящий интерфейс UserService, а именно UserServiceImpl

4) Тут ключевая строка, которая необходима: UserDao userDao = new UserDaoJDBCImpl();
Создаем объект класса UserDaoJDBCImpl с учетом наследования (имплементации интерфейса UserDao)
на этом объекте будем вызывать нужные нам методы. saveUser
В этом классе не забываем добавить соут при вызове метода
saveUser + возвращаем вывод в консоль эррей листа пользователей из таблицы
5) UserDao без изменений
6) И самое сложное.
UserDaoJDBCImpl implements UserDao
Главное - трай с ресурсами и нет головняка с закрытиями. try (Statement statement = connection.createStatement()) {
----------------------------------------------------------------------------------------------
Про разницу DAO и Service - DAO помогает нам в будущем поменять инструмент,
с которым мы работаем, не меняя саму реализацию, допустим, подключить другую БД или использовать Hibernate.
Я сперва завис на этом моменте, поэтому советую внимательно к этому отнестись.
Код пишем в DAO, в сервисе только вызываем реализацию.

----------------------------------------------------------------------------------------------------------

7) Что нужно понять из всего увиденного:
    а) У нас есть сущность User, это model
    b) Data Access Object (DAO) — это класс, содержащий CRUD методы для конкретной модели
    c) Логика слоёв: DB (база данных) -> DAO (CRUD методы) -> Service (бизнес-логика) -> Main (запуск программы)
8) Создаём собственную MySQL базу данных и подключаем ее к IntelliJ IDEA, как показано на скриншоте в ТЗ
9) Начинаем писать код с класса Utill  // реализуйте здесь настройку соединения с БД
10) Ниже полезный комментарий Максима Чуприна (мне очень помог, повторяться не буду)
11) UserDaoJDBCImpl - здесь расписываем основной функционал (CRUD методы взаимодействия с SQL)
12) Вишенка на торте! Комментарий от самой Каты (чуть ли не первое видео в комментах).
UP: ссылка на 3й урок, а там всего 5 уроков со всей необходимой информацией (как жалко, что я увидела это, когда код был почти готов (написано через боль) https://www.youtube.com/watch?v=nfpq6-bvDcc
13) Финал: в Мейне используем методы слоя Service для объекта класса UserService
14) RUN запускаем из класса Test (должны загореться 6 зелёных галочек и это чистый кайф!)
 */
