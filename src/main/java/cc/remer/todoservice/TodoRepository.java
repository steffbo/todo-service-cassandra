package cc.remer.todoservice;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface TodoRepository extends CassandraRepository<Todo, UUID> {
}
