import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class server {
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
			if((new File("received.jpg")).exists()){
				(new File("received.jpg")).delete();
			}
			FileOutputStream fos = new FileOutputStream("received.jpg");
			fos.write(receivedData);
			fos.close();
			System.out.println("Image received");
			
			new DisplayImage();
			dataOut.writeUTF("Byebye!");




			dataOut.close();
			dataIn.close();
			s1.close();

		}
	}
}