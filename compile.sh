#!/bin/bash

# Compile the source code using the CMA compiler
# Usage: ./compile.sh <file1.c> [<file2.c> ...] [debug]

# Check if at least one file was provided
if [ $# -eq 0 ]; then
    echo "Usage: ./compile.sh <file1.c> [<file2.c> ...] [debug]"
    exit 1
fi

# Check if the last argument is "debug"
DEBUG=false
if [ "${!#}" == "debug" ]; then
    DEBUG=true
    # Remove the debug flag from arguments
    set -- "${@:1:$(($#-1))}"
fi

# Build files list for Maven exec
FILES=""
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
    
    # Get the absolute path of the file
    ABSOLUTE_PATH=$(realpath "$FILE")
    
    # Build the command arguments
    if [ -z "$FILES" ]; then
        FILES="\"$ABSOLUTE_PATH\""
    else
        FILES="$FILES \"$ABSOLUTE_PATH\""
    fi
done

# Print compilation start message
echo "Compiling: $@"

# Change to the cma directory
cd "$(dirname "$0")/cma" || { echo "Error: Could not change to cma directory"; exit 1; }

# Run the Maven command
eval "mvn exec:java -Dexec.mainClass=\"de.htwsaar.compiler.App\" -Dexec.args=$FILES"

echo "Compilation complete"
echo "CMA files have been created in the same directory as the source files"

