package example.chess.problem


object ArgumentParser {

  def parseArgument(args: Seq[String]): List[PieceInfo] =
    args.sliding(2, 2).toList.map(f =>
      f(0).toString match {
        case "-King" | "-king" | "-KING" => PieceInfo(King, f(1).toInt)
        case "-Queen" | "-queen" | "-QUEEN" => PieceInfo(Queen, f(1).toInt)
        case "-Bishop" | "-bishop" | "-BISHOP" => PieceInfo(Bishop, f(1).toInt)
        case "-Knight" | "-knight" | "KNIGHT" => PieceInfo(Knight, f(1).toInt)
        case "-Rook" | "-rook" | "-ROOK" => PieceInfo(Rook, f(1).toInt)
        case _ => sys.error("USAGE: " +
          "All parameters are optional" +
          "./bin/chessSolver -King 2 -Queen 2 -Bishop 3 -Knight 3 -Rook 5")
      })

  def apply(args: Array[String]) = parseArgument(args)
}
