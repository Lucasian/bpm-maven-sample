package com.lucasian.sample;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.lucasian.bpm.ProcessEngine;
import com.lucasian.bpm.ProcessEngineFactory;

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

		processEngine.getManagementService().deployFile(
				"processes" + File.separator + "MiProceso1.bar");
		
		processEngine.getManagementService().deleteProcessDefinition("MiProceso1", "1.0");

	}

}
