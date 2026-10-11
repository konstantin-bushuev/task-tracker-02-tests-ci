package io.github.konstantinbushuev.tasktracker02testsci.service;

import io.github.konstantinbushuev.tasktracker02testsci.dto.TaskRequestDTO;
import io.github.konstantinbushuev.tasktracker02testsci.model.Task;
import io.github.konstantinbushuev.tasktracker02testsci.model.TaskStatus;
import io.github.konstantinbushuev.tasktracker02testsci.repository.TaskRepository;
import io.github.konstantinbushuev.tasktracker02testsci.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(TaskRequestDTO request) {
        Long id = taskRepository.nextId();
        LocalDateTime currentTime = LocalDateTime.now();

        Task task = new Task(
                id,
                request.title(),
                request.description(),
                TaskStatus.TODO,
                currentTime,
                currentTime
        );

        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task updateTask(Long id, TaskRequestDTO request) {
        Task initialTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        Task task = new Task(
                id,
                request.title(),
                request.description(),
                initialTask.status(),
                initialTask.createdAt(),
                LocalDateTime.now()
        );

        taskRepository.deleteById(id);

        return taskRepository.save(task);
    }

    public Task completeTask(Long id) {
        Task initialTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        Task task = new Task(
                id,
                initialTask.title(),
                initialTask.description(),
                TaskStatus.DONE,
                initialTask.createdAt(),
                LocalDateTime.now()
        );

        taskRepository.deleteById(id);

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        boolean deleted = taskRepository.deleteById(id);

        if (!deleted) {
            throw new TaskNotFoundException(id);
        }
    }

}
