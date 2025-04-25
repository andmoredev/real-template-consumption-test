# COPYRIGHT - CUBIC (“CUBIC“). ALL RIGHTS RESERVED.
#
# Information Contained Herein is Proprietary and Confidential.
# The document is the property of CUBIC and may not be disclosed,
# distributed, or reproduced without the express written
# permission of CUBIC.


# -----------------------
# Default AWS provider
# -----------------------
provider "aws" {
  region = "us-west-2"
  default_tags {
    tags = {
      workload_name     = "example"
      operational_band  = var.band
      company           = "cts-platforms"
      deployment_method = "terraform"
      tf_file_path      = join("/", slice(split("/", path.cwd), index(split("/", path.cwd), "example-service"), length(split("/", path.cwd))))
    }
  }
}

terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
  required_version = ">= 1.10.0"
}


#-----------------------------------------
# S3 bucket for the Terraform state files
#-----------------------------------------
resource "aws_s3_bucket" "tf_state" {
  bucket = "state.tf.example.${var.band}.edge.cubic-platforms.io"

  tags = {
    Name = "S3 Remote Terraform State Store"
  }
}

resource "aws_s3_bucket_versioning" "tf_state" {
  bucket = aws_s3_bucket.tf_state.id

  versioning_configuration {
    status = "Enabled"
  }
}


#------------------------------------------------
# DynamoDB table to lock the Terraform state files
#------------------------------------------------
resource "aws_dynamodb_table" "terraform-lock" {
  name           = "state.tf.example.${var.band}.edge.cubic-platforms.io"
  read_capacity  = 5
  write_capacity = 5
  hash_key       = "LockID"
  attribute {
    name = "LockID"
    type = "S"
  }
  deletion_protection_enabled = true

  tags = {
    Name = "DynamoDB Terraform State Lock Table"
  }
}