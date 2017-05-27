#!/bin/sh

CMDNAME=`basename $0`
SHELL_FOLDER="/data/shell/shadow"
WORKING_NEWS_FOLDER="/data/shadow/news"
EXECUTE_JAR_FOLDER="/data/java/shadow"
LOG_FOLDER="/data/log/shadow"

TODAY=`date +%Y%m%d`
LOG_FILE="$LOG_FOLDER/RUN_NEWS_BOOTSTRAP_$TODAY.log"
CLASSPATH=""