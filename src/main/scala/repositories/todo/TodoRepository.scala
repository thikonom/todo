package repositories.todo

import conf.connection.DataConnection
import domain.todo.Todo
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import org.joda.time.DateTime

import scala.concurrent.Future


sealed class TodoRepository extends CassandraTable[TodoRepository, Todo] {

  object description extends StringColumn(this) with PartitionKey[String]

  object priority extends StringColumn(this)

  object created_at extends DateTimeColumn(this) with ClusteringOrder[DateTime] with Descending

  object completed_at extends OptionalDateTimeColumn(this)


  override def fromRow(row: Row): Todo = {
    Todo(description(row), priority(row), created_at(row), completed_at(row))
  }
}

object TodoRepository extends TodoRepository with RootConnector {
  override lazy val tableName = "todo"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session


  def findAll(): Future[Seq[Todo]] = {
      select.fetchEnumerator() run Iteratee.collect()
  }

  def findActive(): Future[Seq[Todo]] = {
    select.fetchEnumerator() run Iteratee.collect() map {x => x.filter(!_.completed_at.isDefined)}
  }

  def findActiveByDescription(line: String): Future[Option[Todo]] = select
    .where(_.description eqs line)
    .one()

  def setCompletedByDescription(line: String, created_at: DateTime): Future[ResultSet] = update
    .where(_.description eqs line)
    .and(_.created_at eqs created_at)
    .modify(_.completed_at setTo Some(DateTime.now))
    .future()

  def save(todo: Todo): Future[ResultSet] = {
    insert
      .value(_.description, todo.description)
      .value(_.priority, todo.priority)
      .value(_.created_at, todo.created_at)
      .value(_.completed_at, todo.completed_at)
      .future()
  }
}


