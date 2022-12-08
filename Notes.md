**Part 1**
Robustness of the class:
Added asserts in the constructor to ensure
that the relevant fields are assigned positive numbers.
The check for the item quantity needs to be
different than those for the weight, length, etc. because the item quantity can
be zero. I have decided that having length,
height, weight and pricce be zero does not
make sense, so I assert that they are greater than zero.
The user is also informed that the numbers
must be positive in the constructor.
The copy constructor does not need this,
as an item passed into it will always have
valid values.

_Krav til rapport_:
I have chosen the datatypes to be exactly those give in the 
exercise requirements.

I have chosen to add mutator methods for the price and quantity.
Changing the height, length, weight, brand, category, id or decription
does not make sense. If any one of these changes, it should become a new item.
You can make an argument that color should be mutable, and that it should still
be the same item in this case. However, this only works if an item can only be one
color at a time. If an item can be multiple colors, then you need some way to
distinguish them, and the only way to do that with the current implementation
is to have a different item.

Without some form of standardized regex for the id, it is impossible to check
if this is correct.
The description is also free text, so
it is also impossible to assure some form of validity for this field. 
Same goes for the brand and color. 
Since the category is either 1, 2, 3, or 4, I check for this in
the constructor. I also assert that the item quantity is greater than or equal 
to zero. For price, weight, length and height I have decided that the value must 
be *greater* than zero, since e.g. zero length does not make sense, and check for this. You could make an argument that price should be able to be zero, to indicate some form of sale or other property, however, using the price to indicate some other property of the item increases ambiguity and should be avoided.

I have chosen to have mutator methods for the price and the quantity. Changing
the price makes sense, since items can go on sale or the price needs to be 
adjusted for inflation, for example. To change the quantity is inherently necessary, 
since the whole point of the wms is to keep track of which items are available to sell.
I have used the asserts used in the constructor to assure the values are valid.

Another way I ensure robustness, is saying explicitly in the javadoc what values are
accepted in the methods and the constructor.

**Part 2**
The client has an inventory. The inventory has items.
This ensures composition and not aggregation, which
is what makes sense in this case.

Print all products:
Probably don't want to use a toString for this,
but rather a custom format. I think it is best that
the printing is done from the Inventory class as well,
instead of the inventory class returning the data from
the products.

Finding by id and/or descriptioin:
The way this makes sense to me is that they meant
that you only have to implement either type, but that
doesn't seem quite right either. I am going to implement
two separate methods.
In both of them, you shouldn't have to input the exact
text that is stored in the product, rather the input is
checked for all items, and if it contains it, returns the item.
This makes sense to me from a usability perspective.

In functions where you have to look up an item, a possible 
implementation is that you use either id search or description
search, and then present a list of the results. You can then
choose one by a number. It requires a relatively complicated
relationship between the client and the inventory class but
it is quite user friendly.

Might need separate functions for internal search and
external search (searching from the client)
Might make one method were it checks if either
the id or the description matches
All id's have uppercase letters and the search
for id's is case sensitive.
Search for description is case insensitive

Finding by id:
Take in a string
If any id *starts* with the string, it is returned.
For example: P1id: ABC1234, P2id: ABC234
Input: A, AB or ABC will return both
Input: ABC1 will return P1, ABC2 will return P2
Input: Anything *not* starting with A will return nothing

Finding by description:
Take in a string
Case insensitive
Any item that contains the string *as a whole symbol,*
*or a series of symbols, split by spaces*  will be returned
For example: P1des: Shoe color red, P2des: Shoe color blue
Input: "Shoe" or "Shoe color" will return both
Input: "red" returns p1, "blue" returns P2
Input: "hoe", "col", "e" returns nothing


Adding a product:
The client gets information from the user and calls
a method in the inventory that adds a product from that
data.

Increase product quantity:
Use the either of the search methods

Decrause product quantity:
Take in an id and quantity
Decrease its quantity with the input

Deleting a product:
What makes most sense to me is that the user inputs the product
id, and it is deleted from that. Since you are able to search
by description, it might make sense to be able to delete a 
product by that, but I don't think that is a good idea.
Multiple products may contain the string you are searching
for when searching through the descriptions, so it is not
specific enough for deleting a product.
Should probably call the findProductById method

Different methods for changing all the fields

Client:
I'm not sure whether or not I want to use the boxes or not.
Maybe I should just use them for getting certain types of input,
since it makes it very clear where you are supposed to type.
However, that might make a disjointed experience, since you are
moving between using the terminal and the boxes. There is also
the issue that with previous implementations, I am creating and
deleting the boxes when tehy have fulfilled their purpose. I
think I should either make a completely GUI program, which is 
time consuming and way outside of the curriculum, or just a pure
CLI. I am definitely leaning towards the CLI route.

Steps:
user chooses option for what they want to do:
  1. print all products
    1. Call print all products method
    2. Start outer loop again.
  2. Find (print) a product
    1. Call search function
    2. Call printSingleProduct function on inventory
    3. Start outer loop again
  3. Add a product
     1. 
  4. Increase item quantity
     1. Call search function
     2. Once single item is chosen, ask how many they want to increase by
     3. Call increase item function on inventory
     4. Handle case where the input is invalid (might not be necessary)
     5. Start outer loop again
  5. Decrease item quantity: same as above
  6. 

private functions:
2. Search for an item
     1. Write infor about how the search workds
     2. Pass user string to inventory's search function
     3. Check if the list is empty
     4. Yes: print that there is no such item. Start outer loop again.
     5. No: print an option list with the different items (doesn't matter if there is just one, they choose regardless)
     6. return item id
   
hashmap verification of inputs:
You would need an array of all of the different messages
loop through it and print the message for each one
wait for user input
exit if it is --exit 
store it as a string in a hashmap

pass the hashmap into the inventory
inventory loops through the hashmap
if it is not an empty string
this is already incredibly convoluted
ifs it is
   
**Ideas**
Use hashmap with the id's as keys, since this has O(1) lookup time.
This also works since strings are immutable objects.
Counterargument to this is that there will never be enough different items
that linear lookup time is not fast enough.
Another is that the user will have the ability to look up an item based
on different qualities it has. Using the id as the key in a hashmap does
provide a very convenient way to find the item based on this, yes, but
it will make looking up based on other qualities harder, since you have
to iterate over the values in the hashmap for everything else. Therefore
I think it makes most sence to use an array based container.

**WTF...**
Apparently there is a max distance between a variable declaration and its first
use that is allowed, which is kinda dumb if 

**Del 2**
Loose coupling
There might be tight coupling in the edit and add methods in the client.
This is because what input the client is supposed to take in is completely dependent on what parameters the inventory's method has.
However, you can argue that this is not an issue because the design of this program is decided by what the client's functionality is supposed to be.
It is therefore the inventory's method that must change with changes in the client, not the other way around.
There is also an argument to be made about cohesion.
Designing these methods with completely loose coupling would require the Inventory to read user input.
Is this really appropriate functionality for it? 

Cohesion.
The inventory class and Product class are well designed from a cohesion perspective.
The inventory is a collection of Products, and performs operations that match what you would do on a collection of products.
The client might have possibilities for improved cohesion.
The client provides the UI.
This includes showing the user what options they have, and getting the input from the user.
Is it possible that the functionality of using that input should be passed on to another class?
The main problem with this, is that each functionality the user can use requires more input to be had from the user.
This means that any new class that takes responsibility for one or more function will also need to take responsibility for getting the user input, unless we want an extremely convoluted setup for interaction between the client and this new class.
Since each function requires the class to take input, it thereby takes over functionality that was originally the client's task.

