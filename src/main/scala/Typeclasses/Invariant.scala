package Typeclasses

trait Invariant {
  def imap[A, B](fa: F[A])(f: A => B)(g: B => A): F[B]

}
