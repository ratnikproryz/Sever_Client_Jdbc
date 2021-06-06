package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JOptionPane;

public class UpdateKH implements Serializable{
//	private static final long serialVersionUID = 1L;	
	private Vector<String> dataUpdate= new Vector<String>();
	
	public UpdateKH() {
		// TODO Auto-generated constructor stub
	}

	public UpdateKH(Vector<String> dataUpdate) {
		this.dataUpdate = dataUpdate;
	}
	
	public void  execute() {
		try {
			String request="updateKH";
			String sql= "Update khachhang set makh=?, tenkh=?, cmnd=?, diachi=?, sdt=?, "
					+ "maMDSD=? where makh= ?"; 
			System.out.println(sql);
			Client client = new Client(InetAddress.getLocalHost(), 6793);
			Socket socket = new Socket();
			socket = client.connectServer();
			
			
			DataOutputStream dos= new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(request);
			dos.flush();
			dos.writeUTF(sql);
			dos.flush();
			ObjectOutputStream objOutput = new ObjectOutputStream(socket.getOutputStream());
			//gui du lieu den server
			objOutput.writeObject(dataUpdate);
			
			//nhan du lieu tu server
//			DataInputStream dis= new DataInputStream(socket.getInputStream());
//			int num=Integer.parseInt(dis.readUTF());
//			if(num !=0) {
//				JOptionPane.showMessageDialog(null, "Updating sucessfully,"+num + " row is updated");
//			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
