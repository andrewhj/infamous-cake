package traits

trait PersistenceGatewayModule {
  this: EntitiesModule =>


  protected def persistenceGateway: PersistenceGateway

  trait PersistenceGateway {
    // C U
    def writeMany(todos: Set[Todo]): Set[Todo.Existing]
    //
    // R
    def readAll: Set[Todo.Existing]
    def readManyById(ids: Set[Id]): Set[Todo.Existing]
    def readManyByPartialDescription(partialDescription: String): Set[Todo.Existing]

    // D
    def deleteMany(todos: Set[Todo.Existing]): Unit
    def deleteAll(): Unit
  }
}
