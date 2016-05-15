# My todo [Bitbar](https://github.com/matryer/bitbar) plugin made with Scala and Cassandra that looks like:
![screenshot](https://raw.githubusercontent.com/thikonom/todo/master/other/screenshot.png)

### Installation ###
* Create a keyspace that saves your todos in Cassandra:                                                    
  `cqlsh> create keyspace bitbar with replication = {'class':'SimpleStrategy','replication_factor':1};`
* Move the python script from `other/todo.10m.py` to your enabled plugins folder
* Move the .jar file from `other/lambdasloth.todo.jar` to the parent of the enabled plugins folder
* From the menu: Preferences -> Refresh all

### Development ###

* Make changes
* sbt assembly
* java -jar {path_to_jar_file}
