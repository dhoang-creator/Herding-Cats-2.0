package Typeclasses

trait Functor[F[_]] {
  def map[A, B](fa: F[A])(f: A => B): F[B]
}

object Functor {

  // What is going on here is that we're simply applying a function or functor to a sequence of data
  implicit val functorForOption: Functor[Option] = new Functor[Option] {
    def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa match {
      case None     => None
      case Some(a)  => Some(f(a))
    }
  }
}