package utils;

import java.io.IOException;
import java.net.ServerSocket;

import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AppiumServerUtils {

	public static AppiumDriverLocalService service;
//	public static MobileDriver<MobileElement> driver;

	

	public AppiumDriverLocalService startAppiumServer() {
		service = AppiumDriverLocalService.buildDefaultService();
		service.start();
		return service;
	}

	public AppiumDriverLocalService stopAppiumServer() {
		service.stop();
		return service;
	}

	public boolean serverState() {
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(4724);
			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
}
