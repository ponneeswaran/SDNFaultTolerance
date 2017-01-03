package com.g2.pr3.switc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ControlPlaneWatchdog {
	public static void main(String[] args) throws Exception{
		ServerSocket listener = new ServerSocket(9091);
		Socket socket = null;
		try {
			while (true) {
				socket = listener.accept();
				BufferedReader input = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				String port = input.readLine();
				String command = "ovs-vsctl set-controller " + args[0] + " tcp:127.0.0.1:" + port;
				System.out.println(command);
				Process proc = Runtime.getRuntime().exec(command);				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			socket.close();
			listener.close();
		}
	}
}
