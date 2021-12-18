terraform {
  required_version = ">=0.12"
}

resource "aws_instance" "ec2_example" {

  ami = var.instance-id
  instance_type = var.instance-type
  key_name= "aws_key"
  vpc_security_group_ids = [aws_security_group.main.id]

  user_data = <<-EOF
      #!/bin/sh
      sudo apt-get update
      sudo apt install -y apache2
      sudo systemctl status apache2
      sudo systemctl start apache2
      sudo chown -R $USER:$USER /var/www/html
      sudo echo "<html><body><h1>Hello this is module-1 at instance id `curl http://169.254.169.254/latest/meta-data/instance-id` </h1></body></html>" > /var/www/html/index.html
      EOF
}

resource "aws_security_group" "main" {
  name        = "EC2-webserver-SG-1"
  description = "Webserver for EC2 Instances"

  ingress {
    from_port   = 80
    protocol    = "TCP"
    to_port     = 80
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 22
    protocol    = "TCP"
    to_port     = 22
    cidr_blocks = ["115.97.103.44/32"]
  }

  egress {
    from_port   = 0
    protocol    = "-1"
    to_port     = 0
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_key_pair" "deployer" {
  key_name   = "aws_key"
  public_key = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABgQDfOp/BrH91OUJEI2mWflgNxTKIsKXXhbCmY6Haaadx4v/kcNv8Tj5dQgwTR/p9CKtqCL9VQXJWICpGD3FjetGwUKX3UcdQE8pxzok+ED+wHpjj9isTj2W7xSlknhUyNucyZ2Hm06sra2WVa8MxtCUCMEdyMxhchj9g8ngBdZKD3CXIxrnpuGAq5s6HO/oafC8R1wgPpfFck2DiAuq21PrJPfckjvCIPJbC8yZ7zcRQZeccJ4FrkPokLDQ4zWw2hiyKnkcNc6BGr5yMUggo8Xn3H4V39woaJJSYW+tBq2Trf0WRnF96pcmsaCsGM0huI1nDMD6lp6al7PMmR/rIaZANAZEV1FR99Qc6LxXamj3+Qgdew41SNRCatNGddD48jaAsAMxNNhLl9VT0kNocx7iA357iYX8NRxQZp/jjo8mJwQkwi4XxxRA4Pyt1uaQYxclujUu3fKN7m0c7SEfZjKwtT3IMOzj55h8pq8yXKXR6boipMoGJfiZd/E1iYXRDq+U7"
}
