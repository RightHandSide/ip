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
Finally! Bye. I'm leaving!
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
Did you look at what you typed? That's just a random command.
__________________________________________________
If you have no task, please don't bother me.
__________________________________________________
If you are not constrained by a date, use another task type.
__________________________________________________
If your task does not have a start and end time, save me some time and use another task type.
__________________________________________________
Your order is wrong. Check it before wasting my time.
__________________________________________________
Did you even give me an integer? Please don't waste my time!
__________________________________________________
Are you wasting my time? The integer you gave is out of bounds.
__________________________________________________
Why are you entering irrelevant details?
__________________________________________________
Why are you entering irrelevant details?
__________________________________________________
Finally! Bye. I'm leaving!
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
Just another task you won't finish.
__________________________________________________
Added:
[D][ ] submit report (by: Sunday)
Just another task you won't finish.
__________________________________________________
Added:
[E][ ] meeting (from: Monday to: Tuesday)
Just another task you won't finish.
__________________________________________________
You actually completed a task? Bet it's the only task you'll ever complete.
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
Finally! Bye. I'm leaving!
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
Just another task you won't finish.
__________________________________________________
Added:
[T][ ] second task
Just another task you won't finish.
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
Wow, not even a single task? You are so lazy.
__________________________________________________
What do you want me to delete, your brain? The integer you gave is out of bounds.
__________________________________________________
Did you even give me an integer? Please don't waste my time!
__________________________________________________
Finally! Bye. I'm leaving!
__________________________________________________
```

## Test Case: Load stored tasks containing delimiters

### Aim

Verify that Yuno loads every task type with its status intact and reconstructs
descriptions that contain the storage delimiter.

### Inputs

```text
list
bye
```

### Initial Data

```text
T | X | read | difficult book
D |   | submit | final report | Sunday
E |   | project | meeting | Monday | Tuesday
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
Wow. Look at how slow you are at completing these tasks.
1. [T][X] read | difficult book
2. [D][ ] submit | final report (by: Sunday)
3. [E][ ] project | meeting (from: Monday to: Tuesday)
__________________________________________________
Finally! Bye. I'm leaving!
__________________________________________________
```

## Test Case: Reject malformed stored tasks

### Aim

Verify that Yuno reports a controlled storage error and exits when saved task
data contains an invalid completion status.

### Inputs

```text
bye
```

### Initial Data

```text
T | ? | corrupted task
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
Why did you change the task file? I can't load your tasks now.
__________________________________________________
```
