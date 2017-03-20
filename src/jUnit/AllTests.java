package jUnit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BuildModeJUnit.class, FileJunit.class, RunModeJunit.class })
public class AllTests {

}
