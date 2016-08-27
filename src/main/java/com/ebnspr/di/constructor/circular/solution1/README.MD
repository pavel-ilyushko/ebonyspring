PROBLEM:
Bean A expects its dependency to Bean B injected in constructor, and vice versa.
This leads to a circular dependency and stack overflow error which is thrown (strangely consumed by NoClassDefFoundError)
by Spring.

SOLUTION #1:
That situation can be fixed when using proxies instead of pure objects which is illustrated by this example:
CGLIB proxies are wrapped around the original beans and Spring somehow figures out how to solve the circular dependency
problem.

However, this is not considered a good approach, a better one is probably refactoring the code to using setter injection.

https://steveschols.wordpress.com/2012/06/05/i-was-wrong-constructor-vs-setter-injection/