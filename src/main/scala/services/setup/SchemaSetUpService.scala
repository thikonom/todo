package services.setup

import repositories.todo.TodoRepository
import services.Service

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by lambdasloth on 14/05/2016.
  */

object SchemaSetUpService extends Service{
  def createSchema = TodoRepository.create.ifNotExists().future()
}
