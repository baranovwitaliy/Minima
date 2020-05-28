package org.minima.system.network.websocket;

import org.minima.utils.MinimaLogger;
import org.minima.utils.nanohttpd.protocols.http.IHTTPSession;
import org.minima.utils.nanohttpd.protocols.websockets.NanoWSD;
import org.minima.utils.nanohttpd.protocols.websockets.WebSocket;

public class WebSocketServer extends NanoWSD {

	WebSocketManager mWSManager;
	
	public WebSocketServer(int zPort, WebSocketManager zWSManager) {
		super(zPort);
		
		//Keep this..
		mWSManager = zWSManager;
	}

	@Override
	protected WebSocket openWebSocket(IHTTPSession zHTTPSession) {
		MinimaLogger.log("New WebSocket "+zHTTPSession.getHeaders());
		
		return new MinimaWebSocket(zHTTPSession, mWSManager);
	}
}
