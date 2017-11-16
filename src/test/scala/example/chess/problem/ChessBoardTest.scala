
package example.chess.problem

import example.chess.problem.ChessBoard.{Square, Board}
import org.scalatest.FunSpec

class ChessBoardTest extends FunSpec {

  describe("Checking intermediate validation and output") {
    it("move pieces on board") {
      val board = Board(1, 1)
      val square = Square(1, 1)
      assertResult(true)(King.filterCriteria(square, board.x, board.y))
      assertResult(true)(King.filterCriteria(square, board.x, board.y))
      assertResult(true)(Queen.filterCriteria(square, board.x, board.y))
      assertResult(false)(Knight.filterCriteria(square, board.x, board.y))
      assertResult(true)(Rook.filterCriteria(square, board.x, board.y))
      assertResult(true)(Bishop.filterCriteria(square, board.x, board.y))
    }

    it("check vulnerability of pieces") {
      var board = Board(1, 1)
      val square = Square(1, 1)
      assertResult(false)(King.isVulnerable(square, board, List(square)))
      board = Board(2, 2)
      val square1 = Square(1, 1)
      val square2 = Square(2, 2)
      assertResult(true)(King.isVulnerable(square1, board, List(square2)))
      assertResult(false)(King.isVulnerable(square1, ChessBoard(board)
        .addPiece(King, square1)
        .addPiece(King, square2).board, List(square1)))
    }

    it("dumping output match ") {
      val board = Board(8, 8)
      val center = Square(5, 4)
      val corner = Square(1, 1)
      val wall = Square(5, 8)

      assertResult("King*------\n" +
        "**------\n" +
        "---***--\n" +
        "---*King*--\n" +
        "---***--\n" +
        "--------\n" +
        "---***--\n" +
        "---*King*--\n")(ChessBoard(board)
        .addPiece(King, center)
        .addPiece(King, corner)
        .addPiece(King, wall).toString)
    }
  }
}

