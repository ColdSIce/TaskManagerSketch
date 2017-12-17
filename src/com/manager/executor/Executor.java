package com.manager.executor;

import com.manager.enums.ProcessStatus;
import com.manager.process.Process;

import java.nio.file.AccessDeniedException;

public interface Executor {
    void setProcess(Process process);
    void startProcess() throws AccessDeniedException;
    void cancelProcess();
    ProcessStatus getStatus();
}
