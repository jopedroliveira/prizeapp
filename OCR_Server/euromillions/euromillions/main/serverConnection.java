package euromillions.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class serverConnection {
	public static void main(String args[]) throws IOException {

		ServerSocket s = new ServerSocket(1234);

		while (true) {
			Socket s1 = s.accept();


			InputStream in = s1.getInputStream();
			DataInputStream dataIn = new DataInputStream(in);

			OutputStream out = s1.getOutputStream();
			DataOutputStream dataOut = new DataOutputStream(out);

			int dataLength = dataIn.readInt();
			byte[] receivedData = new byte[dataLength];

			for(int i = 0; i < receivedData.length; i++){
				receivedData[i] = dataIn.readByte();
			}
			if((new File("received.png")).exists()){
				(new File("received.png")).delete();
			}
			FileOutputStream fos = new FileOutputStream("received.png");
			fos.write(receivedData);
			fos.close();
			
			String results =  euromillionsMainServer.euromillionsMainTask(new File("received.png"));
			
			
			dataOut.writeUTF(results);


			if((new File("received.png")).exists()){
				(new File("received.png")).delete();
			}

			dataOut.close();
			dataIn.close();
			s1.close();
		}
	}

	


}