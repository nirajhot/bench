package retryAnalyzer;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetrySetup implements IRetryAnalyzer {

	// Variable to keep counter of retry
	private int retryCount = 0;

	// Variable to define max retry count
	private static final int maxRetryCount = 3;

	public boolean retry(ITestResult result) {
 
		if(retryCount < maxRetryCount)
		{
			retryCount++;
			System.out.println("Retrying Test method : "+result.getName() + " for " + retryCount +" times. ");
			return true;
		}
		return false;
	}
}