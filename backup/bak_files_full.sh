#!/bin/bash

if [ "$1" = "" ] || [ "$2" = "" ]; then
  echo "Usage: $0 <files_path> <dest_path>"
  exit
fi

current_date=`date +%Y%m%d%H%M%S`
current_folder=`pwd`
files_path=$1
dest_path=$2

cd $files_path
tar -cf "${dest_path}/${current_date}_full.tar" --listed-incremental="${dest_path}/${current_date}_full.idx" .
cd $current_folder
