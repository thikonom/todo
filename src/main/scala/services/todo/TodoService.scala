package services.todo

import com.websudos.phantom.dsl._
import domain.todo.Todo
import repositories.todo.TodoRepository
import services.Service

import scala.concurrent.Future

/**
  * Created by lambdasloth on 14/05/2016.
  */

object TodoService extends Service{
  def save(todo: Todo): Future[ResultSet] = {
    TodoRepository.save(todo)
  }

  def findAll(): Future[Seq[Todo]] = {
    TodoRepository.findAll
  }

  def findActive(): Future[Seq[Todo]] = {
    TodoRepository.findActive
  }

  def findActiveByDescription(line: String): Future[Option[Todo]] = {
    TodoRepository.findActiveByDescription(line)
  }

  def setCompletedByDescription(line: String, created_at: DateTime): Future[ResultSet] = {
    TodoRepository.setCompletedByDescription(line, created_at)
  }

  def terminate(): Unit = {
    session.close()
    cluster.close()
  }

}