# Ti.CertificateStore Module

## Description

A certificate store that can be used with HTTPClient in Titanium.

## Dependencies

This module requires Release 2.1.5 or newer of the Titanium SDK.

## Getting Started

View the [Using Titanium Modules](http://docs.appcelerator.com/titanium/latest/#!/guide/Using_Titanium_Modules) document for instructions on getting
started with using this module in your application.

## Accessing the Module

Use `require` to access this module from JavaScript:

	var CertificateStore = require("ti.certificatestore");

The CertificateStore variable is a reference to the Module object.

## Methods

### _void_ CertificateStore.addCertificate(String path, String password)
Adds a certificate to the certificate store.

* __path[String]:__ The path to the certificate (must be in .p12 format).
* __password[String]:__ The password is the password for the given certificate.

#### Example
	CertificateStore.addCertificate('client.p12', 'password');

### _TrustManager_ CertificateStore.getTrustManager()
Gets the trust manager proxy associated with the certificate store. This should be used with the addTrustManager() method from HTTPClient in titanium.

* __Returns:__ The trust manager proxy associated with the certificate store.

#### Example
	httpClient.addTrustManager(CertificateStore.getTrustManager());

## Usage
See the example applications in the `example` folder of the module. To successfully run the example app, you will need a server to communicate with. To setup the example app and server, follow the steps below.

#### Server
There is webserver code in the _example_ folder that can be used to test this module. To run the server:

* Install [Node](http://nodejs.org/) 
* Go to the __"example/webserver"__ folder from the terminal and run this command `node server.js` to start the server.
* Enter the __PEM pass phrase__ which is "password".

#### Client

* Copy the .p12 file from the _example_ folder into the _Resources_ folder of your app.
* Change the url in the example app to point to the server you just setup.
* Run the app
* Click the send button (if everything is setup correctly, you will get 'success' message)

## Author

Allen Yeung 

## Module History

View the [change log](changelog.html) for this module.

## Feedback and Support

Please direct all questions, feedback, and concerns to [info@appcelerator.com](mailto:info@appcelerator.com?subject=Ti.CertificateStore%20Module).

## License

Copyright(c) 2012-2013 by Appcelerator, Inc. All Rights Reserved. Please see the LICENSE file included in the distribution for further details.
