package test

object OptionPickler extends upickle.AttributeTagged {
  implicit override def OptionWriter[T: Writer]: Writer[Option[T]] =
    implicitly[Writer[T]].comap[Option[T]] {
      case None    => null.asInstanceOf[T]
      case Some(x) => x
    }

  implicit override def OptionReader[T: Reader]: Reader[Option[T]] = {
    new Reader.Delegate[Any, Option[T]](implicitly[Reader[T]].map(Some(_))) {
      override def visitNull(index: Int): Option[T] = None
    }
  }
}