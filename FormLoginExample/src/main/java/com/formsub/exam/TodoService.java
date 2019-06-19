package com.formsub.exam;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private static final Logger logger = LoggerFactory.getLogger(TodoService.class);

	public static Set<Todo> todos = new HashSet<Todo>();
	private static int todoCount = 3;

	static {
		todos.add(new Todo(1, "dsv", "Learn Spring MVC", new Date(), false));
		todos.add(new Todo(2, "dsv", "Learn Struts", new Date(), false));
		todos.add(new Todo(3, "dsv", "Learn Hibernate", new Date(), false));
	}

	public Set<Todo> retrieveTodos(String user) {
		Set<Todo> filteredTodos = new HashSet<Todo>();
		for (Todo todo : todos) {
			if (todo.getUser().equalsIgnoreCase(user)) {
				filteredTodos.add(todo);
			}
		}
		return filteredTodos;
	}

	public Todo findTodoById(int id) {
		Todo toDo = new Todo();
		toDo.setId(id);
		if (todos.contains(toDo)) {
			for (Todo task : todos) {
				if (toDo.equals(task)) {
					return task;
				}
			}
		}
		return null;
	}
}
