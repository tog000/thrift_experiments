Thrift like the Gods intended
==================

Thrift examples without skipping steps.

Step 1: Download and install Thrift
---

```
$ git clone https://git-wip-us.apache.org/repos/asf/thrift.git thrift
$ cd thrift
$ ./bootstrap.sh
$ ./configure
$ make
$ sudo make install
```

Step 2: Scaffolding for Java Server/Client
---

```bash
$ mkdir java
$ cd java
$ mvn archetype:generate -DgroupId=com.math -DartifactId=square -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
$ rm -r square/src/test/
$ rm square/src/main/java/com/math/App.java
```
Create the java source files
```bash
$ thrift --gen java -out java/square/src/main/java/com/math/ square.thrift
```
Add dependencies to Maven (libthrift, slf4j) 
```xml
<dependency>
	<groupId>org.apache.thrift</groupId>
	<artifactId>libthrift</artifactId>
	<version>0.9.1</version>
</dependency>
<dependency>
	<groupId>org.slf4j</groupId>
	<artifactId>slf4j-api</artifactId>
	<version>1.7.7</version>
</dependency>
```
Add JAR generator to Maven:
```xml
<build>
	<sourceDirectory>src</sourceDirectory>
	<plugins>
		<plugin>
			<artifactId>maven-compiler-plugin</artifactId>
			<version>3.1</version>
			<configuration>
				<source>1.6</source>
				<target>1.6</target>
			</configuration>
		</plugin>
		<plugin>
			<artifactId>maven-assembly-plugin</artifactId>
			<version>2.4</version>
			<configuration>
				<descriptorRefs>
					<descriptorRef>jar-with-dependencies</descriptorRef>
				</descriptorRefs>
			</configuration>
			<executions>
				<execution>
					<id>make-assembly</id>
					<phase>package</phase>
					<goals>
						<goal>single</goal>
					</goals>
				</execution>
			</executions>
		</plugin>
	</plugins>
</build>
```

Build java files :
```bash
$ cd java/superchat
$ mvn package
```

Step 3: Scaffolding for Python Client
---

```bash
$ mkdir python
$ thrift --gen py -out python/ square.thrift
```

Remember to edit file `python/square/MathOperationsClient.py` to point to your local Thrift copy (or global, if you installed Thrift using `setup.py`)

Step 4: Scaffolding for Javascript Client
---

```bash
$ mkdir js
$ thrift --gen js -out js/ square.thrift
```

The file `js/lib/thrift.js` was copied from `$THRIFT_HOME/lib/js/src/thrift.js`

Step 5: Testing
---

```bash
# Start Sockets Server
java -cp target/square-1.0-SNAPSHOT-jar-with-dependencies.jar main.java.com.math.MathOperationsServer 1111

#Start HTTP Server
java -cp target/square-1.0-SNAPSHOT-jar-with-dependencies.jar main.java.com.math.Httpd 8088 . /thrift/mathoperations


# Test With Clients
java -cp target/square-1.0-SNAPSHOT-jar-with-dependencies.jar main.java.com.math.MathOperationsClient 1111 16

python MathOperationsClient.py 1111 16

http://localhost/
```




