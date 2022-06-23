批量删除:
docker rm $(docker ps -a -q)
容器内执行命令：
docker exec -it mysql bash
docker-compose启动：
docker-compose up -d
列出所有镜像：
docker image ls
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
启动某个镜像：
docker run --name 启动镜像名称  -d -p 本机映射端口:镜像映射端口 镜像名称
docker run --name nginx -d -p 8080:80 nginx
导出容器：
docker export nginx > /mydata/nginx/nginx.tar
导入容器：
cat nginx.tar | docker import - testNginx:test
