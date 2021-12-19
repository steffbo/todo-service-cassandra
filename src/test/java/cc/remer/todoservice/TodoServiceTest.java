package cc.remer.todoservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TodoServiceTest extends CassandraTest {

    @Autowired
    private TodoService todoService;

    @Test
    void create_stores_a_new_todo() {
        // given
        Todo todo = Todo.builder()
                .text("program")
                .build();

        // when
        Todo createdTodo = todoService.create(todo);

        // then
        assertNotNull(createdTodo.getId());
    }
}
