package Controller;

import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import javax.sound.midi.Soundbank;

import org.ietf.jgss.Oid;

public class GetBill implements Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> dataGetBill;
	
	public GetBill() {
		// TODO Auto-generated constructor stub
	}

	public GetBill(ArrayList<String> dataGetBill) {
		this.dataGetBill = dataGetBill;
	}
	
	public ArrayList<String> getDataGetBill() {
		return dataGetBill;
	}

	public void setDataGetBill(ArrayList<String> dataGetBill) {
		this.dataGetBill = dataGetBill;
	}

	public void execute() {
		try {
			String request = "getBill";
			Client client = new Client(InetAddress.getLocalHost(), 6793);
			Socket socket = new Socket();
			socket = client.connectServer();
			
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(request);
			dos.flush();
			
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(dataGetBill);
			System.out.println(dataGetBill.toString());
			
			ObjectInputStream  objectInputStream = new ObjectInputStream(socket.getInputStream());
			dataGetBill = (ArrayList<String>) objectInputStream.readObject();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
	}
}
