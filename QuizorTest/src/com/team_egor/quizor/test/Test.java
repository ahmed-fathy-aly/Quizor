package com.team_egor.quizor.test;

import web.serverBehaviours.LoginBehavior;
import web.serverRequests.LoginRequest;
import android.test.AndroidTestCase;
import android.util.Log;
import junit.framework.TestCase;

public class Test extends AndroidTestCase
{
	public void test()
	{
		LoginRequest request = new LoginRequest("qwerty", "qwerty", "1");
		LoginBehavior behavior = new LoginBehavior();
		String response = behavior.getResponseString(request);
		
		Log.e("Game", "login response : " + response);
	}
}
