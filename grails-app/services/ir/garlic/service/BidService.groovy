package ir.garlic.service

import ir.garlic.domain.Bidder

class BidService {

  boolean transactional = true
  static expose = ["gwt:ir.garlic.client"]

  String getBidder(int id) {
    def bidder = Bidder.get(id)
    return bidder.encodeAsJSON()
  }

}
