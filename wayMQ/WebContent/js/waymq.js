//////////////////////////////////////////////////////////////////////
// class WayRequest

function WayRequest(url) {

	this.name = "WayRequest";
	this.param = {};
	this.rawURL = url;

}

WayRequest.prototype.setParam = function(key, value) {

	this.param[key] = value;

};

WayRequest.prototype.send = function(callback) {

	var url = this.rawURL;
	var index = 0;
	var map = this.param;
	for ( var key in map) {
		var val = map[key];
		if (val != null) {
			var prefix = ((index++) == 0) ? "?" : "&";
			url += (prefix + key + "=" + val);
		}
	}
	this.finalURL = url;

	var ht = new XMLHttpRequest();
	ht.open("GET", url, true);
	ht.way_callback = callback;
	ht.way_request = this;

	ht.onreadystatechange = function(e) {

		var ht = e.target;

		if (ht.readyState != 4)
			return;

		var req = ht.way_request;
		var url = req.finalURL;
		var json = "http " + ht.status;

		if (ht.status == 200) {
			json = JSON.parse(ht.responseText);
			if (json == null) {
				json = {};
			}
		} else {
			json = {};
		}

		json.http_response_code = ht.status;
		json.http_response_message = ht.statusText;

		ht.way_callback(url, json);
	};
	ht.send(null);
};

// ////////////////////////////////////////////////////////////////////
// class WayMQ

function WayMQ() {
	this.name = "WayMQ";
}

WayMQ.prototype.request = function(url) {
	return new WayRequest(url);
};

// ////////////////////////////////////////////////////////////////////
// EOF
