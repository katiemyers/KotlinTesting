package SockSorting

enum class Foot {
  LEFT,
  RIGHT,
}

data class Sock constructor(
  val colour: String,
  val foot: Foot,
)

data class MutableSockLists constructor(
  val unmatchedSocks: MutableList<Sock>,
  val matchedSocks: MutableList<Pair<Sock, Sock>>
)

data class SockLists constructor(
  val unmatchedSocks: List<Sock>,
  val matchedSocks: List<Pair<Sock, Sock>>
)

fun sortSockByColourOnly(sockList: List<Sock>): SockLists {
  val sockMutableList: MutableList<Sock> = sockList.toMutableList()
  val sockMatchedList: MutableList<Pair<Sock, Sock>> = mutableListOf<Pair<Sock, Sock>>()
  sockList.forEach {
    // If in current viable sock list
    if (it in sockMutableList) {
      // Remove it from the sock list
      sockMutableList.remove(it)
      // Find a possible colour pair or return null
      val pairSock = sockMutableList
        .stream()
        .filter { sock -> sock.colour == it.colour }
        .findFirst()
        .orElse(null)

      // If there is a pair, remove the pair from the viable list
      //  and add a pair to the matched list
      if (pairSock != null) {
        sockMutableList.remove(pairSock)
        sockMatchedList.add(Pair(it, pairSock))
      } else {
        // If no match found, add it back to the viable list
        sockMutableList.add(it)
      }
    }
  }
  return SockLists(sockMutableList.toList(), sockMatchedList.toList())
}

fun sortSockByFootOnly(sockList: List<Sock>): SockLists {
  val sockMutableList: MutableList<Sock> = sockList.toMutableList()
  val sockMatchedList: MutableList<Pair<Sock, Sock>> = mutableListOf<Pair<Sock, Sock>>()
  sockList.forEach {
    // If in current viable sock list
    if (it in sockMutableList) {
      // Remove it from the sock list
      sockMutableList.remove(it)
      // Find a possible foot pair or return null
      val pairSock = sockMutableList
        .stream()
        .filter { sock -> sock.foot != it.foot }
        .findFirst()
        .orElse(null)

      // If there is a pair, remove the pair from the viable list
      //  and add a pair to the matched list
      if (pairSock != null) {
        sockMutableList.remove(pairSock)
        sockMatchedList.add(Pair(it, pairSock))
      } else {
        // If no match found, add it back to the viable list
        sockMutableList.add(it)
      }
    }
  }
  return SockLists(sockMutableList.toList(), sockMatchedList.toList())
}

fun sortSocksOptimally(sockList: List<Sock>): SockLists {
  val sockMutableList: MutableList<Sock> = sockList.toMutableList()
  val sockMatchedList: MutableList<Pair<Sock, Sock>> = mutableListOf<Pair<Sock, Sock>>()
  sockList.forEach {
    // If in current viable sock list
    if (it in sockMutableList) {
      // Remove it from the sock list
      sockMutableList.remove(it)
      // Find a possible colour and foot pair or return null
      val pairSock = sockMutableList
        .stream()
        .filter { sock -> sock.colour == it.colour }
        .filter { sock -> sock.foot != it.foot }
        .findFirst()
        .orElse(null)

      // If there is a pair, remove the pair from the viable list
      //  and add a pair to the matched list
      if (pairSock != null) {
        sockMutableList.remove(pairSock)
        sockMatchedList.add(Pair(it, pairSock))
      } else {
        // If no match found, add it back to the viable list
        sockMutableList.add(it)
      }
    }
  }
  val newSockLists = sortSockByFootOnly(sockMutableList.toList())
  sockMatchedList.addAll(newSockLists.matchedSocks)
  return SockLists(newSockLists.unmatchedSocks.toList(), sockMatchedList.toList())
}
