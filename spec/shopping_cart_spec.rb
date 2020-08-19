require_relative '../shopping_cart/shopping_cart.rb'


#-  Given i have an empty shopping cart, i should be able to add products

# - Given i have added 2 Dove Soaps with unit price 20/- to my cart, the cart should show
# - Total price of 40/
# - 2 Dove soaps added


# - Given i have a cart with 2 Dove Soaps in it and i add another Dove soap, the cart should show
# - Total price of 60/-
# - 3 Dove soaps added


# 2nd Use case

# - Given i have added 2 Dove Soaps(20/-) and 3 Axe Deo(50/-), the cart should show
#     - Total price of 190/-
#     - 2 Dove soaps and 3 Axe deo's


describe ShoppingCart do
  context 'given empty shopping cart' do
    it 'should able to add product' do
      shopping_cart = ShoppingCart.new;
      expect(shopping_cart.add('Dove soaps',1, 20)).to match_array([1, 20]);
    end

    it 'should not add if quantity is less than 1' do
      shopping_cart = ShoppingCart.new;
      expect{ shopping_cart.add('Dove soaps',0, 20) }.to raise_error('Quantity is invalid')
    end
  end

  context 'given that added 2 dove soaps with unit price' do
    it 'should return total dove soaps and total price' do
      shopping_cart = ShoppingCart.new;
      expect(shopping_cart.add('Dove soaps',2, 20)).to match_array([2, 40])
    end
  end

  context 'given cart have 2 dove soaps in it and trying to add one more dove soap' do
    it 'should return total price and total dove soaps' do
      shopping_cart = ShoppingCart.new;
      shopping_cart.add('Dove soaps',2, 20)
      expect(shopping_cart.add('Dove soaps',1, 20)).to match_array([3, 60])
    end
  end

  context 'given cart have 2 dove soaps and try to add 3 Axe deo' do
    it 'should return total cart price and product wise quantity' do
      shopping_cart = ShoppingCart.new;
      shopping_cart.add('Dove soaps', 2, 20)
      shopping_cart.add('Axe deos', 3, 50)
      expect(shopping_cart.get_cart_details).to match('2 Dove soaps and 3 Axe deos')
      expect(shopping_cart.total_price).to eq(190)
    end
  end

end
