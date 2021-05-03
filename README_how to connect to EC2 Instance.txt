1.Download the key value pair when you launch the instance for the first time 
2.Use puttygen to generate the ppk file from PEM file 
3.Open putty , in the hostname give public DNS, example : ec2-13-58-79-83.us-east-2.compute.amazonaws.com
4.Under connection-->SSH-->Auth, browse the PPK file, press ok
5.Give the username as ec2-user for linux or Ubuntu for Ubuntu environment, you will be logged into Amazon EC2 instance
 
 
Step by Step in Ubuntu Environment: 
 
1.Install java : 
     sudo apt-get install openjdk-8-jdk

2.Install Tomcat7: 
      Download the zip from tomcat.tar.gz and upload into EC2 using WinScp
	  Extract the zip -> tar -xvf apache-tomcat-7.0.81.tar.gz
      Run the tomcat using ./startup.sh 
	  
3.Add 'Custom TCP rule' in the dropdown of EC2 security group to access tomcat from webpage(https://stackoverflow.com/questions/26338301/ec2-how-to-add-port-8080-in-security-group)

4.Access the tomcat from http://ec2-52-14-136-35.us-east-2.compute.amazonaws.com:8080 

Option 2: 
You can also Intall Tomcat7:
sudo apt-get install tomcat7

Restarting Tomcat7: 
sudo service tomcat7 restart

Delete Tomcat7: 
sudo apt-get remove tomcat7
and remove the directory in /usr/share

Logs and webapps folder : 
	
bin folder: 
cd /usr/share

Find Everyting about Tomcat7: 
sudo find / -name "tomcat7"


Deploying the webapplication in Amazon EC2: 
https://www.youtube.com/watch?v=XeDMyRJdlAo
https://stackoverflow.com/questions/26338301/ec2-how-to-add-port-8080-in-security-group
 
 
 --------------------------------------------------------------------------
Step by Step in Linux Environment: 
https://stackoverflow.com/questions/32592956/apt-get-command-not-found
 
yum install java-1.7.0-openjdk java-1.7.0-openjdk-devel


Working with Tomcat7 
https://www.digitalocean.com/community/tutorials/how-to-install-apache-tomcat-7-on-centos-7-via-yum


