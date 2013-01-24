/**
 * Ti.Certificatestore Module
 * Copyright (c) 2012-2013 by Appcelerator, Inc. All Rights Reserved.
 * Please see the LICENSE included with this distribution for details.
 */

package ti.certificatestore;

import java.io.InputStream;
import java.security.KeyStore;
import java.util.ArrayList;

import javax.net.ssl.X509TrustManager;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.util.TiFileHelper;
import org.appcelerator.titanium.util.TiUrl;

@Kroll.module(name="Certificatestore", id="ti.certificatestore")
public class CertificatestoreModule extends KrollModule
{
	private static final String TAG = "CertificatestoreModule";
	private ArrayList<KeyStore> certKeystores;
	private AdditionalKeyStoresTrustManager _trustManager;

	public CertificatestoreModule()
	{
		super();
		certKeystores = new ArrayList<KeyStore>();
	}

	@Kroll.method
	public void addCertificate(String certPath, String password)
	{
		try {
			KeyStore keystore = KeyStore.getInstance("pkcs12");
			TiUrl imageUrl = new TiUrl((String) certPath);
			TiFileHelper tfh = new TiFileHelper(TiApplication.getInstance());
			InputStream is = tfh.openInputStream(imageUrl.resolve(), false);
			keystore.load(is, password.toCharArray());
			certKeystores.add(keystore);
		} catch (Exception e) {
			Log.e(TAG, "Error adding certificate" + e.getMessage());
		}
	}

	@Kroll.method
	public X509TrustManager getTrustManager()
	{
		if (_trustManager == null) {
			_trustManager = new AdditionalKeyStoresTrustManager(certKeystores);
		}
		return _trustManager;
	}
}

