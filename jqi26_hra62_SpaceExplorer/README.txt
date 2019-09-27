To run the source code, firstly open a workspace in Eclipse that contains
the project folder. Click File->New->Java Project then 
untick the box "Use default location". Click Browse and choose the location of where
the project folder has been installed, click the Space_Explorer folder, click OK, then click Finish.
The program should be run from the GameEnvironment class. Any errors relating
to importing MigLayout from net can be solved by opening the java file
with the error in WindowBuilder and clicking MigLayout under Layouts in
the Design tab. 

If there are any errors such as String not being recognised,
it is likely because the JRE library in the build path has an error. This
can be fixed by right clicking the project in Eclipse, go to Build Path
-> Configure build path. Go to the Libraries tab and remove the JRE 
System Library. Then click Modulepath, click add Library -> JRE System Library
-> next, and ensure Workspace default JRE is ticked. Click Finish, and the errors
should be resolved.

The JAR can be run by ensuring Java 11 is installed then simply entering 

java -jar jqi26_hra62_SpaceExplorer.jar

in the terminal while inside the extracted project folder containing the jar.
