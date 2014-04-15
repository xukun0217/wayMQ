package ananas.waymq.droid.gap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class News {

	public News(String json) {
		JSONObject js = JSON.parseObject(json);
		this.title = js.getString("title");
		this.text = js.getString("text");
	}

	public boolean hasUpdated;
	public String title;
	public String text;

}
