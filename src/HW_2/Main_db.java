package HW_2;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Main_db {
    public static void main(String[] args) {

        try {
            DbMaker.connect();

            boolean ifCreated = DbMaker.createDB("test_tbl", "id primary key", "name text", "score int");
            if(ifCreated)
                System.out.println("Таблица создана");
            else
                System.out.println("Таблица не создана");


            try {
                int a = DbMaker.uppdateBD("test_tbl", "5, \"five\", 444");
            }catch (StringIndexOutOfBoundsException e){
                System.err.println("StringIndexOutOfBoundsException");
            } catch (SQLException throwables) {
                System.err.println("База не найдена");
            }

            DbMaker.findOne("test_tbl", "id", "5");



            DbMaker.dellDB("test_tbl");

        } catch (SQLException throwables) {
            System.err.println("Ошибка базы");
        } catch (ClassNotFoundException e){
            System.err.println("Ошибка класса");
        }finally {
            DbMaker.disconnect();
        }


        try {
            DbScanerCreator.connect();

            boolean ifCreated = DbScanerCreator.createDB();
            if(ifCreated)
                System.out.println("Таблица создана");
            else
                System.out.println("Таблица не создана");

            try {
                int a = DbScanerCreator.uppdateBD();
            } catch (SQLException e){
                System.out.println("Такой таблицы не существует");
            }

            try{

                ResultSet rs = DbScanerCreator.findOne();
                while (rs.next()) {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        String s = rs.getString(i);
                        System.out.print(s + " | ");
                    }
                    System.out.println();
                }
            }catch (SQLException e){
                System.out.println("База не найдена");
            }

            try {
                DbScanerCreator.deleteOne();

            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("");

            }

            DbScanerCreator.dellDB();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            DbScanerCreator.disconnect();
        }

    }
}
