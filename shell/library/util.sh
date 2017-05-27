#!/bin/sh

checkError() {
    local check_arg=$1
    local fail_message=$2

    if [ "$check_arg" -ne "0" ]; then
        echo "$fail_message"
        exit 1
    fi
}

checkArgumentQuantity() {
    local args_quantity=$1
    local expect_quantity=$2

    isArgumentSupplied $args_quantity
    isArgumentSupplied $expect_quantity

    if [ "$args_quantity" -ne "$expect_quantity" ]; then
        echo "Too much or too little arguments."
        exit 1
    fi
}

isArgumentSupplied() {
    local arg=$1
    
    if [ -z "$arg" ]; then
        echo "No argument supplied."
        exit 1
    fi
}
