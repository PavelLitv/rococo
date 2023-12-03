package qa.guru.rococo.config;

import com.codeborne.selenide.Configuration;

public class DockerConfig implements Config {
    static final DockerConfig config = new DockerConfig();

    static {
        Configuration.remote = "http://localhost:4444/wd";
    }

    private DockerConfig() {
    }

    @Override
    public String rococoFrontUrl() {
        return "rococo-front:3000/";
    }

    @Override
    public String rococoAuthUrl() {
        return "rococo-auth:9000/";
    }

    @Override
    public String databaseHost() {
        return null;
    }
}
