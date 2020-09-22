# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. After the importing is complete, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the below:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
##What can you do with Duke?

The following list some of the functions that you can do with Duke
1. Keep track of deadlines, events and items to do.
2. Find out what is happening on a particular date
3. List down all events that you have to do
4. Find specific events using a certain keyword

##Conventions Used

The following table shows the list of conventions used when describing each function operation

`function name` - shows input that you need to type exactly in the terminal. In this case, type `function name`

`<Compulsory User Input>` - shows input that you will need to provide your own values or words. Program will fail if information is not provided

`[Optional User Input]` - shows input that you can choose to provide your own values. Input for these values are optional.
##Function List

Below is a table containing the possible commands and a brief description of how to use them

Function Name | Function Description
------------ | -------------
bye | terminates the program and saves all recorded information into `~/data/duke.txt`
deadline | creates a deadline task with the date of deadline stored
delete | deletes away a Task from your Task list
done | mark a Task on your task list as done
event | creates an event with the date of the event occurring stored
find | prints out all events that contains a particular sequence of words
list | prints out the list of Tasks stored on your personal list currently
todo | creates a todo Task 

###Bye
terminates the program and saves all recorded information into `~/data/duke.txt`
####Syntax
`bye`

###Deadline
creates a deadline task with the date of deadline stored
####Syntax
`deadline <Task name> /by <Date> [time]`

`<Task name>` - Name of the task that has a deadline. E.g. Homework
`<Date>` - A date in the form yyyy:mm:dd where the deadline will be set. E.g. 2020-03-09
`[Time]` - Optional Time information containing the time when the deadline must be met. If no time provided, assume that time of deadline is set to 12 midnight.

####Example
`deadline Homework /by 2020-04-03 13:00` - creates a deadline task called "Homework" that has a deadline set on 03 April 2020 at 1pm

`deadline Homework /by 1998-02-03` - creates a deadline task called "Homework" that has a deadline set on 03 February 1998 at 12 midnight.

###Delete
deletes away a Task from your Task list

####Syntax
`delete <index>`

`<index>` - a number matching the index number of the item to be deleted from the list

####Example
`delete 3` - deletes the Task with index number 3 from your list of Tasks

###Done
Mark a Task on your task list as done

####Syntax
`done <index>`

`<index>` - a number matching the index number of the item to be marked as done

####Example

`done 2` - marks the Task with index number 2 from your list as completed 

###Event
Creates an event with the date of the event occurring stored

####Syntax
`event <Task name> /at <Date> [time]`

`<Task name>` - Name of the Event. E.g. Christmas 2020

`<Date>` - A date in the form yyyy:mm:dd when the event will occur. E.g. 2020-03-09

`[Time]` - Optional Time information containing the time when the event will occur. If no time provided, assume that time of event occurring is set to 12 midnight.

####Example
`event Christmas /by 2020-12-25 13:00` - creates an event task called "Christmas" that occurs on 25 December 2020 at 1pm

`event Birthday /by 1998-02-03` - creates an event task called "Birthday" that occurs on 03 February 1998 at 12 midnight.

###Find
prints out all events that contains a particular sequence of words

####Syntax
`find <Word Sequence>`

`<Word Sequence>` - Sequence of words user enters. Program will present all Task entries containing those sequence of words.

####Example
Assuming you have the following items in your list, shown below in this table:

Index No. | Event Type | Status | Name of Task | date
---------- | ---------- | ----------- | ----------- | ----------
1 | Todo | Not Done | Homework | N.A.
2 | Deadline | Done | Cook Dinner | 2010-03-01 12:30
3 | Event | Not Done | Hari Raya Puasa | 2021-05-12 00:00
4 | Event | Not Done | Party | 2010-03-01 14:15

`find Cook` - will list out all events with the word "Cook" as shown below
```
Here are the list of activities that contain the word "Cook":

2.[D][✓] Cook Dinner (by: 2010-03-01 12:30)Session saved!
```


###List
prints out the list of Tasks stored on your personal list currently

####Syntax
`list [Date]`

`[Date]`- Optional Date information in the form yyyy-mm-dd. If provided, cause the program to list down all events and deadlines that occur on that date.

####Example
Assuming you have the following items in your list, shown below in this table:

Index No. | Event Type | Status | Name of Task | date
---------- | ---------- | ----------- | ----------- | ----------
1 | Todo | Not Done | Homework | N.A.
2 | Deadline | Done | Cook Dinner | 2010-03-01 12:30
3 | Event | Not Done | Hari Raya Puasa | 2021-05-12 00:00
4 | Event | Not Done | Party | 2010-03-01 14:15

`list` - will list out all the events on the screen in the following format
```
Here are the tasks in your list:
1.[T][✗] Homework
2.[D][✓] Cook Dinner (by: 2010-03-01 12:30)
3.[E][✗] Hari Raya Puasa (at: 2021-05-12 00:00)
4.[E][✗] Party (at: 2010-03-01 14:15)
```

`list 2010-03-01` - will list out all the events on the screen that falls on the 01 March 2010 in the following format
```
Here are all the activities that are conducted on this date: 2010-03-01

2.[D][✓] Cook Dinner (by: 2010-03-01 12:30)
4.[E][✗] Party (at: 2010-03-01 14:15)
```

###Todo
creates a todo Task 

####Syntax
`todo <Task Name>`

`<Task Name>` - name of the todo task. E.g. Eat dinner.

####Example
`todo Eat dinner` - creates a todo task with the name "Eat dinner" and adds it to the list




