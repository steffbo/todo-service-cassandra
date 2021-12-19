package cc.remer.todoservice;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Builder
@Table
public class Todo {

    @PrimaryKey
    private UUID id;

    private String text;

    @Column("is_done")
    private boolean isDone;
}
