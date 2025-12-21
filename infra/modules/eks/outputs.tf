output "cluster_endpoint" {
  description = "EKS cluster endpoint"
  value       = aws_eks_cluster.this.endpoint
}

output "node_security_group_id" {
  value = aws_security_group.eks.id
}

