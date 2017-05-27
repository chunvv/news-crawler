#!/bin/sh

. execute/definition.sh
. library/util.sh
. library/folder_handle.sh

release() {
    local log_folder=$LOG_FOLDER
    
    createFolder $SHELL_FOLDER
    cleanFolder $SHELL_FOLDER

    createFolder $log_folder
    createFolder $WORKING_NEWS_FOLDER
    createFolder $EXECUTE_JAR_FOLDER
    
    copyFolder "library" $SHELL_FOLDER
    copyFolder "execute" $SHELL_FOLDER

    cd ../../shadow-news-entity/
    mvn clean install
    cp target/shadow-news-entity-1.0-SNAPSHOT.jar $EXECUTE_JAR_FOLDER
    
    cd ../shadow-news-crawler
    mvn clean install -DskipTests=true
    cp news-crawler-interface/target/news-crawler-interface-1.0-SNAPSHOT-jar-with-dependencies.jar $EXECUTE_JAR_FOLDER
    checkError $? "Failed to copy news-crawler-interface/target/news-crawler-interface-1.0-SNAPSHOT.jar to $EXECUTE_JAR_FOLDER"

    return 0
}

release
exit $?
