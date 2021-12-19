package cc.remer.todoservice;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo create(Todo todo) {
        todo.setId(Uuids.timeBased());
        return todoRepository.save(todo);
    }

    public Todo findById(UUID id) {
        Optional<Todo> todo = todoRepository.findById(id);
        return todo.orElse(null);
    }

    public Todo update(UUID id, Todo updateTodo) {
        Todo todo = findById(id);
        if (todo == null) {
            return null;
        }

        todo.setDone(updateTodo.isDone());

        if (updateTodo.getText() != null) {
            todo.setText(updateTodo.getText());
        }
        return todoRepository.save(todo);
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public void delete(UUID id) {
        Optional<Todo> todo = todoRepository.findById(id);
        todo.ifPresent(todoRepository::delete);
    }
}
