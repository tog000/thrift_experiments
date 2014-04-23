package main.java.com.math;

import org.apache.thrift.TException;

public class MathOperationsHandler implements MathOperations.Iface{

	@Override
	public int square(int number) throws TException {
		System.out.println("Calculating the square of "+number+"...");
		return number*number;
	}

}
