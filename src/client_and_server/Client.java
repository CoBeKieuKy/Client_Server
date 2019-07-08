package client_and_server;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		int bytesReaded;
		long start, end;
		byte[] buffer_data = new byte[Server.TEN_MB];

		Socket socket;
		OutputStream output;
		FileInputStream input_video;

		System.out.println("Starting uploading video file.");

		socket = new Socket(Server.HOST, Server.PORT);
		output = socket.getOutputStream();
		input_video = new FileInputStream("video/sc2.mp4");
		
		start = System.currentTimeMillis();
		
		System.out.println("Uploading...");
		while((bytesReaded=input_video.read(buffer_data))!= -1) {
			output.write(buffer_data,0,bytesReaded);
		}
		
		end = System.currentTimeMillis();
		
		System.out.println("Uploading to Server completed after: "+(end-start)+"(ms)");
		
		socket.close();
		output.close();
		input_video.close();
	}

}
