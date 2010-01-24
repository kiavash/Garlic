import ir.garlic.domain.Item
import ir.garlic.domain.Bidder

class BootStrap {

  def init = { servletContext ->

    def bidder1 = new Bidder(firstName: "Kiavash", lastName: "Shidani")
    def item1 = new Item(title: "Ardavan")
    bidder1.addToItems(item1)

    bidder1.save()

  }
  def destroy = {
  }

} 