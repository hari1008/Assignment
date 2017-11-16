package example.chess.problem

import example.chess.problem.ChessBoard.{Board, Position, Square}
import scala.annotation.tailrec

case class ChessBoard(board: Board, occupied: List[Position], occupiedSquare: List[Square], availableSquares: List[Square]) {

  @tailrec
  private def filterAvailableSquares(piece: Piece, square: Square, availableSquares: List[Square],
                                     acc: List[Square]): List[Square] = {
    availableSquares match {
      case Nil => acc
      case head :: tail => filterAvailableSquares(piece, square, availableSquares.tail,
        if (!square.equals(head) && !piece.filterCriteria(square, head.x, head.y))
          head :: acc
        else acc)
    }
  }

  def addPiece(piece: Piece, square: Square): ChessBoard =
    copy(occupied = Position(piece, square) :: occupied,
      occupiedSquare = square :: occupiedSquare,
      availableSquares = filterAvailableSquares(piece, square, availableSquares, List()))

  def addPiece(piece: Piece, x: Int, y: Int): ChessBoard =
    addPiece(piece, Square(x, y))

  @tailrec
  private def filterAvailableSquaresAfterFromSquareList(piece: Piece, square: Square, squares: List[Square], acc: List[Square]): List[Square] = {
    squares match {
      case Nil => acc
      case head :: tail => filterAvailableSquaresAfterFromSquareList(piece, square, squares.tail,
        if (square < head && !piece.isVulnerable(head, board, occupiedSquare))
          head :: acc
        else acc)
    }
  }

  def availableSquaresAfterFromSquareList(piece: Piece, square: Square) =
    filterAvailableSquaresAfterFromSquareList(piece, square, availableSquares, List())

  @tailrec
  private def filterAvailableSquaresFor(piece: Piece, squares: List[Square], acc: List[Square]): List[Square] = {
    squares match {
      case Nil => acc
      case head :: tail => filterAvailableSquaresFor(piece, squares.tail,
        if (!piece.isVulnerable(head, board, occupiedSquare))
          head :: acc
        else acc)
    }
  }

  def availableSquaresFor(piece: Piece) =
    filterAvailableSquaresFor(piece, availableSquares, List())

  override def toString: String =
    board.full.map(s =>
      occupied.find {
        case Position(_, Square(s.x, s.y)) => true
        case _ => false
      }.map(_.piece.toString)
        .getOrElse(
          if (!availableSquares.exists(s.equals)) "*"
          else "-"
        ) + (if (s.x == board.x) "\n" else "")
    ).mkString
}

object ChessBoard {

  def apply(board: Board): ChessBoard =
    ChessBoard(board, List(), List(), board.full)

  def apply(x: Int, y: Int): ChessBoard =
    ChessBoard(Board(x, y))

  case class Square(x: Int, y: Int) extends Ordered[Square] {
    override def compare(that: Square): Int = {
      val r = this.y - that.y
      if (r != 0) r
      else this.x - that.x
    }
  }

  case class Position(piece: Piece, square: Square)

  case class Board(x: Int, y: Int) {
    lazy val full =
      (for (_y <- 1 to y; _x <- 1 to x) yield Square(_x, _y)).toList
  }

}
