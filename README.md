# Road-Trip
Designed a trip planner algorithm that finds a shortest route that goes through 
all desired cities exactly once. This trip planner also allows the user to specify 
order of two cities which they would like to visit on their itinerary. 

###MajorCities.txt file
This is a text file used to specify the cities and their traveling distances.

The	first	line	of	the	file	is	a	space-separated list	of	the	names	of	the	cities in	the	itinerary.		If	a	city’s	name	contains two	
or	 more	 words,	words are concatenated	 to	 form	 the	 city	 name	 (e.g.	 New	 Orleans	 would	 be	 represented	 as	
NewOrleans).	

The	second	line	contains	2 cities	from	the	list	above	that	must	be	visited	in	the	order	specified.		For	instance,	if	the	line	
contains	 “Dallas	 Chicago”,	 the	 program	 must	 produce	 a	 route	 where	 Dallas	 is	 visited	 before	 Chicago,	not 
one	immediately	after	the	other.	To	denote	that	order	does	not	matter,	the	second line should	contain	the	string	value	“none”.	

The	 rest	 of	 the	 file	 is	 essentially	 the	 adjacency	 matrix	 of	 the	 city	 graph,	 with	 the	 weights	 representing	 the	 
driving	distance	 between	a	 pair	 of	 cities	 (e.g.	w1_3	 is	 the	 distance	 between	 city	 1	and	 city	 3).	 
Distances are	expressed	as	nonnegative	integers	and	are separated	by	a	space.

###Instructions to run this program using command line
java <MyPlanner> <filename.txt> <start>
<MyPlanner> is	the	name	of	the	main	Java	class
<filename.txt> is	the	text	file	containing	the	adjacency	matrix	as	specified	before
<start> is	the	name	of	the	starting (and	ending) city

###Brute Force Approach
Brute force approach is experimented. According to the results it was observed that brute force approach is not efficient for this problem.
When the number of cities increases, the execution time also increases.  
