When there's a circular dependency in constructor injected beans, Spring throws an exception.
That situation can be fixed when using proxies instead of pure objects which is illustrated by this example:
CGLIB proxies are wrapped around the original beans and Spring figures out how to solve the circular dependency.

However, this is not considered a good approach, a better one is probably refactoring the code to using setter injection.

https://steveschols.wordpress.com/2012/06/05/i-was-wrong-constructor-vs-setter-injection/