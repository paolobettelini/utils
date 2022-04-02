#!/bin/bash

if [ "$1" = "" ] || [ "$2" = "" ]; then
  echo "Usage: $0 <files_path> <dest_path>"
  exit
fi

current_folder=`pwd`
files_path=$1
dest_path=$2

last_index_file=`ls -tr ${dest_path}*.idx | tail -1`
echo $last_index_file

current_date=`date +%Y%m%d%H%M%S`
new_index_file="${dest_path}/${current_date}_inc.idx"
cp "${last_index_file}" "${new_index_file}"

cd $files_path
tar -cf "${dest_path}/${current_date}_inc.tar" --listed-incremental="${new_index_file}" .
cd $current_folder