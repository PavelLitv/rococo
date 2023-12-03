package qa.guru.rococo.config;

public interface Config {

    static Config getInstance() {
        if ("docker".equals(System.getProperty("test.env"))) {
            return DockerConfig.config;
        }
        return LocalConfig.config;
    }

    String rococoFrontUrl();
    String rococoAuthUrl();
    String databaseHost();
    default int databasePort() {
        return 3306;
    }
    default String databaseUser() {
        return "root";
    }

    default String databasePassword() {
        return "secret";
    }
}
