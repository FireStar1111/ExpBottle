package dev.firestar.expbottle.Utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBconnector {

    private static HikariDataSource dataSource;

    public static void connect(String hostname, int port, String database, String username, String password){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://" + hostname + ":" + port + "/" + database);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(250);
        config.setIdleTimeout(300000);
        config.setMaxLifetime(86400000); // 24 uur
        config.setConnectionTimeout(30000); // 30 seconden
        config.setConnectionTestQuery("SELECT 1");
        dataSource = new HikariDataSource(config);
        try (Connection connection = dataSource.getConnection()) {
            if (connection != null && !connection.isClosed()) {
            } else {
                throw new SQLException("Failed to establish database connection!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void disconnect() {
        if (dataSource != null) {
            dataSource.close();
            return;
        }
    }

    public static Connection getConnection(){
        try {
            if (dataSource == null) {
                throw new SQLException("DataSource is not initialized.");

            }
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
