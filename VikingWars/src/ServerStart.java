import java.io.IOException;


public class ServerStart {

	public static void main(String args[]) throws IOException{
		Server server = new Server(1500);
		server.start();
		//GameMulticastServer gms = new GameMulticastServer("localhost", 6400);	
		//gms.start();
	}
	
}
