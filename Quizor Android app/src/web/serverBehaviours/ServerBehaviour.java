package web.serverBehaviours;

import web.serverRequests.ServerRequest;
import web.serverResponse.ServerResponse;

// TODO: Auto-generated Javadoc
/**
 * The general behavior by which a request is handled.
 */
public abstract class ServerBehaviour
{
	
	/**
	 * Handle request.
	 *
	 * @param request the request
	 * @return the server response
	 */
	public abstract ServerResponse handleRequest(ServerRequest request);
}
