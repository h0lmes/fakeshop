pipeline {
	agent {label 'fakeshop'}

	environment {
		ORGANIZATION_NAME = "h0lmes"
		SERVICE_NAME = "fakeshop"
		BUILD_ID = "dontKillMe"
		JENKINS_NODE_COOKIE = "dontKillMe"
	}

	stages {
		stage('Prepare') {
			steps {
				cleanWs()
				git credentialsId: 'GitHub', url: "https://github.com/${ORGANIZATION_NAME}/${SERVICE_NAME}"
			}
		}
		stage('Build') {
			steps {
				sh '''mvn clean package'''
			}
		}

		stage('Build Image') {
			steps {
				sh 'docker image build -t ${SERVICE_NAME} .'
			}
		}

		stage('Delete Previous Deployment') {
			steps {
				catchError {
					sh 'minikube kubectl -- delete pod ${SERVICE_NAME}'
					sh 'minikube kubectl -- delete deployment ${SERVICE_NAME}'
					sh 'minikube kubectl -- delete service ${SERVICE_NAME}'
			    }
			}
		}
		
		stage('Deploy') {
			steps {
				sh 'minikube image load ${SERVICE_NAME}'
				sh 'minikube kubectl run -- ${SERVICE_NAME} --image=${SERVICE_NAME} --port=8080 --image-pull-policy=Never'
				sh 'minikube kubectl -- create deployment ${SERVICE_NAME} --image=${SERVICE_NAME}' 
				sh 'minikube kubectl -- expose deployment ${SERVICE_NAME} --type=NodePort --port=8080'
				sh 'minikube kubectl -- get svc'
				sh 'minikube service ${SERVICE_NAME}'
				sh 'minikube kubectl -- get pods -A'
			}
		}
		
		stage('Forward') {
			steps {
				catchError {
					sh 'nohup minikube kubectl -- port-forward --address 0.0.0.0 ${SERVICE_NAME} 8080:8080 &'
				}
			}
		}
	}
}
