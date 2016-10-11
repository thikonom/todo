# My todo [Bitbar](https://github.com/matryer/bitbar) plugin made with Scala and Cassandra that looks like:
![screenshot](https://raw.githubusercontent.com/thikonom/todo/master/other/screenshot.png)

### Installation ###
* Download latest release of Bitbar from https://github.com/matryer/bitbar/releases
* `brew cask install java`
* `brew install cassandra`
* Create a keyspace that saves your todos in Cassandra (the connection settings are under `src/main/resources/application.conf`):                                                    
  `cqlsh> create keyspace bitbar with replication = {'class':'SimpleStrategy','replication_factor':1};`
* Create a hidden todo file under `$HOME` (`~/.todo`) and add todo tasks **one per line**
* Move the python script from `other/todo.10m.py` to your enabled plugins folder
* Move the .jar file from `other/lambdasloth.todo.jar` to the parent of the enabled plugins folder
* From the menu: `Preferences` -> `Refresh all`

### Development ###

* Make changes
* sbt assembly
* java -jar {path_to_jar_file}
