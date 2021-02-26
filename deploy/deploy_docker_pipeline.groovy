pipeline {
    agent {
        label 'master'
    }

    environment {
        cred_id = '64129bbc-8486-40e0-8e13-3bdc2172052a'
        docker_image_name = 'restful_api'
        docker_container_name = 'irestful_api'
    }

    parameters {
        string(name: 'branch', defaultValue: 'main', description: 'Git branch')
    }

    stages {
        stage('检出代码') {
            steps {
                git credentialsId: cred_id, url: 'https://github.com/jinglv/spring-boot-restful-api.git', branch: "$params.branch"
            }
        }

        stage('Maven编译打包并单元测试') {
            steps {
                sh '''
                    . ~/.bash_profile 
                    
                    cd ${WORKSPACE}
                    mvn clean install
                '''
            }
        }

        stage('SonarQube代码质量扫描') {
            steps {
                sh '''
                    . ~/.bash_profile 
                    
                    cd ${WORKSPACE}
                    mvn sonar:sonar -Dsonar.projectKey=spring-boot-restful-api -Dsonar.host.url=http://8.140.112.109:9000/ -Dsonar.login=ccfc60220c447803b94b827ea6a16ae245399ef1 -Dsonar.branch.name=main
                '''
            }
        }

//        stage('Maven编译打包') {
//            steps {
//                sh '''
//                    . ~/.bash_profile
//
//                    cd ${WORKSPACE}
//                    mvn clean install -Dmaven.test.skip=true
//                '''
//            }
//        }

        stage('停止 / 删除 现有Docker Container/Image ') {
            steps {
                script {
                    try {
                        sh 'docker stop $docker_container_name'
                    } catch (exc) {
                        echo 'The container $docker_container_name does not exist'
                    }

                    try {
                        sh 'docker rm $docker_container_name'
                    } catch (exc) {
                        echo 'The container $docker_container_name does not exist'
                    }

                    try {
                        sh 'docker rmi $docker_image_name'
                    } catch (exc) {
                        echo 'The docker image $docker_image_name does not exist'
                    }
                }
            }
        }

        stage('生成新的Docker Image') {
            steps {
                sh '''
                    cd ${WORKSPACE}/docker
                    rm -f spring-boot-restful-api-0.0.1-SNAPSHOT.jar
                    cp ${WORKSPACE}/target/spring-boot-restful-api-0.0.1-SNAPSHOT.jar .
                    docker build -t $docker_image_name .
                '''
            }
        }

        stage('启动新Docker实例') {
            steps {
                sh '''
                    docker run -d --name $docker_container_name -p 8988:8988 $docker_image_name
                '''
            }
        }
    }
}