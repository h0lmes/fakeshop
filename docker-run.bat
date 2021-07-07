docker container rm -f fakeshop
docker container run -it --name fakeshop -p 8080:8080 fakeshop