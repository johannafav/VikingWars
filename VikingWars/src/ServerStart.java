/*
 * VIKING WARS
 * CMSC 137 LABORATORY PROJECT
 * 1st Semester AY 2015-2016
 */

import java.io.IOException;

//main entry point for the server
public class ServerStart {

	public static void main(String args[]) throws IOException{
		Server server = new Server(1500);
		GameServer gs = new GameServer(3);
		server.start();
	}
	
}
