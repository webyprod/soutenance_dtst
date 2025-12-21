
terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 6.0"
    }
  }

  backend "s3" {
    bucket  = "bucket-projet-spring-boot"
    key     = "spring-boot/state/terraform.tfstate"
    region  = "eu-west-3"
    encrypt = true
  }
}


