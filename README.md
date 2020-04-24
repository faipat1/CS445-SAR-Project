# CS445-SAR-Project, Faisal Patel

## *Project Setup*

### 0. Objective
Setup the final project for CS445 IIT Spring 2020, Share A Ride:

[RESTful API Definition](http://cs.iit.edu/~virgil/cs445/mail.spring2020/project/project-api.html)

The code can be downloaded from the zip file, or from the [repository](https://github.com/faipat1/CS445-SAR-Project).
See specific setup instructions below

### 1. Pre-requisite
This setup contains instructions for Ubuntu 18.04. The same instructions can be followed on other operating systems but specific commands will have to be alterered.

### 2. Install Java 8
```
$ sudo apt install openjdk-8-jdk 
```
Verify java version
```
$ java -version
```

### Install and Configure Eclipse
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

### Install Tomcat 8
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

### Import Project into Eclipse Workspace
