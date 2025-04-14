package com.example.todoaapp.utils;

import com.example.todoaapp.entity.Todo;
import org.thymeleaf.util.StringUtils;

import java.util.Optional;

/*
    Đối tượng này dùng để kiểm tra xem một Object Todo có hợp lệ không
 */
public class TodoValidator {
    /**
     * Kiểm tra một object Todo có hợp lệ không
     *
     * @param todo
     * @return
     */

    public boolean isValid(Todo todo) {
        return Optional.ofNullable(todo)
                .filter(t -> !StringUtils.isEmpty(t.getName())) // kiểm tra khác rỗng
                .filter(t -> !StringUtils.isEmpty(t.getTimeStart()))
                .filter(t -> !StringUtils.isEmpty(t.getTimeEnd()))
                .filter(t -> !StringUtils.isEmpty(t.getDescription()))
                .isPresent();
    }
}
