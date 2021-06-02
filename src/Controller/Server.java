package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.lang.model.element.VariableElement;
import javax.swing.JOptionPane;

public class Server {
	private int port;

	public Server(int port) {
		this.port = port;
	}
	private void execute() throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("Server is started! Waiting the clients");
		
		//server se lien tuc nhan cac yeu cau ket noi tu client
		while(true) {
			Socket socket = serverSocket.accept();
			System.out.println("Server is connected with: " + socket);
			// chờ sokcet gui thong tin den roi truy xuat csdl
			RequestSQL requestSQL = new RequestSQL(socket);
			requestSQL.start();
			
		}
	}


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Server server= new Server(6793);
		server.execute();
	}
}

class RequestSQL extends Thread implements Serializable{
	private static final long serialVersionUID = 1L;
	private Socket socket;
	private Connection connection;
	
	public RequestSQL(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			//ket noi toi csdl
			DAO dataConnect= new DAO();
			connection=dataConnect.getConnection();
			
			DataInputStream dis= null;
			DataOutputStream dos= null;
			while(true){
				dis= new DataInputStream(socket.getInputStream());
				// lay request gui tu client
				String request= dis.readUTF(); 
				System.out.println(request);
				ObjectInputStream objInStream= new ObjectInputStream(socket.getInputStream());
				// tra respone toi client
				ObjectOutputStream objOutStream= new ObjectOutputStream(socket.getOutputStream());
				
				// client se gui preprared statment de server thuc thi
				PreparedStatement preparStatement = (PreparedStatement) objInStream.readObject();
				ResultSet resultSet = null;
				Statement statement;
				
				if(request.startsWith("select")) {
					System.out.println("Select");
					statement= connection.createStatement();
					resultSet= statement.executeQuery(request);
					// thuc thi cau lenh
				
//					resultSet= preparStatement.executeQuery();
					objOutStream.writeObject(resultSet);
					dos= new DataOutputStream(socket.getOutputStream());
					dos.writeUTF("OK");
					dos.flush();
					objOutStream.flush();
				}
				else {
					int n=preparStatement.executeUpdate();
					System.out.println("hi");
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}		
}
