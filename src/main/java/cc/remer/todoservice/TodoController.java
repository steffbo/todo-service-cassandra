package cc.remer.todoservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TodoController {

    private final TodoService todoService;

    TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<Todo> create(@RequestBody Todo todo) {
        return new ResponseEntity<>(
                todoService.create(todo),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Todo>> list() {
        return new ResponseEntity<>(
                todoService.findAll(),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> update(@PathVariable UUID id, @RequestBody Todo todo) {
        return new ResponseEntity<>(
                todoService.update(id, todo),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        todoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
