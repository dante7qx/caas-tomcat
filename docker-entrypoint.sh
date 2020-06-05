#!/bin/bash

set -e

HOST=`hostname -s`
LOG_DIR="/home/ap/logs"
TOMCAT_LOG="/usr/local/tomcat/logs"

mkdir -p "$LOG_DIR"/"$HOST"

if [ -d "$TOMCAT_LOG" ]; then
	rm -rf "$TOMCAT_LOG"
	ln -s "$LOG_DIR"/"$HOST" "$TOMCAT_LOG"
else
	ln -s "$LOG_DIR"/"$HOST" "$TOMCAT_LOG"
fi	


exec "$@"