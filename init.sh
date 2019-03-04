#!/bin/sh
cp ~/src/main/java/config.conf ~/target && java -cp ~/target/storasbot-0.0.1-SNAPSHOT.jar lt.ekgame.storasbot.StorasDiscord config.conf
