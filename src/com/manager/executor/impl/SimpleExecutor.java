package com.manager.executor.impl;

import com.manager.enums.ProcessStatus;
import com.manager.executor.Executor;
import com.manager.process.Process;
import com.manager.role.Role;

import java.nio.file.AccessDeniedException;

public class SimpleExecutor implements Executor {

    private Role role;
    private Process process;

    public SimpleExecutor(Role role, Process process) {
        this.process = process;
        this.role = role;
        process.setExecutorRole(role);
    }

    @Override
    public ProcessStatus getStatus() {
        return process.getStatus();
    }

    @Override
    public void setProcess(Process process) {
        this.process = process;
        process.setExecutorRole(role);
    }

    @Override
    public void startProcess() throws AccessDeniedException {
        process.run();
    }

    @Override
    public void cancelProcess() {
        process.cancel();
    }
}
