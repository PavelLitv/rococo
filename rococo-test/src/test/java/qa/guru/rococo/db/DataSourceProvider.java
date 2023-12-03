package qa.guru.rococo.db;

import com.mysql.cj.jdbc.MysqlDataSource;
import qa.guru.rococo.config.Config;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum DataSourceProvider {
    INSTANCE;

    private static final Config cfg = Config.getInstance();

    private final Map<ServiceDB, DataSource> dataSourceStore = new ConcurrentHashMap<>();

    public DataSource getDataSource(ServiceDB db) {
        return dataSourceStore.computeIfAbsent(db, key -> {
            MysqlDataSource sd = new MysqlDataSource();
            sd.setURL(key.getUrl());
            sd.setUser(cfg.databaseUser());
            sd.setPassword(cfg.databasePassword());
            return sd;
        });
    }

}
