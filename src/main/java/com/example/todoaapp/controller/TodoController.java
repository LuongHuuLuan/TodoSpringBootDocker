package com.example.todoaapp.controller;

import com.example.todoaapp.entity.Todo;
import com.example.todoaapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class TodoController {

    private String message;

    @Autowired
    private TodoService todoService;
    @Autowired
    private Environment env;

    /*
        @RequestParam dùng để đánh dấu một biến là request param trong request gửi lên server.
        Nó sẽ gán dữ liệu của param-name tương ứng vào biến
   */
    @GetMapping(value = {"/todo", "/"})
    public String index(Model model, @RequestParam(value = "limit", required = false) Integer limit) {
        // Trả về đối tượng todoList.
        model.addAttribute("todoList", todoService.findAll(limit));
        model.addAttribute("todo", new Todo());
        model.addAttribute("message", message);
        this.message = "";
        return "todo";
    }


    /*
        @ModelAttribute đánh dấu đối tượng Todo được gửi lên bởi Form Request
    */
    @PostMapping("/todo/add")
    public String addTodo(@ModelAttribute Todo todo) {
        this.message = env.getProperty("todo.message.fail");
        return Optional.ofNullable(todoService.add(todo))
                .map(t -> {
                    this.message = env.getProperty("todo.message.success");
                    return "redirect:/todo";
                }) // Trả về success nếu save thành công
                .orElse("redirect:/todo"); // Trả về failed nếu không thành công
    }

    @GetMapping("/todo/delete")
    public String addTodo(@RequestParam Long id) {
        this.message = env.getProperty("todo.message.fail");
        return Optional.ofNullable(id)
                .map(i -> {
                    todoService.delete(i);
                    this.message = env.getProperty("todo.message.success");
                    return "redirect:/todo";
                })
                .orElse("redirect:/todo");
    }
}
