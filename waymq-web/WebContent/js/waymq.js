/*******************************************************************************
 * getQueryParameter
 */

function getURLQueryParameter(url, paramName) {
	// remove ( i0 <= '?' ) || ( '#' <= i1 )
	var i0 = url.indexOf("?", 0);
	var i1 = url.indexOf("#", 0);
	if (i1 < 0) {
		i1 = url.length;
	}
	if (i0 >= 0) {
		url = url.substring(i0 + 1, i1);
	}
	// make url
	var key = paramName + "=";
	array = url.split("&", 32);
	for ( var i = array.length - 1; i >= 0; i--) {
		var str = "" + array[i];
		if (str.indexOf(key, 0) == 0) {
			str = str.substring(key.length, str.length);
			return str;
		}
	}
	return null;
}

/*******************************************************************************
 * URLBuilder
 */

function URLBuilder(aClass, aMethod) {
	if (aClass != null)
		this["class"] = aClass;
	if (aMethod != null)
		this["do"] = aMethod;
	return this;
}

URLBuilder.prototype.toString = function(base) {
	if (base != null) {
		var i1 = base.indexOf("?", 0);
		if (i1 < 0) {
			var i2 = base.indexOf("#", 0);
			if (i2 < 0) {
			} else {
				base = base.substring(0, i2);
			}
		} else {
			base = base.substring(0, i1);
		}
	} else {
		base = "";
	}
	var sb = "";
	for ( var key in this) {
		if (this.hasOwnProperty(key)) {
			var val = this[key];

			val = encodeURIComponent(val);

			var sp = (sb.length > 0) ? "&" : "";
			sb = sb + sp + key + "=" + val;
		}
	}
	return base + "?" + sb;
};

/*******************************************************************************
 * DateTime
 */

function DateTime() {
	this.zone = 8;
	this.zone_adjust = this.zone * (1000 * 3600);
	var date = new Date();
	this.gmt = date.getTime();
	return this;
}

DateTime.prototype.toString = function() {
	var s1 = this.toDateString();
	var s2 = this.toTimeInDayString();
	return s1 + " " + s2;
};

DateTime.prototype.toDateString = function() {
	var time = this.gmt;
	var t = time + this.zone_adjust;
	var date = new Date();
	date.setTime(t);
	var y = date.getUTCFullYear();
	var m = date.getUTCMonth() + 1;
	var d = date.getUTCDate();
	return y + "-" + m + "-" + d;
};

DateTime.prototype.toTimeInDayString = function() {
	var time = this.gmt;
	var t = time + this.zone_adjust;
	var h = Math.floor(t / (3600 * 1000)) % 24;
	var m = Math.floor(t / (60 * 1000)) % 60;
	var s = Math.floor(t / 1000) % 60;
	return (h + ":" + m + ":" + s);
};

/*******************************************************************************
 * EOF
 */

