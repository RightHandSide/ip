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
__   __
\ \ / /   _ _ __   ___
 \ V / | | | '_ \ / _ \
  | |  | |_| | | | | (_) |
  |_|   \__,_|_| |_|\___/

I'm Yuno.
Can we just get this over quickly?
__________________________________________________
Finally! Bye, I'm going!
__________________________________________________
```

