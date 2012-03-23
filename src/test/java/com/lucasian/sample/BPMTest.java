package com.lucasian.sample;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;


import com.lucasian.bpm.ProcessEngine;
import com.lucasian.bpm.ProcessEngineFactory;
import com.lucasian.bpm.task.Task;

public class BPMTest {

	@Before
	public void setup() {
		System.setProperty("org.ow2.bonita.environment", "conf"
				+ File.separator + "bonita-environment.xml");
		ProcessEngineFactory.registerUserFinder(new UserFinderSample());
	}

	@Test
	public void testAll() {
		ProcessEngine processEngine = ProcessEngineFactory.getProcessEngine();

		//Deploy task
		processEngine.getManagementService().deployFile(
				"processes" + File.separator + "MiProceso1.bar");

		//Start a Miproceso1 process
		processEngine.getRuntimeService().startProcess("MiProceso1");

		//Get list of tasks
		List<Task> tasks = new ArrayList<Task>(processEngine.getTaskService()
				.listPendingTasks());
		
		Task task = tasks.get(0);
		
		//Claim task
		processEngine.getTaskService().assign(task.getId(), "admin");
		
		//Reload the tasks
		tasks = new ArrayList<Task>(processEngine.getTaskService()
				.listPendingTasks());
		
		task = tasks.get(0);
		
		//Print the asignee
		Logger.getLogger(BPMTest.class.getName()).info("The asignee is " + task.getAsignee());
		
		//Start and end the task
		processEngine.getTaskService().execute(task.getId());
		
		tasks = new ArrayList<Task>(processEngine.getTaskService()
				.listPendingTasks());
		
		

		Logger.getLogger(BPMTest.class.getName()).info("There are " + tasks.size() + " tasks left");
		
		processEngine.getManagementService().deleteProcessDefinition(
				"MiProceso1", "1.0");

	}

}
