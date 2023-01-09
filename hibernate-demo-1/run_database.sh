docker image build -t demo-postgresql:1.0 demo
docker run --rm --name demo-postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=mysecretpassword -dp 5432:5432 demo-postgresql:1.0
#go into container
docker container exec -it demo-postgres /bin/bash