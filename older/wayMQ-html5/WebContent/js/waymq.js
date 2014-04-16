function includeJS(file) {
	document.write('<script type="text/javascript" src="' + file
			+ '"  ></script>');
}

function includeJQuery() {
	includeJS("js/jquery-2.1.0.js");
}

function loadAccountInfo(id) {
	var target = $(id);
	target.load("json_request?a=1&b=2", function(text, statusText, xhr) {

		target.text(text);

	});
}

function loadPageHeader(id) {
	var target = $(id);
	target.text("loading...");
	target.load("xml/page-header.xml", function(responseTxt, statusTxt, xhr) {
		target.html(responseTxt);
		// load json
		loadAccountInfo("#header_account");
	});
}
