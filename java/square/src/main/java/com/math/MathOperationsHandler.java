package main.java.com.math;

import org.apache.thrift.TException;

public class MathOperationsHandler implements MathOperations.Iface{

	@Override
	public int square(int number) throws TException {
		return number*number;
	}

}
