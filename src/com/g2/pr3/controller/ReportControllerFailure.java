package com.g2.pr3.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.net.Socket;

public class ReportControllerFailure {

	public static void main(String[] args) throws Exception {
		
		Boolean ctrl1 = true;
		Boolean ctrl2 = true;
		
		while (true) {
			String command = "curl -s http://localhost:8080/ui/index.html";
			Process proc = Runtime.getRuntime().exec(command);
			// Get the output string result
			BufferedInputStream buffer = new BufferedInputStream(
					proc.getInputStream());
			BufferedReader commandOutput = new BufferedReader(
					new InputStreamReader(buffer));

			if(commandOutput.readLine() != null){
				ctrl1 = true;
			}
			if (ctrl1 && commandOutput.readLine() == null ) {
				Socket s = new Socket("localhost", 9091);
				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
				out.println("6753");
				s.close();
				out.close();
				
				String topoCmd = "curl -s http://localhost:8080/wm/device/";
				System.out.println(topoCmd);
				Process p = Runtime.getRuntime().exec(topoCmd);
//				Process p = new ProcessBuilder(topoCmd).redirectOutput(Redirect.appendTo(new File("s1.topo"))).start();
				
				PrintWriter writer = new PrintWriter(new FileOutputStream(new File("s1.topo"), true /* append = true */)); 
				InputStreamReader isr = new InputStreamReader(p.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				String line;

				while ((line = br.readLine()) != null) {
					writer.println(line);
				}
				writer.close();
				
				topoCmd = "curl -s http://localhost:8180/wm/device/";
				System.out.println(topoCmd);
				p = Runtime.getRuntime().exec(topoCmd);
//				Process p = new ProcessBuilder(topoCmd).redirectOutput(Redirect.appendTo(new File("s1.topo"))).start();
				
				writer = new PrintWriter(new FileOutputStream(new File("s1.topo"), true /* append = true */));
				isr = new InputStreamReader(p.getInputStream());
				br = new BufferedReader(isr);

				while ((line = br.readLine()) != null) {
					writer.println(line);
				}
				writer.close();
				
				ctrl1 = false;
			}

			commandOutput.close();

			String command2 = "curl -s http://localhost:8180/ui/index.html";
			Process proc2 = Runtime.getRuntime().exec(command2);
			
			buffer = new BufferedInputStream(
					proc2.getInputStream());
			commandOutput = new BufferedReader(
					new InputStreamReader(buffer));

			if(commandOutput.readLine() != null){
				ctrl2 = true;
			}
			if (ctrl2 && commandOutput.readLine() == null ) {
				Socket s = new Socket("localhost", 9091);
				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
				out.println("6653");
				s.close();
				out.close();
				
				String topoCmd = "curl -s http://localhost:8080/wm/device/";
				System.out.println(topoCmd);
				Process p = Runtime.getRuntime().exec(topoCmd);
//				Process p = new ProcessBuilder(topoCmd).redirectOutput(Redirect.appendTo(new File("s2.topo"))).start();
			
				PrintWriter writer = new PrintWriter(new FileOutputStream(new File("s2.topo"), true /* append = true */));
				InputStreamReader isr = new InputStreamReader(p.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				String line;

				while ((line = br.readLine()) != null) {
					writer.println(line);
				}
				writer.close();
				
				topoCmd = "curl -s http://localhost:8180/wm/device/";
				System.out.println(topoCmd);
				p = Runtime.getRuntime().exec(topoCmd);
//				Process p = new ProcessBuilder(topoCmd).redirectOutput(Redirect.appendTo(new File("s1.topo"))).start();
				
				writer = new PrintWriter(new FileOutputStream(new File("s2.topo"), true /* append = true */));
				isr = new InputStreamReader(p.getInputStream());
				br = new BufferedReader(isr);

				while ((line = br.readLine()) != null) {
					writer.println(line);
				}
				writer.close();
				
				ctrl2 = false;
			}

			commandOutput.close();
			
			
		}
	}
}