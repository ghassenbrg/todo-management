package brg.ghassen.todo.controller;

import brg.ghassen.todo.dto.TodoDto;
import brg.ghassen.todo.entity.Todo;
import brg.ghassen.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/todos")
public class TodoController {

    private TodoService todoService;

    // Build Add Todo_ REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {

        TodoDto savedTodo = todoService.addTodo(todoDto);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    // Build Get Todo_ By Id REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id) {

        TodoDto todo = todoService.getTodo(id);
        return ResponseEntity.ok(todo);
    }

    // Build Get All Todos REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        List<TodoDto> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    // Build Update Todo_ REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long todoId) {
        TodoDto updatedTodoDto = todoService.updateTodo(todoDto, todoId);
        return ResponseEntity.ok(updatedTodoDto);
    }

    // Build Delete Todo_ REST API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {

        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo deleted successfully !");

    }

    // Build Complete Todo_ REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id) {
        TodoDto todoDto = todoService.completeTodo(id);

        return ResponseEntity.ok(todoDto);
    }

    // Build inComplete Todo_ REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable Long id) {
        TodoDto todoDto = todoService.inCompleteTodo(id);

        return ResponseEntity.ok(todoDto);
    }

}
