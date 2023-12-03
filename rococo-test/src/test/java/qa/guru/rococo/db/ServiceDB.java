package qa.guru.rococo.db;

import qa.guru.rococo.config.Config;

public enum ServiceDB {
    AUTH("jdbc:mysql://%s:%d/rococo-auth"),
    CONTENT("jdbc:mysql://%s:%d/rococo-content"),
    GEO("jdbc:mysql://%s:%d/rococo-geo"),
    USERDATA("jdbc:mysql://%s:%d/rococo-userdata");

    private final String url;
    private static final Config cfg = Config.getInstance();

    ServiceDB(String url) {
        this.url = url;
    }

    public String getUrl() {
        return String.format(
                url,
                cfg.databaseHost(),
                cfg.databasePort()
        );
    }
}
