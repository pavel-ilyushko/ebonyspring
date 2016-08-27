Spring can autowire beans in parameters of a constructor. If there are more than one candidate,
the resolution is done by qualifier. If none explicit qualifier is specified, the parameter's name
is used by default. While it works in development env, it might fail in prod if the code
is compile with 'g:none' option which removes debugging info and the parameter names from the compiled code.
As a result, in this case the context start-up fails with exception:

Caused by: org.springframework.beans.factory.NoUniqueBeanDefinitionException:
No qualifying bean of type [com.ebnspr.di.autowire.byparamname.FooInterface]
is defined: expected single matching bean but found 2: fooImpl1,fooImpl2
