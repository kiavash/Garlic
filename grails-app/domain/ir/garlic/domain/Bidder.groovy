package ir.garlic.domain

class Bidder {

  String firstName
  String lastName

  static hasMany = [items: Item]

  static constraints = {
    items(nullable: true)
  }

}
