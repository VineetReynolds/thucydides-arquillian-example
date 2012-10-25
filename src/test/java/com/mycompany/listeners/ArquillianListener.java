package com.mycompany.listeners;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.arquillian.test.spi.LifecycleMethodExecutor;
import org.jboss.arquillian.test.spi.TestRunnerAdaptor;
import org.jboss.arquillian.test.spi.TestRunnerAdaptorBuilder;

import net.thucydides.core.model.Story;
import net.thucydides.core.model.TestOutcome;
import net.thucydides.core.steps.ExecutedStepDescription;
import net.thucydides.core.steps.StepFailure;
import net.thucydides.core.steps.StepListener;

public class ArquillianListener implements StepListener {

	private static final Logger logger = Logger.getLogger(ArquillianListener.class.getName());

	/*
	 * Note about the ThreadLocals.
	 * 
	 * We have an issue here - the testSuiteFinished() method can be invoked from a different thread,
	 * in contrast to the testSuiteStarted() method when the Surefire/Failsafe testrunner runs tests in parallel.  
	 * 
	 * Therefore, a new instance of the ArquillianListener is created because the Thucydides StepEventBus is initalized 
	 * once again for the new thread. 
	 * Eventually, the testSuiteFinished() method does not have access to the Arquillian TestRunnerAdaptor
	 * and the currentClass instance, i.e. the Test class. This also means that the archive is not undeployed by Arquillian. 
	 *   
	 */
	private static final ThreadLocal<TestRunnerAdaptor> adaptor = new ThreadLocal<TestRunnerAdaptor>();
	
	private static final ThreadLocal<Class<?>> currentClass = new ThreadLocal<Class<?>>();

	public void testSuiteStarted(Class<?> paramClass) {
		logger.log(Level.INFO, "Arquillian Test Suite started for test class {0}" , paramClass);
	
		try
		{
		    currentClass.set(paramClass);
			if(adaptor.get() == null)
			{
				adaptor.set(TestRunnerAdaptorBuilder.build());
			}
			TestRunnerAdaptor testRunner = adaptor.get();
			testRunner.beforeSuite();
			testRunner.beforeClass(paramClass, LifecycleMethodExecutor.NO_OP);
		}
		catch (Exception ex)
		{
			throw new RuntimeException(ex);
		}
	}

	public void testSuiteFinished() {
		logger.log(Level.INFO, "Arquillian Test Suite finished.");
		try
		{
			TestRunnerAdaptor testRunner = adaptor.get();
			Class<?> klass = currentClass.get();
			if(testRunner !=null && klass != null)
			{
				testRunner.afterClass(klass, LifecycleMethodExecutor.NO_OP);
				testRunner.afterSuite();
			}
		}
		catch (Exception ex)
		{
			throw new RuntimeException(ex);
		}
	}

	public void testSuiteStarted(Story paramStory) {
		// TODO Auto-generated method stub
	}
	
	public void testStarted(String paramString) {
		// TODO Auto-generated method stub

	}

	public void testFinished(TestOutcome paramTestOutcome) {
		// TODO Auto-generated method stub

	}

	public void stepStarted(ExecutedStepDescription paramExecutedStepDescription) {
		// TODO Auto-generated method stub

	}

	public void skippedStepStarted(
			ExecutedStepDescription paramExecutedStepDescription) {
		// TODO Auto-generated method stub

	}

	public void stepFailed(StepFailure paramStepFailure) {
		// TODO Auto-generated method stub

	}

	public void stepIgnored() {
		// TODO Auto-generated method stub

	}

	public void stepPending() {
		// TODO Auto-generated method stub

	}

	public void stepFinished() {
		// TODO Auto-generated method stub

	}

	public void testFailed(Throwable paramThrowable) {
		// TODO Auto-generated method stub

	}

	public void testIgnored() {
		// TODO Auto-generated method stub

	}

	public void notifyScreenChange() {
		// TODO Auto-generated method stub

	}

    @Override
    public void lastStepFailed(StepFailure arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void stepIgnored(String arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void stepPending(String arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void testFailed(TestOutcome arg0, Throwable arg1) {
        // TODO Auto-generated method stub
        
    }

}
