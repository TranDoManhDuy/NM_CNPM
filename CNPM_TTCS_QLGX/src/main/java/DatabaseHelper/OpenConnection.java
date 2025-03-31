/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseHelper;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author manhh
 */
public class OpenConnection {
    private static final HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSourse;
    
    public static void initializaConnection(String username, String password) {
        config.setJdbcUrl("jdbc:sqlserver://tranmanhduy.database.windows.net:1433;database=VINHOMES; encrypt=true;trustServerCertificate=true;");
        config.setUsername(username);
        config.setPassword(password);
        config.addDataSourceProperty("encrypt", "true");
        config.addDataSourceProperty("trustServerCertificate", "true");
        
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(30000);
        config.setMaxLifetime(600000);
        config.setConnectionTimeout(10000);
        
        dataSourse = new HikariDataSource(config);
    }
    public static Connection getConnection() throws SQLException {
        return dataSourse.getConnection();
    }
}