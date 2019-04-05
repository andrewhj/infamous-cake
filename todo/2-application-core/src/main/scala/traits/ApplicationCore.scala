package traits

trait ApplicationCoreModule extends EntitiesModule with ApplicationBoundaryModule {
  this: PersistenceGatewayModule =>

  final override object applicationBoundary extends ApplicationBoundary {
    // C
    def createOne(todo: Todo.Data): Todo.Existing = {
      createMany(Set(todo)).head
    }

    def createMany(todos: Set[Todo.Data]): Set[Todo.Existing] = {
      writeMany(todos)
    }

    private def writeMany[T <: Todo](todos: Set[T]): Set[Todo.Existing] = {
      persistenceGateway.writeMany {
        todos.map { todo =>
          todo.withUpdatedDescription(todo.description.trim)
        }
      }
    }

    // R
    def readOneById(id: Id): Option[Todo.Existing] = readManyById(Set(id)).headOption

    def readManyById(ids: Set[Id]): Set[Todo.Existing] = 
      persistenceGateway.readManyById(ids)

    def readManyByPartialDescription(partialDescription: String): Set[Todo.Existing] = {
      if(partialDescription.isEmpty)
        Set.empty
      else
        persistenceGateway.readManyByPartialDescription(partialDescription.trim)
    }

    def readAll: Set[Todo.Existing] = 
      persistenceGateway.readAll

    // U
    def updateOne(todo: Todo.Existing): Todo.Existing = {
      updateMany(Set(todo)).head
    }

    def updateMany(todos: Set[Todo.Existing]): Set[Todo.Existing] = {
      writeMany(todos)
    }

    // D
    def deleteOne(todo: Todo.Existing): Unit = {
      deleteMany(Set(todo))
    }

    def deleteMany(todos: Set[Todo.Existing]): Unit = {
      persistenceGateway.deleteMany(todos)
    }

    def deleteAll(): Unit = {
      persistenceGateway.deleteAll
    }
  }
}
