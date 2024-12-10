package brg.ghassen.todo.service;

import brg.ghassen.todo.dto.TodoDto;
import brg.ghassen.todo.entity.Todo;
import brg.ghassen.todo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        // Convert TodoDto into Todo_ jpa entity
        Todo todo = modelMapper.map(todoDto, Todo.class);

        // Save Todo_ jpa entity
        Todo savedTodo = todoRepository.save(todo);

        // Convert Todo_ jpa entity into TodoDto
        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);

        return savedTodoDto;
    }


}
