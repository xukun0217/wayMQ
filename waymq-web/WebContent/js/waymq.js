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
	this["class"] = aClass;
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
			var sp = (sb.length > 0) ? "&" : "";
			sb = sb + sp + key + "=" + val;
		}
	}
	return base + "?" + sb;
};
