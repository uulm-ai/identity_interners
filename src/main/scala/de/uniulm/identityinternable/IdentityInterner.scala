package de.uniulm.identityinternable

import com.google.common.collect.{Interner, Interners}

/**
  * Special interners for [[IdentityInternable]]s.
  *
  * Created by Felix on 28.05.2014.
  **/
trait IdentityInterner[T <: IdentityInternable] {
  def intern(sample: T): T
}

object IdentityInterner {
  def newStrongIdentityInterner[T <: IdentityInternable](): IdentityInterner[T] = new IdentityInterner[T] {
    private val interner: Interner[InternableTwin] = Interners.newStrongInterner()

    override def intern(sample: T): T = {
      interner.intern(sample.twin).internable.asInstanceOf[T]
    }
  }

  def newWeakIdentityInterner[T <: IdentityInternable](): IdentityInterner[T] = new IdentityInterner[T] {
    private val interner: Interner[InternableTwin] = Interners.newWeakInterner()

    override def intern(sample: T): T = {
      interner.intern(sample.twin).internable.asInstanceOf[T]
    }
  }
}
