package services

/**
  * Created by lambdasloth on 14/05/2016.
  */

import conf.connection.DataConnection

trait Service {
  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session
  implicit val cluster = DataConnection.cluster
}
