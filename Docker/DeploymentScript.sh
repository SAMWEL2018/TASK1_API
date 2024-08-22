#!/bin/bash
docker stop task1
docker rm  task1
docker run  \
-e DB_USERNAME=postgres \
-e DB_PASSWORD=root \
-e DB_HOST=db \
-e DB_PORT=5433 \
-e DB_NAME=task1 \
-e LOG_PATH=/home/logs/ \
-v /home/logs/:/home/logs/ \
--add-host db:192.168.87.69 \
--name task1 sam9883/task1:1.0.1
