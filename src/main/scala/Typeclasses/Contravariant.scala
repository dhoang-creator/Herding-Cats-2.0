package Typeclasses

import cats.Show

trait Contravariant[F[_]]  {
  def contramap[A, B](fa: F[A])(f: B => A): F[B]

  def by[T, S](f: T => S)(implicit ord: Ordering[S]): Ordering[T]
}

case class Money(amount: Int)
case class Salary(size: Money)

// Here, we are backpedalling a couple of steps to show us what the primitive data type within a nested data model
object Contravariant {
  implicit val showMoney: Show[Money] = Show.show(m => s"$$${m.amount}")

  implicit val moneyOrdering: Ordering[Money] = Ordering.by(_.amount)
}