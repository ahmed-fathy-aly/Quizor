package web.serverBehaviours;

import web.serverRequests.ServerRequest;
import web.serverResponse.ServerResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerHandler.
 */
public class ServerHandler
{
	/* fields */
	/** The behaviour. */
	private ServerBehaviour behaviour;

	/* setters and getters */
	/**
	 * Gets the behaviour.
	 *
	 * @return behaviour the behaviour in which the HTTPRequest is handled
	 */
	public ServerBehaviour getBehaviour()
	{
		return behaviour;
	}

	/**
	 * Sets the behaviour.
	 *
	 * @param behaviour            the behaviour in which the HTTPRequest is handled
	 */
	public void setBehaviour(ServerBehaviour behaviour)
	{
		this.behaviour = behaviour;
	}

	/* methods */
	/**
	 * Handler request.
	 *
	 * @param request            the params the server needs
	 * @return the server's response
	 */
	ServerResponse handlerRequest(ServerRequest request)
	{
		return behaviour.handleRequest(request);
	}

}
