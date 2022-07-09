#### 镜像删除: 
docker rmi b3364c6835be importnginx:111
 ```
 镜像可以通过 docker rmi 命令进行删除，参数为镜像的id或者镜像名，参数可以有多个，多个参数之间用空格隔开。
[root@localhost ~]# docker image ls
REPOSITORY      TAG                 IMAGE ID       CREATED        SIZE
importnginx     111                 759dd43956ff   2 days ago     180MB
importnginx     ilatest             b3364c6835be   2 days ago     180MB

[root@localhost ~]# docker image ls
REPOSITORY      TAG                 IMAGE ID       CREATED        SIZE
<none>          <none>              ee9a1d9e6e5c   2 days ago     180MB
gogs/gogs       latest              f9a96bbf6474   5 months ago   87.2MB
nginx           latest              605c77e624dd   5 months ago   141MB
redis           5                   c5da061a611a   6 months ago   110MB
mysql           5.7                 c20987f18b13   6 months ago   448MB
rabbitmq        3-management        6c3c2a225947   6 months ago   253MB
mongo           4.2.5               fddee5bccba3   2 years ago    388MB
logstash        7.6.2               fa5b3b1e9757   2 years ago    813MB
kibana          7.6.2               f70986bc5191   2 years ago    1.01GB
elasticsearch   7.6.2               f29a1ee41030   2 years ago    791MB
rabbitmq        3.7.15-management   f05c3eb3cf91   2 years ago    179MB
nginx           1.10                0346349a1a64   5 years ago    182MB
[root@localhost ~]# 

 ```
#### 容器批量删除:
docker rm $(docker ps -a -q)
 ```
 ```
#### 进入容器:
docker exec -it mysql bash
#### docker-compose启动:
docker-compose up -d
 ```
 该命令要在docker-compose.yml当前目录执行
 ```
#### docker-compose启动:
docker- compose - f docker-compose-test.yml up - d
 ```
该命令要在docker-compose-test.yml当前目录执行
现象：如果一个文件夹中有多个.yml文件，那么每次运行其中一个yml就会出现警告有相同项
目的其他孤立容器，这是因为Docker机制把文件夹名称作为默认项目名称，一个项目名称被多
个yml文件使用就会发生这个警告：
WARNING: Found orphan containers (ngrok, nps) for this project. If you removed or renamed this service in your compose file, you can run this command with the --remove-orphans flag to clean it up.
frps is up-to-date
解决方法1：-p自定义project项目名称：
docker- compose - p test - f docker-compose-test.yml up - d
 ```
#### 列出所有镜像:
docker image ls
 ```
 REPOSITORY      TAG                 IMAGE ID       CREATED          SIZE
<none>          <none>              ee9a1d9e6e5c   5 minutes ago    180MB
gogs/gogs       latest              f9a96bbf6474   5 months ago     87.2MB
nginx           latest              605c77e624dd   5 months ago     141MB
redis           5                   c5da061a611a   6 months ago     110MB
mysql           5.7                 c20987f18b13   6 months ago     448MB
rabbitmq        3-management        6c3c2a225947   6 months ago     253MB
mongo           4.2.5               fddee5bccba3   2 years ago      388MB
logstash        7.6.2               fa5b3b1e9757   2 years ago      813MB
kibana          7.6.2               f70986bc5191   2 years ago      1.01GB
elasticsearch   7.6.2               f29a1ee41030   2 years ago      791MB
rabbitmq        3.7.15-management   f05c3eb3cf91   2 years ago      179MB
nginx           1.10                0346349a1a64   5 years ago      182MB
 ```
#### 启动某个镜像:
docker run --name nginx -d -p 8080:80 nginx
 ```
 docker run --name 启动镜像名称  -d -p 本机映射端口:镜像映射端口 镜像名称
 ```
#### 导出容器:
docker export nginx > /mydata/nginx/nginx.tar
#### 导入容器:
cat nginx.tar | docker import - testNginx:test
#### 查找镜像:
docker search nginx
 ```
 NAME                                              DESCRIPTION                                     STARS     OFFICIAL   AUTOMATED
nginx                                             Official build of Nginx.                        16993     [OK]       
linuxserver/nginx                                 An Nginx container, brought to you by LinuxS…   169                  
bitnami/nginx                                     Bitnami nginx Docker Image                      133                  [OK]
ubuntu/nginx                                      Nginx, a high-performance reverse proxy & we…   52                   
bitnami/nginx-ingress-controller                  Bitnami Docker Image for NGINX Ingress Contr…   18                   [OK]

NAME：表示镜像的名称。
DESCRIPTION：表示镜像的简要描述。
STARS：表示用户对镜像的评分，评分越高越可以放心使用。
OFFICIAL：是否为官方镜像。
AUTOMATED：是否使用了自动构建。
 ```
#### 打包镜像: 
 ```
[root@localhost nginx]# pwd
/mydata/nginx
[root@localhost nginx]# ls
111.html  Dockerfile  html  log  nginx.conf
[root@localhost nginx]# cat Dockerfile 
FROM nginx
MAINTAINER  cxb "1059091461@qq.com"
RUN echo 'hello docker!'>/usr/share/nginx/html/index.html
[root@localhost nginx]# docker build -t cxb/nginx:v1 .
Sending build context to Docker daemon  148.5kB
Step 1/3 : FROM nginx
 ---> 605c77e624dd
Step 2/3 : MAINTAINER  cxb "1059091461@qq.com"
 ---> Running in 5be9accdcc68
Removing intermediate container 5be9accdcc68
 ---> cfcae3c59dac
Step 3/3 : RUN echo 'hello docker!'>/usr/share/nginx/html/index.html
 ---> Running in 21c2692ab89e
Removing intermediate container 21c2692ab89e
 ---> 759dc44737ac
Successfully built 759dc44737ac
Successfully tagged cxb/nginx:v1
[root@localhost nginx]# docker image ls
REPOSITORY      TAG                 IMAGE ID       CREATED          SIZE
cxb/nginx       v1                  759dc44737ac   20 seconds ago   141MB
[root@localhost nginx]# docker run -itd --name nginx3 -p 8081:80 cxb/nginx:v1
dd39844e9d29e0b1b9dfe4c8ce02e85089deb32de3fe49ab5f8678adcaafdd8f
[root@localhost nginx]# curl http://localhost:8081
hello docker!
 ```
#### 查看所有数据卷:
docker volume ls
#### 查看数据卷详情:
docker volume inspect
#### 批量停止:
docker-compose down
#### 查看日志:
docker logs 60f486ec7c33
