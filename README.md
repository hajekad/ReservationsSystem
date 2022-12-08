# BI-TJV-semestral

## Description
Simple reservation system for coaches and their trainees. A coach can set his operating hours and pick a place, a trainee can book coaches offered term.
## Business operations
- Creation of coaches new operating time segment.
- Booking a training with a coach.
- Both coach and trainee can delete a planned training.
- Coach can alter the cost rate of each training.
## More complex query
- Selecting most active trainees in a specific time range in relation to specific coach.
	-  Selects all trainings that are in specific time range.
	- Filters out trainings that are not organized by a specified coach.
	- Selects the most active trainee from the previous result.
	- Sends the result to the user.
	- User can now add a secondary coach to the list of coaches from which we get their trainees statistics. 
## Details
- multiple trainees can attend a single training

## Database
<img src="./images/diagram.png">
