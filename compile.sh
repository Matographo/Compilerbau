#!/bin/bash

# Compile the source code using the CMA compiler
# Usage: ./compile.sh <file1.c> [<file2.c> ...] [debug]

# Check if at least one file was provided
if [ $# -eq 0 ]; then
    echo "Usage: ./compile.sh <file1.c> [<file2.c> ...] [debug]"
    exit 1
fi

# Check if the last argument is "debug"
DEBUG_MODE=false
if [ "$#" -gt 0 ] && [ "${!#}" == "debug" ]; then
    DEBUG_MODE=true
    # Remove the debug flag from arguments
    set -- "${@:1:$(($#-1))}"
fi

# Validate files first
for FILE in "$@"; do
    # Check if the file exists
    if [ ! -f "$FILE" ]; then
        echo "Error: File $FILE does not exist"
        exit 1
    fi
    
    # Check if the file has .c extension
    if [[ "$FILE" != *.c ]]; then
        echo "Error: $FILE is not a C file (must have .c extension)"
        exit 1
    fi
done

# Change to the cma directory
cd "$(dirname "$0")/cma" || { echo "Error: Could not change to cma directory"; exit 1; }

# Process each file individually
for FILE in "$@"; do
    # Get the absolute path of the file
    ABSOLUTE_PATH=$(realpath "../$FILE")
    
    # Print compilation start message for this file
    echo "Compiling: $FILE"
    
    # Run the Maven command for this file
    mvn exec:java -Dexec.mainClass="de.htwsaar.compiler.App" -Dexec.args="$ABSOLUTE_PATH"
    
    echo "Compilation of $FILE complete"
done

echo "All compilations complete"
echo "CMA files have been created in the same directory as the source files"

