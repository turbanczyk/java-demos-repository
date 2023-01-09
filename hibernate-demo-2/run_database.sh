docker image build -t demo-mysql:1.0 demo
docker run --rm --name demo-mysql -e MYSQL_ROOT_PASSWORD=mysecretpassword -dp 3306:3306 demo-mysql:1.0
#go into container
docker container exec -it demo-mysql /bin/bash