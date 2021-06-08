package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JOptionPane;

public class DeleteKH {
	
	public DeleteKH() {
		// TODO Auto-generated constructor stub
	}
	
	public void execute(String key) {
		String request= "deleteKH";
		try {
			Client client = new Client(InetAddress.getLocalHost(), 6793);
			Socket socket = client.connectServer();
			DataOutputStream dos= new DataOutputStream(socket.getOutputStream());
			
			dos.writeUTF(request);
			dos.flush();
			dos.writeUTF(key);
			
//			DataInputStream dis = new DataInputStream(socket.getInputStream());
//			int num = Integer.parseInt(dis.readUTF());
//			System.out.println(num);
//			if(num> 0) {
//				JOptionPane.showMessageDialog(null, "Delete successfully!");
//			}
//			else {
//				JOptionPane.showMessageDialog(null, "Delete failed!");
//			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
