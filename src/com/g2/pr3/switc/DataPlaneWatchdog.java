package com.g2.pr3.switc;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class DataPlaneWatchdog {
	private static String brokenHost = "";

	/*
	 * Continuously ping hosts for their connectivity. If host isn't getting
	 * connected, report it
	 */
	private static boolean watchDataplane() {
		boolean h1_reachable = true;
		boolean h2_reachable = true;
		boolean h3_reachable = true;
		boolean h4_reachable = true;

		while (true) {
			try {
				InetAddress h1 = InetAddress.getByName("10.0.0.3");
				h1_reachable = h1.isReachable(100);
				if (!h1_reachable) {
					brokenHost = "10.0.0.3";
					break;
				}

				InetAddress h2 = InetAddress.getByName("10.0.0.4");
				h2_reachable = h2.isReachable(100);
				if (!h2_reachable) {
					brokenHost = "10.0.0.4";
					break;
				}

				InetAddress h3 = InetAddress.getByName("10.0.0.5");
				h3_reachable = h3.isReachable(100);
				if (!h3_reachable) {
					brokenHost = "10.0.0.5";
					break;
				}

				InetAddress h4 = InetAddress.getByName("10.0.0.6");
				h4_reachable = h4.isReachable(100);
				if (!h4_reachable) {
					brokenHost = "10.0.0.6";
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	/*
	 * Inform controller which switch-host link is down
	 */
	private static void informController() throws Exception {
		System.out.println("Broken host = " + brokenHost);
		Socket s = new Socket("localhost", 9090);
		PrintWriter out = new PrintWriter(s.getOutputStream(),
				true);
		out.println(brokenHost);
		s.close();
	}

	public static void main(String[] args) throws Exception {
		boolean dataPlaneLinkBroken;
		System.out.println("Starting processes");
		while (true) {
			dataPlaneLinkBroken = watchDataplane();
			if (dataPlaneLinkBroken == false) {
				informController();
			}
		}
	}
}