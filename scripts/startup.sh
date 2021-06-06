#!/bin/bash
java -jar AAM-1.0.war 
chromium-browser http://localhost:8080
#killall chromium-browser
#echo "all done!"