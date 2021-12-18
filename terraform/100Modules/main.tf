provider "aws" {
  access_key = "AKIASBKB6MCT337O54MJ"
  secret_key = "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"
  region = "us-west-2"
}

module "module-1" {
  source = "./module-1"
}

/*
module "module-2" {
  source = "module-2"
}*/
