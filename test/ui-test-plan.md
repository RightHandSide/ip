# UI Test Plan

This file is the source of truth for automated console UI test cases. Each
test case starts a new Yuno process, and its commands are entered in order.

## Test Case: Exit immediately

### Aim

Verify that Yuno displays its greeting and exits cleanly when the first command
is `bye`.

### Inputs

```text
bye
```

### Expected Output

```text
__________________________________________________
__   __ _   _ _   _  ___
\ \ / /| | | | \ | |/ _ \
 \ V / | | | |  \| | | | |
  | |  | |_| | |\  | |_| |
  |_|   \___/|_| \_|\___/

I'm Yuno.
Can we just get this over quickly?
__________________________________________________
Finally! Bye, I'm going!
__________________________________________________
```

## Test Case: Reject malformed commands

### Aim

Verify that Yuno reports common command errors, continues accepting commands,
and exits normally afterward.

### Inputs

```text
nonsense
todo
deadline submit report
event meeting /from Monday
event meeting /to Tuesday /from Monday
mark abc
mark 0
list extra
bye extra
bye
```

### Expected Output

```text
__________________________________________________
__   __ _   _ _   _  ___
\ \ / /| | | | \ | |/ _ \
 \ V / | | | |  \| | | | |
  | |  | |_| | |\  | |_| |
  |_|   \___/|_| \_|\___/

I'm Yuno.
Can we just get this over quickly?
__________________________________________________
Did you look at what you are typing? It's just a random string of command.
__________________________________________________
If you have no task, please don't bother me.
__________________________________________________
If you are not constrained by a date, use another task type.
__________________________________________________
If your task does not have a start and end time, use another task type.
__________________________________________________
Your order is wrong. Check it before wasting my time.
__________________________________________________
Did you even give me an integer? Please don't waste my time!
__________________________________________________
Are you wasting my time? The integer you gave is out of bounds.
__________________________________________________
Please don't enter irrelevant details.
__________________________________________________
Please don't enter irrelevant details.
__________________________________________________
Finally! Bye, I'm going!
__________________________________________________
```

## Test Case: Manage tasks successfully

### Aim

Verify that valid task commands still work after exception handling is added.

### Inputs

```text
todo read book
deadline submit report /by Sunday
event meeting /from Monday /to Tuesday
mark 1
unmark 1
list
bye
```

### Expected Output

```text
__________________________________________________
__   __ _   _ _   _  ___
\ \ / /| | | | \ | |/ _ \
 \ V / | | | |  \| | | | |
  | |  | |_| | |\  | |_| |
  |_|   \___/|_| \_|\___/

I'm Yuno.
Can we just get this over quickly?
__________________________________________________
Added:
[T][ ] read book
Just another task you would not finish.
__________________________________________________
Added:
[D][ ] submit report (by: Sunday)
Just another task you would not finish.
__________________________________________________
Added:
[E][ ] meeting (from: Monday to: Tuesday)
Just another task you would not finish.
__________________________________________________
You actually completed a task? Bet it's the only task you would complete.
[T][X] read book
__________________________________________________
Wow! So you lied about completing it? Typical behavior.
[T][ ] read book
__________________________________________________
Wow. Look at how slow you are at completing these tasks.
1. [T][ ] read book
2. [D][ ] submit report (by: Sunday)
3. [E][ ] meeting (from: Monday to: Tuesday)
__________________________________________________
Finally! Bye, I'm going!
__________________________________________________
```

## Test Case: Delete tasks

### Aim

Verify that deleting tasks displays the removed task, updates the list, handles
the last task, and rejects invalid task numbers.

### Inputs

```text
todo first task
todo second task
delete 1
list
delete 1
list
delete 1
delete abc
bye
```

### Expected Output

```text
__________________________________________________
__   __ _   _ _   _  ___
\ \ / /| | | | \ | |/ _ \
 \ V / | | | |  \| | | | |
  | |  | |_| | |\  | |_| |
  |_|   \___/|_| \_|\___/

I'm Yuno.
Can we just get this over quickly?
__________________________________________________
Added:
[T][ ] first task
Just another task you would not finish.
__________________________________________________
Added:
[T][ ] second task
Just another task you would not finish.
__________________________________________________
Wow! Did you give up, or did you actually finish it?
[T][ ] first task
__________________________________________________
Wow. Look at how slow you are at completing these tasks.
1. [T][ ] second task
__________________________________________________
Wow! Did you give up, or did you actually finish it?
[T][ ] second task
__________________________________________________
Wow. Look at how slow you are at completing these tasks.
__________________________________________________
What do you want me to delete, your brain? The integer you gave is out of bounds.
__________________________________________________
Did you even give me an integer? Please don't waste my time!
__________________________________________________
Finally! Bye, I'm going!
__________________________________________________
```
