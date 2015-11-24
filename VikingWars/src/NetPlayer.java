import java.net.InetAddress;

/**
 * This class encapsulates a network players
 * @author Joseph Anthony C. Hermocilla
 *
 */

public class NetPlayer {
	/**
	 * The network address of the player
	 */
	private InetAddress address;
	
	/**
	 * The port number of  
	 */
	private int port;
	
	/**
	 * The name of the player
	 */
	private String name;
	
	private boolean winner = false;
	/**
	 * The position of player
	 */
	private int x,y, remaining, remainingClass;

	/**
	 * Constructor
	 * @param name
	 * @param address
	 * @param port
	 */
	public NetPlayer(String name,InetAddress address, int port){
		this.address = address;
		this.port = port;
		this.name = name;
	}

	/**
	 * Returns the address
	 * @return
	 */
	public InetAddress getAddress(){
		return address;
	}

	/**
	 * Returns the port number
	 * @return
	 */
	public int getPort(){
		return port;
	}

	/**
	 * Returns the name of the player
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Sets the X coordinate of the player
	 * @param x
	 */
	public void setX(int x){
		this.x=x;
	}
	
	
	/**
	 * Returns the X coordinate of the player
	 * @return
	 */
	public int getX(){
		return x;
	}
	
	
	/**
	 * Returns the y coordinate of the player
	 * @return
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Sets the y coordinate of the player
	 * @param y
	 */
	public void setY(int y){
		this.y=y;		
	}
	
	public void setRemaining(int b){
		this.remaining = b;
	}
	
	public int getRemaining(){
		return remaining;
	}
	
	public void setRemainingClass(int b){
		this.remainingClass = b;
	}
	
	public int getRemainingClass(){
		return remainingClass;
	}
	
	public void setWinner(){
		this.winner = true;
	}
	
	public boolean getWinner(){
		return winner;
	}

	/**
	 * String representation. used for transfer over the network
	 */
	public String toString(){
		String retval="";
		retval+="PLAYER ";
		retval+=name+" ";
		retval+=remainingClass+" ";
		retval+=remaining;
		return retval;
	}	
	
	public String declareWinner(){
		String retval="";
		retval+="WINNER ";
		retval+=name+" ";
		retval+=winner;
		return retval;
	}
}
