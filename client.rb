require 'rest_client'

puts "Empty? "
puts RestClient.get "localhost:5000/fizbuzz/"

puts "false? "
puts RestClient.get "localhost:5000/fizbuzz/450"

puts "make a new one "
puts RestClient.put "localhost:5000/fizbuzz/450", {}

puts "in the list?"
puts RestClient.get "localhost:5000/fizbuzz/"

puts "does it exist?"
puts RestClient.get "localhost:5000/fizbuzz/450"

puts "Not even?"
puts RestClient.get "localhost:5000/fizbuzz/even/451"

puts "even?"
puts RestClient.get "localhost:5000/fizbuzz/even/450"

