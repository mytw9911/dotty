// TODO remove when reference compiler is updated
package scala.runtime.quoted

import scala.quoted.{Expr, QuoteContext, Type}

/** Provides methods to unpickle `Expr` and `Type` trees. */
@deprecated("Use scala.internal.quoted.Unpickler", "0.22.0")
object Unpickler {

  type PickledQuote = List[String]
  type PickledExprArgs = Seq[Seq[Any] => (((given QuoteContext) => Expr[Any]) | Type[_])]
  type PickledTypeArgs = Seq[Seq[Any] => Type[_]]

  /** Unpickle `repr` which represents a pickled `Expr` tree,
   *  replacing splice nodes with `args`
   */
  def unpickleExpr[T](repr: PickledQuote, args: PickledExprArgs): (given QuoteContext) => Expr[T] =
    summon[QuoteContext].tasty.internal.unpickleExpr(repr, args).asInstanceOf[Expr[T]]

  /** Unpickle `repr` which represents a pickled `Type` tree,
   *  replacing splice nodes with `args`
   */
  def unpickleType[T](repr: PickledQuote, args: PickledTypeArgs): (given QuoteContext) => Type[T] =
    summon[QuoteContext].tasty.internal.unpickleType(repr, args).asInstanceOf[Type[T]]


}
