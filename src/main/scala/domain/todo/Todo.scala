package domain.todo

import org.joda.time.DateTime

/**
  * Created by lambdasloth on 14/05/2016.
  */

case class Todo (description: String, priority: String, created_at: DateTime, completed_at: Option[DateTime])


