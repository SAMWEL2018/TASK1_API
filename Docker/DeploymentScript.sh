docker stop task1
docker rm task1
docker run --name task1 sam9883/task1:1.0.1 \
 -e DB_PASSWORD=root \
 -e DB_USERNAME=postgres \
 -e DB_HOST=192.168.87.69 \
 -e DB_PORT=5432 \
 -e DB_NAME=task1 \
 -e LOG_PATH=/datadrive/logs/task1/ \
 -e LOGFILE=TODAY.log \
 -v datadrive/logs/task1/:datadrive/logs/task1/
