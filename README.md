# Become A Musician

## Phase 1 - *Proposal*

This application is designed to help users with memorizing chords/scales. This educational app is
for anyone who is willing to study music theory. Users can create a character and level them up by earning points for
each chord (scale to be added in the later version) they have memorized.


## User Stories

- As a user, I want to be able to view the list of notes that belongs to the chords/scales I choose.
- As a user, I want to be able to take quizzes to test my knowledge in chords I studied for.
- As a user, I want to be able to add chords to my list of chords to memorize.
- As a user, I want to be able to view the chords I've added to the list of chords to memorize.
- As a user, I want to be able to view the points the character I'm playing has accumulated.
- (phase 2) As a user, when I select the quit option from the application menu, I want to be reminded to save my list of
chords to memorize.
- (phase 2) As a user, when I start the application, I want to be given the option to load my previously-played 
character's list of chords to memorize.
- (phase 3) As a user, I want to be able to save my points and load it next time I play.
- (phase 3) As a user, I want to be able to remove chords from my list of chords to memorize.


## Phase 4: Task 2

I'm using the **Map interface** in one of my model classes, **Note**.

The following 4 methods in the Note class: (1) setNotesIntToStrForMajor(), (2) setNotesIntToStrForMinor(), 
(3) setNotesStrToIntForMajor(), and (4) setNotesStrToIntForMinor(), each have a hashmap with 12 key & value pairs. 
Each of the 4 maps has a method that converts either an int to a String or a String to an int. These 4 methods are 
named: (1) getNoteForMajor(int key), (2) getNoteForMajor(String key), (3) getNoteForMinor(int key), (4) getNoteForMinor
(String key). These methods are used in other model classes (i.e. Chord, Scale) that depend on the Note class, as well 
as in BecomeAMusician (ui) and BecomeAMusician (gui) classes.  
TMI: The purpose of using the maps was: (when the inputs are String: ) to read user's inputs (i.e. note names) and to 
convert these to number values, which enable calculating the intervals between the notes for major/minor chords/scales; 
(when the inputs are int: ) users will have no idea if they were given, for example, '4' instead of a note, C/c. 
By converting the number value to its corresponding String value, users will be given an actual note name.