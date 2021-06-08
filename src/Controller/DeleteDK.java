package Controller;

import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class DeleteDK {
	

	
	public DeleteDK() {
		// TODO Auto-generated constructor stub
	}
	
	public void execute(String key) {
		try {
			String request = "deleteDK";
			Client client = new Client(InetAddress.getLocalHost(), 6793);
			Socket socket = new Socket();
			socket = client.connectServer();
			
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(request);
			dos.flush();
			dos.writeUTF(key);
			dos.flush();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
