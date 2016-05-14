package bin

/**
  * Created by lambdasloth on 14/05/2016.
  */

import services.todo.TodoService
import domain.todo.Todo
import services.setup.SchemaSetUpService

import scala.io.Source
import java.io.File
import org.joda.time.DateTime

import scala.concurrent.Await
import scala.concurrent.duration._


object TodoApp extends App {
  val interval = 30 seconds

  val home = new File(System.getProperty("user.home"))
  val filePath = new File(home, ".todo").getPath()

  Await.result(SchemaSetUpService.createSchema, interval)

  val todoList = Await.result(TodoService.findActive(), interval)

  var descActive = collection.mutable.Set[String]()

  for (line <- Source.fromFile(filePath).getLines) {
    val result = Await.result(TodoService.findActiveByDescription(line), interval)
    result match {
      case Some(todo) => {
        if (todo.completed_at.isDefined) {
          val insert = TodoService.save(Todo(line, "high", DateTime.now, None))
          Await.result(insert, interval)
        }
        descActive += todo.description
      }
      case None => {
        val insert = TodoService.save(Todo(line, "high", DateTime.now, None))
        Await.result(insert, interval)
        descActive += line
      }
    }
  }

  for (todo <- todoList) {
    if (!(descActive contains todo.description)) {
      TodoService.setCompletedByDescription(todo.description, todo.created_at)
    }
  }

  println("%d items in your list".format(descActive.size))
  println("---")
  descActive.foreach(println)

  TodoService.terminate()
}
