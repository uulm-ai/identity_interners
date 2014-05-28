package com.google.common.collect

/**
 * Provides alternate `equals()` and `hashCode()` implementation via `internableEquals()` and
 * `internableHashCode()` for usage with `IdentityInterners`, so as to allow for leaving normal
 * `equals()` and `hashCode()` untouched and fast. Note that an implementing class should make sure
 * that all instances created by it are first through an `IdentityInterners` before they are given to application
 * code.
 *
 * User: Felix
 * Date: 12.10.12
 * Time: 14:50
 */
trait IdentityInternable extends AnyRef {

  /**
   * Replacement for normal `equals()`, the same rules as for `equals()` apply.
   * @return true, if `this` is equal to `other`, false else
   */
  def internableEquals(other: AnyRef): Boolean

  /**
   * Replacement for normal `hashCode()`, the same rules as for `hashCode()` apply.
   * @return the hash code
   */
  def internableHashCode(): Int

  final override val hashCode: Int = System.identityHashCode(this)

  final override def equals(obj: Any): Boolean = obj match {
    case ar: AnyRef => this eq ar
    case _ => false
  }
}