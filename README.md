# Time Source Service

This is a simple REST service designated for injection of
false time into a system under a test.

If some tested system works with current timestamps, the
timestamps must be replaced by some false time. Otherwise,
the test is not able to check correctly created data as the
timestamp is changing all the time.

This project is a simple service keeping several groups
of timestamps which can be controlled during run of the
tests. Tested system must be configured to get current time
from this service instead of taking time from the operating
system.

The service offers two endpoints:
```
GET /time/<group id>
```
and
```
POST /time/<group id>
```
Format of the message (posted or got) is a simple JSON:
```json
{
  "seconds": <unix epoche seconds>,
  "micro_seconds": <usec>
}
```

There is a special time group ID `realtime`. Time in this group
cannot be set (the POST fails) and returned value is current time
taken from the operating system. The purpose of this group is
a possibility to configure a running containerized application
in a development environment, which can work both under a test
and under manual control of a developer.  