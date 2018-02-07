#!/bin/bash

base=$(cd $(dirname $0); pwd)


#for single testcase

#java -Xss256M -XX:+UseSerialGC -cp "$base/bin" Main "$@"

#for unit of testcases

TESTCASESdir="testcases/"
if [ "$(ls -A $TESTCASESdir)" ]; then
    TESTCASESdir="$TESTCASESdir*"
    for f in $TESTCASESdir
    do
        java -Xss256M -XX:+UseSerialGC -cp "$base/bin" fvs_bf.FVS_BF "$f"
    done
else
    echo "no testcases in 'testcases' directory."
fi
