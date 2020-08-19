class ShoppingCart
  attr_accessor :products_quantity, :total_price

  def initialize
    @products_quantity = {}
    @total_price = 0.0
  end

  def add product_name, quantity, unit_price
    raise 'Quantity is invalid' if quantity < 1
    @products_quantity[product_name] = @products_quantity[product_name].to_i + quantity.to_i
    @total_price += (quantity.to_i * unit_price.to_f)
    [@products_quantity.values.sum, @total_price]
  end

  def get_cart_details
    product_details = ""
    @products_quantity.map{|name, quantity| "#{quantity} #{name}"}.join(' and ')
  end
end
