package packages

trait PersistenceGateway {
  // C U
  def writeMany(todos: Set[Todo]): Set[Todo.Existing]
  //
  // R
  def readAll: Set[Todo.Existing]
  def readManyById(ids: Set[String]): Set[Todo.Existing]
  def readManyByPartialDescription(partialDescription: String): Set[Todo.Existing]

  // D
  def deleteMany(todos: Set[Todo.Existing]): Unit
  def deleteAll(): Unit
}
