variable "region" {
  default = "us-east-1"
}

variable "cluster_name" {
  default = "my-eks-cluster"
}

variable "subnets" {
  type    = list(string)
  default = ["subnet-xxxx", "subnet-yyyy"] # Substitua pelos seus IDs
}

variable "vpc_id" {
  type    = string
  default = "vpc-zzzz" # Substitua pelo seu VPC ID
}
