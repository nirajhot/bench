package utility;

public abstract class Initializer {
	
	public abstract void browserSetUp(String browser);
	
	public abstract void quitBrowser();
	
	public void waitFor(int ms){
		try {
			BaseClass.logger.info("Waiting for element with wait of "+ ms + " ms" );
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}