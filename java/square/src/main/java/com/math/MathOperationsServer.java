package main.java.com.math;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class MathOperationsServer {

	private static MathOperations.Processor<MathOperationsHandler> processor;
	private static MathOperationsHandler handler;
	
	public static void main(String[] args){
		int port = 9999;
		if(args.length==1){
			port = Integer.valueOf(args[0]);
		}
		
		new MathOperationsServer(port);
	}
	
	public MathOperationsServer(int port){
		handler = new MathOperationsHandler();
		processor = new MathOperations.Processor<MathOperationsHandler>(handler); 
		
		try {
			TServerTransport serverTransport = new TServerSocket(port);
			TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));

			System.out.println("Starting the math server...");
			server.serve();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
