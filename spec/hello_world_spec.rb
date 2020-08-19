require_relative '../hello_world.rb'

describe HelloWorld do
  context 'Hello message' do
    it 'should return hello message with given name' do
      hello_world = HelloWorld.new
      expect(hello_world.say_hello 'John').to match('Hello John')
    end
  end
end
