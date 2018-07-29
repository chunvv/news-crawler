#!/bin/sh

. definition.sh
. ../library/util.sh
. ../library/folder_handle.sh

##
# Arguents:
# $1: Working directory
# $2: Supplier type
# $3: From date
# $4: To date
#
# Example: /data/news 1 20170524 20170528
##
runJava() {
    local working_dir=$1
    local supplier=$2
    local from_date=$3
    local to_date=$4

    cd $EXECUTE_JAR_FOLDER
    java -cp $CLASS_PATH com.chariot.shadow.NewsBootstrap $working_dir $supplier $from_date $to_date >>${LOG_FILE} 2>&1

    checkError $? "Cannot run java!"
    return 1
}

echoSomething() {
    local content=$1

    echo >>$LOG_FILE
    echo "===============================================">>$LOG_FILE
    echo "==  $content  ==">>$LOG_FILE
    echo "===============================================">>$LOG_FILE
    echo >>$LOG_FILE
}

# Here's main
echoSomething "The crawler's started in `date`"
runJava /data/news/vtvtechnology 10 `date -v-7d +%Y%m%d` $TODAY
echoSomething "The crawler's finished at `date`"