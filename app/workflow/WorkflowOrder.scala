package workflow

import slick.jdbc.PostgresProfile.api.*
import java.time.Instant

case class WorkflowOrder(id: Long, description: String, status: String, statusData: String, createdAt: Instant, updatedAt: Instant)

case class WorkflowOrderForm(description: String)

case class TransitionForm(orderId: Long, action: String, data: String)

class WorkflowOrdersTable(tag: Tag) extends Table[WorkflowOrder](tag, "workflow_orders"):
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def description = column[String]("description")
  def status = column[String]("status")
  def statusData = column[String]("status_data")
  def createdAt = column[Instant]("created_at")
  def updatedAt = column[Instant]("updated_at")
  def * = (id, description, status, statusData, createdAt, updatedAt).mapTo[WorkflowOrder]

  given instantColumnType: BaseColumnType[Instant] =
    MappedColumnType.base[Instant, Long](_.toEpochMilli, Instant.ofEpochMilli)
