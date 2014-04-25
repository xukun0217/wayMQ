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
 * checkPhoneId
 */

function checkPhoneId(phone) {

	var s = "" + phone;
	var sb1 = "";
	var sb2 = "000000";
	var len = s.length;
	for ( var i = 0; i < len; ++i) {
		var ch = s.charAt(i);
		if (ch == ' ') {
			// skip
		} else if (ch == '\t') {
			// skip
		} else if ('0' <= ch && ch <= '9') {
			sb2 = sb2 + ch;
		} else {
			sb1 = sb1 + ch;
		}
	}
	if (sb1.length > 0)
		return null;
	len = sb2.length;
	switch (len) {
	case (11 + 6):
		break;
	case (5 + 6):
		break;
	default:
		return null;
	}
	return sb2.substring(len - 6, len);
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

URLBuilder.prototype.doPost = function(base, callback) {

	var func = callback;
	var xhr = new XMLHttpRequest();
	xhr.open("POST", base, true);
	xhr.onreadystatechange = function(e) {

		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var str = xhr.responseText;
				var json = $.parseJSON(str);
				func(json, xhr.status, xhr);
			} else {
				alert("HTTP " + xhr.status + " " + xhr.statusText);
			}
		}
	};
	var data = JSON.stringify(this);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(data);
};

URLBuilder.prototype.doGet = function(base, callback) {
	var url = this.toString(base);
	$.getJSON(url, callback);
};

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

DateTime.prototype.parse = function(str) {

	str = str + "(end)";

	var array = new Array();
	var sb = "";
	var len = str.length;
	for ( var i = 0; i < len; ++i) {
		var ch = str.charAt(i);
		if ('0' <= ch && ch <= '9') {
			sb = sb + ch;
		} else {
			if (sb.length > 0) {
				array.push(sb);
				sb = "";
			}
		}
	}
	var yy = parseInt(array[0]);
	var mm = parseInt(array[1]) - 1;
	var dd = parseInt(array[2]);
	var h = parseInt(array[3]);
	var m = parseInt(array[4]);
	var s = parseInt(array[5]);

	var date = new Date();
	date.setFullYear(yy, mm, dd);
	date.setHours(h, m, s, 0);
	return date.getTime();
};

DateTime.prototype.toString = function() {
	var s1 = this.toDateString();
	var s2 = this.toTimeInDayString();
	return s1 + " " + s2;
};

var day_table = null;

function dayToString(index) {
	var table = day_table;
	if (table == null) {
		// table = new Object();
		table = [ "日", "一", "二", "三", "四", "五", "六" ];
		day_table = table;
	}
	return table[index];
}

DateTime.prototype.toDateString = function() {
	var time = this.gmt;
	var t = time + this.zone_adjust;
	var date = new Date();
	date.setTime(t);
	var y = date.getUTCFullYear();
	var m = date.getUTCMonth() + 1;
	var d = date.getUTCDate();

	var day = date.getUTCDay();
	day = dayToString(day);

	return y + "-" + m + "-" + d + "(" + day + ")";
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

