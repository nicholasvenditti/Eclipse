function validate() {

	var ok = true;
	var p = document.getElementById("credit_taken").value;
	
	if (p == "" || p < 0) {
	
		alert("Invalid. Minimum Credit Taken must be 0 or greater.");
		ok = false;
	}
	
	return ok;
}

function doSimpleAjax(address) {

	var request = new XMLHttpRequest();
 	var data = '';
 	
 	/* add your code here to grab all parameters from form */
	data += "namePrefix=" + document.getElementById("namePrefix").value + "&";
	data += "credit_taken=" + document.getElementById("credit_taken").value + "&";
	data += "report=true&";
	data += "xml=false&";
	data += "json=false";
	console.log(data);
 	request.open("GET", (address + "?" + data), true);
 	request.onreadystatechange = function() {
 		handler(request);
 	};
 	
 	request.send(null);
}

function handler(request) {

 	if ((request.readyState == 4) && (request.status == 200) && (request.getResponseHeader("Content-Type") == 'application/json;charset=ISO-8859-1')) {
 	
 		var target = document.getElementById("ajaxTarget");
 		target.innerHTML = request.responseText;
 	}
} 