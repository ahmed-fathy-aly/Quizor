package web;

import core.GroupInfo;
import utils.HTTPUtils;
import utils.ServerUtils;
import web.serverBehaviours.ViewGroupsBehavior;
import web.serverRequests.ServerRequest;
import web.serverRequests.ViewGroupsRequest;
import web.serverResponse.ServerResponse;
import web.serverResponse.ViewGroupsResponse;
import android.test.AndroidTestCase;
import android.util.Log;
import junit.framework.TestCase;

public class ViewGroupBehaviorTest extends AndroidTestCase
{
	protected void setUp() throws Exception
	{
		super.setUp();

		// reset the server
		ServerUtils utils = ServerUtils.getInstance();
		utils.resetServer();
	}

	public void testHandleRequest()
	{
		ViewGroupsRequest request = new ViewGroupsRequest();
		ViewGroupsBehavior viewGroups = new ViewGroupsBehavior();
		ViewGroupsResponse response = (ViewGroupsResponse) viewGroups.handleRequest(request);
		
		
		assertNotNull(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
	}

	public void testGetResponseString()
	{
		ViewGroupsRequest request = new ViewGroupsRequest();
		ViewGroupsBehavior viewGroups = new ViewGroupsBehavior();
		String responseString = viewGroups.getResponseString(request);

		Log.e("Game", "response string : " + responseString);
	}

	public void testParseResponseStringSuccessful()
	{
		String responseString = "{\"errors\":\"none\",\"groups\":[{\"id\":0,\"name\":\"asdf34\",\"description\":\"asdfasdf\",\"professor_info\":{\"name\":\"Ahmed\",\"username\":\"alwa\",\"email\":\"alwa@eng.asu.edu.eg\"}},{\"id\":1,\"name\":\"asdf34\",\"description\":\"asdfasdf\",\"professor_info\":{\"name\":\"Ahmed\",\"username\":\"alwa\",\"email\":\"alwa@eng.asu.edu.eg\"}}]}";
		ViewGroupsBehavior viewGroups = new ViewGroupsBehavior();
		ViewGroupsResponse response = viewGroups.decodeResponseString(responseString);

		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
		assertEquals(2, response.getGroupInfos().size());
		for (int i = 0; i < 2; i++)
		{
			GroupInfo group = response.getGroupInfos().get(i);
			assertEquals(i + "", group.getWebId());
			assertEquals("asdf34", group.getGroupName());
			assertEquals("asdfasdf", group.getDescription());
			assertEquals("Ahmed", group.getProfessorInfo().getFullName());
			assertEquals("alwa", group.getProfessorInfo().getUserName());
			assertEquals("alwa@eng.asu.edu.eg", group.getProfessorInfo().getMail());
		}

	}

	public void testParseResponseStringError()
	{
		String responseString = "{\"errors\":\"noob minion\"}";
		ViewGroupsBehavior viewGroups = new ViewGroupsBehavior();
		ViewGroupsResponse response = viewGroups.decodeResponseString(responseString);

		assertEquals("noob minion", response.getStatus());
	}
}
