# chisel-bug-repro

How to reproduce bug:

```
# using mill 0.11.0
$ mill bug-repro.test
[85/85] bug-repro.test.test 
TestTest:
- Test should work *** FAILED ***
  java.lang.NullPointerException: Cannot invoke "Object.toString()" because "obj" is null
  at ... ()
  at bugrepro.Test._moduleDefinitionIdentifierProposal(Test.scala:5)
  at chisel3.experimental.BaseModule._definitionIdentifier(Module.scala:313)
  at chisel3.experimental.BaseModule.<init>(Module.scala:318)
  at chisel3.RawModule.<init>(RawModule.scala:21)
  at chisel3.Module.<init>(Module.scala:168)
  at bugrepro.Test.<init>(Test.scala:6)
  at bugrepro.TestTest.$anonfun$new$2(Test.scala:9)
  at ... ()
  at ... (Stack trace trimmed to user code only. Rerun with --full-stacktrace to see the full stack trace)
  ...
1 targets failed
bug-repro.test.test 1 tests failed: 
  bugrepro.TestTest Test should work
```