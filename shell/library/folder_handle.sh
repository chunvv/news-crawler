#!/bin/sh

createFolder() {
    local folder_name=$1

    mkdir -p $folder_name
}

cleanFolder() {
    local folder_name=$1

    rm -rf "$folder_name/*"
}

copyFolder() {
    local source_folder_name=$1
    local dest_folder_name=$2
    
    cp -r "$source_folder_name" "$dest_folder_name"
    checkError $? "Failed to copy $source_folder_name to $dest_folder_name."
}
