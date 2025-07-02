package co.com.simulator.config;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Duration;

@Configuration
public class R2dbcPostgresConfig {

    public static final int INITIAL_SIZE = 12;
    public static final int MAX_SIZE = 15;
    public static final int MAX_IDLE_TIME = 30;

    @Primary
    @Bean
    public ConnectionPool getConnectionPool() {
        PostgresqlConnectionConfiguration dbConfiguration = PostgresqlConnectionConfiguration.builder()
                .host("localhost")
                .port(5432)
                .database("user")
                .schema("etcd")
                .username("admin")
                .password("admin123")
                .build();

        ConnectionPoolConfiguration poolConfiguration = ConnectionPoolConfiguration.builder()
                .connectionFactory(new PostgresqlConnectionFactory(dbConfiguration))
                .name("api-postgres-connection-pool")
                .initialSize(INITIAL_SIZE)
                .maxSize(MAX_SIZE)
                .maxIdleTime(Duration.ofMinutes(MAX_IDLE_TIME))
                .validationQuery("SELECT 1")
                .build();

        return new ConnectionPool(poolConfiguration);
    }

}
