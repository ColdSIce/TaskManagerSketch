package com.manager.process;

import com.manager.enums.ProcessStatus;
import com.manager.role.Role;
import com.manager.task.Task;

import java.nio.file.AccessDeniedException;

public interface Process {
    void addTask(Task task);
    void run() throws AccessDeniedException;
    void cancel();
    ProcessStatus getStatus();
    void setExecutorRole(Role role);
}
