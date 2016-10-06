package com.heroiccreations;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SocketChat {
	public static void main(String[] args){
		int port = 0;
		boolean running = false;

		try{
			port = Integer.parseInt(args[0]);

			Chat myChat = new Chat(port);

			myChat.run();

		}catch(NumberFormatException | InputMismatchException in){
			System.out.println("ERROR! Invalid Port #");
			System.exit(1);
		}
	}
}

class Chat{
	boolean running = true;
	int port;

	HashMap<String, Integer> connections = new HashMap<>();

	public Chat(int port){
		this.port = port;

		System.out.println("Welcome To SocketChat!");
		System.out.println("Running on port: " + this.port);

	}

	public void run(){
		//initialize scanner
		Scanner in = new Scanner(System.in);
		String command;

		while(running){
			System.out.print(">> ");

			command = in.nextLine();

			//help command will describe all other commands
			if(command.equals("help")){
				System.out.println("Help goes here");
			}

			//show the client's IP
			else if(command.equals("myip")){
				try{
					System.out.println(Inet4Address.getLocalHost().getHostAddress());
				}catch(UnknownHostException u){
					System.out.println("Error! Unable to retrieve IP Address");
				}
			}

			//show the client's port
			else if(command.equals("myport")){
				System.out.println("MyPort: " + this.port);
			}

			else if(command.contains("connect")){
				String[] input = command.split(" ");
				try {
					String destination = input[1];

					int portnum = Integer.parseInt(input[2]);

					connect(destination, portnum);

				} catch (Exception e) {
					System.out.println("The connect command must be in the form 'connect <ip> <port>'" +
					"\nDo not forget the spaces in between & to make sure the information is valid");
				}
			}

			//list all open connections
			else if(command.equals("list")){
				listConnections();
			}

			//exit command will terminate the program and close all connections
			else if(command.equals("exit")){
				//close all connections
				System.out.println("Thanks for using SocketChat!");
				break;
			}

			System.out.println("");
		}
	}

	void connect(String destination, int portnum){
		//check if ip is valid
		String error = validateIP(destination);

		if(!error.equals("")){
			System.out.println(error);
			return;
		}

		//add connection to list
		this.connections.put(destination, portnum);

		System.out.println("Connected to: " + destination + " on port: " + portnum);

	}

	private String validateIP(String ip){
		String error = "";

		//make sure IP contains 3 or more dots
		if(!ip.matches("[0-9+.0-9+.0-9+(.*0-9*)]")){
			error = "IP Address must contain at least 3 dots";
		}

		//check if IP contains letters
		if(ip.matches("[A-z]*")){
			error = "IP Address Must Only Be Numbers and Dots!";
		}

		return error;
	}

	void listConnections(){
		int connectionNum = 0;

		System.out.println("Connection ID: \t IP Address: \t Port #:");

		for(String ip : this.connections.keySet()){
			connectionNum++;

			int portnum = this.connections.get(ip);

			System.out.println("\t" + connectionNum + ") \t\t\t" + ip + "\t\t\t" + portnum);
		}

		if(connectionNum == 0){
			System.out.println("\t   No Current Connections :( ");
		}
	}
}