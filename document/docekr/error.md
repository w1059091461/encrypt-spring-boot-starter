#### failed to create network error:
 ```
 Error response from daemon: Failed to Setup IP tables: Unable to enable SKIP DNAT rule:  (iptables failed: iptables --wait -t nat -I DOCKER -i br-b649822bbcff -j RETURN: iptables: No chain/target/match by that name. (exit status 1))
这是因为在启动docker的时候防火墙做了策略，如果容器在运行中，停止防火墙，在操作容器就会报这个错误，我们可以重启docker解决此问题
systemctl restart docker.service
然后
docker-compose up -d 
 ```
#### The container name “***” is already in use
 ```
删除原来的容器

查看容器
docker ps -a
删除指定的容器
docker rm 容器号

 ```