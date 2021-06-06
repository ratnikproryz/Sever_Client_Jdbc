package Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.cert.TrustAnchor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;

import javax.lang.model.element.VariableElement;
import javax.swing.JOptionPane;
import javax.swing.text.html.CSS;

public class Server {
	private int port;

	public Server(int port) {
		this.port = port;
	}

	private void execute() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server is started! Waiting the clients");

			// server se lien tuc nhan cac yeu cau ket noi tu client
			while (true) {

				Socket socket = serverSocket.accept();
				System.out.println("Server is connected with: " + socket);
				// chờ sokcet gui thong tin den roi truy xuat csdl

				RequestSQL requestSQL = new RequestSQL(socket);
				requestSQL.start();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				serverSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Server server = new Server(6793);

		server.execute();
	}
}

class RequestSQL extends Thread implements Serializable {
	private static final long serialVersionUID = 1L;
	private Socket socket;
	private Connection connection;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private ObjectOutputStream objOutStream = null;
	private ObjectInputStream objInStream = null;
	public RequestSQL(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// ket noi toi csdl
			System.out.println("OK");
			DAO dataConnect = new DAO();
			System.out.println("dao");
			connection = dataConnect.getConnection();
			System.out.println("connected");

			System.out.println("test");
			dis = new DataInputStream(socket.getInputStream());
			objOutStream = new ObjectOutputStream(socket.getOutputStream());
			// lay request gui tu client
			System.out.println("hello");

			String request = dis.readUTF();
			System.out.println(request + "   -------1");

			loadTable(request);

			switch (request) {
			case "searchKH":
				searchKH();
				break;
			case "insertKH":
				insertKH();
				break;
			case "updateKH":
				updateKH();
				break;
				
			default:
				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	public void loadTable(String request) {
		try {
			ResultSet resultSet = null;
			Statement statement;
			if (request.startsWith("select")) {

				statement = connection.createStatement();
				resultSet = statement.executeQuery(request);
				// thuc thi cau lenh
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

				int column = resultSetMetaData.getColumnCount();
				Vector vTitle = new Vector(column);
				for (int i = 1; i <= column; i++) {
					vTitle.add(resultSetMetaData.getColumnLabel(i));
					System.out.print(resultSetMetaData.getColumnLabel(i) + "\t");
				}

				objOutStream.writeObject(vTitle);
				objOutStream.flush();

				Vector vData = new Vector();
				while (resultSet.next()) {
					System.out.println();
					Vector row = new Vector(column);
					for (int i = 1; i <= column; i++) {
						row.add(resultSet.getString(i));
						System.out.println(resultSet.getString(i));
					}
					vData.add(row);
				}
				objOutStream.writeObject(vData);
				objOutStream.flush();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void searchKH() {
		try {
			String sql = "{call searchKH(?)}";
			dis = new DataInputStream(socket.getInputStream());
			String key = dis.readUTF();
			
			CallableStatement callableStatement = connection.prepareCall(sql);
			callableStatement.setString(1, key);
			ResultSet resultSet = callableStatement.executeQuery();
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int column = resultSetMetaData.getColumnCount();
//				
			Vector vTitle = new Vector(column);
			for (int i = 1; i <= column; i++) {
				vTitle.add(resultSetMetaData.getColumnLabel(i));
				System.out.print(resultSetMetaData.getColumnLabel(i) + "\t");
			}
			objOutStream.writeObject(vTitle);
			objOutStream.flush();

			Vector vData = new Vector();
			while (resultSet.next()) {
				System.out.println();
				Vector row = new Vector(column);
				for (int i = 1; i <= column; i++) {
					row.add(resultSet.getString(i));
					System.out.println(resultSet.getString(i));
				}
				vData.add(row);
			}
			objOutStream.writeObject(vData);
			objOutStream.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	
	public void insertKH() {
		try {
			String sql= dis.readUTF();
			System.out.println(sql);
			objInStream = new ObjectInputStream(socket.getInputStream());
			Vector<String> dataInsert = (Vector<String>) objInStream.readObject();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			Enumeration<String> enumeration = dataInsert.elements();
			int i=1;
			while (enumeration.hasMoreElements()) {
				String string = (String) enumeration.nextElement();
				System.out.println(i + " "+ string);
				preparedStatement.setString(i, string);
				i++;
			}
			System.out.println(preparedStatement.toString());
			int num = preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public void updateKH() {
		try {
			String sql= dis.readUTF();
			System.out.println(sql);
			objInStream = new ObjectInputStream(socket.getInputStream());
			Vector<String> dataUpdate = (Vector<String>) objInStream.readObject();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			Enumeration<String> enumeration = dataUpdate.elements();
			int i=1;
			while (enumeration.hasMoreElements()) {
				String string = (String) enumeration.nextElement();
				System.out.println(i + " "+ string);
				preparedStatement.setString(i, string);
				i++;
			}
			System.out.println(preparedStatement.toString());
			int num = preparedStatement.executeUpdate();
			System.out.println(num+"");
//			dos= new DataOutputStream(socket.getOutputStream());
//			dos.writeUTF(num+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
