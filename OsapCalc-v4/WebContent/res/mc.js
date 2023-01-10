function validate() {

	var ok = true;
	var p = document.getElementById("principal").value;
	
	if (isNaN(p) || p <= 0) {
	
		alert("Invalid. Principal must be greater than 0.");
		document.getElementById("principal-error").style.display = "inline";
		document.getElementById("principal-error").style.color = "red";
		ok = false;
	} else {
		document.getElementById("principal-error").style.display = "none";
	}
	
	p = document.getElementById("interest").value;
	
	if ((isNaN(p) || p <= 0 || p >= 100) && ok) {
	
		alert("Invalid. Interest must be in (0, 100).");
		document.getElementById("interest-error").style.display = "inline";
		document.getElementById("interest-error").style.color = "red";
		ok = false;
	} else {
		document.getElementById("interest-error").style.display = "none";
	}
	
	p = document.getElementById("period").value;
	
	if ((isNaN(p) || p <= 0) && ok) {
	
		alert("Invalid. Period must be greater than 0.");
		document.getElementById("period-error").style.display = "inline";
		document.getElementById("period-error").style.color = "red";
		ok = false;
	} else {
		document.getElementById("period-error").style.display = "none";
	}
	
	return ok;
}

function doSimpleAjax(address) {

	var request = new XMLHttpRequest();
 	var data = '';
 	
 	/* add your code here to grab all parameters from form */
	data += "principal=" + document.getElementById("principal").value + "&";
	data += "interest=" + document.getElementById("interest").value + "&";
	data += "period=" + document.getElementById("period").value + "&";
	data += "grace=" + document.getElementById("grace").checked + "&";
	data += "calculate=true";
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