import SockSorting.Foot
import SockSorting.Sock
import SockSorting.sortSockByColourOnly
import SockSorting.sortSockByFootOnly
import SockSorting.sortSocksOptimally

fun main(args: Array<String>) {
  println("What function would you like?")
  val functionName = readLine()

  if (functionName.equals("greeting") || functionName.equals("g")) {
    greeting()
  } else if (functionName.equals("socks") || functionName.equals("s")) {
    sockSorting()
  } else if (functionName.equals("vending") || functionName.equals("v")) {
    vendingMachine()
  } else {
    println("Unknown function $functionName, please try again.")
  }
}

fun sockSorting() {
  val sockList: List<Sock> = listOf(
    Sock("blue", Foot.LEFT),
    Sock("red", Foot.RIGHT),
    Sock("blue", Foot.RIGHT),
    Sock("red", Foot.RIGHT),
    Sock("black", Foot.LEFT),
    Sock("white", Foot.LEFT),
    Sock("green", Foot.LEFT)
  )
  val colourOnly = sortSockByColourOnly(sockList)
  println("Sort by Colour Only")
  println("Matches" + colourOnly.matchedSocks)
  println("Unmatched" + colourOnly.unmatchedSocks)

  val footOnly = sortSockByFootOnly(sockList)
  println("Sort by Foot Only")
  println("Matches" + footOnly.matchedSocks)
  println("Unmatched" + footOnly.unmatchedSocks)

  val colourThenFoot = sortSocksOptimally(sockList)
  println("Sort by Color and Foot")
  println("Matches" + colourThenFoot.matchedSocks)
  println("Unmatched" + colourThenFoot.unmatchedSocks)

}

fun vendingMachine() {

}

fun greeting() {
  println("What is your name?")

  val name = readLine()
  println("Hello {$name}")
}
