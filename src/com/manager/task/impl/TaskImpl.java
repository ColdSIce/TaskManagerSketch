package com.manager.task.impl;

import com.manager.role.Role;
import com.manager.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskImpl implements Task{

    private List<Role> roles = new ArrayList<>();
    private String name;

    public TaskImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void addRole(Role role){
        this.roles.add(role);
    }

    @Override
    public void execute() {
        System.out.println(name + " executed");
    }

    @Override
    public void undo() {
        System.out.println(name + " canceled");
    }

    @Override
    public boolean canExecute(Role role) {
        return this.roles.contains(role);
    }
}
