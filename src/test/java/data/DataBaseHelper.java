package data;


import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Assertions;

import java.sql.Connection;
import java.sql.DriverManager;


public class DataBaseHelper {
    private static final String url = System.getProperty("db.url");
    private static final String user = System.getProperty("db.user");
    private static final String password = System.getProperty("db.password");


    @SneakyThrows
    public static String getStatusPaymentWithoutCredit() {
        String statusSql = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        QueryRunner runner = new QueryRunner();
        Connection conn = getConnection();
        return (runner.query(conn, statusSql, new ScalarHandler<>()));
    }

    @SneakyThrows
    public static String getStatusPaymentWithCredit() {
        var statusSql = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        QueryRunner runner = new QueryRunner();
        Connection conn = getConnection();
        return (runner.query(conn, statusSql, new ScalarHandler<>()));

    }

    @SneakyThrows
    private static Connection getConnection() {
        return DriverManager.getConnection(
                url, user, password
        );
    }

    @SneakyThrows
    public static void cleanDataBase() {

        var pays = "DELETE FROM payment_entity";
        var credits = "DELETE FROM credit_request_entity";
        var orders = "DELETE FROM order_entity";
        QueryRunner runner = new QueryRunner();
        Connection conn = getConnection();
        runner.execute(conn, pays);
        runner.execute(conn, credits);
        runner.execute(conn, orders);


    }

    @SneakyThrows
    public static void assertDbEmpty() {
        String sql = "Select count(*) from credit_request_entity;";
        String sql1 = "Select count(*) from order_entity;";
        String sql2 = "Select count(*) from payment_entity;";
        QueryRunner runner = new QueryRunner();
        Connection conn = getConnection();
        Long count0 = runner.query(conn, sql, new ScalarHandler<>());
        Assertions.assertEquals(0, count0);
        Long count1 = runner.query(conn, sql1, new ScalarHandler<>());
        Assertions.assertEquals(0, count1);
        Long count2 = runner.query(conn, sql2, new ScalarHandler<>());
        Assertions.assertEquals(0, count2);

    }

}
