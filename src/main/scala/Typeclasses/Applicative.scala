package Typeclasses

trait Applicative[F[_]] extends Functor[F] {
  def ap[A, B](ff: A => B)(fa: F[A]): F[B]

  def pure[A](a: A): F[A]

  def map[A, B](fa: F[A])(f: A => B): F[B] = ap(pure(f))(fa)

  def product[A, B](fa: F[A], fb: F[B]): F[(A, B)]
}

object Applicative {

  implicit def applicativeForEither[L]: Applicative[Either[L, *]] =
    new Applicative[Either[L, *]] {
      def product[A, B](fa: Either[L, A], fb: Either[L, B]): Either[L, (A, B)]
        = (fa, fb) match {
        case (Right(a)  , Right(b)) => Right((a, b))
        case (Left(l)   , _       ) => Left(l)
        case (_         , Left(l) ) => Left(l)
      }

      override def pure[A](a: A): Either[L, A] = Right(a)

      def map[A, B](fa: Either[L, A])(f: A => B): Either[L, B] = fa match {
        case Right(a) => Right(f(a))
        case Left(l)  => Left(l)
      }
    }
}
