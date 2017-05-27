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

    createFolder "$working_dir/$TODAY"
    cleanFolder "$working_dir/$TODAY"

    cd $EXECUTE_JAR_FOLDER
    java -jar news-crawler-interface-1.0-SNAPSHOT-jar-with-dependencies.jar $working_dir $supplier $from_date $to_date >>${LOG_FILE} 2>&1
    
    checkError $? "Cannot run java!"
    return 1
}

echoSomething() {
    local content=$1

    echo >>$LOG_FILE
    echo $content>>$LOG_FILE
    echo >>$LOG_FILE
}

# Here's main
echoSomething "The crawler's stopped in `date`"
runJava $WORKING_NEWS_FOLDER 1 `date -v-1d +%Y%m%d` $TODAY
exit_status=$?
echoSomething "The crawler's finished at `date`"
exit $exit_status
