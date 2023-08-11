package DataTypes

import cats.{FlatMap, Functor}
import cats.implicits.{toFlatMapOps, toFunctorOps}

/*
  Kleisli is very useful in converting Options and Eithers into primitive data types but is there another trick?
 */
final case class Kleisli[F[_], A, B](run: A => F[B]) {
  def compose[Z](k: Kleisli[F, Z, A])(implicit F: FlatMap[F]): Kleisli[F, Z, B] =
    Kleisli[F, Z, B](z => k.run(z).flatMap(run))

  def map[C](f: B => C)(implicit F: Functor[F]): Kleisli[F, A, C] =
    Kleisli[F, A, C](a => F.map(run(a))(f))
}

object Kleisli {

  val parse: Kleisli[Option, String, Int] =
    Kleisli((s: String) => if (s.matches("-?[0-9]+")) Some(s.toInt) else None)

  val reciprocal: Kleisli[Option, Int, Double] =
    Kleisli((i: Int) => if (i != 0) Some(1.0 / i ) else None)

  val parseAndReciprocal: Kleisli[Option, String, Double] =
    reciprocal.compose(parse)

  implicit def kleisliFlatMap[F[_], Z](implicit F: FlatMap[F]): FlatMap[Kleisli[F, Z, *] = {
    new FlatMap[Kleisli[F, Z, *]] {
      def flatMap[A, B](fa: Kleisli[F, Z, A])(f: A => Kleisli[F, Z, B]): Kleisli[F, Z, B] = {
        Kleisli(z => fa. run(z).flatMap(a => f(a).run(z)))
      }

      def map[A, B](fa: Kleisli[F, Z, A])(f: A => B): Kleisli[F, Z, B] =
        Kleisli(z => fa.run(z).map(f))

      def tailRecM[A, B](a: A)(f: A => Kleisli[F, Z, Either[A, B]]) =
        Kleisli[F, Z, B]({ z => FlatMap[F].tailRecM(a) { f(_).run(z) }  })
    }
  }
}