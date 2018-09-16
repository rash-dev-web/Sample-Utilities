package com.utility;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.xml.XmlSuite;

public class CustomReport implements IReporter {

	@Override
	public void generateReport(List<XmlSuite> arg0, List<ISuite> arg1,
			String arg2) {
		// TODO Auto-generated method stub
		for(ISuite isuite : arg1)
		{
			
			Map<String,ISuiteResult> s = isuite.getResults();
			
			Set<String> sk = s.keySet();
			
			for(String sr : sk)
			{
				ITestContext it = s.get(sr).getTestContext();
				
				System.out.println("Suite Name->"+it.getName()

	                    + "::Report output Ditectory->"+it.getOutputDirectory()

	                     +"::Suite Name->"+ it.getSuite().getName()

	                     +"::Start Date Time for execution->"+it.getStartDate()

	                     +"::End Date Time for execution->"+it.getEndDate());

			
			
			IResultMap resultMap = it.getFailedTests();

            //Get method detail of failed test cases

            Collection<ITestNGMethod> failedMethods = resultMap.getAllMethods();

            //Loop one by one in all failed methods

            System.out.println("--------FAILED TEST CASE---------");

            for (ITestNGMethod iTestNGMethod : failedMethods) {

                //Print failed test cases detail

                System.out.println("TESTCASE NAME->"+iTestNGMethod.getMethodName()

                        +"\nDescription->"+iTestNGMethod.getDescription()

                        +"\nPriority->"+iTestNGMethod.getPriority()

                        +"\n:Date->"+new Date(iTestNGMethod.getDate()));

                

            }

		}
	}

}
}
