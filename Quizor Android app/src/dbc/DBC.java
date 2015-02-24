package dbc;

// TODO: Auto-generated Javadoc
/**
 * The Class DBC.
 */
public class DBC
{
	
	/** The Constant DEBUG_MODE. */
	public static final  boolean DEBUG_MODE = true;
	
	/**
	 * Require.
	 *
	 * @param succesful the succesful
	 */
	public static void require(boolean succesful)
	{
		if (!DEBUG_MODE)
			return;
		else
			if (!succesful)
				throw new DBCPreConditionException();
	}
	
	/**
	 * Ensure.
	 *
	 * @param succesful the succesful
	 */
	public static void ensure(boolean succesful)
	{
		if (!DEBUG_MODE)
			return;
		else
			if (!succesful)
				throw new DBCPostConditionException();
	}
	
	/**
	 * Invariant.
	 *
	 * @param succesful the succesful
	 */
	public static void invariant(boolean succesful)
	{
		if (!DEBUG_MODE)
			return;
		else
			if (!succesful)
				throw new DBCInvariantException();
	}
}
