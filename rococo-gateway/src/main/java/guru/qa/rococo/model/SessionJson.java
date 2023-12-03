package guru.qa.rococo.model;

import java.util.Date;

public class SessionJson {
    private final String name;
    private final Date from;
    private final Date to;
    public SessionJson(String name, Date from, Date to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    public static SessionJson empty() {
        return null;
    }
}
