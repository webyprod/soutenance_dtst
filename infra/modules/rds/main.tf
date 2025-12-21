# -------------------------------
# RDS Subnet Group (PRIVATE)
# -------------------------------
resource "aws_db_subnet_group" "this" {
  name       = "${var.project}-db-subnet-group"
  subnet_ids = var.private_subnet_ids

  tags = {
    Name    = "${var.project}-db-subnet-group"
    Project = var.project
  }
}

# -------------------------------
# RDS PostgreSQL Instance
# -------------------------------
resource "aws_db_instance" "postgres" {
  identifier = "${var.project}-postgres"

  engine         = "postgres"
  engine_version = "14"

  instance_class    = var.instance_class
  allocated_storage = var.allocated_storage

  db_name  = var.db_name
  username = var.db_username
  password = var.db_password

  db_subnet_group_name   = aws_db_subnet_group.this.name
  vpc_security_group_ids = [aws_security_group.rds.id]

  publicly_accessible = false
  storage_encrypted   = true

  backup_retention_period = 7
  multi_az                = false
  skip_final_snapshot     = true

  tags = {
    Name    = "${var.project}-postgres"
    Project = var.project
  }
}


