package de.uniulm.identityinternable

/**
  * Created by Felix on 20.11.2017.
  */
trait IdentityInternable extends Product {

  private[identityinternable] val twin: InternableTwin = new InternableTwin(this)

  final override val hashCode: Int = System.identityHashCode(this)

  final override def equals(obj: Any): Boolean = obj match {
    case ar: AnyRef => this eq ar
    case _ => false
  }
}
