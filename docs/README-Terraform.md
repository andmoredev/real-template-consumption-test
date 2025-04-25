# Working with the Terraform Backend and Core resources

## Table of Contents
1. [Terraform Backend Resources](#terraform-backend-resources)
1. [Terraform Deployment Core Resources](#terraform-deployment-core-resources)

## Terraform Backend Resources
There are two resources needed for Terraform to hold the current state of the resources and to block multiple deployments of the same resources to happen at the same time.

1. S3 Bucket
This is the location where the state files for this module will be uploaded to. Terraform will use these state files to compare against the current deployment and know exactly what things need to be created, updated or deleted.

1. DynamoDB Table
This table is used as a locking mechanism to not allow concurrent deployments that could result in corrupted resources or state.

### Deploying the Terraform backend for the first time
1. Navigate to the `terraform/backend` directory
1. Run `terraform init`
1. Run `terraform apply`  
    1. Enter `band` value. It should be *play*, *work* or *live*

## Updating backend resources
This shouldn't happen very often.

1. **Point Terraform backend to existing state files** - The backend Terraform state is stored within the GitHub repository in the path “tfstate/[band]/terraform.tfstate”. We’ll need to tell Terraform to look at this directory to be able to view the current state of the resources and update them accordingly.
In the main.tf file in the terraform/backend directory add the backend section as shown below.  
```
terraform {
 backend "local" {
   path = "./tfstate/[band]/terraform.tfstate"
 }
 required_providers {
   aws = {
     source  = "hashicorp/aws"
     version = "~> 5.0"
   }
 }
 required_version = ">= 1.10.0"
}
```

2. **Run Terraform commands**

Now we need to run the commands to update the resources in the cloud.
1. Run `terraform init`
1. Run `terraform apply`

This should update the state file for the band you have updated.

3. **Update Terraform State** - 
The last step is to commit the updated tfstate file. Make sure to not commit the changes made in step 1.

## Terraform Deployment Core Resources
This will deploy the IAM role for GitHub to assume for it to deploy the AWS resources.

1. `terraform init -backend-config=./config/backend.[band].tfvars`

1. `terraform apply -var-file=./[band].variables.tfvars`
