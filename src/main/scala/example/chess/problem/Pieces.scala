package example.chess.problem

import example.chess.problem.ChessBoard.{Board, Square}
import scala.annotation.tailrec

case class PieceInfo(piece: Piece, count: Int)

sealed trait Piece {
  def filterCriteria(current: Square, x: Int, y: Int): Boolean

  @tailrec
  final def isVulnerable(current: Square, board: Board, targets: List[Square]): Boolean = {
    def testSquare(current: Square, x: Int, y: Int) = if ((x == current.x && y == current.y) || (!filterCriteria(current, x, y))) false else true

    if (targets.isEmpty) false
    else {
      val target = targets.head
      if (testSquare(current, target.x, target.y)) true
      else isVulnerable(current, board, targets.tail)
    }
  }
}

case object King extends Piece {
  override def toString: String = "King"

  override def filterCriteria(current: Square, x: Int, y: Int): Boolean =
    Math.abs(current.x - x) <= 1 && Math.abs(current.y - y) <= 1
}

case object Queen extends Piece {
  override def toString: String = "Queen"

  override def filterCriteria(current: Square, x: Int, y: Int): Boolean =
    current.x == x || current.y == y ||
      Math.abs(current.x - x) == Math.abs(current.y - y)
}

case object Rook extends Piece {
  override def toString: String = "Rook"

  override def filterCriteria(current: Square, x: Int, y: Int): Boolean =
    current.x == x || current.y == y
}

case object Bishop extends Piece {
  override def toString: String = "Bishop"

  override def filterCriteria(current: Square, x: Int, y: Int): Boolean =
    Math.abs(current.x - x) == Math.abs(current.y - y)
}

case object Knight extends Piece {
  override def toString: String = "Knight"

  override def filterCriteria(current: Square, x: Int, y: Int): Boolean =
    (Math.abs(current.x - x) == 1 && Math.abs(current.y - y) == 2) ||
      (Math.abs(current.x - x) == 2 && Math.abs(current.y - y) == 1)
}

