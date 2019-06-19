package com.formsub.exam;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TodoController {

	private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	TodoService service;

	@Autowired
	LoginController loginController;

	private String name = "";

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model, HttpServletRequest req) {
		try {
			if (isSessionValid()) {
				name = (String) model.get("name");
				model.put("todos", service.retrieveTodos(name));
				return "list-todos";
			}
		} catch (Exception e) {
			model.put(ErrorMessageConstant.ERROR_MSG, ErrorMessageConstant.SESSION_INVALIDATE);
			logger.error(e.getMessage());
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/add-todos", method = RequestMethod.GET)
	public String addToDoForm(ModelMap model, @ModelAttribute("todo") Todo todo) {
		try {
			if (isSessionValid()) {
				Todo todos = new Todo();
				todos = todo;
				model.put("addTodo", todos);
				return "add-todos";
			}
		} catch (Exception e) {
			model.put(ErrorMessageConstant.ERROR_MSG, ErrorMessageConstant.SESSION_INVALIDATE);
			logger.error(e.getMessage());
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/add-todos", method = RequestMethod.POST)
	public String addToDoData(ModelMap model, @ModelAttribute("todo") Todo todo, 
			@Valid Todo todo2,
			BindingResult bindingResult) {
		try {
			if (isSessionValid()) {
				if (!bindingResult.hasErrors() && !TodoService.todos.contains(todo)) {
					name = (String) model.get("name");
					todo.setUser(name);
					todo.setTargetDate(new Date());
					TodoService.todos.add(todo);
					model.put("todos", TodoService.todos);
				} else {
					model.put("todos", TodoService.todos);
//					model.put(ErrorMessageConstant.ERROR_MSG, "Id should be unique");
					return "add-todos";
				}
				return "list-todos";
			}
		} catch (NumberFormatException e) {
			model.put(ErrorMessageConstant.ERROR_MSG, "ID Should Be Integer Value");
			logger.error(e.getMessage());
		} catch (Exception e) {
			model.put(ErrorMessageConstant.ERROR_MSG, ErrorMessageConstant.SESSION_INVALIDATE);
			logger.error(e.getMessage());
		}
		return "redirect:/login";

	}

	@RequestMapping(value = "/delete-todos/{id}", method = RequestMethod.GET)
	public String deleteTodoTask(ModelMap model, @PathVariable("id") int id) {
		try {
			if (isSessionValid()) {
				Todo todo = service.findTodoById(id);
				TodoService.todos.remove(todo);
				model.put("todos", TodoService.todos);
				System.out.println("TodoService.todos" + TodoService.todos.toString());
				return "list-todos";
			}
		} catch (Exception e) {
			model.put(ErrorMessageConstant.ERROR_MSG, ErrorMessageConstant.SESSION_INVALIDATE);
			logger.error(e.getMessage());
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/edit-todos/{id}", method = RequestMethod.GET)
	public String editForm(ModelMap model, @PathVariable("id") int id) {
		try {
			if (isSessionValid()) {
				Todo todo = service.findTodoById(id);
				model.put("edittodo", todo);
				return "edit-todos";
			}
		} catch (Exception e) {
			model.put(ErrorMessageConstant.ERROR_MSG, ErrorMessageConstant.SESSION_INVALIDATE);
			logger.error(e.getMessage());
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/edit-todos/{id}", method = RequestMethod.POST)
	public String editTodoTask(ModelMap model, @PathVariable("id") int id, @ModelAttribute("edittodo") Todo edittodo) {
		try {
			Todo todo = service.findTodoById(id);
			todo.setDesc(edittodo.getDesc());
			TodoService.todos.add(todo);
			model.put("todos", TodoService.todos);
			return "list-todos";
		} catch (Exception e) {
			model.put(ErrorMessageConstant.ERROR_MSG, ErrorMessageConstant.SESSION_INVALIDATE);
			logger.error(e.getMessage());
		}
		return "redirect:/login";
	}

	private boolean isSessionValid() {
		if (loginController.session.getAttribute("isValid") != null) {
			return true;
		} else
			return false;
	}
}
