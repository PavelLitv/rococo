package qa.guru.rococo.db.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import qa.guru.rococo.db.DataSourceProvider;
import qa.guru.rococo.db.ServiceDB;

import java.nio.ByteBuffer;
import java.sql.PreparedStatement;
import java.util.UUID;

public class UserDao {

    private final TransactionTemplate authTtpl;
    private final JdbcTemplate authJdbcTemplate;
    private final JdbcTemplate userDataJdbcTemplate;

    public UserDao() {
        JdbcTransactionManager authTm = new JdbcTransactionManager(
                DataSourceProvider.INSTANCE.getDataSource(ServiceDB.AUTH)
        );
        JdbcTransactionManager userDataTm = new JdbcTransactionManager(
                DataSourceProvider.INSTANCE.getDataSource(ServiceDB.USERDATA)
        );

        this.authTtpl = new TransactionTemplate(authTm);
        this.authJdbcTemplate = new JdbcTemplate(authTm.getDataSource());
        this.userDataJdbcTemplate = new JdbcTemplate(userDataTm.getDataSource());
    }

    public void deleteUserAuthByUserId(UUID userId) {
        authTtpl.execute(status -> {
            authJdbcTemplate.update(con -> {
                PreparedStatement authorityPs = con.prepareStatement("DELETE from authorities WHERE user_id = ?");
                authorityPs.setBytes(1, asBytes(userId));
                return authorityPs;
            });
            authJdbcTemplate.update(con -> {
                PreparedStatement usersPs = con.prepareStatement("DELETE from users WHERE id = ?");
                usersPs.setObject(1, asBytes(userId));
                return usersPs;
            });
            return 0;
        });
    }

    public UUID getUserIdByName(String userName) {
        byte[] uuid =
                authJdbcTemplate.queryForObject(
                        "SELECT id FROM users WHERE username = ?",
                        byte[].class,
                        userName
                );

        return toUUID(uuid);
    }

    public void deleteUserDataByName(String username) {
        userDataJdbcTemplate.update(
                "DELETE FROM users WHERE username = ?", username
        );
    }


    static UUID toUUID(byte[] bytes) {
        if (bytes.length != 16) {
            throw new IllegalArgumentException();
        }
        int i = 0;
        long msl = 0;
        for (; i < 8; i++) {
            msl = (msl << 8) | (bytes[i] & 0xFF);
        }
        long lsl = 0;
        for (; i < 16; i++) {
            lsl = (lsl << 8) | (bytes[i] & 0xFF);
        }
        return new UUID(msl, lsl);
    }

    private static byte[] asBytes(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }
}
