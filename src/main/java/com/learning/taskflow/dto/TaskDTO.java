package com.learning.taskflow.dto;

import com.learning.taskflow.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Status status;

    private LocalDateTime dueDate;

    private Long userId;


}
