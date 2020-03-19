package configuration;

import com.ataccama.databasebrowser.service.databaseinfo.DatabaseInfoService;
import com.ataccama.databasebrowser.service.databaseinfo.IDatabaseInfoService;
import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

public class TestContextConfiguration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
    }

    @Bean
    public IDatabaseInfoService dbInfoService() {
        return new DatabaseInfoService();
    }
}
