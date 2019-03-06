package cn.itheima.tools;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtils {
    //创建c3p0核心类对象
    private static ComboPooledDataSource cpds = new ComboPooledDataSource();
    // 定义一个静态方法对外提供连接
    public static Connection getConnection() {
        try {
            //从c3p0数据库连接池获取连接
            Connection conn = cpds.getConnection();
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //定义一个静态方法让外界获取数据库连接池对象
    public static DataSource getDataSource()
    {
        return cpds;
    }

    // 定义一个静态方法让外界关闭资源
    public static void release(Connection conn, Statement st, ResultSet rs) {
        try {
            // 关闭资源
            if (conn != null) {

                conn.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            // 关闭资源
            if (st != null) {
                st.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            // 关闭资源
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
