package de.uniulm.identityinternable

/**
  * Created by Felix on 21.11.2017.
  */
private[identityinternable] class InternableTwin(val internable: IdentityInternable) {

  def canEqual(other: Any): Boolean = other.isInstanceOf[InternableTwin]

  override def equals(other: Any): Boolean = other match {
    case that: InternableTwin =>
      (that canEqual this) && internable.productIterator.toSeq == that.internable.productIterator.toSeq
    case _ => false
  }

  override def hashCode(): Int = {
    val state = internable.productIterator
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
