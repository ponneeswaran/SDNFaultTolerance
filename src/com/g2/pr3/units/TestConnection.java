package com.g2.pr3.units;

import java.net.InetAddress;

public class TestConnection {

	public static void main(String[] args) {
		boolean reachable 	 = true;
		boolean h1_reachable = true;
		boolean h2_reachable = true;
		boolean h3_reachable = true;
		boolean h4_reachable = true;

		System.out.println("Start of execution");
		while (reachable != false) {
			try {
				InetAddress h1 = InetAddress.getByName("10.0.0.3");
				h1_reachable = h1.isReachable(1000);
				
				InetAddress h2 = InetAddress.getByName("10.0.0.4");
				h2_reachable = h2.isReachable(1000);
				
				InetAddress h3 = InetAddress.getByName("10.0.0.5");
				h2_reachable = h3.isReachable(1000);
				
				InetAddress h4 = InetAddress.getByName("10.0.0.6");
				h2_reachable = h4.isReachable(1000);
				
				reachable = h1_reachable && h2_reachable && h3_reachable && h4_reachable;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// Exited from loop, which means the connection is broken; 
		// inform controller
		System.out.println("End of execution");
	}
}