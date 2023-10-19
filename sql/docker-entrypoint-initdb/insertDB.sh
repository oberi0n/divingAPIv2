#!/bin/sh

/usr/bin/mysqld_safe --skip-grant-tables &
sleep 5
mysql --user=$MYSQL_USER --password=$MYSQL_PASSWORD $MYSQL_DATABASE < equipment.sql