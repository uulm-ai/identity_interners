package com.google.common.collect

import com.google.common.base.Preconditions._
import java.util.concurrent.ConcurrentMap

/**
 * Special interners for [[com.google.common.collect.IdentityInternable]]s that use the `IdentityInternable.internableEquals(AnyRef)`
 * and `IdentityInternable.internableHashCode()` methods for comparisons related to interning. Since the standard
 * `equals(Any)` and `hashCode()` methods of an [[com.google.common.collect.IdentityInternable]] are based on reference equality,
 * this means that once an [[com.google.common.collect.IdentityInternable]] is interned, `equals(Any)` and `hashCode()`
 * are both fast and carry the comparison semantics of `IdentityInternable.internableEquals(AnyRef)`
 * and `IdentityInternable.internableHashCode()`.
 *
 * Created by Felix on 28.05.2014.
 */
trait IdentityInterner[T <: IdentityInternable] {
  def intern(sample: T): T
}

object IdentityInterner {
  def newStrongIdentityInterner[T <: IdentityInternable](): IdentityInterner[T] = {
    val map: ConcurrentMap[T, T] = new MapMaker().keyEquivalence(IdentityInternableEquivalence).makeMap()
    new IdentityInterner[T] {
      def intern(sample: T): T = {
        val canonical: T = map.putIfAbsent(checkNotNull(sample), sample)
        if (canonical == null) sample else canonical
      }
    }
  }

  def newWeakIdentityInterner[T <: IdentityInternable](): IdentityInterner[T] = {
    new IdentityInterner.WeakIdentityInterner[T]
  }

  /**
   * A weak interner suited for [[com.google.common.collect.IdentityInternable]]s.
   *
   * Created by Felix on 28.05.2014.
   */
  private class WeakIdentityInterner[T <: IdentityInternable] extends IdentityInterner[T] {

    private object Dummy
    private final val map: MapMakerInternalMap[T, Any] = new MapMaker().weakKeys.keyEquivalence(IdentityInternableEquivalence).makeCustomMap()

    def intern(sample: T): T = {
      while (true) {
        val entry = map.getEntry(sample)
        if (entry != null) {
          val canonical: T = entry.getKey
          if (canonical != null) return canonical
        }
        val sneaky = map.putIfAbsent(sample, Dummy)
        if (sneaky == null) return sample
      }
      throw new IllegalStateException()
    }
  }

}
