import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class client {
	
	public static void main(String args[]) throws IOException {
		Socket socket = new Socket("192.168.43.150", 1234);

		InputStream in = socket.getInputStream();
		DataInputStream dataIn = new DataInputStream(in);

		OutputStream out = socket.getOutputStream();
		DataOutputStream dataOut = new DataOutputStream(out);

		
		File file = new File("euroticket.png");
        FileInputStream fin = new FileInputStream(file);
        byte sendData[] = new byte[(int)file.length()];
        fin.read(sendData);
        
        dataOut.writeInt(sendData.length);
        dataOut.write(sendData, 0, sendData.length);
        dataOut.flush();
        
        String serverResponse = dataIn.readUTF();
		System.out.println("The server replied: " + serverResponse);

       

		
		
		
		dataOut.close();
		dataIn.close();
		socket.close();
	}
}