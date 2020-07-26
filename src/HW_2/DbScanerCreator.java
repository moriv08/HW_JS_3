package HW_2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DbScanerCreator {

    private static Connection connection;
    public static Statement stmt;

    public static void main(String[] args) {
        //        try {
//            DbScanerCreator.connect();
//
//
//            boolean ifCreated = DbScanerCreator.createDB();
//            if(ifCreated)
//                System.out.println("Таблица создана");
//            else
//                System.out.println("Таблица не создана");
//
//            try {
//                int a = DbScanerCreator.uppdateBD();
//            } catch (SQLException e){
//                System.out.println("Такой таблицы не существует");
//            }
//
//            try{
//
//                ResultSet rs = DbScanerCreator.findOne();
//                while (rs.next()) {
//                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
//                        String s = rs.getString(i);
//                        System.out.print(s + " | ");
//                    }
//                    System.out.println();
//                }
//            }catch (SQLException e){
//                System.out.println("База не найдена");
//            }
//
//            try {
//                DbScanerCreator.deleteOne();
//
//            } catch (ArrayIndexOutOfBoundsException e){
//                System.out.println("");
//
//            }
//
//            DbScanerCreator.dellDB();
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e){
//            e.printStackTrace();
//        }finally {
//            DbScanerCreator.disconnect();
//        }
    }

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


    // создание таблицы
    public static boolean createDB(){

        String[] columns;
        String name;
        int size = -1;
        int cheker = -1;
        String request = "";

        System.out.printf("%cСоздаем таблицу %c____________________%c", '\n', '\n', '\n');
        name = Logic.scannerStr("Введите название таблицы");
        while (size < 1)
            size = Logic.scannerInt("Введите количество колонок");

        columns = new String[size];

        for (int i = 0; i < size; i++) {
            columns[i] = Logic.scannerStr("Введите название и описание " + (i + 1) + "-й колонки");
            request += columns[i] + ", ";
        }

        try {
            cheker = stmt.executeUpdate("CREATE TABLE IF NOT EXISTS " + name + "(" + request.substring(0, (request.length() - 2)) + ")");
            System.out.println("Вы создали таблицу " + name + " со следующими полями(" + request.substring(0, (request.length() - 2)) + ")");
        } catch (SQLException e){
            System.out.println("Ошибка ввода!!!!! Проверьте введенные данные!!!!!");
            System.out.println("Таблица " + name + " введенные поля(" +  request.substring(0, (request.length() - 2)) + ") ");
            return false;
        }

        return (cheker == 0) ? true : false;
    }

    // удаление таблицы
    public static boolean dellDB() throws SQLException{
        Scanner scan = new Scanner(System.in);

        System.out.printf("%cУдаляем таблицу %c____________________%c", '\n', '\n', '\n');
        System.out.println("Введите название таблицы которую хотите удалить");

        String name = scan.nextLine();

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

    // добавление в таблицу
    public static int uppdateBD() throws SQLException{

        int count = -1;
        String tableColumns = "";
        String request = "";
        Scanner scan = new Scanner(System.in);

        System.out.printf("%cЗаполняем таблицу %c____________________%c", '\n', '\n', '\n');
        System.out.println("Введите название таблицы которую хотите заполнить");
        String name = scan.nextLine();

        ResultSet rs = stmt.executeQuery("SELECT * FROM " + name);

        ResultSetMetaData rsmd = rs.getMetaData();
        String[] lines = new String[rsmd.getColumnCount()];

        for(int i = 1; i <= rsmd.getColumnCount(); i++) {
            System.out.println("Введите " + rsmd.getColumnName(i));
            lines[i - 1] = scan.nextLine();
        }

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            tableColumns += rsmd.getColumnName(i) + ", ";
            request += Logic.makeRq(rsmd.getColumnType(i), lines[i - 1]);
        }

        try {
            count = stmt.executeUpdate("INSERT INTO " + name + "(" +  tableColumns.substring(0, tableColumns.length() - 2) + ") " + "VALUES(" + request.substring(0, request.length() - 2) + ");");
            System.out.println("Вы внесли в таблицу " + name + " следующие данные: название(" +  tableColumns.substring(0, tableColumns.length() - 2) + ") " + "значения(" + request.substring(0, request.length() - 2) + ")");
        } catch (SQLException throwables) {
            System.out.println("Ошибка ввода!!!!! Проверьте введенные данные!!!!!");
            System.out.println("Таблица " + name + " необходимо заполнить поля(" +  tableColumns.substring(0, tableColumns.length() - 2)+ ") " + " вы ввели следующие значения(" + request.substring(0, request.length() - 2) + ")");
            return -1;
        }
        return count;
    }

    // получение записи из таблицы
        public static ResultSet findOne() throws SQLException{

        String table = Logic.scannerStr("Введите название таблицы");
        String id = Logic.scannerStr("Введите название колонки");
        String name = Logic.scannerStr("Введите название ячейки");

        ResultSet rs = stmt.executeQuery("SELECT " + id + " FROM " + table);
        String request = Logic.makeRq(rs.getMetaData().getColumnType(1), name);

        System.out.println("В таблице " + table + " найдено " + id + "=" + request.substring(0, request.length() - 2));
        rs = stmt.executeQuery("SELECT * FROM " + table + " WHERE " + id + "=" + request.substring(0, request.length() - 2));

        return rs;
    }

    // удаление из таблицы
    public static int deleteOne() throws IndexOutOfBoundsException, SQLException{

        System.out.printf("%cУдаляем элементы из таблицы %c____________________%c", '\n', '\n', '\n');

        String name = Logic.scannerStr("Введите название таблицы, название колонки, идентификатор ячейки через пробел");
        String[] tokens = name.split(" ");

        return stmt.executeUpdate("DELETE FROM " + tokens[0] + " WHERE " + tokens[1] + "=" + tokens[2]);
    }
}
