variable "project" {
  type = string
}
variable "instance_class" {
  type        = string
  description = "Instance type for the RDS (e.g. db.t3.micro)"
}

variable "allocated_storage" {
  type        = number
  description = "Storage size in GB for the RDS instance"
}

variable "private_subnet_ids" {
  type = list(string)
}

variable "db_username" {
  type      = string
  sensitive = true
}

variable "db_password" {
  type      = string
  sensitive = true
}

variable "db_name" {
  type = string
}

variable "allowed_sg_ids" {
  type = list(string)
}

variable "vpc_id" {}
