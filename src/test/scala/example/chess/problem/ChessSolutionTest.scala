package example.chess.problem

import org.scalatest.FunSpec

class ChessSolutionTest extends FunSpec {

  describe("chess solution output ") {
    it("check 3X3 Board containing 2 King 1 Queen") {
      val solution = ChessSolution.solveChessProblem(ChessBoard(3, 3), List(PieceInfo(King, 2), PieceInfo(Queen, 1)))
      val resultStr = "*Queen*\n" +
        "***\n" +
        "King*King\n" +
        "\n" +
        "**King\n" +
        "Queen**\n" +
        "**King\n" +
        "\n" +
        "King**\n" +
        "**Queen\n" +
        "King**\n" +
        "\n" +
        "King*King\n" +
        "***\n" +
        "*Queen*\n"
      assertResult(4)(solution.size)
      assertResult(resultStr)(solution.mkString("\n"))
    }
  }

  it("check 4X4 Board containing 2 Rooks and 4 Knights") {
    val solution = ChessSolution.solveChessProblem(ChessBoard(4, 4), List(PieceInfo(Rook, 2), PieceInfo(Knight, 4)))
    val resultStr = "Knight*Knight*\n" +
      "***Rook\n" +
      "Knight*Knight*\n" +
      "*Rook**\n" +
      "\n" +
      "*Knight*Knight\n" +
      "**Rook*\n" +
      "*Knight*Knight\n" +
      "Rook***\n" +
      "\n" +
      "Knight*Knight*\n" +
      "*Rook**\n" +
      "Knight*Knight*\n" +
      "***Rook\n" +
      "\n" +
      "*Knight*Knight\n" +
      "Rook***\n" +
      "*Knight*Knight\n" +
      "**Rook*\n" +
      "\n" +
      "***Rook\n" +
      "Knight*Knight*\n" +
      "*Rook**\n" +
      "Knight*Knight*\n" +
      "\n" +
      "**Rook*\n" +
      "*Knight*Knight\n" +
      "Rook***\n" +
      "*Knight*Knight\n" +
      "\n" +
      "*Rook**\n" +
      "Knight*Knight*\n" +
      "***Rook\n" +
      "Knight*Knight*\n" +
      "\n" +
      "Rook***\n" +
      "*Knight*Knight\n" +
      "**Rook*\n" +
      "*Knight*Knight\n"
    assertResult(8)(solution.size)
    assertResult(resultStr)(solution.mkString("\n"))
  }
}
