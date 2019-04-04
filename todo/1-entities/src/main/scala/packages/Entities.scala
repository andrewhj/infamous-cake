package packages
import java.time.LocalDateTime

sealed trait Todo {
  def description: String 
  def deadline: LocalDateTime 

  def withUpdateDescription(newDescription: String): Todo
  def withUpdatedDeadline(newDeadline: LocalDateTime): Todo
}

case object Todo {
  final case class Data(description: String, deadline: LocalDateTime) extends Todo {
    override def withUpdateDescription(newDescription: String): Data =
      copy(description = newDescription)
    override def withUpdatedDeadline(newDeadline: LocalDateTime): Data = 
      copy(deadline = newDeadline)
  }

  final case class Existing(id: String, data: Data) extends Todo {
    override def description: String = data.description
    override def deadline: LocalDateTime = data.deadline

    override def withUpdateDescription(newDescription: String): Existing = 
      copy(data = data.withUpdateDescription(newDescription))

    override def withUpdatedDeadline(newDeadline: LocalDateTime): Existing =
      copy(data = data.withUpdatedDeadline(newDeadline))
  }
}
