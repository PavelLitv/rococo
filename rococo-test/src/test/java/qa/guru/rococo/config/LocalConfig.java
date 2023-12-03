package qa.guru.rococo.config;

import com.codeborne.selenide.Configuration;

public class LocalConfig implements Config {

    static final LocalConfig config = new LocalConfig();

    static {
        Configuration.browserSize = "1980x1024";
    }

    private LocalConfig() {
    }

    @Override
    public String rococoFrontUrl() {
        return "http://127.0.0.1:3000/";
    }

    @Override
    public String rococoAuthUrl() {
        return "http://127.0.0.1:9000/";
    }

    @Override
    public String databaseHost() {
        return "localhost";
    }
}
