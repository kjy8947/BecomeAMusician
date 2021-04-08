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


## Phase 4: Task 3

- Since chords and scales are very similar in their class structures (i.e. necessary methods), it would have been nice 
to have an abstract class that both Chord and Scale classes can extend.
- In Chord class: The only differences between buildMajorTriadChord(int root) and buildMinorTriadChord(int root) methods
 are the method called on the Note object (i.e. note), one number used for the calculation (i.e. 4 for major chord and 3
  for minor chord for the middle note in a chord), and some words in the else statement. It will look nice if I 
  extracted a method out of it.
- In Scale class: The same thing as what I wrote above could have been done for buildMajorScale(int roo) and 
buildMinorScale(int root) methods.
- BecomeAMusicianGUI: Just like the 2 points above, there are lots of methods that have exactly the same structure 
in my codes, due to the way I designed the Note class (i.e. uppercase letters for notes used in the major keys and 
lowercase letters for notes used in the minor keys). randomMajorChordQuiz() and randomMinorChordQuiz() are exactly the 
same -- even their helper methods -- so the lines of codes between the setup of the JPanel can be extracted. The same 
for the helper methods (i.e. randomMajor(/Minor)ChordQuizButtonClicked(jb, cb, randomNote)).
- BecomeAMusicianGUI: A method can be extracted for the 4 helper methods in initializeComponentsForEditTheList() method.
 The extracted method can have 2 parameters that will determine whether to add/remove a chord, as well as whether to 
 print "major chord" or "minor chord" in its message dialog. By doing this, lots of duplications can be removed.
- It would have been nice to create a separate class each for the quiz methods and editTheList() method in the GUI 
class. I couldn't do this due to my lack of skills, but it's something that I'd like to try if I had more time and 
brain power.