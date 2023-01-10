function ajaxVisit(address) {
	if (document.getElementById("bidRecord") == null)
		return;
	if (document.getElementById("reviewText") != null)
		document.getElementById("reviewText").value= "";
	
	
	var request = new XMLHttpRequest();
 	var data = '';
 	
 	/* add your code here to grab all parameters from form */
	data += "bid=" + document.getElementById("bidRecord").value + "&";
	data += "ajax=viewer";
 	request.open("GET", (address + "?" + data), true);
 	request.onreadystatechange = function() {
 		;
 	};
 	
 	request.send(null);
}

function showBilling(checkbox) {
	var sbox= document.getElementById('shippingBox');
	if (checkbox.checked)
		sbox.style.display= "none";
	else
		sbox.style.display= "block";
}

function validateCC() {
	var ccnum= document.getElementById('ccardNumber');
	var ccname= document.getElementById('ccardOwner');
	var button= document.getElementById('confirmOrder');
	if (ccnum.value.includes(" "))
		ccnum.value= ccnum.value.replace(" ","");
	if (ccnum.value.length == '16' && ccname.value.length>0)
		button.disabled= false;
	else
		button.disabled= true;
}