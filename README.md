# storasbot_reborn
# A Discord bot for osu! leaderboad tracking and notifications.
# ALL RIGHTS RESERVED BY THE OWNER: https://github.com/ekgame

Prerequisites:

1. Heroku Account.
2. MariaDB/MySQL Server.

Steps to deploy the bot in Heroku:

1. Create a new Heroku app.
2. Select the Heroku/Java buildpack in the Settings > Buildpacks section.
3. Set up the Environment Variables as follows:


    DBDATABASE   : Database Name.
    
    
    DBHOST       : Database Host
    
    
    DBPORT       : Database Port.
    
    
    DBUSER       : Database Username.
    
    
    DBPASS       : Database Password.
    
    
    OSU_API      : osu!API Key. (Get it from https://osu.ppy.sh/p/api)
    
    
    TOKEN        : Discord Bot account Token. (Get it from https://discordapp.com/developers/applications/)
    
    
4. Fork this repository.
5. Under Deploy > App connected to Github, Connect the forked repo with Heroku.
6. Under Deploy > Manual Deploy, Click on Deploy Branch
7. After the bot has successfully deployed, Refresh the page manually once, and then go to Resources.
8. Under Free dynos, click on the pencil button and enable the slider.
9. Under More > View logs, you can see the current status of the bot.
