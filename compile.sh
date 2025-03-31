#!/bin/bash

# Compile the source code

C_FILE=$1
FLAGS=$2
TARGET="${C_FILE%.*}"

echo "Compiling $C_FILE"

cd tree-sitter-cma
pwd
tree-sitter parse -cst --no-ranges $C_FILE | sed 's/\x1b\[[0-9;]*m//g' > $TARGET
# head -n -5 $TARGET > $TARGET
head -n -5 $TARGET > tmpfile && mv tmpfile $TARGET

cd ../cma

mvn exec:java -Dexec.args="$TARGET"
echo "Compiled $C_FILE"
if [ "$FLAGS" != "debug" ]; then
    rm $TARGET
fi

