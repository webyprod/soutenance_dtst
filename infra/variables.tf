variable "aws_region" {
  type        = string
  description = "AWS region"
}

variable "vpc_cidr" {
  type    = string
  description = "valeur vpc"
}

variable "public_subnet_cidr" {
  type = list(string)
}

variable "private_subnet_cidr" {
  type = list(string)
}

variable "eks_cluster_name" {
  type = string
}

variable "cluster_version" {
  type = string
}

variable "node_groups" {
  type = map(object({
    instance_types = list(string)
    capacity_type  = string
    scaling_config = object({
      desired_size = number
      max_size     = number
      min_size     = number
    })
  }))
}

variable "project" {
  type = string
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

variable "aws_access_key" {
  type = string
  sensitive = true
}

variable "aws_secret_key" {
  type = string
  sensitive = true
}
variable "instance_class" {
  type        = string
  description = "Instance type for the RDS (e.g. db.t3.micro)"
}

variable "allocated_storage" {
  type        = number
  description = "Storage size in GB for the RDS instance"
}

