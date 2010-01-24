package ir.garlic.domain

class Item {

  String title
  static belongsTo = [bidder: Bidder]
}
