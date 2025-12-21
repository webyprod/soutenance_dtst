
data "aws_availability_zones" "azscurrent" {
  state = "available"
}


module "vpc" {
  source = "./modules/vpc"

  vpc_cidr            = var.vpc_cidr
  public_subnet_cidr  = var.public_subnet_cidr
  private_subnet_cidr = var.private_subnet_cidr
}

module "eks" {
  source = "./modules/eks"

  eks_cluster_name = var.eks_cluster_name
  cluster_version  = var.cluster_version
  vpc_id           = module.vpc.vpc_id
  subnet_id        = module.vpc.private_subnet_ids
  node_groups      = var.node_groups
}

module "rds" {
  source = "./modules/rds"

  project               = var.project
  vpc_id             = module.vpc.vpc_id
  private_subnet_ids     = module.vpc.private_subnet_ids
  
  allowed_sg_ids = [
    module.eks.node_security_group_id
  ]

  db_name     = "var.db_name"
  db_username = var.db_username
  db_password = var.db_password
  instance_class    = var.instance_class
  allocated_storage = var.allocated_storage
}

