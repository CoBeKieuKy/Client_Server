package client_and_server;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	static int TEN_MB = 10240;
	static int PORT = 2019;
	static final String HOST = "localhost";

	public static void main(String[] args) throws IOException, InterruptedException {
		long start, end;
		int bytesReaded;
		byte[] buffer_data = new byte[TEN_MB];

		Socket server_socket;
		InputStream input;
		FileOutputStream output_video;
		ServerSocket server_listener = new ServerSocket(PORT);
	
		System.out.println("Server is waiting for data from client...");
		
		server_socket = server_listener.accept();
		input = server_socket.getInputStream();
		output_video = new FileOutputStream("final.mp4");
		
		System.out.println("Incomming client accepted, connection established: ");
						
		System.out.println("Starting transfering video file.");
		
		start = System.currentTimeMillis();
		
		System.out.println("Transfering...");
		while((bytesReaded=input.read(buffer_data))!=-1) {
			output_video.write(buffer_data,0,bytesReaded);
		}
		
		end = System.currentTimeMillis();
		
		System.out.println("Transfering file completed after: "+(end-start)+"(ms)");
		
		input.close();
		output_video.close();
		server_socket.close();
		server_listener.close();
	}
}
