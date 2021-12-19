package cc.remer.todoservice;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.springframework.data.cassandra.config.SchemaAction;
import org.testcontainers.containers.CassandraContainer;

public abstract class CassandraTest {

    private static final String KEYSPACE_NAME = "todo_service";

    public static final CassandraContainer cassandra;

    static {
        cassandra = (CassandraContainer) new CassandraContainer("cassandra:3.11.9")
                .withExposedPorts(9042)
                .withReuse(true); // keeps the container around
        cassandra.start();

        setupCassandraConnectionProperties();
        createKeyspace(cassandra.getCluster());
    }

    private static void setupCassandraConnectionProperties() {
        System.setProperty("spring.data.cassandra.local-datacenter", "datacenter1");
        System.setProperty("spring.data.cassandra.schema-action", String.valueOf(SchemaAction.CREATE_IF_NOT_EXISTS));
        System.setProperty("spring.data.cassandra.keyspace-name", KEYSPACE_NAME);
        System.setProperty("spring.data.cassandra.contact-points", cassandra.getContainerIpAddress());
        System.setProperty("spring.data.cassandra.port", String.valueOf(cassandra.getMappedPort(9042)));
    }

    private static void createKeyspace(Cluster cluster) {
        try (Session session = cluster.connect()) {
            session.execute("CREATE KEYSPACE IF NOT EXISTS " + KEYSPACE_NAME +
                    " WITH replication = \n" +
                    "{'class':'SimpleStrategy','replication_factor':'1'};");
        }
    }
}
