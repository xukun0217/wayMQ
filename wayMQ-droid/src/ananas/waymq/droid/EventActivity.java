package ananas.waymq.droid;

import java.util.HashMap;
import java.util.Map;

import ananas.waymq.droid.protocol.Protocol;
import ananas.waymq.droid.task.BackgroundContext;
import ananas.waymq.droid.task.BackgroundTask;
import ananas.waymq.droid.task.DefaultServiceAgent;
import ananas.waymq.droid.task.ForegroundContext;
import ananas.waymq.droid.task.ServiceAgent;
import ananas.waymq.droid.util.Util;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

public class EventActivity extends Activity {

	private ServiceAgent _agent;
	private TextView _text_title;
	private TextView _text_time;
	private TextView _text_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);
		ServiceAgent agent = new DefaultServiceAgent(this);
		this._agent = agent;

		this._text_title = (TextView) this.findViewById(R.id.text_event_title);
		this._text_time = (TextView) this.findViewById(R.id.text_event_time);
		this._text_content = (TextView) this
				.findViewById(R.id.text_event_content);

		this.setupButtonListener(R.id.button_join);
		this.setupButtonListener(R.id.button_cancel);

	}

	private void setupButtonListener(int id) {
		Button btn = (Button) this.findViewById(id);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				int id = view.getId();
				EventActivity.this.onClickButton(id);
			}
		});
	}

	protected void onClickButton(int id) {
		switch (id) {
		case R.id.button_join: {
			this.doJoin();
			break;
		}
		case R.id.button_cancel: {
			this.doCancel();
			break;
		}
		default:
			break;
		}
	}

	private void doCancel() {
		this._agent.runInBackground(new BackgroundTask() {

			private Map<String, String> _param;
			private JSONObject _json;

			@Override
			public void onStart(ForegroundContext fc) {
				Map<String, String> param = new HashMap<String, String>();
				this._param = param;
				param.put(Protocol.ParamKey.class_, Protocol.Event.class_name);
				param.put(Protocol.ParamKey.method_, Protocol.Event.do_cancel);
			}

			@Override
			public void onProcess(BackgroundContext bc) {
				ServiceAgent agent = bc.getServiceAgent();
				Map<String, String> param = this._param;
				this._json = agent.requestJSON("POST", param);
			}

			@Override
			public void onFinish(ForegroundContext fc) {
				EventActivity.this.refresh();
			}
		});
	}

	private void doJoin() {
		this._agent.runInBackground(new BackgroundTask() {

			private Map<String, String> _param;
			private JSONObject _json;

			@Override
			public void onStart(ForegroundContext fc) {
				Map<String, String> param = new HashMap<String, String>();
				this._param = param;
				param.put(Protocol.ParamKey.class_, Protocol.Event.class_name);
				param.put(Protocol.ParamKey.method_, Protocol.Event.do_join);
			}

			@Override
			public void onProcess(BackgroundContext bc) {
				ServiceAgent agent = bc.getServiceAgent();
				Map<String, String> param = this._param;
				this._json = agent.requestJSON("POST", param);
			}

			@Override
			public void onFinish(ForegroundContext fc) {
				EventActivity.this.refresh();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		final int id = item.getItemId();
		if (id == R.id.action_debug) {

			startActivity(new Intent(this, DebugActivity.class));

		} else {
			return super.onOptionsItemSelected(item);
		}
		return true;

	}

	@Override
	protected void onPause() {
		this._agent.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		this._agent.onResume();
		this.refresh();
	}

	@Override
	protected void onStart() {
		super.onStart();
		this._agent.onStart();
	}

	@Override
	protected void onStop() {
		this._agent.onStop();
		super.onStop();
	}

	private void refresh() {

		_agent.runInBackground(new BackgroundTask() {

			private Map<String, String> _param;
			private JSONObject _json;

			@Override
			public void onStart(ForegroundContext fc) {
				Map<String, String> param = new HashMap<String, String>();
				this._param = param;
				param.put(Protocol.ParamKey.class_, Protocol.Event.class_name);
				param.put(Protocol.ParamKey.method_, Protocol.Event.do_get_info);
			}

			@Override
			public void onProcess(BackgroundContext bc) {
				ServiceAgent agent = bc.getServiceAgent();
				Map<String, String> param = this._param;
				this._json = agent.requestJSON("GET", param);
			}

			@Override
			public void onFinish(ForegroundContext fc) {

				if (_json == null) {
					return;
				}
				JSONObject event = _json.getJSONObject("event");
				TheEvent ev = new TheEvent();
				ev.title = event.getString("title");
				ev.content = event.getString("content");
				ev.time_open = event.getLongValue("open_time");
				EventActivity.this.refresh(ev);

			}
		});

	}

	protected void refresh(TheEvent ev) {

		String time = Util.timeToString(ev.time_open);

		this._text_title.setText(ev.title);
		this._text_time.setText(time);
		this._text_content.setText(ev.content);

	}

	class TheEvent {

		protected String title;
		protected String content;
		protected long time_open;
	}

}
