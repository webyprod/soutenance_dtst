resource "aws_iam_user" "terraform_user" {
  name = "terraform_user"
}

resource "aws_iam_user_policy" "terraform_user_policy" {
  name   = "TerraformUserPolicy"
  user   = aws_iam_user.terraform_user.name
  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect = "Allow"
        Action = [
          "iam:CreateRole",
          "iam:AttachRolePolicy",
          "iam:GetRole",
          "iam:PassRole"
        ]
        Resource = "*"
      },
      {
        Effect = "Allow"
        Action = [
          "ecr:*",
          "eks:*",
          "ec2:*",
          "rds:*",
          "elasticloadbalancing:*",
          "s3:*",
          "cloudwatch:*"
        ]
        Resource = "*"
      }
    ]
  })
}

