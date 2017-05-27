#!/bin/sh

CMDNAME=`basename $0`
SHELL_FOLDER="/data/shell/shadow"
WORKING_NEWS_FOLDER="/data/shadow/news"
EXECUTE_JAR_FOLDER="/data/java/shadow"
LOG_FOLDER="/data/log/shadow"

TODAY=`date +%Y%m%d`
LOG_FILE="$LOG_FOLDER/RUN_NEWS_BOOTSTRAP_$TODAY.log"
EXECUTE_JAR_FILE="news-crawler-interface-1.0-SNAPSHOT-jar-with-dependencies.jar"
EXTERNAL_JAR_FILE="shadow-news-entity-1.0-SNAPSHOT.jar"
CLASS_PATH=".:$EXECUTE_JAR_FOLDER:$EXECUTE_JAR_FOLDER/$EXECUTE_JAR_FILE:$EXECUTE_JAR_FOLDER/$EXTERNAL_JAR_FILE"
