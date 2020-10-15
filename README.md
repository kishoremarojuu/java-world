# spring_datajpa_rds
spring data jpa with RDS(h2, mysql remote, aws rds)

This project will guide you to connect to mysql database
for this example I have hosted mysql database in aws console

How to setup a RDS DB in aws console ?
select my sql free tier db
make sure in advance configurations, you select  db name
make sure it is viewable to public
for that, open the security groups to all traffic
once DB setup is done, you can test from intelliJ DB database setup
run the app.
result: this will create a new table called user, you can try posting some sample data using post method
and retrieve the records using the get method

To post the newrequest, use postman
http://localhost:8085/users/load

{
    "name":"ashoka",
    "teamName":"teamIndia",
    "salary":8000
 }

local working without docker:
run the SpringDatajpaRdsApplication and load sample data with above request and
use http://localhost:8085/users/all to fetch the data from get URL

local working with docker:
1. create a file called Dockerfile in local
Approach1: Installing docker in windows 10 home
if you are using windows 10 home, follow the below steps to install docker
we will install docker toolbox, that will run on a virutal machine and we interact with virutal machine
2. Install docker in your local https://docs.docker.com/toolbox/toolbox_install_windows/ and
https://www.youtube.com/watch?v=ymlWt1MqURY&t=49s and Make sure 'virtualization is enabled' in local by going to taskmanager/performance
4. You need to set some paths in env variables
DOCKER_MACHINE=C:\Program Files\Docker Toolbox\docker-machine.exe
DOCKER_TOOLBOX_INSTALL_PATH "C:\Program Files\Docker Toolbox
VBOX_MSI_INSTALL_PATH=C:\Program Files\Oracle\VirtualBox
VBOX_INSTALL_PATH VBOX_INSTALL_PATH=C:\Program Files\Oracle\VirtualBox
Some known Issues: https://stackoverflow.com/questions/39373217/docker-looks-something-went-wrong-in-step-looking-for-vboxmanage-exe
5. Once done open dockerquick start terminal when you open this
docker is configured to use the default machine with IP *******192.168.99.100**** make note of this IP. as this is the IP you would use for instead
of localhost in windows as it started on virtual machine inside windows
6. create account in https://hub.docker.com/ if you don't have one
commands : docker login
Note: this will install virtual machine

6.Approach2: installing docker in windows 10 pro
installing docker in windows 10 pro is pretty simple, 
Simply download installer.exe https://hub.docker.com/editions/community/docker-ce-desktop-windows/
open DockerDesktop , that will start dockerDesktop in the background 
once the docker is up, run the docker -version to test it.

7. Docker commands from now on
docker build -f Dockerfile -t spring_datajpa_rds .
or
docker build -f Dockerfile -t kishoremarojuu/spring_datajpa_rds .

8.run the app:
docker run -p 8052:8084 spring_datajpa_rds
or
to run the app in background
docker run -d -p 8052:8084 spring_datajpa_rds

9.To see list of containers
docker container ls

10. Now access the URL in browser
http://localhost:8085/users/all
http://192.168.99.100:8085/users/all  --> as this will be running in virtual machine, make sure of this

11. Some imp docker comamnds
docker container ls
docker image rm -f "imagename"   -->to remove image
docker container rm "containerName"  -->to remove container
docker port "containerID"  -->to know on what port the container is listening
docker exec -it "containerId" bash --> to login into the running container
curl http://localhost:8085/users/all --> for testing from cmd

13.To push the image into dockerHub
docker push kishoremarojuu/spring_datajpa_rds:latest

14. To pull the image from dockerHub
docker pull kishoremarojuu/spring-boot-rds-aws

15. How to run the app in ec2 ?
provision the low level ubuntu ec2 instance
download the keypair if you don't have one
ssh into the ec2 instance
be a root user by "sudo -i"
udpate all packages:  sudo yum update -y

16. install docker
sudo yum install docker -y
or
sudo apt-get install docker -y
or
apt install docker.io

17. start the docker :
sudo docker service start

18. Login into the docker hub
docker login

19. pull the image from dockerHub:
docker pull kishoremarojuu/spring_datajpa_rds:latest

20. run the image
docker run -d -p 80:8085 kishoremarojuu/spring_datajpa_rds  --> this will run the app

21. take the instance public IP and test from the browser
http://ec2-34-239-126-27.compute-1.amazonaws.com:8084/users/all

22.Make sure security groups are open in inbound rules from your laptop
open ssh and http, https
how to find my pulic IP  in browser ?
grap the ipv4 address
I have attached following inbound rules to allow traffic from mylaptop and from ec2 as well
Type         Protocol   Port Range   Source
HTTP         TCP        80           76.187.83.126/32(myHPLaptop IP)
SSH          TCP        22           76.187.83.126/32
MYSQL/Aurora TCP        3306         76.187.83.126/32
MYSQL/Aurora TCP        3306         sg-4442b81e (default)(myEc2SG)



23. Additional step on how to connect to h2 embedded database
Make sure you include h2 jar
for simplicity add data.sql and schema.sql files, this will generate schemal and data for you.
