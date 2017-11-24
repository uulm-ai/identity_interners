identity_interners
==================

Implements identity-based interning based on guava. To use, make your class extend the trait `IdentityInternable` and intern them with an `IdentityInterner`. 

Interning with an `IdentityInterner` `interner` has the following properties:
* For two `IdentityInternable`s `i` and `j` it holds that `interner.intern(i) == interner.intern(j)` if and only if `i.productIterator.toSeq == j.productIterator.toSeq`
* `equals(Any)` and `hashCode()` of an `IdentityInternable` are based on reference equality, i.e., fast.

`IdentityInterner`s have an advantage over guava's `com.google.common.collect.Interner` when you want to use interning in conjunction with ordinary Scala or Java collections: These collections compare elements using `equals(Any)` and `hashCode()` (with a few exceptions), which is inefficient when applied to interned elements, since then you know that `a == b` if and only if `a eq b`, i.e., the elements could be compared based on fast reference equality checking.

To implement this, the members of an `IdentityInternable` are used for comparisons related to interning (through a "twin" that compares the members of the `IdentityInternable` with `Product.productIterator`). Hence, it is the class you want to be internable needs to extend `Product`, which is most easily achieved by making it a case class. 

Adding identity_interners to your sbt project
--------------------------------
To use the library, add the following lines to your `.sbt` file:

    resolvers += "fmueller repository" at "http://companion.informatik.uni-ulm.de/~fmueller/mvn"

    libraryDependencies += "de.uni-ulm" %% "identity_interners" % "2.0"

