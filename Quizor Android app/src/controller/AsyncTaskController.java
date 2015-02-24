package controller;


import utils.HTTPUtils;
import web.serverBehaviours.ServerBehaviour;
import web.serverRequests.RegisterProfessorRequest;
import web.serverRequests.ServerRequest;
import web.serverResponse.ServerResponse;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TableLayout;

// TODO: Auto-generated Javadoc
/**
 * makes an AsyncTask which executes an HTTPBehaviour.
 */
public class AsyncTaskController
{
	
	/**
	 * The Interface PostExecutioner.
	 */
	public interface PostExecutioner
	{
		
		/**
		 * On post execution.
		 *
		 * @param response the response
		 */
		void onPostExecution(ServerResponse response);
	}

	/**
	 * Gets the async task.
	 *
	 * @param request the request
	 * @param behavior the behavior
	 * @param postExecutioner the post executioner
	 * @param context the context
	 * @return an Async task that displays a progress dialog while the behavior executes
	 */
	public static AsyncTask<ServerRequest, Object, ServerResponse> getAsyncTask(ServerRequest  request,
			final ServerBehaviour behavior, final PostExecutioner postExecutioner, final Context context)
	{
		
		return new AsyncTask<ServerRequest, Object, ServerResponse>()
		{
			/* fields */
			ProgressDialog progressDialog;
			
			@Override
			protected void onPreExecute()
			{
				progressDialog = ProgressDialog.show(context, "", "busy");
				progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				progressDialog.show();
			}

			@Override
			protected ServerResponse doInBackground(ServerRequest... requests)
			{
				// execute the request
				ServerResponse response = behavior.handleRequest(requests[0]);
				
				// check if there's no connection
				if (HTTPUtils.isConnected(context) == false)
					response.setStatus(ServerResponse.STATUS_NO_CONECTION);
				return response;
			}

			@Override
			protected void onPostExecute(ServerResponse response)
			{
				progressDialog.dismiss();
				postExecutioner.onPostExecution(response);
			}
		};
	}
	
}
