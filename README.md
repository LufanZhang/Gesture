Gesture
=======

an android app
 I made an app which is involved with gesture. 
write an app to show the touch position using a circle of a finger on the Android device screen. The circle should be large 
enough so it can be seen under the finger.
I created a new class which extends SurfaceView and implements SurfaceHolder.Callback. When open this app, a textView will show up 
with the black background. And there will be no change before users touch the screen.
After users touch the screen, the android phone will get this signal and call onTouchEvent function. And lock current canvas first
and then get the id, x and y position of the pointer and show them on the screen via TextView. Finally, draw corresponding lines 
and circle according to the position information. In order to make sure users can see the circle which under their fingers, I can
adjust the argument in drawCircle function.
(2)
Detect the movements of the figure to show the new position of the touch of the figure during motion. And the pointer ID, X, Y 
position of the touch must be continuously shown while the figure move on the screen. Also, the circle should be moved according
to the figure move, remember that the circle always centered the touch position of the figure.
In order to monitor multiple motive pointers on the screen, I created an array to store these paint variables. And in order to 
differentiate them, I created an array of color and initiate this array to let different paints have different color according to
their paint ID.
(2) 
In order to limit the max fingers(up to 5) on the screen, when call onTouchEvent, check how many fingers on the screen first and 
the number of fingers beyond 5, ignore extra fingers.


