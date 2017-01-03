package com.g2.pr3.units;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketClient {
	public static void main(String[] args) throws Exception {
		Socket s = new Socket("localhost", 9090);
		BufferedReader input = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		String answer = input.readLine();
		System.out.println(answer);
		s.close();
		input.close();
		
	}
}
