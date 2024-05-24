package id.my.hendisantika.springbootcdcwithdebezium.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-cdc-with-debezium
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/25/24
 * Time: 05:41
 * To change this template use File | Settings | File Templates.
 */
@Configuration
public class DebeziumConnectorConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongoDbUri;

    @Value("${spring.data.mongodb.username:}")
    private String mongoDbUsername;

    @Value("${spring.data.mongodb.password:}")
    private String mongoDbPassword;

    @Value("${spring.datasource.url}")
    private String postgresUrl;

    @Value("${spring.datasource.username}")
    private String postgresUsername;

    @Value("${spring.datasource.password}")
    private String postgresPassword;

    @Value("${database.include.list}")
    private String databaseIncludeList;

    @Value("${collection.include.list}")
    private String collectionIncludeList;

    @Value("${spring.profiles.active}")
    private String activeProfile;
}
