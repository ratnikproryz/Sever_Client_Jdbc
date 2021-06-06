package Controller;

import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;

public class insertKH implements Serializable{
	private Vector<String> dataInsert = new Vector<String>();
	public insertKH() {
		// TODO Auto-generated constructor stub
	}
	public insertKH(Vector<String> dataInsert) {
		this.dataInsert = dataInsert;
	}
	
	public void execute() {
		try {
			String request="insertKH";
			String sql = "insert into khachhang values(?,?,?,?,?,?)";
			Client client = new Client(InetAddress.getLocalHost(), 6793);
			Socket socket = new Socket();
			socket = client.connectServer();
			
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(request);
			dos.flush();
			dos.writeUTF(sql);
			ObjectOutputStream objOutStream = new ObjectOutputStream(socket.getOutputStream());
			objOutStream.writeObject(dataInsert);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
