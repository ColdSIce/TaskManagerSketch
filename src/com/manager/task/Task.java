package com.manager.task;

import com.manager.role.Role;

public interface Task {
    void execute();
    void undo();
    void addRole(Role role);
    boolean canExecute(Role role);
}
