package test;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Set;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class Server extends WebSocketServer{

	private static int TCP_PORT = 4444;

	private Set<WebSocket> conns;

	public Server() {
		super(new InetSocketAddress(TCP_PORT));
		conns = new HashSet<>();
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		conns.add(conn);
		conn.send("<p>Hey! I was sent from the server</p>");
		log("New connection from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		conns.remove(conn);
		log("Closed connection to " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		log("Message from client: " + message);

	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		ex.printStackTrace();
		if (conn != null) {
			conns.remove(conn);
			// do some thing if required
		}
		log("ERROR from " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
	}

	@Override
	public void onStart() {
		log("Started on port " + TCP_PORT);
	}

	private void log(String msg) {
		System.out.println(msg);
	}

	public void sendToEveryone(String msg) {
		for (WebSocket sock : conns) {
			sock.send(msg);
		}
	}

}
