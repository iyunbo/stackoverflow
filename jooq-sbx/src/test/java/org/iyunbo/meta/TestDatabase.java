package org.iyunbo.meta;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.iyunbo.meta.generated.Tables.AUTHOR;

@Slf4j
public class TestDatabase {

    private final String userName = "postgres";
    private final String password = "";
    private final String url = "jdbc:postgresql://book:5432/nas";

    @Test
    public void should_connect_to_db() {
        // Connection is the only JDBC resource that we need
        // PreparedStatement and ResultSet are handled by jOOQ, internally
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            Assertions.assertThat(conn.isClosed()).isFalse();
        } catch (SQLException e) {
            throw new IllegalStateException("cannot connect to database", e);
        }
    }

    @Test
    public void can_select_from_table() {
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);
            Result<Record> result = create.select().from(AUTHOR).fetch();
            for (Record r : result) {
                Integer id = r.getValue(AUTHOR.ID);
                String firstName = r.getValue(AUTHOR.FIRST_NAME);
                String lastName = r.getValue(AUTHOR.LAST_NAME);

                Assertions.assertThat(id).isPositive();
                Assertions.assertThat(firstName).isNotBlank();
                Assertions.assertThat(lastName).isNotBlank();
            }
        } catch (SQLException e) {
            throw new IllegalStateException("failed executing SQL", e);
        }

    }
}
