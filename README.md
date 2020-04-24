# CS445-SAR-Project, Faisal Patel

## *Project Setup*

### 0. Objective
Run the final project for CS445 IIT Spring 2020, Share A Ride:

[RESTful API Definition](http://cs.iit.edu/~virgil/cs445/mail.spring2020/project/project-api.html)

The code can be downloaded from the zip file, or from the [repository](https://github.com/faipat1/CS445-SAR-Project).
See specific setup instructions below

### 1. Pre-requisite
This setup contains instructions for Ubuntu 18.04. The same instructions can be followed on other operating systems but specific commands will have to be altered.

### 2. Install Java 8
```
$ sudo apt install openjdk-8-jdk 
```
Verify java version
```
$ java -version
```

### 3. Install and Configure Eclipse
Download the eclipse installer from [the Eclipse website](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2020-03/R/eclipse-inst-linux64.tar.gz)
Uncompress and unarchive the installer
```
$ gunzip eclipse-inst-linux64.tar.gz
$ tar xvf eclipse-inst-linux64.tar
```
- Navigate to ~/Downloads/eclipse-installer and then double click on the installer (eclipse-inst) to run it. Select "Eclipse IDE for Java Developers" and complete the installation.
- Launch Eclipse
- Go to "Help > Install New Software"
- Select “Work with: 2020-03 - http://download.eclipse.org/releases/2020-03”
- Expand the “Web, XML, Java EE Development and OSGi Enterprise Development” menu item and then select the following:
  - Eclipse Java EE Developer Tools
  - Eclipse Java Web Developer Tools
  - Eclipse Web Developer Tools
  - JST Server Adapters
  - JST Server Adapters Extensions
- Click "Next"
- Accept the licensing terms and “Finish”. Allow some time for installation to complete.

### 4. Install Tomcat 8
```
$ cd ~/Downloads
$ wget http://apache.mirrors.hoobly.com/tomcat/tomcat-8/v8.5.53/bin/apache-tomcat-8.5.53.tar.gz
$ gunzip apache-tomcat-8.5.53.tar.gz
$ tar xvf apache-tomcat-8.5.53.tar
$ sudo mv apache-tomcat-8.5.53 /opt/tomcat
```
Start the server to verify installation
```
$ cd /opt/tomcat/bin
$ ./startup.sh
```
Go to http://localhost:8080 and make sure the Tomcat banner is displayed.
Shutdown the server
```
$ ./shutdown.sh
```

### 5. Import Project into Eclipse Workspace
Download the source code by either downloading the zip file **or** by checking out from the repository.

**To get from the zip file:**
Download the zip file into your downloads folder.
```
$ cd ~/Downloads
$ gunzip CS445Project.tar.gz
$ tar xvf CS445Project.tar
$ mv CS445Project <path_to_your_eclipse_workspace>
```

**To checkout from repository:**
```
$ git clone https://github.com/faipat1/CS445-SAR-Project
$ mv CS445Project <path_to_your_eclipse_workspace>
```

Build the project
```
$ cd <path_to_your_eclipse_workspace>/CS445Project
$ ./gradlew CS445Project
```
Now import the project into your eclipse workspace
- Start eclipse
- Go to "File > Import" and select "General > Existing Projects into Workspace"
- Select the CS445Project.

### 6. Setup a Runtime Environment in Eclipse
- Go to "Window > Preferences"  and find "Runtime Environments".
- Click on "Add" to create a new Runtime, then select "Apache > Apache Tomcat v8.5". Click on "Next".
- Type /opt/tomcat in the "Tomcat installation path" text box. Click "Finish".
- Click "Apply and Close".
- Right click on the project name and select "Properties" then select "Targeted Runtimes". Check the box for "Apache Tomcat v8.5" then "Apply and Close".


### 7. Create a Server Where the Code Will be Deployed
- Go to "Window > Show View > Other" expand "Server" and click on "Servers"; this will open a new tab named "Servers" to your workbench. Right click in that window and select "New > Server". Select "Apache Tomcat" and then press the "New" icon.
- Keep the host name as "localhost", select the "Apache Tomcat v8.5 Server" as the type, name the server whatever you like, and make sure the Server runtime you installed is selected in the drop down list. Click "Next", select CS445Project and "Add" it to the Configured pane.
- Click "Finish" and there should now be a server entry with the name you specified in the "Servers" window.
To configure the REST Services to be accessible at http://localhost:8080/sar instead of http://localhost:8080/CS445Project/sar:
- Expand the server you created: you should see a number of files associated with the server, such as catalina.policy, catalina.properties, etc.
- Right click on server.xml and open it with a text editor. Scroll down to the line that begins with "<Context" and edit the value of path from "/CS445Project" to "/". Save the file.

### 8. Run the project
If everything was setup properly, you can now right click on the project and select "Run > Run on Server". The REST Services will be accessible at http://localhost:8080/sar





