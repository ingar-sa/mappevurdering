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