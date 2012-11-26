var certificateStore = require('ti.certificatestore');

var win = Ti.UI.createWindow({backgroundColor: '#FFF'});
win.open();

var sendButton = Ti.UI.createButton({
	title: 'Send Request',
	height: "15%",
	width: "90%"
});
win.add(sendButton);

sendButton.addEventListener('click', function() {
	// This should be the url of the webserver you are trying to test
	var url = "https://127.0.0.1:5678";
	
	var httpClient = Ti.Network.createHTTPClient({
		onload : function(e) {
			Ti.API.info("Received text: " + this.responseText);
			alert('success');
		},
		onerror : function(e) {
			Ti.API.info("Error: " + e.error);
			alert('error');
		},
		timeout : 5000
	});
	
	// Enable certificate validation
	httpClient.validatesSecureCertificate = true;
	
	// Add the certificate to the certificate store
	certificateStore.addCertificate('server.p12', 'password');
	// Add the trust manager to the http client
	httpClient.addTrustManager(certificateStore.getTrustManager());
	
	httpClient.open("GET", url);
	httpClient.send();	
});

var minsdk = '2.1.5';
if (Ti.version < minsdk) {
	Ti.API.error('CertificateStore Module will not work on pre '+minsdk+' TiSDK versions');
	alert('CertificateStore Module will not work on pre '+minsdk+' TiSDK versions');
}