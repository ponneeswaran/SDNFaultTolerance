package com.g2.pr3.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class GetFailedHost {

	public static void main(String[] args) throws Exception {
		ServerSocket listener = new ServerSocket(9090);
		Socket socket = null;
		List<String> hosts = Arrays.asList("10.0.0.3", "10.0.0.4", "10.0.0.5", "10.0.0.6");

		try {
			while (true) {
				socket = listener.accept();
				BufferedReader input = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				String answer = input.readLine();
				if ( hosts.contains(answer) ) {
					// Store failed node details
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			socket.close();
			listener.close();
		}
	}
}
