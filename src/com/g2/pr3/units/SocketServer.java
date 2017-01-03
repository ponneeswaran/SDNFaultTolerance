package com.g2.pr3.units;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SocketServer {

	public static void main(String[] args) throws Exception {
		ServerSocket listener = new ServerSocket(9090);
		Socket socket = null;
		try {
			while (true) {
				socket = listener.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(),
						true);
				out.println(new Date().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			socket.close();
			listener.close();
		}
	}
}