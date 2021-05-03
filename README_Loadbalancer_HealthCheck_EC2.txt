what is health check  ?

Load balancer will perform regular health checks on the ec2 instance to make sure it is reachable by pinging the target every userdefined input 



install httpd service in the ec2 instance 
yum install httpd -y
service httpd start 
chkconfig httpd on 
yum install wget -y 
yum install php php-mysql-mysql-y


make this script available in the userdata on the start itself

when you hit the public IP in the browser, you should seee test page

Step2: you can install the custom responnse in the html as well by going to /var/www/html/welcome.html , that page will come 

Step3: you can now attach this instance to your load balancer 
Provide the 'pingProtoco':TCP and 'pingport':80 , and the instances will become 'inservice' 