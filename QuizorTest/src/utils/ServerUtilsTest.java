package utils;

import web.serverResponse.ServerResponse;
import junit.framework.TestCase;

public class ServerUtilsTest extends TestCase
{
	public void testClearServer()
	{
		ServerUtils utils = ServerUtils.getInstance();
		utils.resetServer();

	}

	public void testClearServerStudent()
	{
		ServerUtils utils = ServerUtils.getInstance();
		utils.resetServerStudent();
	}

}
