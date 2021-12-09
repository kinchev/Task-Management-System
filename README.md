# Task Management project

---

##Project description
This project implements a Tasks Management console application.

The application will be used by a small team of developers, who need to keep track of all the tasks, surrounding a software product they are building.

**Programing language:**   
**Java**
---
##Operations
**The application supports the following operations:**

· Create a new person.  
· Show all people.  
· Show person's activity.  
· Create a new team.  
· Show all teams.  
· Show team's activity.  
· Add person to team.  
· Show all team members.  
· Create a new board in a team.  
· Show all team boards.  
· Show board's activity.  
· Create a new Bug/Story/Feedback in a board.  
· Change the Priority/Severity/Status of a bug.  
· Change the Priority/Size/Status of a story.  
· Change the Rating/Status of a feedback.  
· Assign/Unassign a task to a person.  
· Add comment to a task
---
##Listing operations:

List all tasks (display the most important info).

    · Filter by title
    · Sort by title

List tasks with assignee.

    · Filter by status and/or assignee  
    · Sort by title

---
## Input
```
createteam team1
createboard team1 board1
createmember member1 team1
createbug team1 board1 FirstBugItem bug1Description Active Medium Major
createbug team1 board1 SecondBugItem bug1Description Active Medium Major
createbug team1 board1 ThirdBugItem bug1Description Fixed Medium Major
createstory team1 board1 SecondStory story1Description Done Low Medium
createfeedback team1 board1 ThirdFeedback feedback1Description New 3
showtasks
showtasks FirstBugItem
assigntask 1 member1
assigntask 2 member1
assigntask 3 member1
assigntask 4 member1
assigntask 2 member1
showassignables -a member1 -s Active
showassignables -a member1
showassignables -s Active
showassignables
addcomment 1 author1 {{this is first comment}}
addcomment 1 author1 {{this is secondcomment}}
printcomments 1
removecomment 1 author1 {{this is first comment}}
printcomments 1
addstep {{First, you should do this.}} 1
addstep {{Second, you should do that.}} 1
showsteps 1
removestep {{First, you should do this.}} 1
showsteps 1
changestatus 1 fixed //bug status w msg
changebugseverity 1 critical
changebugpriority 1 high
changestorypriority 4 low
changestorysize 4 large
showworkitemactivity 1
showworkitemactivity 2
showworkitemactivity 3
showmembers team1
showmemberactivity member1
showboardactivity team1 board1
changestatus 1 fixed
changerating 5 5
exit
```
---
## Output
```
Team team1  created successfully!
####################
Board board1 created successfully
####################
Member member1 created successfully
####################
Bug FirstBugItem created successfully
####################
Bug SecondBugItem created successfully
####################
Bug ThirdBugItem created successfully
####################
Story SecondStory created successfully!
####################
Feedback created successfully!
####################
FirstBugItem
SecondBugItem
SecondStory
ThirdBugItem

####################
FirstBugItem

####################
Task [1] was assigned successfully to member1!
####################
Task [2] was assigned successfully to member1!
####################
Task [3] was assigned successfully to member1!
####################
Task [4] was assigned successfully to member1!
####################
Task [2] was assigned successfully to member1!
####################
FirstBugItem
SecondBugItem

####################
FirstBugItem
SecondBugItem
SecondStory
ThirdBugItem

####################
FirstBugItem
SecondBugItem

####################
FirstBugItem
SecondBugItem
SecondStory
ThirdBugItem

####################
Comment added successfully!
####################
Comment added successfully!
####################
Task [FirstBugItem] Comments: 
[this is first comment] by author1
[this is secondcomment] by author1

####################
Comment remove successfully
####################
Task [FirstBugItem] Comments: 
[this is secondcomment] by author1

####################
Step First, you should do this. added successfully
####################
Step Second, you should do that. added successfully
####################
TaskId: 1
Steps to reproduce: 
1. First, you should do this.
2. Second, you should do that.
####################
Step removed
####################
TaskId: 1
Steps to reproduce: 
1. Second, you should do that.
####################
Status change successfully
####################
Bug severity changed successfully!
####################
Bug priority changed successfully!
####################
Story priority changed successfully!
####################
Story size changed successfully
####################
WorkItem 1
[31-August-2021 01:35:29] Comment [this is first comment] was successfully added to workitem!
[31-August-2021 01:35:29] Comment [this is secondcomment] was successfully added to workitem!
[31-August-2021 01:35:29] Comment was successfully removed!
[31-August-2021 01:35:29] Status changed from Active to Fixed
[31-August-2021 01:35:29] Severity changed from Major to Critical
[31-August-2021 01:35:29] Priority changed from Medium to High
####################
No activity in work item #2.
####################
No activity in work item #3.
####################
Team: team1
Members: 
member1
####################
Member: member1
Activity history:
[31-August-2021 01:35:29] Task with 1 was assigned to member1.
[31-August-2021 01:35:29] Task with 2 was assigned to member1.
[31-August-2021 01:35:29] Task with 3 was assigned to member1.
[31-August-2021 01:35:29] Task with 4 was assigned to member1.
[31-August-2021 01:35:29] Task with 2 was assigned to member1.
####################
Team: team1
Board: board1
[31-August-2021 01:35:29] Bug [FirstBugItem] was successfully added to board board1!
[31-August-2021 01:35:29] Bug [SecondBugItem] was successfully added to board board1!
[31-August-2021 01:35:29] Bug [ThirdBugItem] was successfully added to board board1!
[31-August-2021 01:35:29] Story [SecondStory] was successfully added to board board1!
####################
Cannot change status, already at [Fixed].
####################
Rating changed successfully!
####################
```
