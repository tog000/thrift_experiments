package main.java.com.math;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class MathOperationsClient {
	
	static TTransport transport;
	
	public static void main(String args[]){
		
		if(args.length!=2){
			System.out.println("Usage: MathOperationsClient <server port> <number>");
			System.exit(1);
		}
		
		transport = new TSocket("localhost", Integer.valueOf(args[0]));
		
		try{
			transport.open();
			
			TProtocol protocol = new  TBinaryProtocol(transport);
			MathOperations.Client client = new MathOperations.Client(protocol);
			
			System.out.println("Result:"+client.square(Integer.valueOf(args[1])));
		
		} catch (TException x) {
			x.printStackTrace();
	    } 
	}
}
