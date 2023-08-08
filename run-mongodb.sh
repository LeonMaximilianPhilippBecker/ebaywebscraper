docker build -t my-mongodb .
docker run -d -p 27017:27017 --name my-mongodb-instance my-mongodb