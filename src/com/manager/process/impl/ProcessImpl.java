package com.manager.process.impl;

import com.manager.enums.ProcessStatus;
import com.manager.role.Role;
import com.manager.task.Task;
import com.manager.process.Process;

import java.nio.file.AccessDeniedException;
import java.util.ArrayDeque;
import java.util.Deque;

public class ProcessImpl implements Process {

    private Deque<Task> tasks = new ArrayDeque<>();
    private Deque<Task> executed = new ArrayDeque<>();
    private ProcessStatus status = ProcessStatus.NEW;
    private Role executorRole;

    public ProcessImpl() {
    }

    @Override
    public void run() throws AccessDeniedException {
        status = ProcessStatus.IN_PROGRESS;
        while(ProcessStatus.IN_PROGRESS.equals(status) && tasks.peekLast() != null){
            Task task = tasks.pollLast();
            if(!task.canExecute(executorRole)){
                status = ProcessStatus.ERROR;
                throw new AccessDeniedException("Tasks and Executors role mismatched!");
            }
            task.execute();
            executed.addLast(task);
        }
        status = ProcessStatus.SUCCESS;
    }

    @Override
    public void cancel() {
        status = ProcessStatus.CANCELED;
        while(ProcessStatus.CANCELED.equals(status) && executed.peekLast() != null){
            Task task = executed.pollLast();
            task.undo();
            tasks.addLast(task);
        }
    }

    @Override
    public void addTask(Task task) {
        tasks.addFirst(task);
    }

    @Override
    public ProcessStatus getStatus() {
        return status;
    }

    @Override
    public void setExecutorRole(Role role) {
        executorRole = role;
    }
}
