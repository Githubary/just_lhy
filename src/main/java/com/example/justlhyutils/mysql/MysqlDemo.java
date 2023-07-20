package com.example.justlhyutils.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2023/7/7 10:45
 */
public class MysqlDemo {

    public static void main(String[] args) throws SQLException, InterruptedException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/order-center", "root", "lhyshizhu");
//        normal(connection);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    transactions(connection);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
//        System.out.println("开始执行查询");
//        normal(connection);
//        System.out.println("执行完毕");
        exceptionDeal(connection);
    }


    /**
     * 23000: 违反唯一约束（Unique Constraint Violation）
     * 可能原因：插入或更新操作违反了唯一约束条件，例如重复的主键值。
     * 23505: 违反唯一约束（Unique Constraint Violation）
     * 可能原因：违反了唯一约束条件，例如在插入或更新操作中试图插入重复的唯一值。
     * 23503: 外键约束失败（Foreign Key Constraint Failure）
     * 可能原因：违反了外键约束条件，例如在插入或更新操作中引用了不存在的外键值。
     * 23502: 非空约束失败（Not Null Constraint Failure）
     * 可能原因：违反了非空约束条件，例如在插入或更新操作中试图插入空值到非空列。
     * 42000: 语法错误或命令解析错误（Syntax Error or Command Parsing Error）
     * 可能原因：SQL语句存在语法错误或无法解析的命令。
     * 40001: 事务冲突（Transaction Conflict）
     * 可能原因：并发事务之间发生了冲突，例如死锁或事务回滚
     * @param connection
     */
    private static void exceptionDeal(Connection connection) {
        try {
            connection.prepareStatement("select * from customer");
        } catch (SQLException e) {
            if (e.getSQLState().startsWith("40")) {

            } else {

            }
        }
    }


    public static void normal(Connection con) throws SQLException {
        //statement类似一个执行者。帮助执行sql命令
        Statement statement = con.createStatement();
        //execute可以执行任何的语句，但不能防止sql注入
        boolean execute = statement.execute("select * from `customer` where id = '181201'");
        System.out.println("查询是否执行成功:" + execute);
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("pwd"));
            System.out.println(resultSet.getString("sex"));
            System.out.println(resultSet.getString("birthday"));
            System.out.println(resultSet.getString("address"));
            System.out.println(resultSet.getString("email"));
        }
    }

    public static void preparedStatement(Connection con) throws SQLException {
        String sql = "select * from `customer` where id = '181201'";
        //通过PreparedStatement可以阻止sql注入
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("pwd"));
            System.out.println(resultSet.getString("sex"));
            System.out.println(resultSet.getString("birthday"));
            System.out.println(resultSet.getString("address"));
            System.out.println(resultSet.getString("email"));
        }
    }

    public static void transactions(Connection con) throws SQLException, InterruptedException {
        //关闭自动提交，改成手动提交
        con.setAutoCommit(false);
        String sql = "update customer set name = '张三2',pwd = '123456',sex = '男' where id = '181201'";
        String sql2 = "update customer set name = '李四2',pwd = '987654',sex = '女' where id = '364859'";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        PreparedStatement preparedStatement2 = con.prepareStatement(sql2);
        preparedStatement.executeUpdate();
        //此时去查询数据库，可以发现第一条语句并没有执行成功。直到commit后，两条语句才一起执行
        System.out.println("开始执行第一条更新，等待10秒");
        normal(con);
        Thread.sleep(10000);
        System.out.println("开始执行第二条更新，等待结束");
        preparedStatement2.executeUpdate();
        con.commit();
        normal(con);
    }

}
