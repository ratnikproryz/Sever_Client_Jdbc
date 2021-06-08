package Controller;

import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;

public class UpdateDK {
	private Vector<String> dataUpdate = new Vector<String>();
	public UpdateDK() {
		// TODO Auto-generated constructor stub
	}
	public UpdateDK(Vector<String> dataUpdate) {
		this.dataUpdate = dataUpdate;
	}
	
	public void execute() {
		try {
			String request = "updateDK";
			String sql= "update dienke set madk=?, ngaythang=?, cs_cu=?, cs_moi=?,"
					+ "makh=? where madk= ?";
			Client client = new Client(InetAddress.getLocalHost(), 6793);
			Socket socket = new Socket();
			socket = client.connectServer();
			
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(request);
			dos.flush();
			dos.writeUTF(sql);
			dos.flush();
			ObjectOutputStream objOutStream = new ObjectOutputStream(socket.getOutputStream());
			objOutStream.writeObject(dataUpdate);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
