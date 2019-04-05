package traits

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// object TodoApp extends App with ApplicationCoreModule with InMemoryPersistenceGatewayModule with TerminalUserInterfaceModule {
object TodoApp extends App with ApplicationCoreModule with InMemoryPersistenceGatewayModule with TerminalUserInterfaceModule {
  final override val pattern: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy H:m")

  applicationBoundary.createOne(
    Todo.Data(
      description = "First One",
      deadline = LocalDateTime.of(2020, 6, 16, 4, 0)
    )
  )

  applicationBoundary.createOne(
    Todo.Data(
      description = "Second One",
      deadline = LocalDateTime.of(1955, 11, 16, 12, 34)
    )
  )

  userInterface.run()
}
