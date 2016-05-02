#!/bin/sh
for deviceId in $(adb devices  | tail -n +2 | awk '{print $1}'); 
do 
	echo "$deviceId"; 
	$(adb -s $deviceId uninstall com.prateekj.android)
done;
