package Controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {
	private InetAddress host;
	private int port;
	
	public Client(InetAddress host, int port) {
		this.port = port;
		this.host = host;
	}
	
	public Socket connectServer() {
		Socket socket= null;
		try {
			socket = new Socket(host, port);
			
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return socket;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Client client = new Client(InetAddress.getLocalHost(), 6793);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}
