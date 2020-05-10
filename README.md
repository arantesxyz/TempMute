# TempMute

Simple and fast file based mute plugin.


## Installation
Download the latest [release](https://github.com/arantesxyz/TempMute/releases) and drop it in your plugins folder.

## Commands

The commands are very simple and easy to use:

#### Tempmute
Sintaxe: `/tempmute <player> <reason time and flags>`

Sintaxe examples:
```
/tempmute Arantes 12h 15s Flood - Mutes the player for 12 hours and 15 seconds for the reason "Flood"
/tempmute Arantes 120s I don't like you - mutes the player for 2 minutes for the reason "I don't like you"
/tempmute Arantes I don't 5h like you 10s - Mutes the player for 5 hours and 10 seconds for the reason "I don't like you"
/tempmute Arantes 10s -s - Mutes the player for 10s but doesn't broadcast the message.
```

#### Unmute
Sintaxe: `/unmute <player> [flags]`

Sintaxe examples:
```
/unmute Arantes - Unmutes the player
/unmute Arantes -s - Unmutes the player without broadcast the message
```

## Time units and flags:
The following time units are available:
```
s = second
m = minute
h = hour
d = day
w = week
M = month
y = year
```

The following flags are available:
```
-s = Silent mode (doesn't broadcast the message)
```