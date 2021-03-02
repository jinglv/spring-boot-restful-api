pipeline {
    agent {
        label 'master'
    }

    environment {
        cred_id = 'b4d58207-0fa3-43f5-bf1d-c635025a7684'
        docker_image_name = 'restful_api'
        docker_container_name = 'irestful_api'
    }

    parameters {
        string(name: 'branch', defaultValue: 'main', description: 'Git branch')
        string(name: 'pomPath', defaultValue: 'pom.xml', description: 'pom.xml的相对路径')
    }

    stages {
        stage('检出代码') {
            steps {
                git credentialsId: cred_id, url: 'https://gitee.com/jeanlv/spring-boot-restful-api.git', branch: '${branch}'
            }
        }

        stage('Maven编译打包并单元测试') {
            steps {
                sh '''
                    . ~/.bash_profile 
                    
                    cd ${WORKSPACE}
                    mvn org.jacoco:jacoco-maven-plugin:prepare-agent -f ${pomPath} clean install -Dautoconfig.skip=true -Dmaven.test.skip=false
                '''
            }
        }

        stage('SonarQube代码质量扫描') {
            steps {
                sh '''
                    . ~/.bash_profile 
                    
                    cd ${WORKSPACE}
                    mvn sonar:sonar -Dsonar.projectKey=spring-boot-restful-api -Dsonar.host.url=http://60.205.228.49:9000/ -Dsonar.login=053ed1077e82a2bb36eebf619d24d75b8c5738b9 -Dsonar.branch.name=${branch}
                    jacoco changeBuildStatus: true, maximumLineCoverage:"20"
                '''
            }
        }

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

    post {
        always {
            script {
                println "Do some actions when always need."
            }
        }
        failure {
            script {
                println "Do some actions when build failed."
            }
        }
        success {
            script {
                println "Here we kickoff run job SpringBoot-Restful-Api-Test-Pipeline"
                job_run_result = build job: 'SpringBoot-Restful-Api-Test-Pipeline', propagate: false, wait: true
                println job_run_result.getResult()
            }
        }
    }
}