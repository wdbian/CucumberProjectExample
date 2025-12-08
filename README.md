Summary: Create a Thread-safe Cucumber Automation Testing Framework
Components:
  1) Web driver generator: generate web driver instance based on type(local/remote) and vendor(chrome/edge/firefox)
  2) feature file: specify all testing scenarios
  3) step definition: link step in feature file to code methods
  4) test runner: configure plugins, glue, report and tags to be executed for cucumber
  5) hook: setup precondition and cleanup before and after each scenario execution
  6) listener: generate extent report entry based on each testing scenario step
  7) pages: generate POM(page obect model) elements locator and action methods on page level
  8) data dictionary: save runtime testing data for each thread
  9) utility: provide different, reusable utility methods to simlify coding effort
  10) db operation: provide db connection and query methods
