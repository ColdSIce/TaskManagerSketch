package com.manager;

import com.manager.executor.Executor;
import com.manager.executor.impl.SimpleExecutor;
import com.manager.process.Process;
import com.manager.process.impl.ProcessImpl;
import com.manager.role.Role;
import com.manager.role.impl.RoleImpl;
import com.manager.task.Task;
import com.manager.task.impl.TaskImpl;

import java.nio.file.AccessDeniedException;

public class Main {

    public static void main(String[] args) {

	    Task t1 = new TaskImpl("First");
	    Task t2 = new TaskImpl("Second");
	    Task t3 = new TaskImpl("Third");
	    Task t4 = new TaskImpl("Fourth");
	    Task t5 = new TaskImpl("Fifth");

	    Role r1 = new RoleImpl("Role one");
	    Role r2 = new RoleImpl("Role two");

	    t1.addRole(r1);

	    t2.addRole(r1);
	    t2.addRole(r2);

	    t3.addRole(r1);
	    t3.addRole(r2);

	    t4.addRole(r1);

	    t5.addRole(r1);

        Process p1 = new ProcessImpl();
        p1.addTask(t1);
        p1.addTask(t2);
        p1.addTask(t3);
        p1.addTask(t4);
        p1.addTask(t5);

        Executor firstExecutor = new SimpleExecutor(r1, p1);
        System.out.println("First exec status: " + firstExecutor.getStatus());
        try {
            firstExecutor.startProcess();
        } catch (AccessDeniedException e) {
            e.printStackTrace();
        }
        System.out.println("First exec status: " + firstExecutor.getStatus());

        firstExecutor.cancelProcess();
        System.out.println("First exec status: " + firstExecutor.getStatus());

        
        Process p2 = new ProcessImpl();
        p2.addTask(t4);

        Executor secondExecutor = new SimpleExecutor(r2, p2);
        System.out.println("Second exec status: " + secondExecutor.getStatus());
        try{
            secondExecutor.startProcess();
        } catch (AccessDeniedException e) {
            e.printStackTrace();
        }
        System.out.println("Second exec status: " + secondExecutor.getStatus());
    }
}
