package analytics

import users.UserRepository
import orders.{Order, OrderRepository}

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

case class DashboardData(
  userCount: Int,
  orderCount: Int,
  topItems: Seq[(String, Int)],
  recentOrders: Seq[Order]
)

object DashboardData:
  val empty: DashboardData = DashboardData(0, 0, Seq.empty, Seq.empty)

@Singleton
class AnalyticsService @Inject()(
  userRepository: UserRepository,
  orderRepository: OrderRepository
)(using ec: ExecutionContext):

  def dashboard(): Future[DashboardData] =
    // Start all futures before combining (runs in parallel)
    val userCountF: Future[Int] = userRepository.count()
    val orderCountF: Future[Int] = orderRepository.count()
    val topItemsF: Future[Seq[(String, Int)]] = orderRepository.topItems(5)
    val recentOrdersF: Future[Seq[Order]] = orderRepository.recent(10)

    // Compose with for-comprehension
    val dashboard = for
      userCount    <- userCountF
      orderCount   <- orderCountF
      topItems     <- topItemsF
      recentOrders <- recentOrdersF
    yield DashboardData(userCount, orderCount, topItems, recentOrders)

    // recover - graceful fallback
    dashboard.recover {
      case _: Exception => DashboardData.empty
    }
