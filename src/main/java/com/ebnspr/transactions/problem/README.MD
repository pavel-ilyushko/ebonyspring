PROBLEM:

A service method marked with @Transactional annotation and propagation mode = REQUIRES_NEW causes a new transaction
to be created outside of the currently executed transaction. This is a problem when testing such a service:
normally, after each test method completes, Spring automatically rolls back the transactional methods, but in case
of a REQUIRES_NEW transaction, it does not. That might cause the test database inconsistency.

SOLUTION:
?