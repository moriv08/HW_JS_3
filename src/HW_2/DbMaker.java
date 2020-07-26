package HW_2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DbMaker {

    private static Connection connection;
    public static Statement stmt;

    public static void connect() throws ClassNotFoundException, SQLException{
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:test_bd.db");
        stmt = connection.createStatement();
    }

    public static void disconnect(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //  Создаем таблицу
    public static boolean createDB(String... request){
        System.out.printf("%cСоздаем таблицу %c____________________%c", '\n', '\n', '\n');

        int checker = -1;
        String result = "";

        for (int i = 1; i < request.length; i++)
            result += request[i] + ", ";

        try {
            checker = stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + request[0] + "(" + result.substring(0, result.length() - 2) + ")");
        } catch (SQLException e){
            System.out.println("Ошибка ввода!!!!! Проверьте введенные данные!!!!!");
            return false;
        }

        return (checker == 0) ? true : false;
    }

    // удаляем таблицу
    public static boolean dellDB(String name) throws SQLException{

        System.out.printf("%cУдаляем таблицу %c____________________%c", '\n', '\n', '\n');

        Scanner scan = new Scanner(System.in);

        List<String> allAnswers = new ArrayList<>();
        allAnswers.add("no");
        allAnswers.add("n");
        allAnswers.add("нет");
        allAnswers.add("н");
        System.out.println("Вы уверены, что хотите удалить таблицу " + name + "? для отказа нажмите нет, н, n, или no");

        String ansver = scan.nextLine();

        if (allAnswers.contains(ansver))
            return false;

        return stmt.execute("DROP TABLE IF EXISTS " + name);
    }

// добавляем в таблицу строчку
    public static int uppdateBD(String table, String values) throws SQLException{

        System.out.printf("%cЗаполняем таблицу %c____________________%c", '\n', '\n', '\n');

        int count = -1;
        String tableColumns = "";
        String request = "";

        ResultSet rs = stmt.executeQuery("SELECT * FROM " + table);
        ResultSetMetaData rsmd = rs.getMetaData();

        for (int i = 1; i <= rsmd.getColumnCount(); i++)
            tableColumns += rsmd.getColumnName(i) + ", ";

        try {
            count = stmt.executeUpdate("INSERT INTO " + table + "(" +  tableColumns.substring(0, tableColumns.length() - 2) + ") " + "VALUES(" + values + ");");
            System.out.println("Вы внесли в таблицу " + table + " следующие данные: название(" +  tableColumns.substring(0, tableColumns.length() - 2)+ ") " + "значения(" + values + ")");
        } catch (SQLException throwables) {
            System.out.println("Ошибка ввода!!!!! Проверьте введенные данные!!!!!");
            System.out.println("Таблица " + table + " необходимо заполнить поля(" +  tableColumns.substring(0, tableColumns.length() - 2)+ ") " + " вы ввели следующие значения(" + values + ")");

            return count;
        }
        return count;
    }


    // получение записи из таблицы
    public static void findOne(String table, String id, String name) throws SQLException{

        ResultSet rs = stmt.executeQuery("SELECT " + id + " FROM " + table);
        String request = Logic.makeRq(rs.getMetaData().getColumnType(1), name);

        System.out.println("В таблице " + table + " найдено " + id + "=" + request.substring(0, request.length() - 2));
        rs = stmt.executeQuery("SELECT * FROM " + table + " WHERE " + id + "=" + request.substring(0, request.length() - 2));

        while (rs.next()){
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                String s = rs.getString(i);
                System.out.print(s + " | ");
            }
            System.out.println();
        }
    }


}
