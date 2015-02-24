package web;

import android.util.Log;
import utils.HTTPUtils;
import utils.ServerUtils;
import web.serverBehaviours.AddGroupBehaviour;
import web.serverRequests.AddGroupRequest;
import web.serverResponse.AddGroupResponse;
import web.serverResponse.ServerResponse;
import junit.framework.TestCase;

public class AddGroupBehaviorTest extends TestCase
{

	protected void setUp() throws Exception
	{
		// reset the server
		ServerUtils utils = ServerUtils.getInstance();
		utils.resetServer();

		super.setUp();
	}

	
	public void testGetResponseString()
	{
		AddGroupRequest request = new AddGroupRequest("New Group", "Lame group");
		AddGroupBehaviour addGroup = new AddGroupBehaviour();
		String response = addGroup.getResponseString(request);

		assertNotNull(response);
		Log.e("Game", "add group response:" + response);
	}

	public void testDecodeResponseStringSuccesful()
	{
		String responseStr = "{ \"errors\":\"none\", \"group_id\":\"4\"}";
		AddGroupBehaviour behavior = new AddGroupBehaviour();
		AddGroupResponse response = behavior.decodeResponseString(responseStr);

		assertEquals(ServerResponse.STATUS_SUCCESSFUL, response.getStatus());
		assertEquals("4", response.getGroupId());
	}

	public void testDecodeResponsStringError()
	{
		String responseStr = "{ \"errors\":\"noob minion\"}";
		AddGroupBehaviour behavior = new AddGroupBehaviour();
		AddGroupResponse response = behavior.decodeResponseString(responseStr);

		assertEquals("noob minion", response.getStatus());
	}

}
