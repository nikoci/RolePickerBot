# RolePickerBot

RolePickerBot is a small Java project that connects to the Discord API through the library JDA (JAVA DISCORD API) and utilizes Interactions and SelectMenu's to give your members a option to choose their own roles.

This was made for fun. If you want a more functional Discord Bot software, check out [Ryder](https://github.com/nikoci/Ryder) or [RoboFlask](https://github.com/nikoci/RoboFlask) which are my other 2 projects I've made.

## Usage

Clone the project
```
git clone https://github.com/nikoci/RolePickerBot & cd ./RolePickerBot
```

Compile the project using Maven
```
mvn clean package
```

Start the java application
```
java -jar ./target/rolepickerbot-<VERSION>.jar "<BOT TOKEN HERE>"
```

The `VERSION` can be found in the packaged jar's namespace inside of the **target** folder.
The `BOT TOKEN` can be generated over at [Discord's Developer Portal](https://discord.com/developers/applications) by creating a new **application** and then adding a **bot** to that application. Make sure you enable necessary **Gateway Intents**.