import java.io.*;
import java.net.*;

public class MultiUser extends Thread {
	private Socket client;
	
	public MultiUser(Socket c) {
		this.client = c;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream());
			String str = in.readLine();
			String str2 = "";
			for(int i = str.length() - 1; i > -1; i--) {
				str2 += String.valueOf(str.charAt(i));
			}
			out.println(str2);
			out.flush();
			}
			client.close();
		} catch(IOException e) {
		} finally {
		}
	}

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(3333);
		while(true) {
			MultiUser mu = new MultiUser(server.accept());
			mu.start();
		}
	}
	
}