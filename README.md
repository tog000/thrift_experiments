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

Step 2: Scaffolding for Java
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
```
$ cd java/superchat
$ mvn package
```