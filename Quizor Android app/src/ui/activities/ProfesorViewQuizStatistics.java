package ui.activities;

import ui.adapters.SolutionStatisticsAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.team_egor.quizor.R;

import controller.QuizController;
import controller.Refreshable;

// TODO: Auto-generated Javadoc
/**
 * The Class ProfesorViewQuizStatistics.
 */
public class ProfesorViewQuizStatistics extends Activity implements Refreshable
{

	/* cosntants */
	/** The Constant TAG_QUIZ_ID. */
	public static final String TAG_QUIZ_ID = "tagQuizId";

	/* fields */
	/** The controller. */
	private QuizController controller;

	/** The quiz id. */
	private String quizId;

	/** The list view. */
	private ListView listView;

	/** The list adapter. */
	private SolutionStatisticsAdapter listAdapter;

	/** The graph view. */
	private GraphView graphView;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// init
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_quizz_statistics);
		controller = new QuizController(this);

		// restore from intent
		quizId = getIntent().getStringExtra(TAG_QUIZ_ID);

		// set up list
		listView = (ListView) findViewById(R.id.solutionsListView);
		listAdapter = new SolutionStatisticsAdapter(this, controller);
		listView.setAdapter(listAdapter);

		// set up graph view
		graphView = new GraphView(this);
		LinearLayout container = (LinearLayout) findViewById(R.id.graphViewContainer);
		container.addView(graphView);
		graphView.getGridLabelRenderer().setHorizontalAxisTitle("Score");
		graphView.getGridLabelRenderer().setVerticalAxisTitle("Students count");

		// start downloading data
		controller.downloadSolutionsStatistics(this, quizId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_quizz_statistics, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		return super.onOptionsItemSelected(item);
	}

	/* controller stuff */
	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.Refreshable#refreshUI()
	 */
	@Override
	public void refreshUI()
	{
		listAdapter.notifyDataSetChanged();
		updateGraphView();
	}

	/**
	 * Update graph view.
	 */
	private void updateGraphView()
	{

		// make the data points
		int[] histogram = controller.getSolutionsHistogram();
		if (histogram.length <= 1)
			return;
		DataPoint[] data = new DataPoint[histogram.length];
		for (int i = 0; i < histogram.length; i++)
			data[i] = new DataPoint(i, histogram[i]);

		// make the series
		BarGraphSeries<DataPoint> series = new BarGraphSeries<>(data);
		series.setSpacing(50);
		series.setDrawValuesOnTop(true);
		series.setColor(getResources().getColor(R.color.apptheme_color));

		// set to graph view
		graphView.removeAllSeries();
		graphView.addSeries(series);

	}
}
