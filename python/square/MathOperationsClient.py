import sys, glob
sys.path.append('.')
sys.path.insert(0, glob.glob('/home/tog/programs/thrift/lib/py/build/lib.*')[0])

import MathOperations
from ttypes import *

from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol


if len(sys.argv) != 3:
	print("Usage: MathOperationsClient <server_port> <number>")
	sys.exit(1);

transport = TSocket.TSocket('localhost', int(sys.argv[1]))

protocol = TBinaryProtocol.TBinaryProtocol(transport)

client = MathOperations.Client(protocol)

transport.open()

print("Result: {}".format(client.square(int(sys.argv[2]))));
