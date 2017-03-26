package me.F_o_F_1092.CreativeElytra.PluginManager.Spigot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.bukkit.Bukkit;

import me.F_o_F_1092.CreativeElytra.Main;
import me.F_o_F_1092.CreativeElytra.PluginManager.ServerLog;

public class UpdateListener extends me.F_o_F_1092.CreativeElytra.PluginManager.UpdateListener {

	public static void checkForUpdate() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
			@Override
			public void run() {
				try {
					TrustManager[] trustAllCerts = new TrustManager[] {
						new X509TrustManager() {
							public java.security.cert.X509Certificate[] getAcceptedIssuers() {
								return null;
							}
							
							public void checkClientTrusted(X509Certificate[] certs, String authType) {}
							
							public void checkServerTrusted(X509Certificate[] certs, String authType) {}
						}
					};
					
					SSLContext sslC = SSLContext.getInstance("SSL");
					sslC.init(null, trustAllCerts, new java.security.SecureRandom());
					
					HttpsURLConnection.setDefaultSSLSocketFactory(sslC.getSocketFactory());

					HostnameVerifier allHostsValid = new HostnameVerifier() {
						public boolean verify(String hostname, SSLSession session) {
							return true;
						}
					};
						    
					HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
						 
					URL url = new URL(versionURL);
					URLConnection connection = url.openConnection();
					final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
					
					if (!reader.readLine().equals("Version: " + updateString)) {
						ServerLog.log(tag + "§4 A new update is available.");
						updateAvailable = true;
					}
					
				} catch ( IOException e) {
					ServerLog.log(tag +"§4 Couldn't check for updates. [" + e.getMessage() +"]");
				} catch (NoSuchAlgorithmException e) {
					ServerLog.log(tag +"§4 Couldn't check for updates. [" + e.getMessage() +"]");
				} catch (KeyManagementException e) {
					ServerLog.log(tag +"§4 Couldn't check for updates. [" + e.getMessage() +"]");
				}
			}
		}, 0L);
	}
}
